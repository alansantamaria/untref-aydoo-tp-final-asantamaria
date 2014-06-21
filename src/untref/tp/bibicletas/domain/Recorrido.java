package untref.tp.bibicletas.domain;

public class Recorrido {
	private String idEstacionOrigen;
	private String idEstacionDestino;

	public Recorrido(String lineInfo, String lineInfo2) {
		this.idEstacionOrigen = lineInfo;
		this.idEstacionDestino = lineInfo2;
	}

	public String getIdEstacionOrigen() {
		return idEstacionOrigen;
	}
	public void setIdEstacionOrigen(String idEstacionOrigen) {
		this.idEstacionOrigen = idEstacionOrigen;
	}
	public String getIdEstacionDestino() {
		return idEstacionDestino;
	}
	public void setIdEstacionDestino(String idEstacionDestino) {
		this.idEstacionDestino = idEstacionDestino;
	}

	public int hashCode() {
		int hashCode = ((100 * Integer.parseInt(idEstacionDestino)) + (200 * Integer.parseInt(idEstacionOrigen))) / Integer.parseInt(idEstacionOrigen);
		return hashCode; 

	}

	public boolean equals (Object obj) {
		if (this.idEstacionDestino.equals(((Recorrido) obj).getIdEstacionDestino()) &&
				this.idEstacionOrigen.equals(((Recorrido) obj).idEstacionOrigen)) {
			return true;
		}else{
			return false;
		}
	}

	public String toString() {
		return this.idEstacionOrigen + " " + this.idEstacionDestino;
	}
}
