package py.una.compilation;

import java.util.List;

/**
 * Clase que representa un estado de un AFD.
 * 
 * @author Uriel González
 * 
 */
public class EstadoAFD extends Estado {

	private boolean marcado;

	private final List<EstadoAFN> estadosAFN;

	/**
	 * Construye un estado de un AFD, especificando su etiqueta y el conjunto de
	 * estados AFN al cual el nuevo estado AFD representa.
	 * 
	 * @param etiqueta
	 *            Etiqueta del estado del AFD.
	 * @param estadosAFN
	 *            Conjunto de estados AFN al cual representa el nuevo estado
	 *            AFD.
	 */
	public EstadoAFD(final String etiqueta, final List<EstadoAFN> estadosAFN) {
		super(etiqueta);
		this.estadosAFN = estadosAFN;
		this.marcado = false;
	}

	/**
	 * Permite saber si el estado AFD ha sido marcado.
	 * 
	 * @return true en caso de que el estado este sin marcar, o false en caso
	 *         contrario.
	 */
	public boolean sinMarcar() {
		return !this.marcado;
	}

	/**
	 * Cambio el estado AFD a marcado.
	 */
	public void marcarEstado() {
		this.marcado = true;
	}

	/**
	 * Recupera el conjunto de estados AFN al cual representa el estado AFD.
	 * 
	 * @return Conjunto de estados AFN al cual representa el estado aFD.
	 */
	public List<EstadoAFN> getEstadosAFN() {
		return this.estadosAFN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.getEtiqueta() == null) ? 0 : this.getEtiqueta()
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		// TODO para ser iguales tambien deben tener el mismo conjunto de
		// estados AFN.
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final EstadoAFD other = (EstadoAFD) obj;
		if (this.getEtiqueta() == null) {
			if (other.getEtiqueta() != null) {
				return false;
			}
		} else if (!this.getEtiqueta().equals(other.getEtiqueta())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getEtiqueta();
	}

}
