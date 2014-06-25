package untref.tp.bicicletas;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import untref.tp.bicicletas.services.DirectoryMonitorTimerTask;

public class DirectoryMonitorTimerTaskTest {
	@Test
	public void debeGenerarDosArchivosYMLYMoverDosZipALaCarpetaDeProcesados() throws InterruptedException {
		DirectoryMonitorTimerTask timerTask =  new DirectoryMonitorTimerTask("test/untref/tp/bicicletas/files/");
		timerTask.run();
		
		File dir =  new File("test/untref/tp/bicicletas/files/processedFiles/");
		File[] files = dir.listFiles();
		int zipFileCnt = 0;
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".zip")) {
				zipFileCnt++;
			}
		}
		
		dir =  new File("test/untref/tp/bicicletas/files/");
		files = dir.listFiles();
		int ymlFileCnt = 0;
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".yml")) {
				ymlFileCnt++;
			}
		}
		
		Assert.assertEquals(3, zipFileCnt);
		Assert.assertEquals(3, ymlFileCnt);
		
	}
}
