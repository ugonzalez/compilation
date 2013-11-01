package py.una.compilation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Genera el codigo fuente de un validador de expresion regular.
 * 
 * @author Uriel González
 * 
 */
public class Generador {

	private final ExpresionRegular expresion;

	/**
	 * Construye un generador.
	 * 
	 * @param expresion
	 *            Expresión regular para el cual se va a generar el validado.
	 */
	public Generador(final ExpresionRegular expresion) {
		this.expresion = expresion;
	}

	/**
	 * Genera el validador para la expresión regular.
	 */
	public void generarValidador() {
		FileWriter fichero;
		try {
			fichero = new FileWriter("validador/Fichero.java");
			final PrintWriter pw = new PrintWriter(fichero);
			pw.println("import java.io.BufferedReader;");
			pw.println("import java.io.IOException;");
			pw.println("import java.io.InputStreamReader;");
			pw.println();
			pw.println("public class Fichero {");
			pw.println();
			pw.println("\tpublic static void main(String[] args) {");
			pw.println();
			pw.println("\t\tfinal String expresion = \""
					+ this.expresion.getCadena() + "\";");
			pw.println();
			pw.println("\t\tSystem.out.println(\"Validador para la expresion \" + expresion);");
			pw.println("\t\tSystem.out.println(\"Cadena: \");");
			pw.println("\t\tBufferedReader br = new BufferedReader(new InputStreamReader(System.in));");
			pw.println("\t\tString cadena;");
			pw.println("\t\ttry {");
			pw.println("\t\t\tcadena = br.readLine();");
			pw.println();
			pw.println("\t\t\tint estado = "
					+ this.expresion.getAFDMin().getEstadoInicial() + ";");
			pw.println("\t\t\tint siguienteEstado;");
			pw.println("\t\t\tfor (int indice = 0; indice < cadena.length(); indice++) {");
			pw.println("\t\t\t\tsiguienteEstado = getSiguienteEstado(estado,cadena.charAt(indice));");
			pw.println("\t\t\t\testado = siguienteEstado;");
			pw.println("\t\t\t\tif (estado == -1) {");
			pw.println("\t\t\t\t\tbreak;");
			pw.println("\t\t\t\t}");
			pw.println("\t\t\t}");
			pw.println();
			pw.println("\t\t\tif(estado == -1){");
			pw.println("\t\t\t\tSystem.out.println(\"Cadena no válida\");");
			pw.println("\t\t\t}else{");
			for (final EstadoAFD estadoFinal : this.expresion.getAFDMin()
					.getEstadosFinales()) {
				pw.println("\t\t\t\tif(estado == " + estadoFinal + "){");
				pw.println("\t\t\t\t\tSystem.out.println(\"Cadena válida\");");
				pw.println("\t\t\t\t\treturn;");
				pw.println("\t\t\t\t}");
			}
			pw.println("\t\t\t\telse{");
			pw.println("\t\t\t\t\tSystem.out.println(\"Cadena no válida\");");
			pw.println("\t\t\t\t}");
			pw.println("\t\t\t}");
			pw.println("\t\t} catch (IOException e) {");
			pw.println("\t\t\te.printStackTrace();");
			pw.println("\t\t}");
			pw.println("\t}");
			pw.println();
			pw.println("\tprivate static int getSiguienteEstado(int estado, char caracter) {");
			for (final Simbolo simbolo : this.expresion.getAFDMin()
					.getAlfabeto().getSimbolos()) {
				pw.println("\t\tif (caracter == '" + simbolo + "') {");
				for (final EstadoAFD estado : this.expresion.getAFDMin()
						.getEstadosAFD()) {
					pw.println("\t\t\tif (estado == " + estado + ") {");
					final FilaAFD fila = this.expresion.getAFDMin()
							.getTablaTransicion().getFila(estado);
					final ColumnaAFD columna = this.expresion.getAFDMin()
							.getTablaTransicion().getColumna(fila, simbolo);
					if (columna == null) {
						pw.println("\t\t\t\treturn -1;");
					} else {
						final EstadoAFD estadoSiguiente = columna
								.getEstadoSiguiente();
						pw.println("\t\t\t\treturn " + estadoSiguiente + ";");
					}
					pw.println("\t\t\t}");
				}
				pw.println("\t\t}");
			}
			pw.println();
			pw.println("\t\treturn -1;");
			pw.println("\t}");
			pw.println();
			pw.println("}");

			fichero.close();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}