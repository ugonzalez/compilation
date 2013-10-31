package py.una.compilation;

/**
 * Nodo que representa al no terminal exp.
 * 
 * @author Uriel González
 * 
 */
public class Exp extends Nodo {

	/**
	 * Construye un no terminal exp especificando su no terminal term y su no
	 * terminal R.
	 * 
	 * @param term
	 *            No terminal term.
	 * @param r
	 *            No terminal R.
	 */
	public Exp(final Term term, final R r) {
		this.setHijoIzq(term);
		this.setHijoDer(r);
	}

}
