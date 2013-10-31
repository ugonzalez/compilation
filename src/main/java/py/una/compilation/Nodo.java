package py.una.compilation;

/**
 * Representa un nodo de un árbol.
 * 
 * @author Uriel González
 * 
 */
public class Nodo {

	private Nodo hijoIzq;

	private Nodo hijoDer;

	/**
	 * Setea el hijo izquierdo del nodo.
	 * 
	 * @param hijoIzq
	 *            Nuevo hijo izquierdo del nodo.
	 */
	public void setHijoIzq(final Nodo hijoIzq) {
		this.hijoIzq = hijoIzq;
	}

	/**
	 * Setea el hijo derecho del nodo.
	 * 
	 * @param hijoDer
	 *            Nuevo hijo derecho del nodo.
	 */
	public void setHijoDer(final Nodo hijoDer) {
		this.hijoDer = hijoDer;
	}

	/**
	 * Recupera el hijo izquierdo del nodo.
	 * 
	 * @return Hijo izquierdo del nodo.
	 */
	public Nodo getHijoIzq() {
		return this.hijoIzq;
	}

	/**
	 * Recupera el hijo derecho del nodo.
	 * 
	 * @return Hijo derecho del nodo.
	 */
	public Nodo getHijoDer() {
		return this.hijoDer;
	}

}
