package py.una.compilation;

/**
 * Nodo que representa al no terminal R.
 * 
 * @author Uriel González
 * 
 */
public class R extends Nodo {

	/**
	 * Construye un no terminal R especificando su no terminal term y su no
	 * terminal R.
	 * 
	 * @param term
	 *            No terminal term.
	 * @param r
	 *            No terminal R.
	 */
	public R(final Term term, final R r) {
		this.setHijoIzq(term);
		this.setHijoDer(r);
	}

}
