package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un alfabeto.
 * 
 * @author Uriel Gonzalez
 * 
 */
public class Alfabeto {

	private String nombre;
	private final List<Simbolo> simbolos;

	/**
	 * Construye un alfabeto.
	 */
	public Alfabeto() {
		this.simbolos = new ArrayList<Simbolo>();
	}

	/**
	 * Setea el nombre del alfabeto.
	 * 
	 * @param nombre
	 *            Nuevo nombre del alfabeto.
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	/**
	 * Recupera el nombre del alfabeto.
	 * 
	 * @return El nombre del alfabeto.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Agrega un nuevo simbolo al alfabeto.
	 * 
	 * @param simbolo
	 *            Nuevo simbolo del alfabeto.
	 */
	public void addSimbolo(final Simbolo simbolo) {
		this.simbolos.add(simbolo);

	}

	/**
	 * Recupera la lista de simbolos del alfabeto.
	 * 
	 * @return Los simbolos del alfabeto.
	 */
	public List<Simbolo> getSimbolos() {
		return this.simbolos;
	}

	/**
	 * Recupera un simbolo del alfabeto a partir de su etiqueta.
	 * 
	 * @param etiqueta
	 *            Etiqueta del simbolo a recuperar.
	 * @return Simbolo del alfabeto al cual pertenece la etiqueta pasada como
	 *         parametro. En caso de que no exista un simbolo en el alfabeto con
	 *         la etiqueta pasada como parametro retorna null.
	 */
	public Simbolo getSimbolo(final String etiqueta) {
		final Simbolo simboloBuscado = new Simbolo(etiqueta);
		for (final Simbolo simbolo : this.simbolos) {
			if (simbolo.equals(simboloBuscado)) {
				return simbolo;
			}
		}
		return null;
	}

	/**
	 * Permite saber si el simbolo pasado como parámetro se encuentro en el
	 * alfabeto.
	 * 
	 * @param simbolo
	 *            Simbolo a ser buscado.
	 * @return true en caso que el simbolo se encuentre en el alfabeto.
	 */
	public boolean hasSimbolo(final Simbolo simbolo) {
		return this.simbolos.contains(simbolo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.nombre + " = { ");
		for (final Simbolo simbolo : this.simbolos) {
			sb.append(simbolo + " ");
		}
		sb.append("}");
		return sb.toString();
	}

}
