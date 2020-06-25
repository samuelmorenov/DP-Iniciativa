package Cargadores;

/**
 * Clase creada para ser usada en la utilidad cargador
 * estudiada previamente en sesión práctica "Excepciones"
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
import java.io.*;

public class FicheroCarga {
	/**  
	atributo de la clase que indica el numero máximo de campos que se pueden leer
	*/
	public static final int MAXCAMPOS  = 10;

	/**  
	buffer para almacenar el flujo de entrada
	*/
	private static BufferedReader bufferIn;
	/**
	 * Metodo para procesar el fichero. Sin excepciones
	 * @return codigoError con el error que se ha producido
	 * @throws Exception. Puede lanzar una excepción en la apertura del buffer de lectura
	 */
	 public static void procesarFichero(String nombreFichero, Cargador cargador) throws  FileNotFoundException, IOException {
		 String vCampos[]=new String[MAXCAMPOS];
	     String S=new String();
	     int numCampos=0;

//	     System.out.println( "Procensando el fichero..." );
	     bufferIn = new BufferedReader(new FileReader(nombreFichero));//creación del filtro asociado al flujo de datos

         while((S=bufferIn.readLine())!= null) {
//	     	 System.out.println( "S: "+S );
  	 		 if (!S.startsWith("--"))  {
  	 			 numCampos = trocearLinea(S, vCampos);
  	 			 for (int i = 0; i < vCampos.length; i++) {
					
				}
  	 			 cargador.crear(vCampos[0],numCampos,vCampos);
	 		}
	     }
	     bufferIn.close();	     //cerramos el filtro
	 }

	 /**
	  * Metodo para trocear cada línea y separarla por campos
	  * @param S cadena con la línea completa leída
	  * @param vCampos. Array de String que contiene en cada posición un campo distinto
	  * @return numCampos. Número campos encontrados
	 */
	 private static int trocearLinea(String S,String[] vCampos) {
		 boolean eol = false;
		 int pos=0,posini=0, numCampos=0;

   	     while (!eol)
   	     {
	    			pos = S.indexOf("#");
	     	    	if(pos!=-1) {
	     	    		vCampos[numCampos] = new String(S.substring(posini,pos));
	     	    		S=S.substring(pos+1, S.length());
	     	    		numCampos++;
	     	    	}
	     	    	else eol = true;
		  }
		  return numCampos;
	 }

}
