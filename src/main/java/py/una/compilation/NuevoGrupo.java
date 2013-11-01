package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Estructura utilizada para particionar un grupo en subgrupos.
 * 
 * @author Uriel González
 * 
 */
public class NuevoGrupo {

	private List<Grupo> grupos;

	private List<EstadoAFD> estados;

	/**
	 * Construye un nuevo NuevoGrupo.
	 * 
	 * @param grupos
	 *            Grupos a los que puede ir el nuevo grupo dada una transición.
	 */
	public NuevoGrupo(final List<Grupo> grupos) {
		this.grupos = grupos;
		this.estados = new ArrayList<EstadoAFD>();
	}

	/**
	 * Recupera los grupos del NuevoGrupo.
	 * 
	 * @return Grupos a los que puede ir los estados del NuevoGrupo dada una
	 *         transición.
	 */
	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	/**
	 * Setea los grupos del NuevoGrupo.
	 * 
	 * @param grupos
	 *            Grupos a los que pueda ir los estados del NuevoGrupo dada una
	 *            transición.
	 */
	public void setGrupos(final List<Grupo> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Recupera los estados del NuevoGrupo.
	 * 
	 * @return Los estados del NuevoGrupo.
	 */
	public List<EstadoAFD> getEstados() {
		return this.estados;
	}

	/**
	 * Setea los estados del NuevoGrupo.
	 * 
	 * @param estados
	 *            Nuevo conjunto de estados del NuevoGrupo.
	 */
	public void setEstados(final List<EstadoAFD> estados) {
		this.estados = estados;
	}

	/**
	 * Agrega un nuevo estado al NuevoGrupo.
	 * 
	 * @param estado
	 *            Nuevoe estado.
	 */
	public void addEstado(final EstadoAFD estado) {
		this.estados.add(estado);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final NuevoGrupo other = (NuevoGrupo) obj;
		if (this.grupos == null) {
			if (other.grupos != null) {
				return false;
			}
		} else {
			if (other.getGrupos().size() != this.grupos.size()) {
				return false;
			}
			for (final Grupo grupo : other.getGrupos()) {
				if (!this.grupos.contains(grupo)) {
					return false;
				}
			}
		}
		return true;
	}

}
