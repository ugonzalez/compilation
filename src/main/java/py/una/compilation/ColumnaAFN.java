package py.una.compilation;

import java.util.List;

/**
 * Clase que representa una columna de una tabla de transicion de un AFN.
 * 
 * @author Uriel González
 * 
 */
public class ColumnaAFN extends Columna {
	private final List<EstadoAFN> estadosSiguientes;

	/**
	 * Construye una columna de una tabla de transicion de un AFN especificando
	 * su simbolo y el conjunto de estados AFN siguientes.
	 * 
	 * @param simbolo
	 *            Simbolo de la columna.
	 * @param estadosSiguientes
	 *            Conjunto de estados AFN siguientes.
	 */
	public ColumnaAFN(final Simbolo simbolo,
			final List<EstadoAFN> estadosSiguientes) {
		super(simbolo);
		this.estadosSiguientes = estadosSiguientes;
	}

	/**
	 * Recupera el conjunto de estados AFN siguientes.
	 * 
	 * @return Conjunto de estado AFN siguientes.
	 */
	public List<EstadoAFN> getEstadosSiguientes() {
		return this.estadosSiguientes;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getSimbolo() + "\n{\t");
		for (int i = 0; i < this.estadosSiguientes.size(); i++) {
			sb.append(this.estadosSiguientes.get(i) + "\t");
		}
		sb.append("}");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final ColumnaAFN other = (ColumnaAFN) obj;
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
