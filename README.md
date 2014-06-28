Trabajo practico final

Procesador Estadistico de Bicicletas

para correr la aplicacion se debe ejecutar el siguiente comando:

java -jar buildprocesadorEstadistico.jar [directorio] [--deamon | --demand]

Ej: java -jar buildprocesadorEstadistico.jar C:\untref-aydoo-tp-final-asantamaria\test\untref\tp\bicicletas\files\processedFiles\ --demand

java -jar buildprocesadorEstadistico.jar C:\untref-aydoo-tp-final-asantamaria\test\untref\tp\bicicletas\files\processedFiles\ --daemon
(notese que la ruta debe terminar con /)
Si se ejecuta en modo deamon se puede probar colocando el o los archivos .zip en el directorio ingresado como parametro. Una vez procesado, cada zip se copia en una nueva carpeta llamada processedFiles.