package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

public class Conversion {

	/**
	 * Construye un AFD a partir de un AFN usando construcción de conjuntos
	 * 
	 * @param afn
	 *            AFN a partir del cual se va a construir el AFD.
	 * @return AFD del AFN pasado como parámetro
	 */
	public static AFD AfnToAfd(final AFN afn) {

		// while(hay un estado sin marcar T en Destados){
		// marcar T;
		// for(cada simbolo de entrada a){
		// U = cerradura(mover(T, a))
		// if(U no esta en Destados)
		// agregar U como estado sin marcar en Destados
		// Dtran[T, a] = U
		// }
		// }

		int etiqueta = 0;
		final List<EstadoAFD> Destados = new ArrayList<EstadoAFD>();
		Destados.add(new EstadoAFD(Integer.toString(etiqueta), cerradura(
				afn.getEstadoSo(), afn.getTablaTransicion())));
		// TODO Cuidado que se esta modificando también el alfabeto del AFN
		final Simbolo simboloAEliminar = afn.getAlfabeto().getSimbolo("#");
		afn.getAlfabeto().getSimbolos().remove(simboloAEliminar);
		final AFD afd = new AFD(Destados.get(0), afn.getAlfabeto());
		while (existenEstadosSinMarcar(Destados)) {
			final EstadoAFD estado = getEstadoSinMarcar(Destados);
			marcarEstado(estado);
			for (final Simbolo simbolo : afn.getAlfabeto().getSimbolos()) {
				etiqueta++;
				final EstadoAFD temp = new EstadoAFD(
						Integer.toString(etiqueta),
						cerradura(
								mover(estado, simbolo, afn.getTablaTransicion()),
								afn.getTablaTransicion()));
				if (!temp.getEstadosAFN().isEmpty()) {
					if (!esListaDuplicada(Destados, temp)) {
						Destados.add(temp);
						afd.addNuevoEstadoAFD(temp);
						afd.DTran(estado, simbolo, temp);

					} else {
						etiqueta--;
						afd.DTran(estado, simbolo, getEstadoAFD(Destados, temp));

					}
				} else {
					etiqueta--;
				}
			}
		}
		afd.setEstadosFinales(getEstadosFinales(Destados, afn.getEstadoFinal()));

		return afd;

	}

	private static boolean existenEstadosSinMarcar(final List<EstadoAFD> estados) {
		// Determina si hay un estado sin marcar en estados. Retorna true en
		// caso de que exista por lo menos un estado sin marcar
		boolean aRet = false;
		for (final EstadoAFD estado : estados) {
			if (estado.sinMarcar()) {
				aRet = true;
				break;
			}
		}

		return aRet;

	}

	private static EstadoAFD getEstadoSinMarcar(final List<EstadoAFD> estados) {
		// Recupera un estado sin marcar en estados. Retorna null en caso de que
		// no exista ningún estado sin marcar.

		EstadoAFD aRet = null;
		for (final EstadoAFD estado : estados) {
			if (estado.sinMarcar()) {
				aRet = estado;
				break;
			}
		}

		return aRet;

	}

	private static void marcarEstado(final EstadoAFD estado) {
		// Establece el estado pasado como parámetro como marcado.
		estado.marcarEstado();
	}

	private static List<EstadoAFN> cerradura(final EstadoAFN estadoAFN,
			final TablaTransicionAFN tablaTransicionAFN) {

		// Retorna el conjunto de estados del AFN a los cuales se puede llegar
		// desde el estado estadoAFN del AFN, solo en las transiciones #

		final FilaAFN filaAFN = tablaTransicionAFN.getFila(estadoAFN);
		final List<EstadoAFN> aRet = tablaTransicionAFN.getColumna(filaAFN,
				new Simbolo("#")).getEstadosSiguientes();

		aRet.add(estadoAFN); // un estado puede llegar a si mismo

		return aRet;
	}

	private static List<EstadoAFN> mover(final EstadoAFD estadoAFD,
			final Simbolo simbolo, final TablaTransicionAFN tablaTransicionAFN) {

		// Retorna el conjunto de estados del AFN para los cuales hay una
		// transición sobre el simbolo pasado como parámetro a partir de cierto
		// estado AFN que este en el estadoAFD.

		final List<EstadoAFN> aRet = new ArrayList<EstadoAFN>();
		for (final EstadoAFN estadoAFN : estadoAFD.getEstadosAFN()) {
			final FilaAFN filaAFN = tablaTransicionAFN.getFila(estadoAFN);
			// final List<ColumnaAFN> columnasAFN = filaAFN.getColumnas();
			final List<EstadoAFN> estadosAFN = tablaTransicionAFN.getColumna(
					filaAFN, simbolo).getEstadosSiguientes();
			for (final EstadoAFN estado : estadosAFN) {
				if (!aRet.contains(estado)) {
					aRet.add(estado);
				}
			}
		}

		return aRet;

	}

