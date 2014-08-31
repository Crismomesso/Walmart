package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the arestat database table.
 * 
 */
@Entity
@NamedQuery(name="Arestat.findAll", query="SELECT a FROM Arestat a")
public class Arestat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ARESTA_ID")
	private int arestaId;

	private String destino;

	private double distancia;

	//bi-directional many-to-one association to Verticet
	@ManyToOne
	@JoinColumn(name="VERTICE_ID")
	private Verticet verticet;

	public Arestat() {
	}

	public int getArestaId() {
		return this.arestaId;
	}

	public void setArestaId(int arestaId) {
		this.arestaId = arestaId;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return this.distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Verticet getVerticet() {
		return this.verticet;
	}

	public void setVerticet(Verticet verticet) {
		this.verticet = verticet;
	}

}