package untref.tp.bicicletas;

import java.io.IOException;

import org.junit.Test;

import untref.tp.bicicletas.main.ProcesadorEstadisticoBicicletas;

public class ProcesadorEstadisticoBicicletasTest {
	@Test
	public void processDirectoryTest() throws IOException {
		ProcesadorEstadisticoBicicletas procesador = new ProcesadorEstadisticoBicicletas();
		procesador.processDirectory("test/untref/tp/bicicletas/files/");
	}
}