	private static List<EstadoAFN> cerradura(final List<EstadoAFN> estadosAFN,
			final TablaTransicionAFN tablaTransicionAFN) {

		// Retorna el conjunto de estados del AFN a los cuales se puede llegar
		// desde el estado estadoAFN del AFN en el conjunto estadosAFN, solo en
		// las transiciones #.

		final List<EstadoAFN> aRet = new ArrayList<EstadoAFN>();
		for (final EstadoAFN estadoAFN : estadosAFN) {
			final List<EstadoAFN> temp = cerradura(estadoAFN,
					tablaTransicionAFN);
			for (final EstadoAFN estado : temp) {
				if (!aRet.contains(estado)) {
					aRet.add(estado);
				}
			}
		}

		return aRet;
	}

	private static boolean esListaDuplicada(final List<EstadoAFD> estadosAFD,
			final EstadoAFD nuevoEstadoAFD) {

		// Determina si el nuevoEstadoAFD tiene un conjunto de estados AFN igual
		// a un estado AFD agregado previamente.

		int temp = 0;
		for (final EstadoAFD estado : estadosAFD) {
			if (estado.getEstadosAFN().size() != nuevoEstadoAFD.getEstadosAFN()
					.size()) {
				temp++;
				continue;
			}
			boolean romper = false;
			for (final EstadoAFN nodo : estado.getEstadosAFN()) {
				if (!nuevoEstadoAFD.getEstadosAFN().contains(nodo)) {
					romper = true;
					break;
				}
			}
			if (romper) {
				temp++;
				continue;
			}
		}

		if (temp == estadosAFD.size()) {
			return false;
		} else {
			return true;
		}

	}

	private static EstadoAFD getEstadoAFD(final List<EstadoAFD> estadosAFD,
			final EstadoAFD nuevoEstadoAFD) {

		// Retorna un estado AFD de una lista de estados AFD que sea igual al
		// nuevoEstadoAFD. Los estados AFD son iguales si se tienen el mismo
		// conjunto de estados AFN.

		EstadoAFD aRet = null;
		for (final EstadoAFD estado : estadosAFD) {
			if (estado.getEstadosAFN().size() != nuevoEstadoAFD.getEstadosAFN()
					.size()) {
				continue;
			}
			boolean tmp = true;
			for (final EstadoAFN estadoAFN : nuevoEstadoAFD.getEstadosAFN()) {
				if (!estado.getEstadosAFN().contains(estadoAFN)) {
					tmp = false;
					break;
				}
			}
			if (tmp) {
				aRet = estado;
				break;
			}
		}

		return aRet;

	}

	private static List<EstadoAFD> getEstadosFinales(
			final List<EstadoAFD> estadosAFD, final EstadoAFN estadoAFNFinal) {

		// Recupera los estados finales del AFD a partir de los estados finales
		// del AFN.

		final List<EstadoAFD> aRet = new ArrayList<EstadoAFD>();
		for (final EstadoAFD estadoAFD : estadosAFD) {
			for (final EstadoAFN estadoAFN : estadoAFD.getEstadosAFN()) {
				if (estadoAFN.equals((estadoAFNFinal))) {
					aRet.add(estadoAFD);
					break;
				}
			}
		}

		return aRet;

	}

	/**
	 * Obtiene el AFD mínimo equivalente a un AFD.
	 * 
	 * @param afd
	 *            AFD del cual se va a obtener el mínimo.
	 * @return AFD mínimo equivalente al AFD pasado pasado como parámetro.
	 */
	public static AFD AfdToAfdMinimo(final AFD afd) {

		Particion particion = new Particion(generarParticionInical(afd));

		Particion nuevaParticion = construirNuevaParticion(particion, afd);

		while (nuevaParticion.getGrupos().size() > particion.getGrupos().size()) {
			particion = nuevaParticion;
			nuevaParticion = construirNuevaParticion(particion, afd);
		}

		final List<Representante> representantes = new ArrayList<Representante>();
		for (final Grupo grupo : nuevaParticion.getGrupos()) {
			final Representante representante = new Representante(grupo
					.getEstados().get(0), grupo.getEstados());
			representantes.add(representante);
		}

		final AFD nuevoAFD = new AFD(buscarRepresentante(
				afd.getEstadoInicial(), representantes).getRepresentante(),
				afd.getAlfabeto());

		for (final Representante representante : representantes) {
			if (!nuevoAFD.getEstadosAFD().contains(
					representante.getRepresentante())) {
				nuevoAFD.addNuevoEstadoAFD(representante.getRepresentante());
			}
		}
		for (final Representante representante : representantes) {
			final FilaAFD fila = afd.getTablaTransicion().getFila(
					representante.getRepresentante());
			for (final Simbolo simbolo : afd.getAlfabeto().getSimbolos()) {
				if (afd.getTablaTransicion().getColumna(fila, simbolo) == null) {

				} else {
					nuevoAFD.DTran(
							representante.getRepresentante(),
							simbolo,
							buscarRepresentante(
									afd.getTablaTransicion()
											.getColumna(fila, simbolo)
											.getEstadoSiguiente(),
									representantes).getRepresentante());
				}

			}

		}

		final List<EstadoAFD> nuevosEstadosFinales = new ArrayList<EstadoAFD>();
		for (final EstadoAFD estadoFinal : afd.getEstadosFinales()) {
			final EstadoAFD nuevoEstadoFinal = buscarRepresentante(estadoFinal,
					representantes).getRepresentante();
			if (!nuevosEstadosFinales.contains(nuevoEstadoFinal)) {
				nuevosEstadosFinales.add(nuevoEstadoFinal);
			}

		}
		nuevoAFD.setEstadosFinales(nuevosEstadosFinales);

		return nuevoAFD;
	}

