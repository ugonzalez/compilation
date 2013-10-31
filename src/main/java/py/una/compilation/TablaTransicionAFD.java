package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una tabla de transicion de un AFD.
 * 
 * @author Uriel González
 * 
 */
public class TablaTransicionAFD {
	private final List<FilaAFD> filas;

	/**
	 * Construye una tabla de transicion de un AFD.
	 */
	public TablaTransicionAFD() {
		this.filas = new ArrayList<FilaAFD>();
	}

	/**
	 * Agrega una nueva fila a la tabla de transicion AFD,
	 * 
	 * @param filaAFD
	 *            Nueva fila AFD.
	 */
	public void addNuevaFila(final FilaAFD filaAFD) {
		this.filas.add(filaAFD);
	}

	/**
	 * Agrega una nueva columna a la tabla de transicion AFD.
	 * 
	 * @param estadoAFD
	 *            Estado de la fila AFD a ser actualizada.
	 * @param columnaAFD
	 *            Simbolo de la nueva columna AFD.
	 */
	public void addNuevaColumna(final EstadoAFD estadoAFD,
			final ColumnaAFD columnaAFD) {
		final FilaAFD filaAFD = this.getFila(estadoAFD);
		filaAFD.addColumnaAFD(columnaAFD);

	}

	/**
	 * Recupera una fila de la tabla de transicion AFD dado el estado AFD de la
	 * fila.
	 * 
	 * @param estadoAFD
	 *            Estado AFD de la fila a ser recuperada.
	 * @return Fila AFD de la tabla de transicion AFD a la cual pertenece el
	 *         estado pasado como parametro. En caso de que no exista ninguna
	 *         fila en la tabla de transicion AFD con el estado AFD pasado como
	 *         parametro retorna null.
	 */
	public FilaAFD getFila(final EstadoAFD estadoAFD) {

		FilaAFD aRet = null;
		for (final FilaAFD filaAFD : this.filas) {
			if (filaAFD.getEstado().equals(estadoAFD)) {
				aRet = filaAFD;
				break;
			}
		}

		return aRet;
	}

	/**
	 * Recupera una columna de la tabla de transicion AFD dado una fila AFD y un
	 * simbolo.
	 * 
	 * @param filaAFD
	 *            Fila AFD en donde se encuentra la columna a ser recuperada.
	 * @param simbolo
	 *            Simbolo de la columna a ser recuperada.
	 * @return Columna AFD de la tabla de transicion del AFD. En caso de que no
	 *         exista una columna en la fila pasada como parametro con el
	 *         simbolo pasado como parametro retorna null.
	 */
	public ColumnaAFD getColumna(final FilaAFD filaAFD, final Simbolo simbolo) {
		// TODO Agregar un metodo que retorne una columna dado un Estado y un
		// Simbolo

		ColumnaAFD aRet = null;
		for (final ColumnaAFD columnaAFD : filaAFD.getColumnasAFD()) {
			if (columnaAFD.getSimbolo().equals(simbolo)) {
				aRet = columnaAFD;
				break;
			}
		}

		return aRet;
	}

	/**
	 * Recupera las filas de la tabla de transicion del AFD.
	 * 
	 * @return Filas de la tabla de transicion del AFD.
	 */
	public List<FilaAFD> getFilas() {
		return this.filas;
	}
}
