package py.una.compilation;

import java.util.List;

/**
 * Clase que representa un Automata finito no determinista.
 * 
 * @author Uriel González
 * 
 */
public class AFN {

	private final List<EstadoAFN> estados;

	private final Alfabeto alfabeto;

	private final TablaTransicionAFN tablaTransicion;

	private final EstadoAFN estadoInicial;

	private final EstadoAFN estadoFinal;

	/**
	 * Construye un AFN.
	 * 
	 * @param estados
	 *            Conjunto de estados del AFN
	 * @param alfabeto
	 *            Alfabeto del AFN.
	 * @param tablaTransicion
	 *            Tabla de transicion del AFN.
	 * @param So
	 *            Estado inicial del AFN..
	 * @param estadoFinal
	 *            Estado final del AFN.
	 */
	public AFN(final List<EstadoAFN> estados, final Alfabeto alfabeto,
			final TablaTransicionAFN tablaTransicion, final EstadoAFN So,
			final EstadoAFN estadoFinal) {
		this.estados = estados;
		this.alfabeto = alfabeto;
		this.tablaTransicion = tablaTransicion;
		this.estadoInicial = So;
		this.estadoFinal = estadoFinal;
	}

	/**
	 * Recupera el estado inicial del AFN.
	 * 
	 * @return Estado inicial del AFN.
	 */
	public EstadoAFN getEstadoSo() {
		return this.estadoInicial;
	}

	/**
	 * Recupera el conjunto de estado del AFN.
	 * 
	 * @return Estados del AFN.
	 */
	public List<EstadoAFN> getEstados() {
		return this.estados;
	}

	/**
	 * Recupera la tabla de transicion del AFN.
	 * 
	 * @return Tabla de transicion del AFN.
	 */
	public TablaTransicionAFN getTablaTransicion() {
		return this.tablaTransicion;
	}

	/**
	 * Recupera el alfabeto del AFN.
	 * 
	 * @return Alfabeto del AFN.
	 */
	public Alfabeto getAlfabeto() {
		return this.alfabeto;
	}

	/**
	 * Recupera el estado final del AFN.
	 * 
	 * @return Estado final del AFN.
	 */
	public EstadoAFN getEstadoFinal() {
		return this.estadoFinal;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Estado inicial: { " + this.estadoInicial + " }\n");
		sb.append("Estado final: { " + this.estadoFinal + " }\n");
		sb.append("Tabla transición:\n\t");
		for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
			sb.append(simbolo + "\t");
		}
		sb.append("\n");
		for (final FilaAFN filaAFN : this.tablaTransicion.getFilas()) {
			sb.append(filaAFN.getEstado() + "\t");
			for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
				final ColumnaAFN columnaAFN = this.tablaTransicion.getColumna(
						filaAFN, simbolo);
				sb.append("{");
				for (final EstadoAFN estadoAFN : columnaAFN
						.getEstadosSiguientes()) {
					sb.append(" " + estadoAFN + " ");
				}
				sb.append("}\t");
			}
			sb.append("\n");

		}
		return sb.toString();
	}

}
