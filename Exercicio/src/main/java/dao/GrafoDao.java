package dao;

import javax.persistence.Query;

import model.Grafot;

/**
 * Classe que manipula grafos da base
 * @author Cristiano Momesso
 *
 */
public class GrafoDao extends BaseDao {
 
	public GrafoDao(){
		
	}
	
	
	/**
	   * Método utilizado para salvar um grafo.
	   * @param Grafot
	   *  @author Cristiano Momesso
	   * @return Grafot
	   * @throws java.lang.Exception
	   */
	  public Grafot salvar(Grafot grafo) throws Exception {
		  
		  this.estabeleceConexao();
			try {
				this.getEntityManager().persist(grafo);			
			} catch (Exception e) {
				this.fechaConexao();
			}
			this.commit();
			return grafo;
	    }

	  /**
	   * Busca grafo existente na base
	   *  @author Cristiano Momesso
	   * @param grafo
	   * @return Grafot
	   */
		public Grafot buscaGrafo(Grafot grafo) {
			this.estabeleceConexao();
			String nomeMapa = grafo.getNomeMapa();
			try {
				

				Query query = this.getEntityManager().createNamedQuery("Grafot.findByNome",Grafot.class);
				query.setParameter("nome", nomeMapa);
				if(query.getResultList().size()!=0){
					grafo = (Grafot) query.getResultList().get(0);
					grafo = this.getEntityManager().find(Grafot.class, grafo.getGrafoId());
					this.getEntityManager().refresh(grafo);
					return grafo;
				}

			} catch (Exception e) {
				this.fechaConexao();
			}
			this.fechaConexao();
			return null;
		}
	  
	  /**
	   * Método que verifica existencia do grafo
	   * @author Cristiano Momesso
	   * @param grafo
	   * @return boolean
	   */
	public boolean existeGrafo(Grafot grafo) {
		this.estabeleceConexao();
		String nomeMapa = grafo.getNomeMapa();
		try {
			

			Query query = this.getEntityManager().createNamedQuery("Grafot.findByNome",Grafot.class);
			query.setParameter("nome", nomeMapa);
			if(query.getResultList().size()!=0){
				return true;
			}

		} catch (Exception e) {
			this.fechaConexao();
		}
		this.fechaConexao();
		return false;
	}
}
