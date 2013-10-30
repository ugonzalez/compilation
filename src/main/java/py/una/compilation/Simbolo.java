package py.una.compilation;

/**
 * Clase que representa a un simbolo del alfabeto.
 * 
 * @author Uriel González
 * 
 */
public class Simbolo {

	private final String etiqueta;

	/**
	 * Construye un simbolo especificando su etiqueta.
	 * 
	 * @param etiqueta
	 *            Etiqueta del simbolo.
	 */
	public Simbolo(final String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Recupera la etiqueta del simbolo.
	 * 
	 * @return La etiqueta del simbolo.
	 */
	public String getEtiqueta() {
		return this.etiqueta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.etiqueta == null) ? 0 : this.etiqueta.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Simbolo other = (Simbolo) obj;
		if (this.etiqueta == null) {
			if (other.etiqueta != null) {
				return false;
			}
		} else if (!this.etiqueta.equals(other.etiqueta)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO Reemplazar los # por ε en todos los archivos del proyecto
		if (this.etiqueta == "#") {
			return "ε";
		} else {
			return this.etiqueta;
		}

	}

}
