package untref.tp.bicicletas.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import untref.tp.bicicletas.domain.Recorrido;
import untref.tp.bicicletas.domain.Salida;

public class YAMLFileWritter {

	public static void writeYAML(String dir, String name, Salida salida) {
		File file = new File(dir + name);
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println("--- !Procesamiento del directorio: " + dir);
			
			pw.println("Bicicletas Mas Usadas:");
			Iterator<String> iteradorDeBicis = salida.getBicicletasMasUsadas().iterator();
			while (iteradorDeBicis.hasNext()) {
				String bicicletaId = iteradorDeBicis.next();
				pw.println(" id: " + bicicletaId);
			}
			
			pw.println("Bicicletas Menos Usadas:");
			iteradorDeBicis = salida.getBicicletasMenosUsadas().iterator();
			while (iteradorDeBicis.hasNext()) {
				String bicicletaId = iteradorDeBicis.next();
				pw.println(" id: " + bicicletaId);
			}
			
			pw.println("Recorrido Mas Realizado:");
			Iterator<Recorrido> iteradorDeRecorridos = salida.getRecorridoMasRealizado().iterator();
			while (iteradorDeRecorridos.hasNext()) {
				Recorrido recorrido = iteradorDeRecorridos.next();
				pw.println(" Origen id: " +  recorrido.getIdEstacionOrigen());
				pw.println(" Destino id: " +  recorrido.getIdEstacionDestino());
			}
			
			pw.println("Promedio de uso de las bicicletas: " + salida.getPromedioDeUso()); 
			
			pw.close();
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		};
	}
}
