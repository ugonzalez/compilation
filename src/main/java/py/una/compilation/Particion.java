package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Una particion es una lista de grupos. Donde cada grupo es un conjunto de
 * estados AFD que no se han diferenciado todavia.
 * 
 * @author Uriel González
 * 
 */
public class Particion {

	private final List<Grupo> grupos;

	/**
	 * Construye una particion especificando su lista de grupos.
	 * 
	 * @param grupos
	 *            Conjunto de grupos.
	 */
	public Particion(final List<Grupo> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Copia una particion.
	 * 
	 * @param particion
	 *            Particion a ser copiada.
	 * @return Copia de la particion.
	 */
	public static Particion copy(final Particion particion) {

		final List<Grupo> gruposCopy = new ArrayList<Grupo>();
		for (final Grupo grupo : particion.getGrupos()) {
			final List<EstadoAFD> estadosCopy = new ArrayList<EstadoAFD>();
			for (final EstadoAFD estado : grupo.getEstados()) {
				estadosCopy.add(estado);
			}
			final Grupo grupoCopy = new Grupo(estadosCopy);
			gruposCopy.add(grupoCopy);
		}

		return new Particion(gruposCopy);
	}

	/**
	 * Retorna los grupos de la partición.
	 * 
	 * @return Grupos de la partición.
	 */
	public List<Grupo> getGrupos() {
		return this.grupos;
	}

}
