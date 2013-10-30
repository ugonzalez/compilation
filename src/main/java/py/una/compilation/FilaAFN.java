package py.una.compilation;

import java.util.List;

/**
 * Clase que representa una fila de una tabla de transicion de un AFN.
 * 
 * @author Uriel González
 * 
 */
public class FilaAFN {

	private final EstadoAFN estadoAFN;
	private final List<ColumnaAFN> columnasAFN;

	/**
	 * Construye una fila de una tabla de transicion de un AFN especificando su
	 * estado AFN y su conjunto de columnas AFN.
	 * 
	 * @param estado
	 *            Estado AFN de la nueva fila.
	 * @param columnas
	 *            Conjunto de columnas AFN de la nueva fila.
	 */
	public FilaAFN(final EstadoAFN estado, final List<ColumnaAFN> columnas) {
		this.estadoAFN = estado;
		this.columnasAFN = columnas;
	}

	/**
	 * Recupera el estado AFN de la fila.
	 * 
	 * @return Estado AFN de la fila.
	 */
	public EstadoAFN getEstado() {
		return this.estadoAFN;
	}

	/**
	 * Recupera el conjunto de columnas AFN de la fila.
	 * 
	 * @return Conjunto de columnas AFN de la fila.
	 */
	public List<ColumnaAFN> getColumnas() {
		return this.columnasAFN;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.estadoAFN + "\n");
		for (int i = 0; i < this.columnasAFN.size(); i++) {
			sb.append(this.getColumnas().get(i) + "\n");
		}

		return sb.toString();
	}

}
