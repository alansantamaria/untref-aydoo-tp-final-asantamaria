package untref.tp.bicicletas;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import untref.tp.bicicletas.main.ProcesadorEstadisticoBicicletas;

public class ProcesadorEstadisticoBicicletasTest {
		
	@Test
	public void processDirectoryTestDebeGenerarUnArchivoYml() throws Exception {
		ProcesadorEstadisticoBicicletas procesador = new ProcesadorEstadisticoBicicletas();
		procesador.processDirectory("test/untref/tp/bicicletas/files/testProcessDirectory/");
		
		File dir =  new File("test/untref/tp/bicicletas/files/testProcessDirectory/");
		File[] files = dir.listFiles();
		int ymlFileCnt = 0;
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".yml")) {
				ymlFileCnt++;
			}
		}
		
		Assert.assertEquals(1, ymlFileCnt);
	}
}
