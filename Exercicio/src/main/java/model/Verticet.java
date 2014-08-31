package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


/**
 * The persistent class for the verticet database table.
 * 
 */
@Entity
@NamedQuery(name="Verticet.findAll", query="SELECT v FROM Verticet v")
public class Verticet implements Serializable , Comparable<Verticet> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VERTICE_ID")
	private int verticeId;

	@Column(name="NOME_VERTICE")
	private String nomeVertice;

	//bi-directional many-to-one association to Arestat
	@OneToMany(mappedBy="verticet",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private List<Arestat> arestats= new ArrayList<Arestat>();

	//bi-directional many-to-one association to Grafot
	@ManyToOne
	@JoinColumn(name="GRAFO_ID")
	private Grafot grafot;

	@Transient  
	private  double distMinima = Double.POSITIVE_INFINITY;
	
	@Transient  
	private Verticet anterior;
	
	public Verticet() {
	}

	public int compareTo(Verticet outro) {
		return Double.compare(distMinima, outro.distMinima);
	}
	
	public double getDistMinima() {
		return distMinima;
	}

	public void setDistMinima(double distMinima) {
		this.distMinima = distMinima;
	}

	public Verticet getAnterior() {
		return anterior;
	}

	public void setAnterior(Verticet anterior) {
		this.anterior = anterior;
	}

	public int getVerticeId() {
		return this.verticeId;
	}

	public void setVerticeId(int verticeId) {
		this.verticeId = verticeId;
	}

	public String getNomeVertice() {
		return this.nomeVertice;
	}

	public void setNomeVertice(String nomeVertice) {
		this.nomeVertice = nomeVertice;
	}

	public List<Arestat> getArestats() {
		return this.arestats;
	}

	public void setArestats(List<Arestat> arestats) {
		this.arestats = arestats;
	}

	public Arestat addArestat(Arestat arestat) {
		getArestats().add(arestat);
		arestat.setVerticet(this);

		return arestat;
	}

	public Arestat removeArestat(Arestat arestat) {
		getArestats().remove(arestat);
		arestat.setVerticet(null);

		return arestat;
	}

	public Grafot getGrafot() {
		return this.grafot;
	}

	public void setGrafot(Grafot grafot) {
		this.grafot = grafot;
	}

	
}