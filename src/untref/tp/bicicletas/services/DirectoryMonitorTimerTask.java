package untref.tp.bicicletas.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import untref.tp.bicicletas.domain.Salida;

public class DirectoryMonitorTimerTask extends TimerTask{

	private static String directoryToControl;

	public DirectoryMonitorTimerTask(String dir ) {
		directoryToControl = dir;
	}
	
	@Override
	public void run() {
		File dir =  new File(directoryToControl);
		File[] files = dir.listFiles();
		if (countZipFilesFromDirectory() > 0) {
			System.out.println("Procesando directorio " + directoryToControl);
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().contains(".zip")) {
					try {
						processZip(files[i]);
						new File(directoryToControl + "processedFiles/").mkdir();
						files[i].renameTo(new File(directoryToControl + "processedFiles/" + files[i].getName()));

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private static int countZipFilesFromDirectory() {
		File dir =  new File(directoryToControl);
		File[] files = dir.listFiles();
		int zipFileCnt = 0;
		for(int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".zip")) {
				zipFileCnt++;
			}
		}
		return zipFileCnt;
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
				File f = new File(cadaContenidoDelZip.getName());
				f.delete();
			}
			zip.closeEntry();
		}
		zip.close();
		Salida salida = new Salida(parser.getBicicletaMasUsada(), parser.getBicicletaMenosUsada(), parser.getRecorridoMasVecesRealizado(), parser.getPromedioDeUso());
		YAMLFileWritter.writeYAML(directoryToControl, file.getName().replace(".zip", ".yml"), salida);
	}
}
