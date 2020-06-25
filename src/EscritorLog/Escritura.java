package EscritorLog;
import java.io.FileWriter;
import java.io.PrintWriter;


/**
 * Clase escritura, su funcionalidad consiste en escribir todo en el archivo mylog.log.
 *
 * @version 1.0
 * @author 
 * <b> Grupo "Los Topos v1.3.7a" </b><br>
 * Compuesto por:<br/>
 * Santiago Rangel Colon<br/>
 * Samuel Moreno Vincent<br/>
 * 
 * Asignatura Desarrollo de Programas<br/>
 * Numero de entrega: EC2
 * Curso 12/13
 */
public class Escritura {

    FileWriter fichero = null;
    PrintWriter pw = null;
	public Escritura(){
        try{
            //fichero = new FileWriter("C:\\Users\\Xamel\\Dropbox\\workspace\\DP\\Proyecto\\mylog.log");
            fichero = new FileWriter("registro.log");
            pw = new PrintWriter(fichero);
        }
        catch (Exception e){
        	System.err.println("ERROR: Al crear el fichero de la clase classEscritura");
        }
	}
        public void cerrarFichero(){
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                   fichero.close();
                } catch (Exception e2) {
                   e2.printStackTrace();
                }
        }
        public PrintWriter getPW(){
        	return pw;
        }
}