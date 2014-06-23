package untref.tp.bicicletas.domain;

import java.util.List;

public class Salida {
	public List<String> bicicletasMasUsadas;
	public List<String> bicicletasMenosUsadas;
	public List<Recorrido> recorridoMasRealizado;
	public double promedioDeUso;
	
	public Salida(List<String> bicicletasMasUsadas,
			List<String> bicicletasMenosUsadas,
			List<Recorrido> recorridoMasRealizado, double promedioDeUso) {
		this.bicicletasMasUsadas = bicicletasMasUsadas;
		this.bicicletasMenosUsadas = bicicletasMenosUsadas;
		this.recorridoMasRealizado = recorridoMasRealizado;
		this.promedioDeUso = promedioDeUso;
	}

	public List<String> getBicicletasMasUsadas() {
		return bicicletasMasUsadas;
	}

	public List<String> getBicicletasMenosUsadas() {
		return bicicletasMenosUsadas;
	}

	public List<Recorrido> getRecorridoMasRealizado() {
		return recorridoMasRealizado;
	}

	public double getPromedioDeUso() {
		return promedioDeUso;
	}
	
}
