package model;

/**
 * Classe que representa a entrada de dados para o calculo
 * @author Cristiano Momesso
 *
 */
public class EntradaCalculo {

	private String nomeMapa;
	private String vertice1;
	private String vertice2;
	private double autonomia;
	private double valorLitro;
	
	public String getNomeMapa() {
		return nomeMapa;
	}
	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}
	public String getVertice1() {
		return vertice1;
	}
	public void setVertice1(String vertice1) {
		this.vertice1 = vertice1;
	}
	public String getVertice2() {
		return vertice2;
	}
	public void setVertice2(String vertice2) {
		this.vertice2 = vertice2;
	}
	public double getAutonomia() {
		return autonomia;
	}
	public void setAutonomia(double autonomia) {
		this.autonomia = autonomia;
	}
	public double getValorLitro() {
		return valorLitro;
	}
	public void setValorLitro(double valorLitro) {
		this.valorLitro = valorLitro;
	}
	
	
	
}
