package py.una.compilation;

import java.util.List;

/**
 * Clase que representa una fila de una tabla de transicion de un AFD.
 * 
 * @author Uriel González
 * 
 */
public class FilaAFD {

	private final EstadoAFD estadoAFD;
	private final List<ColumnaAFD> columnasAFD;

	/**
	 * Construye una fila de una tabla de transicion de un AFD especificando su
	 * estado AFD y su conjunto de columnas AFD.
	 * 
	 * @param estadoAFD
	 *            Estado AFD de la nueva afila.
	 * @param columnasAFD
	 *            Conjunto de columnas AFD de la nueva fila AFD.
	 */
	public FilaAFD(final EstadoAFD estadoAFD, final List<ColumnaAFD> columnasAFD) {
		this.estadoAFD = estadoAFD;
		this.columnasAFD = columnasAFD;
	}

	/**
	 * Recupera el estado AFD de la fila.
	 * 
	 * @return Estado AFD de la fila.
	 */
	public EstadoAFD getEstado() {
		return this.estadoAFD;
	}

	/**
	 * Agrega una nueva columna AFD al conjunto de columnas.
	 * 
	 * @param columnaAFD
	 *            Nueva columna AFD.
	 */
	public void addColumnaAFD(final ColumnaAFD columnaAFD) {
		// TODO Aca se debe de verificar que la columna no ha sido añadida
		// previamente
		this.columnasAFD.add(columnaAFD);
	}

	/**
	 * Recupera el conjunto de columnas AFD de la fila.
	 * 
	 * @return Conjunto de columnas AFD de la fila.
	 */
	public List<ColumnaAFD> getColumnasAFD() {
		return this.columnasAFD;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.estadoAFD + "\n");
		for (int i = 0; i < this.columnasAFD.size(); i++) {
			sb.append(this.columnasAFD.get(i) + "\n");
		}

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.columnasAFD == null) ? 0 : this.columnasAFD.hashCode());
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
		final FilaAFD other = (FilaAFD) obj;
		if (this.columnasAFD == null) {
			if (other.columnasAFD != null) {
				return false;
			}
		} else {
			// TODO Falta verificar que las filas tengan la misma cantidad de
			// columnas
			for (final ColumnaAFD columna : this.columnasAFD) {
				if (!other.getColumnasAFD().contains(columna)) {
					return false;
				}
			}
		}
		return true;
	}

}
