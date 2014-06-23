package untref.tp.bicicletas.main;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import untref.tp.bicicletas.domain.Salida;
import untref.tp.bicicletas.services.ReccorridoBicisFileParser;
import untref.tp.bicicletas.services.YAMLFileWritter;

public class ProcesadorEstadisticoBicicletas {

	private static String directoryToControl = "test/untref/tp/bicicletas/files/";
	private static String directoryOfProcessedFiles =  "test/untref/tp/bicicletas/files/processedFiles/";
	private static int numberOfFiles = 0;

	public static void main (String [ ] args) {

		File files =  new File(directoryToControl);
		numberOfFiles = files.listFiles().length;
		Timer directoryMonitor = new Timer();
		TimerTask processOnDirectoryChange = new TimerTask() {

			@Override
			public void run() {
				File dir =  new File(directoryToControl);
				File[] files = dir.listFiles();
				if (files.length != numberOfFiles) {
					System.out.println("Procesando directorio " + directoryToControl);
					for (int i = 0; i < files.length; i++) {
						if (files[i].getName().contains(".zip")) {
							try {
								processZip(files[i]);
								files[i].renameTo(new File("test/untref/tp/bicicletas/files/processedFiles/" + files[i].getName()));
								numberOfFiles = dir.listFiles().length;

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};


		directoryMonitor.schedule(processOnDirectoryChange, 0, 1000);

	}

	public void processDirectory(String path) throws IOException {
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

	public void changeControlDirectory (String dir) { 
		this.directoryToControl = dir;
	}

	private static void processZip(File file) throws IOException {
		ReccorridoBicisFileParser parser = new ReccorridoBicisFileParser();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(file.getPath()));
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
				System.out.println(cadaContenidoDelZip.getName() + " procesado.");
				fos.close();
			}
			zip.closeEntry();
		}
		zip.close();
		Salida salida = new Salida(parser.getBicicletaMasUsada(), parser.getBicicletaMenosUsada(), parser.getRecorridoMasVecesRealizado(), parser.getPromedioDeUso());
		YAMLFileWritter.writeYAML(directoryToControl, file.getName().replace(".zip", ".yml"), salida);
	}

}
