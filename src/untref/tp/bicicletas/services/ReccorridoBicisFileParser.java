package untref.tp.bicicletas.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import untref.tp.bibicletas.domain.Recorrido;

public class ReccorridoBicisFileParser {

	private Map<String, Integer> bicicletas = new HashMap<String, Integer>();
	private Map<Recorrido, Integer> recorridos = new HashMap<Recorrido, Integer>();
	private double promedioDeUso = 0.0;
	private int horasDeUso = 0;
	private int cantidadDeRegistros = 0;


	public void parseFile (BufferedReader br) {
		boolean firstLineReaded = false;
		try {
			while (br.ready()) {

				if (firstLineReaded) {					
					processLine(br.readLine());
				}else{
					br.readLine();
					firstLineReaded = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processLine(String readLine) {
		String[] lineInfo = readLine.split(";");

		Recorrido recorrido = new Recorrido(lineInfo[3], lineInfo[6]);

		if (bicicletas.containsKey(lineInfo[1])) {
			bicicletas.replace(lineInfo[1], bicicletas.get(lineInfo[1]) + 1);
		}else{
			bicicletas.put(lineInfo[1], 1);
		}

		if (recorridos.containsKey(recorrido)) {
			recorridos.replace(recorrido, recorridos.get(recorrido) + 1);
		}else{
			recorridos.put(recorrido, 1);
		}

		horasDeUso += Integer.parseInt(lineInfo[8]);
		cantidadDeRegistros++;
		promedioDeUso = horasDeUso / cantidadDeRegistros;

	}

	public Map<String, Integer> getBicicletas() {
		return bicicletas;
	}

	public Map<Recorrido, Integer> getRecorridos() {
		return recorridos;
	}

	public double getPromedioDeUso() {
		return promedioDeUso;
	}

	public List<String> getBicicletaMasUsada() {
		List<String> bicicletasMasUsadas = new LinkedList<String>();
		int cantidadDeVecesUtilizada = 0;
		Iterator<String> iterador = bicicletas.keySet().iterator();
		while (iterador.hasNext()) {
			String cadaBicicleta = (String) iterador.next();
			int usosDeCadaBicileta = bicicletas.get(cadaBicicleta);
			if (usosDeCadaBicileta > cantidadDeVecesUtilizada) {
				bicicletasMasUsadas.clear();
				bicicletasMasUsadas.add(cadaBicicleta);
				cantidadDeVecesUtilizada = usosDeCadaBicileta;
			}else if (usosDeCadaBicileta == cantidadDeVecesUtilizada) {
				bicicletasMasUsadas.add(cadaBicicleta);
			}
		}
		return bicicletasMasUsadas;
	}

	public List<String> getBicicletaMenosUsada() {
		List<String> bicicletasMenosUsadas = new LinkedList<String>();

		if (!bicicletas.isEmpty()) {
			Iterator<String> iterador = bicicletas.keySet().iterator();
			String cadaBicicleta = (String) iterador.next();
			int usosDeCadaBicileta = bicicletas.get(cadaBicicleta);
			int cantidadDeVecesUtilizada = usosDeCadaBicileta;
			while (iterador.hasNext()) {
				cadaBicicleta = (String) iterador.next();
				usosDeCadaBicileta = bicicletas.get(cadaBicicleta);
				if (usosDeCadaBicileta < cantidadDeVecesUtilizada) {
					bicicletasMenosUsadas.clear();
					bicicletasMenosUsadas.add(cadaBicicleta);
					cantidadDeVecesUtilizada = usosDeCadaBicileta;
				}else if (usosDeCadaBicileta == cantidadDeVecesUtilizada) {
					bicicletasMenosUsadas.add(cadaBicicleta);
				}
			}
		}
		return bicicletasMenosUsadas;
	}
	
	public List<Recorrido> getRecorridoMasVecesRealizado() {
		List<Recorrido> recorridoMasVecesRealizado = new LinkedList<Recorrido>();
		int cantidadDeVecesRealizado = 0;
		Iterator<Recorrido> iterador = recorridos.keySet().iterator();
		while (iterador.hasNext()) {
			Recorrido cadaRecorrido = (Recorrido) iterador.next();
			int cantidadDeVecesDeCadaRecorrido = recorridos.get(cadaRecorrido);
			if (cantidadDeVecesDeCadaRecorrido > cantidadDeVecesRealizado) {
				recorridoMasVecesRealizado.clear();
				recorridoMasVecesRealizado.add(cadaRecorrido);
				cantidadDeVecesRealizado = cantidadDeVecesDeCadaRecorrido;
			}else if (cantidadDeVecesDeCadaRecorrido == cantidadDeVecesRealizado) {
				recorridoMasVecesRealizado.add(cadaRecorrido);
			}
		}
		return recorridoMasVecesRealizado;
	}
}
