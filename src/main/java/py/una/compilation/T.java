package py.una.compilation;

/**
 * Nodo que representa al no terminal T.
 * 
 * @author Uriel González
 * 
 */
public class T extends Nodo {

	private final char tipo;

	/**
	 * Construye un no terminal T especificando su tipo.
	 * 
	 * @param t
	 *            No terminal T.
	 * @param tipo
	 *            Tipo del no terminal. Puede ser * , ? o +.
	 */
	public T(final T t, final char tipo) {
		// TODO Asegurarse que solo se le pasen los tipos permitidos
		this.setHijoIzq(t);
		this.setHijoDer(null);
		this.tipo = tipo;
	}

	/**
	 * Recupera el tipo del no terminal.
	 * 
	 * @return Tipo del no terminal. Debe ser *, ? o +.
	 */
	public char getTipo() {
		return this.tipo;
	}
}
