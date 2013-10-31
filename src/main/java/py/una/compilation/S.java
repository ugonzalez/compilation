package py.una.compilation;

/**
 * Nodo que representa al no terminal S.
 * 
 * @author Uriel González
 * 
 */
public class S extends Nodo {

	/**
	 * Construye un no terminal S especificando su no terminal factor y su no
	 * terminal S.
	 * 
	 * @param factor
	 *            No terminal factor.
	 * @param s
	 *            No terminal S.
	 */
	public S(final Factor factor, final S s) {
		this.setHijoIzq(factor);
		this.setHijoDer(s);

	}

}