	private static Representante buscarRepresentante(final EstadoAFD estado,
			final List<Representante> representantes) {
		// Busca el representante del grupo del cual forma parte del estado
		// pasado como parámetro.
		for (final Representante representante : representantes) {
			if (representante.getEstados().contains(estado)) {
				return representante;

			}
		}
		return null;
	}

	private static List<Grupo> generarParticionInical(final AFD afd) {
		// La partición inicial tiene dos grupos: el grupo de estados de
		// aceptación y el grupo de estados de no aceptación.

		final List<Grupo> grupos = new ArrayList<Grupo>();
		grupos.add(new Grupo(afd.getEstadosFinales())); // estados de aceptacion
		final List<EstadoAFD> estadosNoAceptacion = new ArrayList<EstadoAFD>();
		for (final EstadoAFD estado : afd.getEstadosAFD()) {
			if (!grupos.get(0).getEstados().contains(estado)) {
				estadosNoAceptacion.add(estado);
			}
		}
		grupos.add(new Grupo(estadosNoAceptacion));

		return grupos;
	}

	private static Particion construirNuevaParticion(final Particion particion,
			final AFD afd) {

		/*
		 * Particiona la particion G en subgrupos, de forma que dos estados s y
		 * t se encuentren en el mismo subgrupo, si y sólo si para todos los
		 * simbolos de entrada a, los estados s y t tienen transiciones sobre a
		 * hacia estados en el mimo grupo de G.
		 */

		final Particion particionNueva = Particion.copy(particion);
		for (final Grupo G : particion.getGrupos()) {
			particionNueva.getGrupos().remove(G);
			final List<Grupo> nuevosGrupos = particionarGrupo(G, afd,
					particion.getGrupos());
			for (final Grupo grupoNuevo : nuevosGrupos) {
				particionNueva.getGrupos().add(grupoNuevo);
			}
		}

		return particionNueva;
	}

	private static List<Grupo> particionarGrupo(final Grupo grupo,
			final AFD afd, final List<Grupo> gruposParticionActual) {

		// Particiona un grupo en subgrupos.

		List<Grupo> gruposNuevos = new ArrayList<Grupo>();
		gruposNuevos.add(new Grupo(grupo.getEstados()));

		if (grupo.getEstados().size() == 1) {
			// No se puede mas dividir el grupo
			return gruposNuevos;
		} else {
			final List<SubGrupo> subgrupos = new ArrayList<SubGrupo>();
			for (final EstadoAFD estado : grupo.getEstados()) {
				final SubGrupo subgrupo = new SubGrupo();
				subgrupo.setEstado(estado);
				final FilaAFD fila = afd.getTablaTransicion().getFila(estado);
				for (final Simbolo simbolo : afd.getAlfabeto().getSimbolos()) {
					final ColumnaAFD columna = afd.getTablaTransicion()
							.getColumna(fila, simbolo);
					if (columna == null) {
						continue;
					}
					final EstadoAFD estadoSiguiente = columna
							.getEstadoSiguiente();
					for (final Grupo grupoExistente : gruposParticionActual) {
						if (grupoExistente.getEstados().contains(
								estadoSiguiente)) {
							if (!subgrupo.getGrupos().contains(grupoExistente)) {
								subgrupo.addGrupo(grupoExistente);
							}

						}
					}
				}
				subgrupos.add(subgrupo);

			}

			// separa el grupo en un conjunto de grupos.
			final List<NuevoGrupo> nuevosGrupos = new ArrayList<NuevoGrupo>();
			for (final SubGrupo temp : subgrupos) {
				boolean flag = true;
				for (final NuevoGrupo temp2 : nuevosGrupos) {
					if (temp2.equals(new NuevoGrupo(temp.getGrupos()))) {
						temp2.addEstado(temp.getEstado());
						flag = false;
					}
				}
				if (flag) {
					final NuevoGrupo nuevoGrupo = new NuevoGrupo(
							temp.getGrupos());
					nuevoGrupo.addEstado(temp.getEstado());
					nuevosGrupos.add(nuevoGrupo);
				}
			}
			gruposNuevos = new ArrayList<Grupo>();
			for (final NuevoGrupo tmp : nuevosGrupos) {
				gruposNuevos.add(new Grupo(tmp.getEstados()));
			}
			return gruposNuevos;
		}

	}

}
