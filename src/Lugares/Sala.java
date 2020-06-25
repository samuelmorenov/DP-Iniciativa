package Lugares;

import java.util.LinkedList;
import java.util.Queue;

import Objetos.Llaves;
import Objetos.Puerta;
import Personaje.Personas;

/**
 * Implementacion de los metodos de la clase Sala.
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
public class Sala {
	/** Identificador de la clase Sala */
	private int idSala;
	/** Marca de la sala */
	private int marca;
	/** Cola para almacenar Personas */	
	private Queue<Personas> colaPersonas=new LinkedList<Personas>();
	/** Pila donde almacenaremos las llaves que hay en una sala */	
	private Queue<Llaves>listaLlaves = new LinkedList<Llaves>();
	/** Buleano para saber si hay puerta en la sala */
	private boolean hayPuerta;
	/** puerta de la sala */
	private Puerta puerta;
	
	/** Constructor por defecto de la clase sala */
	Sala(){
		this.idSala=0;
		this.marca=0;
		this.hayPuerta=false;
		//TODO faltan 2 cosas.
	}
	/** Constructor parametrizado de la clase sala */
	Sala(int num,boolean s){
		this.idSala=num;
		this.hayPuerta=false;
		this.marca=num;
	}
	/**
	 * Metodo donde los personajes realizan sus acciones
	 * @param turno 
	 */
	public void Simular(){
		if(!colaPersonas.isEmpty()){
			Object[] personas = colaPersonas.toArray();
			for (int i=0;i<personas.length;i++){
				Personas persona= (Personas) personas[i];
				persona.simular();
			}
		}	
	}
	/**
	 * Metodo que inserta un personaje en la cola de personajes
	 * @param p que sera la persona a introducir a la cola
	 */
	public void insertarPersonaje(Personas p){
		this.colaPersonas.add(p);
	}
	/**
	 * borrra el frente de la cola
	 */
	public void desencolarPersonaje(Personas P){
		if(!colaPersonas.remove(P))System.err.println("No se ha podido remover la persona de la cola de la sala.");
	}
	/**
	 * Metodo para poner el idSala
	 * @param id que es el valor a introducir en el atributo idSala
	 */
	public void setIdentificador(int id){
		this.idSala=id;
	}
	
	/**
	 * Pone la marca a la sala
	 * @param m marca que se va a poner en la sala
	 */
	public void setMarca(int m){
		this.marca=m;
	}
	/**
	 * Metodo para obtener la puerta de una sala
	 * @return la puerta que esta en una determinada sala
	 */
	public Puerta getPuerta(){
		return puerta;
	}
	/**
	 * Metodo para obtener el identificador de la sala (1 a 35)
	 * @return el atributo idSala
	 */
	public int getIdentificador(){
		return this.idSala;
	}
	/**
	 * Metodo que devuelve la marca de la sala
	 * @return La marca de la sala
	 */
	public int getMarca(){
		return this.marca;
	}
	/**
	 * Metodo para insertar una llave en la pila de llaves de la sala
	 * @param l que es la Llave a insertar en la pila
	 */
	public void insertarLlave(Llaves l){
		listaLlaves.add(l);
	}
	/**
	 * Metodo para que un personaje Coja la llave que se encuentra en la cima de la pila
	 * @return la llave que se cogera
	 */
	public Llaves cogerLlave(){
		return listaLlaves.poll();
	}
	/**
	 * Metodo que devuelve true si en la sala no hay llaves
	 * @return true si en la sala no hay llaves y false si hay llaves
	 */
	public boolean vaciaLlaves(){
		return listaLlaves.isEmpty();	
	}
	/**
	 * Metodo para obtener el frente de la cola de personajes
	 * @return la persona que esta en el frente de la cola de personajes
	 */
	public Personas getPersonas(){
		return colaPersonas.element();
	}
	public Queue<Personas> getColaPersonas(){
		return colaPersonas;
	}
	/**
	 * Metodo para saber si en una determinada sala hay puerta o no
	 * @return true si hay puerta, false si no
	 */
	public boolean hayPuerta(){
		return hayPuerta;
	}
	/**
	 * Metodo que devuelve el numero de personas que hay en una sala (elementos de la cola)
	 * @return el numero de elementos almacenados en la cola de personas
	 */
	public int cuantasPersonas(){
		return this.colaPersonas.size();
	}
	/**
	 * Metodo que devuelve la cola de personas
	 * @return La cola de personas
	 */
	public Queue<Personas> ObtenerColaPersonas() {	
		return this.colaPersonas;
	}
	/** Metodo que devuelve si hay personas que no sean intrusos en la sala */
	public boolean hayPersonas(){
		boolean hay = false;
		if(!colaPersonas.isEmpty()){
			Queue<Personas>aux = new LinkedList<Personas>();
			while(!colaPersonas.isEmpty()){
				Personas P = colaPersonas.poll();
				aux.add(P);
				if(P.getCodigo()!=2){
					hay = true;
				}
			}
			colaPersonas=aux;
		}
		return hay;
	}
	public void mostrarLlaves() {
		Planta planta = Singleton.GetPlanta();
		
		Queue<Llaves>aux = new LinkedList<Llaves>();
		
		while(!listaLlaves.isEmpty()){
			Llaves LL;
			LL = listaLlaves.poll();
			aux.add(LL);
			planta.out().print(" "+LL.getIdentificador());
		}
		listaLlaves=aux;
		
	}
	public void mostrarRecorridos(){
		Queue<Personas>aux = new LinkedList<Personas>();
		while(!colaPersonas.isEmpty()){
			Personas P = colaPersonas.poll();
			aux.add(P);
			P.mostrarRecorrido();
		}
		colaPersonas=aux;
	}
	public void mostrarPersonas(){
		Queue<Personas>aux = new LinkedList<Personas>();
		while(!colaPersonas.isEmpty()){
			Personas P = colaPersonas.poll();
			aux.add(P);
			P.print();
		}
		colaPersonas=aux;
	}
	/*
	public static void main (String args[]) {
		Sala sala = new Sala();
		sala.setMarca(5);
		int A = sala.getMarca();
		System.out.println(A);
		Llaves llave1 = new Llaves(1);
		Llaves llave2 = new Llaves(3);
		Llaves llave3 = new Llaves(5);
		Llaves llave4 = new Llaves(2);
		Llaves llave5 = new Llaves(4);
		Llaves llave6 = new Llaves(8);
		sala.insertarLlave(llave1);
		sala.insertarLlave(llave2);
		sala.insertarLlave(llave3);
		sala.insertarLlave(llave4);
		sala.insertarLlave(llave5);
		sala.insertarLlave(llave6);
		System.out.println("Mostrando llaves:");
		sala.mostrarLlaves();
		System.out.println("");
		sala.mostrarLlaves();
	}
	*/

}
