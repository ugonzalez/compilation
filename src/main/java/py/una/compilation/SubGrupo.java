package py.una.compilation;

import java.util.ArrayList;
import java.util.List;

/**
 * Un subgrupo es el conjunto de grupos de una particion en la que un estado
 * puede ir dada una transición.
 * 
 * @author Uriel González
 * 
 */
public class SubGrupo {

	private EstadoAFD estado;

	private List<Grupo> grupos;

	/**
	 * Construye un subgrupo.
	 */
	public SubGrupo() {
		this.grupos = new ArrayList<Grupo>();
	}

	/**
	 * Recupera el estado del subgrupo.
	 * 
	 * @return Estado del subgrupo.
	 */
	public EstadoAFD getEstado() {
		return this.estado;
	}

	/**
	 * Setea el estado del subgrupo.
	 * 
	 * @param estado
	 *            Nuevo estado del subgrupo.
	 */
	public void setEstado(final EstadoAFD estado) {
		this.estado = estado;
	}

	/**
	 * Recupera los grupos del subgrupo.
	 * 
	 * @return Grupos del subgrupo.
	 */
	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	/**
	 * Setea los grupos del subgrupo.
	 * 
	 * @param grupos
	 *            Grupos del subgrupo.
	 */
	public void setGrupos(final List<Grupo> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Agrega un nuevo grupo al subgrupo.
	 * 
	 * @param grupo
	 *            Nuevo grupo.
	 */
	public void addGrupo(final Grupo grupo) {
		this.grupos.add(grupo);
	}

}
