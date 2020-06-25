package Personaje;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



import Lugares.Planta;
import Lugares.Sala;
import Lugares.Singleton;


/**
 * Implementacion de los metodos de la clase Lider.
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
public class Lider extends Personas {

	/**Constructor parametrizado de la clase intruso
	 * @param nombre nombre del intruso
	 * @param i Codigo: en este caso seria siempre 2
	 * @param marca identificador
	 * @param turno turno en el que empieza
	 */
	public Lider(String nombre, char marca, int turno){
		super(nombre, 1, marca, turno);
	}
	/**
	 * Metodo para interaccionar con la puerta
	 */
	public void puerta(){
		Planta planta= Singleton.GetPlanta();
		int actual = super.getSala();
		int salida = planta.getSalaSalida();
		if(actual==salida&&!(planta.getPuerta().getEstadoPuerta()==1)){
			planta.getPuerta().probarLlave(ObtenerCima());
			apilarLlavesProbadas(ObtenerCima());
			DesapilarLlave();
		}

		/*
		for(int i=0;i<Trabajadores.size();i++){
			planta.getPuerta().probarLlave(Trabajadores.get(i).ObtenerCima());
			Trabajadores.get(i).apilarLlavesProbadas(Trabajadores.get(i).ObtenerCima());
			Trabajadores.get(i).Desapilar();
		}
		*/
}
	
//	/**
//	 * Metodo para interaccionar con otros personajes
//	 */
//	public void Interaccion(){
////		Planta A=Singleton.GetPlanta();
////		Sala B=A.getSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho());
////		int num_per=B.cuantasPersonas();
////		boolean Trabajador = false;
////		Queue <Personas> enSala = B.ObtenerColaPersonas();
////		while(num_per>0 && !Trabajador){
////			if(B.getPersonas().getCodigo()==0){
////				Trabajador=true;
////			}
////			enSala.add(B.getPersonas());
////			B.desencolarPersonaje();
////		}
////		if(Trabajador){
////			this.Trabajadores.add(enSala.poll());
////		}
////		while(B.cuantasPersonas()>0){
////			enSala.add(B.getPersonas());
////			B.desencolarPersonaje();
////		}
////		while(enSala.size()>0){
////			B.insertarPersonaje(enSala.poll());
////		}
////		A.modificarSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho(), B);
//	}
	/**
	 * Metodo donde los personajes cogen las llaves de una sala
	 */
	public void Llaves(){
		Planta planta= Singleton.GetPlanta();
		if(!(planta.getPuerta().getEstadoPuerta()==1)){
			Sala sala = planta.calcularSala(super.getSala());
			if(!sala.vaciaLlaves()){
				super.insertarLlave(sala.cogerLlave());
			}
		}
	}

	public void crearMovimiento() {
    	Planta planta = Singleton.GetPlanta();
    	int actual = planta.getSalaInicio();
    	int fin = planta.getSalaSalida();
    	ArrayList<Integer> Visitados = new ArrayList<Integer>();
    	Queue<Integer> caminos = new LinkedList<Integer>();
		colaMovimientos(planta, actual, fin, Visitados, caminos);
		super.setCola(caminos);
	}
    private void colaMovimientos(Planta planta, Integer actual, Integer fin,
    	ArrayList<Integer> Visitados, Queue<Integer> caminos){  
    	Visitados.add(actual);
    	ArrayList<Integer> Adyacentes = new ArrayList<Integer>();
    	planta.getGrafo().adyacentes(actual, Adyacentes);
    	while(!Adyacentes.isEmpty()){
    		int salaAdy = Adyacentes.get(0);
    		Adyacentes.remove(0);
    		if(!Visitados.contains(salaAdy))
    			colaMovimientos(planta, salaAdy, fin, Visitados, caminos);

    	}
        if(actual==fin && caminos.size()==0)
        	for (int i = 0; i < Visitados.size(); i++) 
            	caminos.add(Visitados.get(i));
    	Visitados.remove(actual);
    }
	public void hacerMovimiento() {
		Planta planta = Singleton.GetPlanta();
		if(planta.getSalaSalida()==super.getSala()&&planta.getPuerta().getEstadoPuerta()==1){
			int salaActual = super.getCola().peek();
			int salaSiguiente = planta.getSalaEscapados().getIdentificador();
			planta.calcularSala(salaActual).desencolarPersonaje(this);
			planta.getSalaEscapados().insertarPersonaje(this);
			super.setSala(salaSiguiente);
		}else if(super.getCola().size()>1){
			int salaActual = super.getCola().poll();
			int salaSiguiente = super.getCola().peek();
			planta.calcularSala(salaActual).desencolarPersonaje(this);
			planta.calcularSala(salaSiguiente).insertarPersonaje(this);
			super.setSala(salaSiguiente);
		}
	}
}

