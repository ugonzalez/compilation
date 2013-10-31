package py.una.compilation;

import java.util.List;

/**
 * Clase que representa una tabla de transicion de un AFN.
 * 
 * @author Uriel González.
 * 
 */
public class TablaTransicionAFN {

	private final List<FilaAFN> filas;

	/**
	 * Construye una tabla de transicion de un AFN especificando sus filas.
	 * 
	 * @param filas
	 *            Filas de la nueva tabla de transicion AFN.
	 */
	public TablaTransicionAFN(final List<FilaAFN> filas) {
		this.filas = filas;
	}

	/**
	 * Recupera las filas de la tabla de transicion del AFN.
	 * 
	 * @return Filas de la tabla de transicion del AFN.
	 */
	public List<FilaAFN> getFilas() {
		return this.filas;
	}

	/**
	 * Recupera una fila de la tabla de transicion del AFN dado un estado AFN.
	 * 
	 * @param estadoAFN
	 *            Estado AFN de la fila del AFN.
	 * @return Fila AFN de la tabla de transicion del AFN. En caso de que no
	 *         exista una fila con el estado AFN pasado como parametro retorna
	 *         null.
	 */
	public FilaAFN getFila(final EstadoAFN estadoAFN) {

		FilaAFN aRet = null;
		for (final FilaAFN filaAFN : this.filas) {
			if (filaAFN.getEstado().equals(estadoAFN)) {
				aRet = filaAFN;
				break;
			}
		}

		return aRet;
	}

	/**
	 * Recupera una columna de la tabla de transicion del AFN dado una fila y un
	 * simbolo.
	 * 
	 * @param filaAFN
	 *            Fila AFN a la cual pertenece la columna a retornar.
	 * @param simbolo
	 *            Simbolo de la columna AFN.
	 * @return Columna AFN de la tabla de transicion del AFN. En caso de que no
	 *         exista una columna en la fila pasada como parametro con el
	 *         simbolo pasado como parametro retorna null.
	 */
	public ColumnaAFN getColumna(final FilaAFN filaAFN, final Simbolo simbolo) {
		// TODO Agregar un metodo que retorne una columna dado un Estado y un
		// Simbolo
		ColumnaAFN aRet = null;
		for (final ColumnaAFN columnaAFN : filaAFN.getColumnas()) {
			if (columnaAFN.getSimbolo().equals(simbolo)) {
				aRet = columnaAFN;
				break;
			}
		}

		return aRet;
	}

	/**
	 * Actualiza una entrada de la tabla de transición del AFN.
	 * 
	 * @param estado
	 *            Estado AFN de la fila a actualizar.
	 * @param simbolo
	 *            Simbolo de la columna a actualizar.
	 * @param estadoSiguiente
	 *            Nuevo estado siguiente.
	 */
	public void actualizar(final EstadoAFN estado, final Simbolo simbolo,
			final EstadoAFN estadoSiguiente) {
		final FilaAFN filaAFN = this.getFila(estado);
		final ColumnaAFN columnaAFN = this.getColumna(filaAFN, simbolo);
		// TODO se debería verificar que el estadoSiguiente ya no se encuentre
		// en el conjunto de EstadosSiguientes
		columnaAFN.getEstadosSiguientes().add(estadoSiguiente);

		if (simbolo.equals(new Simbolo("#"))) {
			// Los estados siguientes del estadoSiguiente deben formar parte del
			// conjunto de estados siguientes del estado
			final FilaAFN filaSiguiente = this.getFila(estadoSiguiente);
			final ColumnaAFN columnaSiguiente = this.getColumna(filaSiguiente,
					simbolo);
			for (final EstadoAFN tmp : columnaSiguiente.getEstadosSiguientes()) {
				if (!columnaAFN.getEstadosSiguientes().contains(tmp)) {
					columnaAFN.getEstadosSiguientes().add(tmp);
				}
			}

			// Debido a que se actualizo el conjunto de estados siguientes de
			// estado, se debe actualizar el conjunto de estados siguientes de
			// los estados que tengan como estado siguiente a estado
			for (final FilaAFN filaDesactualizada : this.filas) {
				final ColumnaAFN columnaDesactualizada = this.getColumna(
						filaDesactualizada, simbolo);
				if (columnaDesactualizada.getEstadosSiguientes().contains(
						estado)) {
					for (final EstadoAFN tmp : columnaAFN
							.getEstadosSiguientes()) {
						if (!columnaDesactualizada.getEstadosSiguientes()
								.contains(tmp)) {
							columnaDesactualizada.getEstadosSiguientes().add(
									tmp);
						}
					}
				}
			}
		}
	}

}
