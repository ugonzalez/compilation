package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una expresión regular.
 * 
 * @author Uriel González
 * 
 */
public class ExpresionRegular {

	private final String cadena;
	private final Alfabeto alfabeto;
	private Nodo arbol; // Arbol sintatico de la expresion regular

	private char preanalisis;
	private int indice; // Sirve como puntero en la cadena de la expresion
						// regular
	private int etiqueta; // Etiqueta para los estados del AFN
	private AFN afn;
	private AFD afd;
	private AFD afdMin;

	/**
	 * Construye una expresión especificando la definición de la expresión
	 * regular y el alfabeto sobre el cual esta definida la expresión regular.
	 * 
	 * @param cadena
	 *            Definición de la expresión regular.
	 * @param alfabeto
	 *            Alfabeto sobre el cual esta definida la expresión regular.
	 */
	public ExpresionRegular(final String cadena, final Alfabeto alfabeto) {
		this.cadena = cadena;
		this.alfabeto = alfabeto;
		// TODO Ver la forma para no agregar la cadena vacia al alfabeto
		this.alfabeto.addSimbolo(new Simbolo("#"));
	}

	/**
	 * Determina si una expresión regular esta bien formada. Ademas construye el
	 * árbol sintáctico de la expresión regular.
	 * 
	 * @return true en caso de que la expresión regular este bien formada, false
	 *         en caso contrario.
	 */
	public boolean isValido() {
		this.indice = 0;
		this.coincidir();
		try {
			this.arbol = this.expr();

			if (!this.esFinDeCadena()) {
				return false;
			} else {
				return true;
			}

		} catch (final ErrorSintaxis e) {
			return false;
		}

	}

	private void coincidir() {
		// Mueve el puntero sobre la cadena de la expresion regular

		if (this.indice == this.cadena.length()) {
			// El caracter $ se utiliza como fin de cadena
			this.preanalisis = '$';
			return;
		}
		this.preanalisis = this.cadena.charAt(this.indice);
		this.indice++;
	}

	private Exp expr() throws ErrorSintaxis {
		// Implementa la produccion exp -> term R

		final Term term = this.term();
		final R r = this.R();

		final Exp exp = new Exp(term, r);

		return exp;
	}

	private R R() throws ErrorSintaxis {
		// Implementa la produccion R -> '|'term R | #

		if (this.preanalisis == '|') {
			this.coincidir();
			final Term term = this.term();
			final R r = this.R();
			final R aRet = new R(term, r);
			return aRet;
		} else {
			return null;
		}
	}

	private Term term() throws ErrorSintaxis {
		// Implementa la produccion term -> factor S

		final Factor factor = this.factor();
		final S s = this.S();
		final Term term = new Term(factor, s);
		return term;
	}

	private S S() throws ErrorSintaxis {
		// Implementa la produccion S -> . factor S | #

		if (this.preanalisis == '.') {
			this.coincidir();
			final Factor factor = this.factor();
			final S s = this.S();
			final S aRet = new S(factor, s);
			return aRet;
		} else {
			// R -> #
			return null;
		}
	}

	private Factor factor() throws ErrorSintaxis {
		// Implementa la produccion factor -> ( expr ) T | x_1 T | .. | x_n T
		// donde cada x_i es un simbolo del alfabeto

		if (this.preanalisis == '(') {
			this.coincidir();
			final Exp exp = this.expr();
			if (this.preanalisis == ')') {
				this.coincidir();
				final Parentesis parentesis = new Parentesis(exp);
				final T t = this.T();
				final Factor factor = new Factor(parentesis, t);
				return factor;
			} else {
				throw new ErrorSintaxis();
			}
		} else if (this.alfabeto.hasSimbolo(new Simbolo(String
				.valueOf(this.preanalisis)))) {
			final char valor = this.preanalisis;
			this.coincidir();
			final Terminal terminal = new Terminal(valor);
			final T t = this.T();
			final Factor factor = new Factor(terminal, t);
			return factor;

		} else {
			throw new ErrorSintaxis();
		}
	}

