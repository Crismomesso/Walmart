package teste;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.UUID;

import model.EntradaCalculo;
import model.MapaMalha;
import model.RespostaWebService;

import org.junit.Test;

import bll.CalculoRotasBll;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.CalculoRotaException;
import exception.PersistenciaGrafoExcpetion;

/**
 * Classe que testa o serviços
 * @author Cristiano Momesso
 *
 */

public class TesteService {

	/**
	 * Método que testa se distancia é menor que zero .
	 * @author Cristiano Momesso
	 * @return void - o método não retorna nada.
	 * @throws PersistenciaGrafoExcpetion
	 */
	@Test(expected = PersistenciaGrafoExcpetion.class)  
	public void testaMapaDistanciaNegativa()throws PersistenciaGrafoExcpetion{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"rotas\":[{\"p1\" :\"A\" , \"p2\":\"B\" , \"distancia\":\"-10\" },{\"p1\" :\"B\" , \"p2\":\"D\" , \"distancia\":\"15\" },{\"p1\" :\"A\" , \"p2\":\"C\" , \"distancia\":\"20\" },{\"p1\" :\"C\" , \"p2\":\"D\" , \"distancia\":\"30\" },{\"p1\" :\"B\" , \"p2\":\"E\" , \"distancia\":\"50\" },{\"p1\" :\"D\" , \"p2\":\"E\" , \"distancia\":\"30\" }]}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MapaMalha mapa = objectMapper.readValue(entrada,MapaMalha.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			//gera nome mapa ficticio 
			String uuid = UUID.randomUUID().toString();
			mapa.setNomeMapa(uuid);
			calc.gravaGrafo(mapa);
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	/**
	 * Método que testa se existe coordenadas vazias. 
	 * @author Cristiano Momesso
	 * @return void - o método não retorna nada.
	 * @throws PersistenciaGrafoExcpetion 
	 */
	@Test(expected = PersistenciaGrafoExcpetion.class)  
	public void testaMapaCoordenadaVazia()throws PersistenciaGrafoExcpetion{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"rotas\":[{\"p1\" :\"A\" , \"p2\":\"\" , \"distancia\":\"10\" },{\"p1\" :\"B\" , \"p2\":\"D\" , \"distancia\":\"15\" },{\"p1\" :\"A\" , \"p2\":\"C\" , \"distancia\":\"20\" },{\"p1\" :\"C\" , \"p2\":\"D\" , \"distancia\":\"30\" },{\"p1\" :\"B\" , \"p2\":\"E\" , \"distancia\":\"50\" },{\"p1\" :\"D\" , \"p2\":\"E\" , \"distancia\":\"30\" }]}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MapaMalha mapa = objectMapper.readValue(entrada,MapaMalha.class);
			String uuid = UUID.randomUUID().toString();
			//gera nome mapa ficticio 
			mapa.setNomeMapa(uuid);
			CalculoRotasBll calc = new CalculoRotasBll();
			calc.gravaGrafo(mapa);
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Método que testa calculo da rota
	 * @author Cristiano Momesso
	 * @throws CalculoRotaException
	 */
	@Test 
	public void testaCalculoRota()throws CalculoRotaException{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"vertice1\":\"A\",\"vertice2\":\"D\",\"autonomia\":\"10\",\"valorLitro\":\"2.5\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			EntradaCalculo objEntrada = objectMapper.readValue(entrada,EntradaCalculo.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			RespostaWebService resp  = calc.calculaRota(objEntrada);
			assertEquals("Distancia para D: 25.0 Caminho:->A->B->D Custo de 6.25",resp.getResultado());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (CalculoRotaException e) {
			e.printStackTrace();
			fail();
		}
	}
	/**
	 * Método que testa entrada da rota invalida - O mapa deve existir na base
	 * @author Cristiano Momesso
	 * @throws CalculoRotaException
	 */
	@Test(expected = CalculoRotaException.class)
	public void testaEntradaCalculoRotaInvalida()throws CalculoRotaException{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"vertice1\":\"\",\"vertice2\":\"D\",\"autonomia\":\"10\",\"valorLitro\":\"2.5\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			EntradaCalculo objEntrada = objectMapper.readValue(entrada,EntradaCalculo.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			calc.calculaRota(objEntrada);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Método que testa rota existe o mapa deve existir na base
	 * @author Cristiano Momesso
	 * @throws CalculoRotaException
	 */
	@Test(expected = CalculoRotaException.class)
	public void testaRotaNaoExiste()throws CalculoRotaException{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"vertice1\":\"B\",\"vertice2\":\"A\",\"autonomia\":\"10\",\"valorLitro\":\"2.5\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			EntradaCalculo objEntrada = objectMapper.readValue(entrada,EntradaCalculo.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			calc.calculaRota(objEntrada);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
	/**
	 * Método que testa se existe coordenadas vazias. O mapa não deve estar cadastrado na base
	 * @author Cristiano Momesso
	 * @return void - o método não retorna nada.
	 * @throws PersistenciaGrafoExcpetion 
	 */
	@Test 
	public void testaGrava()throws PersistenciaGrafoExcpetion{
		String entrada = "{\"nomeMapa\":\"mapa SP\",\"rotas\":[{\"p1\" :\"A\" , \"p2\":\"B\" , \"distancia\":\"10\" },{\"p1\" :\"B\" , \"p2\":\"D\" , \"distancia\":\"15\" },{\"p1\" :\"A\" , \"p2\":\"C\" , \"distancia\":\"20\" },{\"p1\" :\"C\" , \"p2\":\"D\" , \"distancia\":\"30\" },{\"p1\" :\"B\" , \"p2\":\"E\" , \"distancia\":\"50\" },{\"p1\" :\"D\" , \"p2\":\"E\" , \"distancia\":\"30\" }]}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MapaMalha mapa = objectMapper.readValue(entrada,MapaMalha.class);
			CalculoRotasBll calc = new CalculoRotasBll();
			//gera nome mapa ficticio 
			String uuid = UUID.randomUUID().toString();
			mapa.setNomeMapa(uuid);
			//Caso deseje testar, descomente a linha abaixo , comentei pois se não iria gravar dados de teste na base
			//calc.gravaGrafo(mapa);
		} catch (JsonParseException e) {
			e.printStackTrace();
			fail();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
