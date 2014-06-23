package untref.tp.bicicletas.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import untref.tp.bicicletas.domain.Recorrido;

public class ReccorridoBicisFileParser {

	private Map<String, Integer> bicicletas = new HashMap<String, Integer>();
	private Map<Recorrido, Integer> recorridos = new HashMap<Recorrido, Integer>();
	private double promedioDeUso = 0.0;
	private int horasDeUso = 0;
	private int cantidadDeRegistros = 0;


	public void parseFile (BufferedReader br) {
		boolean firstLineReaded = false;
		int cantidadDeLineas = 2;
		try {
			while (br.ready()) {

				if (firstLineReaded) {					
					try {
						processLine(br.readLine(), cantidadDeLineas);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					cantidadDeLineas++;
				}else{
					br.readLine();
					firstLineReaded = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processLine(String readLine, int line) throws Exception {

		try {
			String[] lineInfo = readLine.split(";");

			String idBicicleta = lineInfo[1];
			String idOrigen = lineInfo[3];
			String idDestino = lineInfo[6];
			String tiempoDeUso = lineInfo[8];

			Recorrido recorrido = new Recorrido(idOrigen, idDestino);

			if (bicicletas.containsKey(idBicicleta)) {
				int repeticiones = bicicletas.get(idBicicleta) + 1;
				bicicletas.remove(idBicicleta);
				bicicletas.put(idBicicleta, repeticiones);
			}else{
				bicicletas.put(idBicicleta, 1);
			}

			if (recorridos.containsKey(recorrido)) {
				int repeticiones = recorridos.get(recorrido) + 1;
				recorridos.remove(recorrido);
				recorridos.put(recorrido, repeticiones);
			}else{
				recorridos.put(recorrido, 1);
			}

			horasDeUso += Integer.parseInt(tiempoDeUso);
			cantidadDeRegistros++;
			promedioDeUso = horasDeUso / cantidadDeRegistros;

		}catch(Exception e) {
			throw new Exception("La linea " + line + " no se puede parsear");
		}


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
			bicicletasMenosUsadas.add(cadaBicicleta);
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