	private T T() {
		// Implementa la produccion T -> * T | + T | ? T | #

		if (this.preanalisis == '*') {
			this.coincidir();
			final T t = this.T();
			final T aRet = new T(t, '*');
			return aRet;
		} else if (this.preanalisis == '+') {
			this.coincidir();
			final T t = this.T();
			final T aRet = new T(t, '+');
			return aRet;

		} else if (this.preanalisis == '?') {
			this.coincidir();
			final T t = this.T();
			final T aRet = new T(t, '?');
			return aRet;

		} else {
			// T -> #
			return null;
		}

	}

	private boolean esFinDeCadena() {
		// Retornar true en caso de que el puntero apunte al fin de cadena

		if (this.preanalisis == '$') {
			return true;
		} else {
			return false;
		}
	}

	private AFN recorrer(final Nodo nodo) {
		// TODO Los métodos usados en el algoritmo de Thompson deben enviarse a
		// la clase Conversion

		// Construye el AFN por medio del algoritmo de Thompson

		if (nodo == null) {
			return null;
		}
		if (nodo.getHijoIzq() != null) {
			final AFN afnIzq = this.recorrer(nodo.getHijoIzq());
			final AFN afnDer = this.recorrer(nodo.getHijoDer());
			if (nodo.getClass() == Factor.class) {
				// TODO Llevar lo que esta dentro del if al método
				// construirFactor()
				if (nodo.getHijoDer() == null) {
					return afnIzq;
				} else {
					// El hijo derecho de un nodo Factor es siempre un nodo T
					final T hijoDerecho = (T) nodo.getHijoDer();
					final AFN afn = this.construirFactor(afnIzq,
							hijoDerecho.getTipo());
					return afn;
				}

			} else if (nodo.getClass() == S.class) {
				final AFN afn = this.construirS(afnIzq, afnDer);
				return afn;
			} else if (nodo.getClass() == Term.class) {
				final AFN afn = this.construirTerm(afnIzq, afnDer);
				return afn;
			} else if (nodo.getClass() == Exp.class) {
				final AFN afn = this.construirExp(afnIzq, afnDer);
				return afn;
			} else if (nodo.getClass() == R.class) {
				final AFN afn = this.construirR(afnIzq, afnDer);
				return afn;
			} else if (nodo.getClass() == Parentesis.class) {
				final AFN afn = this.construirParentesis(afnIzq);
				return afn;
			}
		} else {
			// Los unicos tipos de nodos que no tienen hijo izquierdo es un nodo
			// Terminal o un nodo T.
			if (nodo.getClass() == Terminal.class) {
				final AFN afn = this.construirTerminal((Terminal) nodo);
				return afn;
			} else if (nodo.getClass() == T.class) {
				return null;
			}

		}
		// No deberia llegar nunca a esta linea
		return null;
	}

	private AFN construirTerminal(final Terminal nodo) {

		// Para cualquier subexpresion a en el alfabeto

		final List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		final EstadoAFN estadoInicial = this.generarNuevoEstado();
		final EstadoAFN estadoFinal = this.generarNuevoEstado();
		estados.add(estadoInicial);
		estados.add(estadoFinal);

		final List<FilaAFN> filasAFN = new ArrayList<FilaAFN>();
		filasAFN.add(this.generarNuevaFila(estadoInicial));
		filasAFN.add(this.generarNuevaFila(estadoFinal));

		final TablaTransicionAFN tablaTransicionAFN = new TablaTransicionAFN(
				filasAFN);
		tablaTransicionAFN.actualizar(estadoInicial,
				this.alfabeto.getSimbolo(nodo.getValor()), estadoFinal);

		final AFN afn = new AFN(estados, this.alfabeto, tablaTransicionAFN,
				estadoInicial, estadoFinal);

		return afn;

	}

	private EstadoAFN generarNuevoEstado() {
		// TODO Este método debe formar parte de la clase Conversion.
		// Genera un nuevo estado de un AFN
		final EstadoAFN estadoNuevo = new EstadoAFN(
				Integer.toString(this.etiqueta));
		this.etiqueta++;
		return estadoNuevo;
	}

