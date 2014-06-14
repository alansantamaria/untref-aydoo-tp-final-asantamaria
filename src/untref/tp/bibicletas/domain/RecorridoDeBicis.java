package untref.tp.bibicletas.domain;

import java.util.Date;

public class RecorridoDeBicis {
	private Usuario usuario;
	private Date fechaOrigen;
	private Date fechaDestino;
	private Estacion estacionOrigen;
	private Estacion estacionDestino;
	
	public RecorridoDeBicis(Usuario usuario, Date fechaOrigen,
			Date fechaDestino, Estacion estacionOrigen, Estacion estacionDestino) {
		this.usuario = usuario;
		this.fechaOrigen = fechaOrigen;
		this.fechaDestino = fechaDestino;
		this.estacionOrigen = estacionOrigen;
		this.estacionDestino = estacionDestino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaOrigen() {
		return fechaOrigen;
	}

	public void setFechaOrigen(Date fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public Date getFechaDestino() {
		return fechaDestino;
	}

	public void setFechaDestino(Date fechaDestino) {
		this.fechaDestino = fechaDestino;
	}

	public Estacion getEstacionOrigen() {
		return estacionOrigen;
	}

	public void setEstacionOrigen(Estacion estacionOrigen) {
		this.estacionOrigen = estacionOrigen;
	}

	public Estacion getEstacionDestino() {
		return estacionDestino;
	}

	public void setEstacionDestino(Estacion estacionDestino) {
		this.estacionDestino = estacionDestino;
	}
	
}
