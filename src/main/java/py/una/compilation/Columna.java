package py.una.compilation;

/**
 * Clase que representa una columna de una tabla de transicion.
 * 
 * @author Uriel González.
 * 
 */
public class Columna {

	private final Simbolo simbolo;

	/**
	 * Construye una columna especificando su simbolo.
	 * 
	 * @param simbolo
	 *            Simbolo de la columna.
	 */
	public Columna(final Simbolo simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Recupera el simbolo de la columna.
	 * 
	 * @return Simbolo de la columna.
	 */
	public Simbolo getSimbolo() {
		return this.simbolo;
	}

}
