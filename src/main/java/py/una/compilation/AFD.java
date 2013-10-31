package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un automata finito determinista.
 * 
 * @author Uriel González
 * 
 */
public class AFD {

	private final List<EstadoAFD> estados;

	private final Alfabeto alfabeto;

	private final TablaTransicionAFD tablaTransicion;

	private final EstadoAFD estadoInicial;

	private List<EstadoAFD> estadosFinales;

	/**
	 * Construye un AFD.
	 * 
	 * @param estadoInicial
	 *            Estado inicial del AfD.
	 * @param alfabeto
	 *            Alfabeto del AFD.
	 */
	public AFD(final EstadoAFD estadoInicial, final Alfabeto alfabeto) {
		this.estadoInicial = estadoInicial;
		this.alfabeto = alfabeto;
		this.estados = new ArrayList<EstadoAFD>();
		this.addNuevoEstadoAFD(this.estadoInicial);
		this.tablaTransicion = new TablaTransicionAFD();
	}

	/**
	 * Agrega un nuevo estado al AFD.
	 * 
	 * @param estadoAFD
	 *            Nuevo estado del AFD.
	 */
	public void addNuevoEstadoAFD(final EstadoAFD estadoAFD) {
		this.estados.add(estadoAFD);
		this.tablaTransicion.addNuevaFila(new FilaAFD(estadoAFD,
				new ArrayList<ColumnaAFD>()));

	}

	/**
	 * Actualiza una entrada en la tabla de transicion.
	 * 
	 * @param estadoActual
	 *            Estado de la fila del AFD.
	 * @param simbolo
	 *            Simbolo de la columna del AFD
	 * @param estadoSiguiente
	 *            Estado siguiente para el estado estadoActual dado el simbolo
	 *            simbolo.
	 */
	public void DTran(final EstadoAFD estadoActual, final Simbolo simbolo,
			final EstadoAFD estadoSiguiente) {
		this.tablaTransicion.addNuevaColumna(estadoActual, new ColumnaAFD(
				simbolo, estadoSiguiente));
	}

	/**
	 * Setea el conjunto de estados finales del AFD.
	 * 
	 * @param estadosFinales
	 *            Nuevo conjunto de estados finales del AFD.
	 */
	public void setEstadosFinales(final List<EstadoAFD> estadosFinales) {
		this.estadosFinales = estadosFinales;
	}

	/**
	 * Recupera el conjunto de estados finales del AFD.
	 * 
	 * @return Conjunto de estados finales del AFD.
	 */
	public List<EstadoAFD> getEstadosFinales() {
		return this.estadosFinales;
	}

	/**
	 * Recupera el conjunto de estado del AFD.
	 * 
	 * @return Conjunto de estados del AFD.
	 */
	public List<EstadoAFD> getEstadosAFD() {
		return this.estados;
	}

	/**
	 * Recupera el alfabeto del AFD.
	 * 
	 * @return
	 */
	public Alfabeto getAlfabeto() {
		return this.alfabeto;
	}

	/**
	 * Recupera la tabla de transicion del AFD.
	 * 
	 * @return Tabla de transicion del AFD.
	 */
	public TablaTransicionAFD getTablaTransicion() {
		return this.tablaTransicion;
	}

	/**
	 * Recupera el estado inicial del AFD.
	 * 
	 * @return Estado inicial del AFD.
	 */
	public EstadoAFD getEstadoInicial() {
		return this.estadoInicial;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Estado inicial: { " + this.estadoInicial + " }\n");
		sb.append("Estado final: { ");
		for (final EstadoAFD estadoAFD : this.estadosFinales) {
			sb.append(estadoAFD + " ");
		}
		sb.append("}\n");
		sb.append("Tabla transición:\n\t");
		for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
			sb.append(simbolo + "\t");
		}
		sb.append("\n");
		for (final FilaAFD filaAFD : this.tablaTransicion.getFilas()) {
			sb.append(filaAFD.getEstado() + "\t");
			for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
				final ColumnaAFD columnaAFD = this.tablaTransicion.getColumna(
						filaAFD, simbolo);
				if (columnaAFD == null) {
					sb.append("{}\t");
				} else {
					sb.append("{ " + columnaAFD.getEstadoSiguiente() + "}\t");
				}

			}
			sb.append("\n");

		}
		return sb.toString();
	}

}
