package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the grafot database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Grafot.findAll", query="SELECT g FROM Grafot g"),
    @NamedQuery(name = "Grafot.findByCodigo", query = "SELECT c FROM Grafot c WHERE c.grafoId = :codigo"),
    @NamedQuery(name = "Grafot.findByNome", query = "SELECT c FROM Grafot c WHERE c.nomeMapa = :nome")})
public class Grafot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GRAFO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int grafoId;

	private String nomeMapa;

	//bi-directional many-to-one association to Verticet
	@OneToMany(mappedBy="grafot",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private List<Verticet> verticets = new ArrayList<Verticet>();

	public Grafot() {
	}

	public int getGrafoId() {
		return this.grafoId;
	}

	public void setGrafoId(int grafoId) {
		this.grafoId = grafoId;
	}

	public String getNomeMapa() {
		return this.nomeMapa;
	}

	public void setNomeMapa(String nomeMapa) {
		this.nomeMapa = nomeMapa;
	}

	public List<Verticet> getVerticets() {
		return this.verticets;
	}

	public void setVerticets(List<Verticet> verticets) {
		this.verticets = verticets;
	}

	public Verticet addVerticet(Verticet verticet) {
		
		getVerticets().add(verticet);
		verticet.setGrafot(this);
		return verticet;
	}

	public Verticet removeVerticet(Verticet verticet) {
		getVerticets().remove(verticet);
		verticet.setGrafot(null);

		return verticet;
	}

	 public Verticet buscaVertice(String vert){
		 if(verticets!=null){
			 for(Verticet v: verticets){
				 if(v.getNomeVertice().toUpperCase().equals(vert.toUpperCase())){
					 return v;
				 }
			 }			 
		 }
		 return null;
	 }

	 public Verticet criaVertice(String p){
			Verticet verti = new Verticet();
			verti.setNomeVertice(p);
			return verti;
		}
	 
}