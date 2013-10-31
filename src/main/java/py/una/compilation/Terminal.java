package py.una.compilation;

/**
 * Nodo que representa a un terminal.
 * 
 * @author Uriel González
 * 
 */
public class Terminal extends Nodo {

	private final char valor;

	/**
	 * Construye un terminal especificando su valor.
	 * 
	 * @param valor
	 *            Valor del nuevo terminal.
	 */
	public Terminal(final char valor) {
		this.valor = valor;
	}

	/**
	 * Recupera el valor del terminal.
	 * 
	 * @return Valor del terminal
	 */
	public String getValor() {
		return String.valueOf(this.valor);
	}
}
