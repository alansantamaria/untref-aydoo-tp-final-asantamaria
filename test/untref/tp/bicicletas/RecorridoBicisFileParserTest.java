package untref.tp.bicicletas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import untref.tp.bibicletas.domain.Recorrido;
import untref.tp.bicicletas.services.ReccorridoBicisFileParser;

public class RecorridoBicisFileParserTest {
	InputStream is = null; 
	InputStreamReader isr = null;
	BufferedReader br = null;
	ReccorridoBicisFileParser parser;
	
	@Before
	public void prepararParser() {
		try{
			is = new FileInputStream("recorrido-bicis-2010 - Copy.csv");
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			parser = new ReccorridoBicisFileParser();
			parser.parseFile(br);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void getBicicletasDebeDevolverUnMapaDe17ParaElArchivoCSV2010() {
			Map<String, Integer> bicicletas = parser.getBicicletas();
			Iterator<String> iterador = bicicletas.keySet().iterator();
			while (iterador.hasNext()) {
				String cadaBici = (String) iterador.next();
				System.out.println(cadaBici + " - " + bicicletas.get(cadaBici));
			}
			Assert.assertEquals(17, parser.getBicicletas().size());
		
	}
	
	@Test
	public void getRecorridosDebeDevolverUnMapaDe10ParaElArchivoCSV2010() {
			Map<Recorrido, Integer> recorridos = parser.getRecorridos();
			Iterator<Recorrido> iterador = recorridos.keySet().iterator();
			while (iterador.hasNext()) {
				Recorrido cadaRecorrido = (Recorrido) iterador.next();
				System.out.println(cadaRecorrido + " - " + recorridos.get(cadaRecorrido));
			}
			Assert.assertEquals(10, parser.getRecorridos().size());

	}
	
	@Test
	public void getPromedioDeUsoDebeDevolver22ParaElArchivoCSV2010() {	
			System.out.println("promedio de uso: " + parser.getPromedioDeUso());
			Assert.assertEquals(22.0, parser.getPromedioDeUso());

	}
	
	@Test
	public void getBicicletaMasUsadaDebeDevolver2ParaElArchivoCSV2010() {
			System.out.println("bicicletas mas usadas: " + parser.getBicicletaMasUsada().size());
			Assert.assertEquals(2, parser.getBicicletaMasUsada().size());
	}
	
	//@Test
	public void getBicicletaMenosUsadaDebeDevolver15ParaElArchivoCSV2010() {	
			System.out.println("bicicletas menos usadas: " + parser.getBicicletaMenosUsada().size());
			Assert.assertEquals(15, parser.getBicicletaMenosUsada().size());

	}
	
	@Test
	public void getRecorridoMasVecesRealizadoDebeDevolver1ParaElArchivoCSV2010() {
			System.out.println("recorrido mas realizados: " + parser.getRecorridoMasVecesRealizado().size());
			Assert.assertEquals(1, parser.getRecorridoMasVecesRealizado().size());
			
	}
}
