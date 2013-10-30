package py.una.compilation;

/**
 * Clase que representa un estado de un automata finito.
 * 
 * @author Uriel González
 * 
 */
public class Estado {

	private String etiqueta;

	/**
	 * Construye un estado especificando su etiqueta.
	 * 
	 * @param etiqueta
	 *            Etiqueta del estado.
	 */
	public Estado(final String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Recupera la etiqueta del estado.
	 * 
	 * @return Etiqueta del estado.
	 */
	public String getEtiqueta() {
		return this.etiqueta;
	}

	/**
	 * Setea la etiqueta del estado.
	 * 
	 * @param etiqueta
	 *            Nueva etiqueta del estado.
	 */
	public void setEtiqueta(final String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
