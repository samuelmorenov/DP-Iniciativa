package Cargadores;
import Lugares.Planta;
import Lugares.Singleton;
import Personaje.Intruso;
import Personaje.Lider;
import Personaje.Trabajador;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las lineas y se van creando todas las instancias de la simulacion
 * 
 * @version 1.1
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
public class Cargador {
	/**  
	numero de elementos distintos que tendra la simulacion - Planta, Intruso, Trabajador y Lider
	*/
	static final int NUMELTOSCONF  = 4;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	
	/**
	 *  constructor parametrizado 
	 *  @param e referencia a la instancia del patron Singleton
	 */
	Cargador()  {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("PLANTA", 7);
		mapeo[1]= new DatoMapeo("LIDER", 4);
		mapeo[2]= new DatoMapeo("INTRUSO", 4);
		mapeo[3]= new DatoMapeo("TRABAJADOR", 4);
	}
	
	/**
	 *  busca en mapeo el elemento leido del fichero inicio.txt y devuelve la posicion en la que esta 
	 *  @param elto elemento a buscar en el array
	 *  @return res posicion en mapeo de dicho elemento
	 */
	private int queElemento(String elto)  {
	    int res=-1;
	    boolean enc=false;

	    for (int i=0; (i < NUMELTOSCONF && !enc); i++)  {
	        if (mapeo[i].getNombre().equals(elto))      {
	            res=i;
	            enc=true;
	        }
	    }
	    return res;
	}
	
	/**
	 *  metodo que crea las distintas instancias de la simulacion 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 */
	public void crear(String elto, int numCampos, String vCampos[])	{
	    //Si existe elemento y el numero de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);

	    //Comprobacion de datos basicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	        //procesar
	        switch(queElemento(elto))
	        {
	        case 0:	   
	            crearPlanta(numCampos,vCampos);
	            break;
	        case 1:
	            crearLider(numCampos,vCampos);
	            break;
	        case 2:
	            crearIntruso(numCampos,vCampos);
	            break;
	        case 3:
	            crearTrabajador(numCampos,vCampos);
	            break;
	     	}
	    }
	    else
	        System.err.println("ERROR Cargador::crear: Datos de configuracion incorrectos... " + elto + "," + numCampos+"\n");
	}

	/**
	 *  metodo que crea una instancia de la clase Planta
	 *  Planta con identificador 0, con 10 salas de ancho y 5 de alto, cuya entrada está en la
	 *  sala 3 y salida en la 49. La altura de control del ABB de la cerradura es 4 (condición
	 *  de apertura).
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearPlanta(int numCampos, String vCampos[])
	{
	    //System.out.println("Creada planta: " + vCampos[1] + "\n");
	    int id = Integer.parseInt(vCampos[1]);
	    int ancho = Integer.parseInt(vCampos[2]);
	    int alto = Integer.parseInt(vCampos[3]);
	    int inicio = Integer.parseInt(vCampos[4]);
	    int salida = Integer.parseInt(vCampos[5]);
	    int alturaABBApertura = Integer.parseInt(vCampos[6]);
	    new Planta(inicio,salida,alto,ancho,id, alturaABBApertura);
	}
	/**
	 *  metodo que crea una instancia de la clase Lider
	 *  LIDER#jack_shepard#J#1#
		Líder cuyo nombre es “jack_shepard”, cuya marca es 'J' y que comienza a moverse en
		el turno 1 de la simulación.
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearLider(int numCampos, String vCampos[])
	{
		Planta planta= Singleton.GetPlanta();
	    //System.out.println("Creado lider: " + vCampos[1] + "\n");
	    String nombre = vCampos[1];
	    char marca = vCampos[2].charAt(0);
	    int turno = Integer.parseInt(vCampos[3]);
	    Lider P = new Lider(nombre, marca, turno);
	    planta.insertarPersona(P);
	}
	/**
	 *  metodo que crea una instancia de la clase Intruso
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearIntruso(int numCampos, String vCampos[])
	{
		Planta planta= Singleton.GetPlanta();
//	    System.out.println("Creado lider: " + vCampos[1] + "\n");
	    String nombre = vCampos[1];
	    char marca = vCampos[2].charAt(0);
	    int turno = Integer.parseInt(vCampos[3]);
	    Intruso P = new Intruso(nombre, marca, turno);
	    planta.insertarPersona(P);
	}	
	/**
	 *  metodo que crea una instancia de la clase Trabajador
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearTrabajador(int numCampos, String vCampos[])
	{
		Planta planta= Singleton.GetPlanta();
//	    System.out.println("Creado lider: " + vCampos[1] + "\n");
	    String nombre = vCampos[1];
	    char marca = vCampos[2].charAt(0);
	    int turno = Integer.parseInt(vCampos[3]);
	    Trabajador P = new Trabajador(nombre, marca, turno);
	    planta.insertarPersona(P);
	}	
}
