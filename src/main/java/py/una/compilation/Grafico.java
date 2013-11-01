package py.una.compilation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Genera el gráfico de un DFA.
 * 
 * @author Uriel González
 * 
 */
public class Grafico {

	private final AFD afd;

	/**
	 * Construye un gráfico.
	 * 
	 * @param afd
	 *            AFD a graficar.
	 */
	public Grafico(final AFD afd) {
		this.afd = afd;
	}

	/**
	 * Genera el gráfico de un DFA.
	 */
	public void generarGrafico() {
		FileWriter fichero;
		try {
			fichero = new FileWriter("grafico/Grafico.txt");
			final PrintWriter pw = new PrintWriter(fichero);
			pw.println("digraph G");
			pw.println("{");
			pw.println("inicio [shape=none];");
			for (final EstadoAFD estado : this.afd.getEstadosAFD()) {
				if (this.afd.getEstadosFinales().contains(estado)) {
					pw.println(estado + "[shape=doublecircle];");
				} else {
					pw.println(estado + ";");
				}

			}
			pw.println();
			pw.println("inicio -> " + this.afd.getEstadoInicial());
			for (final FilaAFD fila : this.afd.getTablaTransicion().getFilas()) {
				for (final ColumnaAFD columna : fila.getColumnasAFD()) {
					pw.println(fila.getEstado() + " -> "
							+ columna.getEstadoSiguiente() + " [label=\""
							+ columna.getSimbolo() + "\"];");
				}
			}
			pw.println("}");
			fichero.close();

			try {
				// TODO crear un archivo de configuracion
				final String dotPath = "/usr/bin/dot";

				final String fileInputPath = "grafico/Grafico.txt";
				final String fileOutputPath = "grafico/Grafico.jpg";

				final String tParam = "-Tjpg";
				final String tOParam = "-o";

				final String[] cmd = new String[5];
				cmd[0] = dotPath;
				cmd[1] = tParam;
				cmd[2] = fileInputPath;
				cmd[3] = tOParam;
				cmd[4] = fileOutputPath;

				final Runtime rt = Runtime.getRuntime();

				rt.exec(cmd);

			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
