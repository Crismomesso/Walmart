package bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import model.Arestat;
import model.EntradaCalculo;
import model.Grafot;
import model.MapaMalha;
import model.RespostaWebService;
import model.RotaMalha;
import model.Verticet;
import dao.GrafoDao;
import exception.CalculoRotaException;
import exception.PersistenciaGrafoExcpetion;

/**
 * Classe que executa calculos de rotas
 * 
 * @author Cristiano Momesso
 */
public class CalculoRotasBll {

	/**
	 * Método utilizado para gravar um Grafo na base
	 * 
	 * @author Cristiano Momesso
	 * @param malha
	 *            - objeto que possui os dados das rotas
	 * @return void - o método não retorna nada.
	 * @throws IOException
	 */
	public void gravaGrafo(MapaMalha malha) throws PersistenciaGrafoExcpetion,
			IOException {
		UtilBll util = UtilBll.getInstance();

		GrafoDao dados = new GrafoDao();
		Grafot grafo = new Grafot();
		grafo.setNomeMapa(malha.getNomeMapa().toUpperCase().trim());
		// verifica existencia do grafo na base
		if (!dados.existeGrafo(grafo)) {

			// Metodo percorre a malha e monta o objeto do grafo que sera
			// persistido na base
			for (RotaMalha rota : malha.getRotas()) {

				// nao aceita distancia negativa e nem cordenadas vazias.
				if (rota.getDistancia() < 0 || rota.getP1().trim().equals("")
						|| rota.getP2().trim().equals("")) {
					throw new PersistenciaGrafoExcpetion(
							util.getConfProperties("mapaInvalido"));
				}

				Verticet vertice = grafo.buscaVertice(rota.getP1()
						.toUpperCase().trim());

				if (vertice == null) {
					vertice = grafo.criaVertice(rota.getP1().toUpperCase()
							.trim());
					grafo.addVerticet(vertice);
				}

				Arestat ar = new Arestat();
				ar.setDestino(rota.getP2().toUpperCase().trim());
				ar.setDistancia(rota.getDistancia());
				vertice.addArestat(ar);

				vertice = grafo.buscaVertice(rota.getP2().toUpperCase().trim());

				if (vertice == null) {
					vertice = new Verticet();
					vertice = grafo.criaVertice(rota.getP2().toUpperCase()
							.trim());
					grafo.addVerticet(vertice);
				}

			}
			try {
				// persiste grafo montado
				dados.salvar(grafo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new PersistenciaGrafoExcpetion(
					util.getConfProperties("mapaExiste"));
		}

	}

	/**
	 * Método utilizado para o cálculo da rota pelo webservice
	 * 
	 * @author Cristiano Momesso
	 * @param entrada
	 * @return RespostaWebService
	 * @throws IOException
	 * @throws CalculoRotaException
	 */
	public RespostaWebService calculaRota(EntradaCalculo entrada)
			throws IOException, CalculoRotaException {
		RespostaWebService resp = new RespostaWebService();
		double custo = 0.;
		UtilBll util = UtilBll.getInstance();
		GrafoDao dados = new GrafoDao();
		Grafot grafo = new Grafot();
		grafo.setNomeMapa(entrada.getNomeMapa().toUpperCase().trim());
		grafo = dados.buscaGrafo(grafo);

		if (entrada.getVertice1().equals("") || entrada.getAutonomia() <= 0
				|| entrada.getValorLitro() <= 0
				|| entrada.getVertice2().equals("")) {
			throw new CalculoRotaException(
					util.getConfProperties("dadosEntradaInvalidos"));
		}

		// verifica se existe o grafo na base
		if (grafo != null) {
			String resposta = null;
			List<Verticet> verticets = grafo.getVerticets();

			// busca vertice da origem da rota
			Verticet vert = buscaVertice(entrada.getVertice1(), verticets);

			// gera caminhos utilizando o algoritmo de DIJKSTRA
			this.geraCaminhos(vert, verticets);

			// verifica a lista procurando a rota com o destino desejado
			for (Verticet v : verticets) {
				if (v.getNomeVertice().endsWith(
						entrada.getVertice2().toUpperCase().trim())) {

					if (v.getDistMinima() == Double.POSITIVE_INFINITY) {
						throw new CalculoRotaException(
								util.getConfProperties("naoExisteRota"));
					}

					custo = v.getDistMinima() / entrada.getAutonomia()
							* entrada.getValorLitro();
					resposta = "".concat("Distancia para ")
							.concat(v.getNomeVertice()).concat(": ")
							.concat(new Double(v.getDistMinima()).toString());
					resposta = resposta.concat(" Caminho:");
					List<Verticet> path = pegaMenorCaminho(v);

					// concatena a rota
					for (Verticet u : path) {
						resposta = resposta.concat("->").concat(
								u.getNomeVertice());
					}
					// gera o custo
					resposta = resposta.concat(" Custo de ").concat(
							new Double(custo).toString());
					resp.setResultado(resposta);
				}
			}

		} else {
			throw new CalculoRotaException(
					util.getConfProperties("mapaNaoExiste"));
		}

		return resp;
	}

	/**
	 * Método que implementa o algoritmo de DIJKSTRA
	 * 
	 * @author Cristiano Momesso
	 * @param vert
	 * @param verticets
	 */
	private void geraCaminhos(Verticet vert, List<Verticet> verticets) {
		vert.setDistMinima(0.);
		PriorityQueue<Verticet> vertexQueue = new PriorityQueue<Verticet>();
		// coloca vertice incial como prioridade
		vertexQueue.add(vert);
		while (!vertexQueue.isEmpty()) {
			Verticet u = vertexQueue.poll();
			// verifica arestas
			for (Arestat e : u.getArestats()) {
				// busca vertice da aresta
				Verticet v = buscaVertice(e.getDestino(), verticets);
				double distancia = e.getDistancia();
				double distanceAteU = u.getDistMinima() + distancia;
				// aplica regra da distancia
				if (distanceAteU < v.getDistMinima()) {
					vertexQueue.remove(v);
					v.setDistMinima(distanceAteU);
					v.setAnterior(u);
					vertexQueue.add(v);
				}
			}
		}
	}

	/**
	 * Método que após a execução do algoritmo de DIJKSTRA inverte a coleção
	 * para pegar os caminhos do início para o fim
	 * 
	 * @author Cristiano Momesso
	 * @param target
	 * @return
	 */
	private static List<Verticet> pegaMenorCaminho(Verticet target) {
		List<Verticet> path = new ArrayList<Verticet>();

		for (Verticet vertice = target; vertice != null; vertice = vertice
				.getAnterior()) {
			path.add(vertice);
		}
		Collections.reverse(path);
		return path;
	}

	/**
	 * Método que busca vertice de destino em uma lista
	 * 
	 * @author Cristiano Momesso
	 * @param destino
	 * @param verticets
	 * @return
	 */
	private static Verticet buscaVertice(String destino,
			List<Verticet> verticets) {
		for (Verticet e : verticets) {
			if (e.getNomeVertice().toUpperCase().equals(destino.toUpperCase())) {
				return e;
			}
		}
		return null;
	}

}
