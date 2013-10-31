package py.una.compilation;

/**
 * Nodo que representa al no terminal factor.
 * 
 * @author Uriel González
 * 
 */
public class Factor extends Nodo {

	/**
	 * Construye un no terminal factor.
	 * 
	 * @param parentesis
	 * @param t
	 *            No terminal T.
	 */
	public Factor(final Parentesis parentesis, final T t) {
		this.setHijoIzq(parentesis);
		this.setHijoDer(t);

	}

	/**
	 * Construye un no terminal factor.
	 * 
	 * @param terminal
	 *            Terminal.
	 * @param t
	 *            No terminal T.
	 */
	public Factor(final Terminal terminal, final T t) {
		this.setHijoIzq(terminal);
		this.setHijoDer(t);

	}

}
