package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe base para acesso ao banco
 * @author Chris
 *
 */
public abstract  class BaseDao {
	private EntityManagerFactory factory = null;
	private EntityManager entityManager = null;

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * M�todo que estabelece conex�o com o banco de dados
	 * @author Chris
	 */
	protected void estabeleceConexao() {
		    try {
		      //Obt�m o factory a partir da unidade de persist�ncia.
		      factory = Persistence.createEntityManagerFactory("Exercicio");
		      //Cria um entity manager.
		      this.entityManager = factory.createEntityManager();
		      entityManager.getTransaction().begin();
		    }catch(Exception e ){
		    	e.printStackTrace();
		    }
      }
	
	/**
	 * M�todo que commita dados persistidos
	 * @author Chris
	 */
	protected void commit(){
		this.entityManager.getTransaction().commit();
		fechaConexao();
	}
	
	/**
	 * M�todo que fecha conex�o existente
	 * @author Chris
	 */
	protected void fechaConexao(){
		//Fecha o factory e entityManager para liberar os recursos utilizado.
		if(entityManager.isOpen()){
			entityManager.close();
			factory.close();
		}
			
	}
}
