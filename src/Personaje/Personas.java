package Personaje;

import java.util.LinkedList;
import java.util.Queue;

import Estructuras.Pila;
import Lugares.Planta;
import Lugares.Singleton;
import Objetos.Llaves;


enum movimiento {N,S,E,O};

/**
 * Implementacion de los metodos de la clase Personas.
 *
 * @version 1.2
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
public abstract class Personas {
    /** Identificador tipo char de un Personaje */
	private char Identificador;
	/** Codigo de tipo entero, para identificar la profesion del Personaje <br>
	 * Codigo=0 Trabajador <br>
	 * Codigo=1 Lider <br>
	 * Codigo=2 Intruso.*/
	private int Codigo;
	/**Turno en el que empieza*/
	private int turno;
	/** Numero de la sala donde se encuentra el personaje */
	private int sala;
	/** String que almacena el nombre del Peronaje */
	private String Nombre;
	/** Pila con las llaves que lleva un personaje encima */
	private Pila<Llaves> Llaves=new Pila<Llaves>();
	/** Int que representa la sala en la que se encuentra un personaje */
	private Queue<Integer> cMov=new LinkedList<Integer>();
	/** Pila de llaves proadas */
	private Pila<Llaves> llavesProbadas=new Pila<Llaves>();


	/**Constructor parametrizado de la clase personas
	 * @param nombre nombre del intruso
	 * @param i Codigo de personaje
	 * @param marca identificador
	 * @param turno turno en el que empieza
	 */
	Personas(String nombre, int i, char marca, int turno){
		this.Nombre=nombre;
		this.Codigo=i;
		this.Identificador=marca;
		this.turno=turno;
		crearMovimiento();
	}
	/**
	 * Metodo para poner el Identificador
	 * @param a es el valor que se pondra en Identificador
	 */
	public void setIdentificador(char a){
		this.Identificador=a;
	}
	/**
	 * Metodo para poner el Nombre
	 * @param name es el valor que se pondra en Nombre
	 */
	public void setNombre(String name){
		this.Nombre=name;
	}
	public void setCodigo(int Codigo){
		this.Codigo=Codigo;
	}
	public void setTurno(int turno){
		this.turno=turno;
	}
	public void setCola(Queue<Integer> caminos){
		cMov = caminos;
	}
	public void setSala(int sala_){
		sala = sala_;
	}
	/**
	 * Metodo para obtener el nombre
	 * @return el atributo nombre
	 */
	public String getNombre(){
		return this.Nombre;
	}
	/**
	 * Metodo para obetener el identificador de la persona
	 * @return el atributo Identificador
	 */
	public char getIdentificador(){
		return this.Identificador;
	}
	public int getCodigo(){
		return Codigo;
	}
	
	public int getTurno(){
		return turno;
	}
	public Queue<Integer> getCola(){
		return cMov;
	}
	public int getSala(){
		return sala;
	}
	public boolean sinLlaves(){
		return Llaves.estaVacia();
	}
	/**
	 * Metodo con el cual se almacenara una llave el la pila de llaves del personaje
	 * @param l es la llave que se insertara en la pila
	 */
	public void insertarLlave(Llaves l){
		Llaves=Llaves.insertarDato(l);
	}
	/**
	 * Metodo con el cual obtenemos la llave que se encuentra en la cima de la pila de llaves
	 * @return la llave de la cima de la pila
	 */
	public Llaves ObtenerCima(){
		return Llaves.getDatoCima();
	}
	/**
	 * Metodo con el cual sacamos de las llaves la cima
	 */
	public void DesapilarLlave(){
		Llaves=Llaves.sacarDato();
	}
	/**
	 * Metodo que introduce una llave en el array de llaves
	 * @param llave a meter
	 */
	public void apilarLlavesProbadas(Llaves llave){
		llavesProbadas.insertarDato(llave);
	}
	/**
	 * Metodo que retorna la primera llave probada
	 */
	public void retornarLlavesProbadas(){
		while(!llavesProbadas.estaVacia()){
			Llaves.insertarDato(llavesProbadas.getDatoCima());
			llavesProbadas.sacarDato();
		}
	}
	


	public void mostrarRecorrido(){
		Planta planta = Singleton.GetPlanta();
		int ancho = planta.getAncho();
		Object[] aux = this.getCola().toArray();
		planta.out().print("(ruta:"+Identificador+":");
		for (int i = 0; i < aux.length-1; i++) {
			int actual = (Integer) aux[i];
			int siguiente = (Integer) aux[i+1];
				 if (actual+1 == siguiente) planta.out().print(" E");
			else if (actual-1 == siguiente) planta.out().print(" O");
			else if (actual-ancho == siguiente) planta.out().print(" N");
			else if (actual+ancho == siguiente) planta.out().print(" S");
			else System.err.print("No se ha podido encontrar la ruta");
		}
		planta.out().println(")");
	}
	public void mostrarRecorridoPorPantalla(){
		Planta planta = Singleton.GetPlanta();
		int ancho = planta.getAncho();
		Object[] aux = this.getCola().toArray();
		System.out.print("(ruta:"+Identificador+":");
		for (int i = 0; i < aux.length-1; i++) {
			int actual = (Integer) aux[i];
			int siguiente = (Integer) aux[i+1];
				 if (actual+1 == siguiente) System.out.print(" E");
			else if (actual-1 == siguiente) System.out.print(" O");
			else if (actual-ancho == siguiente) System.out.print(" N");
			else if (actual+ancho == siguiente) System.out.print(" S");
			else System.err.print("No se ha podido encontrar la ruta");
		}
		System.out.println(")");
	}
	public void print(){
		Planta planta = Singleton.GetPlanta();
		//(<tipo de personaje>:<marca>:<id sala actual>:<turno>: <llaves>)
		planta.out().print("(");
		
		if(Codigo==0)planta.out().print("trabajador");
		else if(Codigo==1)planta.out().print("lider");
		else if(Codigo==2)planta.out().print("intruso");
		planta.out().print(":"+Identificador+":"+sala+":"+turno+":");
		
		Pila<Llaves> aux=new Pila<Llaves>();
		while(!Llaves.estaVacia()){
			Llaves LL;
			LL = Llaves.getDatoCima();
			aux=aux.insertarDato(LL);
			planta.out().print(" "+LL);
			Llaves=Llaves.sacarDato();
		}
		while(!aux.estaVacia()){
			Llaves LL;
			LL = aux.getDatoCima();
			Llaves=Llaves.insertarDato(LL);
			aux=aux.sacarDato();
		}
		planta.out().println(")");
		
		
	}
	


	public void simular(){
		Planta planta= Singleton.GetPlanta();
		if(planta.getTurno()==turno&&(sala!=planta.getSalaEscapados().getIdentificador())){
			puerta();
			hacerMovimiento();
			Llaves();
			turno++;
		}
	}
	//Metodos abstractos de la clase Personas
	/**
	 * Metodo que crea el movimiento de el personaje y lo pone en la cola
	 */
	public abstract void crearMovimiento();
	/**
	 * Metodo abstracto para el movimiento de un personaje
	 */
	public abstract void hacerMovimiento();
	/**
	 * Metodo abstracto con el cual un personaje interaccionara con la puerta
	 */
	public abstract void puerta();
//	/**
//	 * Metodo abstracto con el cual un personaje interactuara con otros
//	 */
//	public abstract void Interaccion();
	/**
	 * Metodo abstracto con el cual un personaje recogera o dejara caer una llave
	 */
	public abstract void Llaves();
	
}
