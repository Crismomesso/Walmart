package exception;



/**
 * Exception especifica para persistencia dos grafos
 * @author Cristiano Momesso
 *
 */
public class PersistenciaGrafoExcpetion extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenciaGrafoExcpetion(String msg) {
		super(msg);
	}
}
