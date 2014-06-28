package untref.tp.bicicletas.main;

public class Main {

	public static void main(String[] args) throws Exception {
		
		if (args.length == 0) {
			System.out.println("Modo de uso: java -jar procesadorEstadistico.jar [directorio] [--demand  | --daemon]");
			return;
		}else if (args[1].equals("--demand")) {
			ProcesadorEstadisticoBicicletas procesador = new ProcesadorEstadisticoBicicletas();
			procesador.processDirectory(args[0]);
		}else if (args[1].equals("--daemon")) {
			ProcesadorEstadisticoBicicletas procesador = new ProcesadorEstadisticoBicicletas();
			procesador.startDeamon(args[0]);
		}else {
			System.out.println("Modo de uso: java -jar procesadorEstadistico.jar [directorio] [--demand  | --daemon]");
		}
	}

}
