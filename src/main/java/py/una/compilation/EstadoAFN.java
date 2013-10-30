package py.una.compilation;

/**
 * Clase que representa un estado de un AFN.
 * 
 * @author Uriel González
 * 
 */
public class EstadoAFN extends Estado {

	/**
	 * Construye un estado de un AFN especificando su etiqueta.
	 * 
	 * @param etiqueta
	 *            Etiqueta del nuevo estado AFN.
	 */
	public EstadoAFN(final String etiqueta) {
		super(etiqueta);
	}

	@Override
	public String toString() {
		return this.getEtiqueta();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.getEtiqueta() == null) ? 0 : this.getEtiqueta()
						.hashCode());
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
		final EstadoAFN other = (EstadoAFN) obj;
		if (this.getEtiqueta() == null) {
			if (other.getEtiqueta() != null) {
				return false;
			}
		} else if (!this.getEtiqueta().equals(other.getEtiqueta())) {
			return false;
		}
		return true;
	}

}
