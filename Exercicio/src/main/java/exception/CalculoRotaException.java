package exception;

/**
 * Exception especifica para calculo de rotas
 * @author Cristiano Momesso
 *
 */
public class CalculoRotaException extends Exception {

	private static final long serialVersionUID = 1L;

	public CalculoRotaException(String msg) {
		super(msg);
	}
}
