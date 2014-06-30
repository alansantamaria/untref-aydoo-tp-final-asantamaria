package untref.tp.bicicletas.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import untref.tp.bicicletas.domain.Salida;
import untref.tp.bicicletas.services.DirectoryMonitorTimerTask;
import untref.tp.bicicletas.services.ReccorridoBicisFileParser;
import untref.tp.bicicletas.services.YAMLFileWritter;

public class ProcesadorEstadisticoBicicletas {


	public void startDeamon(String path) {
		Timer directoryMonitor = new Timer();
		directoryMonitor.schedule(new DirectoryMonitorTimerTask(path), 0, 1000);
	}

	public void processDirectory(String path) throws Exception {
		ReccorridoBicisFileParser parser = new ReccorridoBicisFileParser();
		File  directory = new File(path);
		if (directory.exists()){
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".zip")) {
					ZipInputStream zip = new ZipInputStream(new FileInputStream(files[i].getPath()));
					ZipEntry cadaContenidoDelZip;
					while (null != (cadaContenidoDelZip = zip.getNextEntry()) ){
						if (cadaContenidoDelZip.getName().contains(".csv")) {
							FileOutputStream fos = new FileOutputStream(cadaContenidoDelZip.getName());
							int readed;
							byte [] buffer = new byte[1024];
							while (0 < (readed = zip.read(buffer))){
								fos.write(buffer,0, readed);
							}
							parser.parseFile(new BufferedReader(new InputStreamReader(new FileInputStream(cadaContenidoDelZip.getName()))));
							fos.close();
							File f = new File(cadaContenidoDelZip.getName());
							System.out.println(f.delete());
						}
						zip.closeEntry();
					}
					zip.close();
				}
			}
		}
		Salida salida = new Salida(parser.getBicicletaMasUsada(), parser.getBicicletaMenosUsada(), parser.getRecorridoMasVecesRealizado(), parser.getPromedioDeUso());
		YAMLFileWritter.writeYAML(path, "salida_unica.yml", salida);
	}

}
