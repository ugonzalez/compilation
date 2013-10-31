package py.una.compilation;

/**
 * Nodo que representa al no terminal term.
 * 
 * @author Uriel González
 * 
 */
public class Term extends Nodo {

	/**
	 * Construye un no terminal term especificando su no terminal factor y su no
	 * terminal S.
	 * 
	 * @param factor
	 *            No terminal factor.
	 * @param s
	 *            No terminal s.
	 */
	public Term(final Factor factor, final S s) {
		this.setHijoIzq(factor);
		this.setHijoDer(s);
	}

}
