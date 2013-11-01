package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 * 
 * @author Uriel González
 * 
 */
public class Compilation {

	public static void main(final String[] args) {

		List<Alfabeto> alfabetos = new ArrayList<Alfabeto>();
		alfabetos = crearAlfabetosPredeterminados();

		final Ventana ventana = new Ventana(alfabetos);

	}

	/**
	 * Crea los alfabetos predeterminados para el programa.
	 * 
	 * @return Lista de alfabetos predeterminados.
	 */
	public static List<Alfabeto> crearAlfabetosPredeterminados() {
		final List<Alfabeto> alfabetos = new ArrayList<Alfabeto>();

		final Alfabeto digitos = new Alfabeto();
		digitos.setNombre("digitos");
		digitos.addSimbolo(new Simbolo("0"));
		digitos.addSimbolo(new Simbolo("1"));
		digitos.addSimbolo(new Simbolo("2"));
		digitos.addSimbolo(new Simbolo("3"));
		digitos.addSimbolo(new Simbolo("4"));
		digitos.addSimbolo(new Simbolo("5"));
		digitos.addSimbolo(new Simbolo("6"));
		digitos.addSimbolo(new Simbolo("7"));
		digitos.addSimbolo(new Simbolo("8"));
		digitos.addSimbolo(new Simbolo("9"));

		alfabetos.add(digitos);

		final Alfabeto letras = new Alfabeto();
		letras.setNombre("letras");
		letras.addSimbolo(new Simbolo("a"));
		letras.addSimbolo(new Simbolo("b"));
		letras.addSimbolo(new Simbolo("c"));
		letras.addSimbolo(new Simbolo("d"));
		letras.addSimbolo(new Simbolo("e"));
		letras.addSimbolo(new Simbolo("f"));
		letras.addSimbolo(new Simbolo("g"));
		letras.addSimbolo(new Simbolo("h"));
		letras.addSimbolo(new Simbolo("i"));
		letras.addSimbolo(new Simbolo("j"));
		letras.addSimbolo(new Simbolo("k"));
		letras.addSimbolo(new Simbolo("l"));
		letras.addSimbolo(new Simbolo("m"));
		letras.addSimbolo(new Simbolo("n"));
		letras.addSimbolo(new Simbolo("o"));
		letras.addSimbolo(new Simbolo("p"));
		letras.addSimbolo(new Simbolo("q"));
		letras.addSimbolo(new Simbolo("r"));
		letras.addSimbolo(new Simbolo("s"));
		letras.addSimbolo(new Simbolo("t"));
		letras.addSimbolo(new Simbolo("v"));
		letras.addSimbolo(new Simbolo("w"));
		letras.addSimbolo(new Simbolo("y"));
		letras.addSimbolo(new Simbolo("x"));
		letras.addSimbolo(new Simbolo("z"));

		alfabetos.add(letras);

		return alfabetos;

	}

}
