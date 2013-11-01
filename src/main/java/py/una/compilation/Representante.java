package py.una.compilation;

import java.util.List;

/**
 * Un representante es un estado del AFD mínimo.
 * 
 * @author Uriel González
 * 
 */
public class Representante {

	private final EstadoAFD representante;

	private final List<EstadoAFD> estados;

	/**
	 * Construye un representante.
	 * 
	 * @param representante
	 *            Estado AFD que forma parte del AFD mínimo.
	 * @param estados
	 *            Estados del grupo del cual forma parte el representante.
	 */
	public Representante(final EstadoAFD representante,
			final List<EstadoAFD> estados) {
		this.representante = representante;
		this.estados = estados;

	}

	/**
	 * Recupera el representante.
	 * 
	 * @return Estado del AFD.
	 */
	public EstadoAFD getRepresentante() {
		return this.representante;
	}

	/**
	 * Recupera los estados del gruo del cual forma parte el representante.
	 * 
	 * @return Estados del AFD del a los cuales representa el representante.
	 */
	public List<EstadoAFD> getEstados() {
		return this.estados;
	}

}
