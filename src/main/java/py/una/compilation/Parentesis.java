package py.una.compilation;

/**
 * Nodo que representa un parentesis.
 * 
 * @author Uriel González
 * 
 */
public class Parentesis extends Nodo {

	/**
	 * Construye un parentesis.
	 * 
	 * @param exp
	 *            No terminal exp.
	 */
	public Parentesis(final Exp exp) {
		this.setHijoIzq(exp);
		this.setHijoDer(null);
	}

}
