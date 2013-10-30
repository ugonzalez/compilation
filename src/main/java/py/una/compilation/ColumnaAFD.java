package py.una.compilation;

/**
 * Clase que representa una columna de un tabla de transicion de un AFD.
 * 
 * @author Uriel González.
 * 
 */
public class ColumnaAFD extends Columna {
	private final EstadoAFD estadoSiguiente;

	/**
	 * Construye una columna de una tabla de transicion de un AFD.
	 * 
	 * @param simbolo
	 *            Simbolo de la columna.
	 * @param estadoSiguiente
	 *            EstadoAFD siguiente al cual se puede llegar dado el simbolo.
	 */
	public ColumnaAFD(final Simbolo simbolo, final EstadoAFD estadoSiguiente) {
		super(simbolo);
		this.estadoSiguiente = estadoSiguiente;
	}

	/**
	 * Recupera el estado AFD siguiente.
	 * 
	 * @return EstadoAFD siguiente.
	 */
	public EstadoAFD getEstadoSiguiente() {
		return this.estadoSiguiente;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getSimbolo() + "\n{\t");
		sb.append(this.estadoSiguiente + "\t");
		sb.append("}");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.estadoSiguiente == null) ? 0 : this.estadoSiguiente
						.hashCode());
		result = prime
				* result
				+ ((this.getSimbolo() == null) ? 0 : this.getSimbolo()
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
		final ColumnaAFD other = (ColumnaAFD) obj;
		if (this.estadoSiguiente == null) {
			if (other.estadoSiguiente != null) {
				return false;
			}
		} else if (!this.estadoSiguiente.equals(other.estadoSiguiente)) {
			return false;
		}
		if (this.getSimbolo() == null) {
			if (other.getSimbolo() != null) {
				return false;
			}
		} else if (!this.getSimbolo().equals(other.getSimbolo())) {
			return false;
		}
		return true;
	}

}
