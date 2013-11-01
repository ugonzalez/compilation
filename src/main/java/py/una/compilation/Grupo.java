package py.una.compilation;

import java.util.List;

/**
 * Un grupo es un conjunto de estados AFD que todavía no se pueden diferenciar.
 * 
 * @author Uriel González
 * 
 */
public class Grupo {

	private final List<EstadoAFD> estados;

	/**
	 * Construye un grupo especificando sus estados AFD.
	 * 
	 * @param estados
	 *            Estados AFD que forman parte del grupo.
	 */
	public Grupo(final List<EstadoAFD> estados) {
		this.estados = estados;
	}

	/**
	 * Recupera los estados que forman parte del grupo.
	 * 
	 * @return Estados que forman parte del grupo.
	 */
	public List<EstadoAFD> getEstados() {
		return this.estados;
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
		final Grupo other = (Grupo) obj;
		if (this.estados == null) {
			if (other.estados != null) {
				return false;
			}
		} else {
			if (this.estados.size() != other.getEstados().size()) {
				return false;
			}
			for (final EstadoAFD estado : this.estados) {
				if (!other.getEstados().contains(estado)) {
					return false;
				}
			}
		}

		return true;
	}
}