	private FilaAFN generarNuevaFila(final EstadoAFN estado) {
		// TODO Este método debe formar parte de la clase TablaTransicionAFN.
		// Genera una nueva fila para una tabla de transición para un AFN.
		final List<ColumnaAFN> columnas = new ArrayList<ColumnaAFN>();
		for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
			final List<EstadoAFN> estadosSiguientes = new ArrayList<EstadoAFN>();
			columnas.add(new ColumnaAFN(simbolo, estadosSiguientes));
		}
		final FilaAFN filaNueva = new FilaAFN(estado, columnas);
		return filaNueva;
	}

	private AFN construirFactor(final AFN afn, final char tipo) {
		// Dependiendo del tipo construye un * , + o un ?

		if (tipo == '*') {
			final List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
			final EstadoAFN estadoInicial = this.generarNuevoEstado();
			final EstadoAFN estadoFinal = this.generarNuevoEstado();
			estados.add(estadoInicial);
			estados.add(estadoFinal);

			final List<FilaAFN> filasAFN = new ArrayList<FilaAFN>();
			filasAFN.add(this.generarNuevaFila(estadoInicial));
			filasAFN.add(this.generarNuevaFila(estadoFinal));

			for (final EstadoAFN estadoAFN : afn.getEstados()) {
				estados.add(estadoAFN);
				// TODO Crear un metodo en la clase TablaTransicionAFN para
				// agregar una nueva fila
				final FilaAFN filaAFN = afn.getTablaTransicion().getFila(
						estadoAFN);
				filasAFN.add(filaAFN);
			}

			final TablaTransicionAFN tablaTransicionAFN = new TablaTransicionAFN(
					filasAFN);
			tablaTransicionAFN.actualizar(estadoInicial,
					this.alfabeto.getSimbolo("#"), afn.getEstadoSo());
			tablaTransicionAFN.actualizar(estadoInicial,
					this.alfabeto.getSimbolo("#"), estadoFinal);
			tablaTransicionAFN.actualizar(afn.getEstadoFinal(),
					this.alfabeto.getSimbolo("#"), estadoFinal);
			tablaTransicionAFN.actualizar(afn.getEstadoFinal(),
					this.alfabeto.getSimbolo("#"), afn.getEstadoSo());

			final AFN aRet = new AFN(estados, this.alfabeto,
					tablaTransicionAFN, estadoInicial, estadoFinal);

			return aRet;
		} else if (tipo == '?') {
			final EstadoAFN estadoInicial = afn.getEstadoSo();
			afn.getTablaTransicion().actualizar(estadoInicial,
					new Simbolo("#"), afn.getEstadoFinal());

			return afn;
		} else {

			// TODO deberia de llevarse esto al método clone de la clase AFN
			// copiamos el AFN
			final List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
			final EstadoAFN estadoInicial = new EstadoAFN(afn.getEstadoSo()
					.getEtiqueta());
			estados.add(estadoInicial);
			final EstadoAFN estadoFinal = new EstadoAFN(afn.getEstadoFinal()
					.getEtiqueta());
			estados.add(estadoFinal);
			final List<FilaAFN> filasAFN = new ArrayList<FilaAFN>();
			// copiamos el conjunto de estados
			for (final EstadoAFN estado : afn.getEstados()) {
				if (!estados.contains(estado)) {
					final EstadoAFN estadoNuevo = new EstadoAFN(
							estado.getEtiqueta());
					estados.add(estadoNuevo);
				}

			}
			// copiamos las filas
			for (final EstadoAFN estado : afn.getEstados()) {
				EstadoAFN estadoNuevo = null;
				for (final EstadoAFN est : estados) {
					if (est.equals(estado)) {
						estadoNuevo = est;
						break;
					}
				}
				final FilaAFN filaAFN = afn.getTablaTransicion()
						.getFila(estado);
				final List<ColumnaAFN> columnasNuevas = new ArrayList<ColumnaAFN>();
				for (final ColumnaAFN columnaAFN : filaAFN.getColumnas()) {

					final List<EstadoAFN> estadosSiguientes = new ArrayList<EstadoAFN>();
					for (final EstadoAFN estadoSiguiente : columnaAFN
							.getEstadosSiguientes()) {
						for (final EstadoAFN tmp : estados) {
							if (tmp.equals(estadoSiguiente)) {
								estadosSiguientes.add(tmp);
								break;
							}
						}
					}
					columnasNuevas.add(new ColumnaAFN(columnaAFN.getSimbolo(),
							estadosSiguientes));
				}
				filasAFN.add(new FilaAFN(estadoNuevo, columnasNuevas));
			}
			final TablaTransicionAFN tablaTransicionAFN = new TablaTransicionAFN(
					filasAFN);
			final AFN copy = new AFN(estados, this.alfabeto,
					tablaTransicionAFN, estadoInicial, estadoFinal);

			// calculamos *, dado que r+ = r*r
			final AFN afn1 = this.construirFactor(afn, '*');

			// renombramos los estados de la copia del afn
			for (final EstadoAFN estado : copy.getEstados()) {
				estado.setEtiqueta(Integer.toString(this.etiqueta));
				this.etiqueta++;

			}

			// hacemos la ultima parte, la concatenacion r*.r
			final AFN aRet = this.construirS(afn1, copy);

			return aRet;

		}

	}

	private AFN construirS(final AFN afnIzq, final AFN afnDer) {
		// Hace la concatenacion .

		if (afnDer == null) {
			return afnIzq;
		}

		final List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		final EstadoAFN estadoInicial = afnIzq.getEstadoSo();

		final List<FilaAFN> filasAFN = new ArrayList<FilaAFN>();

		// Juntamos los estados sin incluir el estadoInicial del afnDer
		for (final EstadoAFN estadoAFN : afnIzq.getEstados()) {
			estados.add(estadoAFN);
			final FilaAFN filaAFN = afnIzq.getTablaTransicion().getFila(
					estadoAFN);
			filasAFN.add(filaAFN);
		}
		for (final EstadoAFN estadoAFN : afnDer.getEstados()) {
			if (!estadoAFN.equals(afnDer.getEstadoSo())) {
				estados.add(estadoAFN);
				final FilaAFN filaAFN = afnDer.getTablaTransicion().getFila(
						estadoAFN);
				filasAFN.add(filaAFN);
			}
		}

		// Juntamos en un solo estado el estado final del afnIzq y el estado
		// inicial del afnDer
		final EstadoAFN estadoIntermedio = afnIzq.getEstadoFinal();
		final FilaAFN filaAFNIzq = afnIzq.getTablaTransicion().getFila(
				estadoIntermedio);
		final FilaAFN filaAFNDer = afnDer.getTablaTransicion().getFila(
				afnDer.getEstadoSo());

		for (final Simbolo simbolo : this.alfabeto.getSimbolos()) {
			final ColumnaAFN columnaAFNIzq = afnIzq.getTablaTransicion()
					.getColumna(filaAFNIzq, simbolo);
			final ColumnaAFN columnaAFNDer = afnDer.getTablaTransicion()
					.getColumna(filaAFNDer, simbolo);
			for (final EstadoAFN estado : columnaAFNDer.getEstadosSiguientes()) {
				if (!columnaAFNIzq.getEstadosSiguientes().contains(estado)) {
					columnaAFNIzq.getEstadosSiguientes().add(estado);
				}
			}
		}

		final TablaTransicionAFN tablaTransicionAFN = new TablaTransicionAFN(
				filasAFN);

		final AFN afn = new AFN(estados, this.alfabeto, tablaTransicionAFN,
				estadoInicial, afnDer.getEstadoFinal());

		// Actualizamos las cadenas vacias del estado intermedio
		for (final EstadoAFN estado : afn.getEstados()) {
			final FilaAFN fila = afn.getTablaTransicion().getFila(estado);
			final ColumnaAFN columna = afn.getTablaTransicion().getColumna(
					fila, new Simbolo("#"));
			if (columna.getEstadosSiguientes().contains(estadoIntermedio)) {
				for (final EstadoAFN estadoSiguiente : afn.getTablaTransicion()
						.getColumna(filaAFNIzq, new Simbolo("#"))
						.getEstadosSiguientes()) {
					if (!columna.getEstadosSiguientes().contains(
							estadoSiguiente)) {
						columna.getEstadosSiguientes().add(estadoSiguiente);
					}
				}
			}
		}

		return afn;

	}

	private AFN construirTerm(final AFN afnIzq, final AFN afnDer) {
		if (afnDer == null) {
			return afnIzq;
		}

		final AFN afn = this.construirS(afnIzq, afnDer);

		return afn;

	}

	private AFN construirExp(final AFN afnIzq, final AFN afnDer) {
		// Hace la union | si el afnDer es no null.
		// exp -> term R, y R -> '|'term R

		if (afnDer == null) {
			return afnIzq;
		}

		final List<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		final EstadoAFN estadoInicial = this.generarNuevoEstado();
		final EstadoAFN estadoFinal = this.generarNuevoEstado();
		estados.add(estadoInicial);
		estados.add(estadoFinal);

		final List<FilaAFN> filasAFN = new ArrayList<FilaAFN>();
		filasAFN.add(this.generarNuevaFila(estadoInicial));
		filasAFN.add(this.generarNuevaFila(estadoFinal));

		// Juntamos los estados
		for (final EstadoAFN estadoAFN : afnIzq.getEstados()) {
			estados.add(estadoAFN);
			final FilaAFN filaAFN = afnIzq.getTablaTransicion().getFila(
					estadoAFN);
			filasAFN.add(filaAFN);
		}
		for (final EstadoAFN estadoAFN : afnDer.getEstados()) {
			estados.add(estadoAFN);
			final FilaAFN filaAFN = afnDer.getTablaTransicion().getFila(
					estadoAFN);
			filasAFN.add(filaAFN);
		}

		final TablaTransicionAFN tablaTransicionAFN = new TablaTransicionAFN(
				filasAFN);
		tablaTransicionAFN.actualizar(estadoInicial,
				this.alfabeto.getSimbolo("#"), afnIzq.getEstadoSo());
		tablaTransicionAFN.actualizar(estadoInicial,
				this.alfabeto.getSimbolo("#"), afnDer.getEstadoSo());

		tablaTransicionAFN.actualizar(afnIzq.getEstadoFinal(),
				this.alfabeto.getSimbolo("#"), estadoFinal);
		tablaTransicionAFN.actualizar(afnDer.getEstadoFinal(),
				this.alfabeto.getSimbolo("#"), estadoFinal);

		final AFN afn = new AFN(estados, this.alfabeto, tablaTransicionAFN,
				estadoInicial, estadoFinal);

		return afn;
	}

	private AFN construirR(final AFN afnIzq, final AFN afnDer) {
		if (afnDer == null) {
			return afnIzq;
		}
		// R -> #
		return null;
	}

	private AFN construirParentesis(final AFN afnIzq) {
		return afnIzq;
	}

	/**
	 * Recupera el AFN construido a partir de la expresión regular.
	 * 
	 * @return AFN construido a partir de la expresión regular.
	 */
	public AFN getAFN() {
		// TODO asegurarse de haber llamado a isValido primero
		this.etiqueta = 0;
		return this.afn = this.recorrer(this.arbol);
	}

	/**
	 * Recupera el AFD construido a partir del AFN de la expresión regular.
	 * 
	 * @return AFD de la expresión regular
	 */
	public AFD getAFD() {
		// TODO Asegurarse que el AFN se haya construido primero
		return this.afd = Conversion.AfnToAfd(this.afn);
	}

	/**
	 * Recupera el AFD mínimo equivalente al AFD de la expresion regular.
	 * 
	 * @return AFD mínimo de la expresión regular.
	 */
	public AFD getAFDMin() {
		return this.afdMin = Conversion.AfdToAfdMinimo(this.afd);
	}

	/**
	 * Recupera la definición de la expresión regular.
	 * 
	 * @return Definición de la expresión regular.
	 */
	public String getCadena() {
		return this.cadena;
	}
}
