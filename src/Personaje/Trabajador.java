package Personaje;

import java.util.LinkedList;
import java.util.Queue;

import Lugares.Planta;
import Lugares.Sala;
import Lugares.Singleton;

/**
 * 
 * Implementacion de los metodos de la clase Trabajador.
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
public class Trabajador extends Personas{	
	/**Constructor parametrizado de la clase intruso
	 * @param nombre nombre del intruso
	 * @param i Codigo: en este caso seria siempre 2
	 * @param marca identificador
	 * @param turno turno en el que empieza
	 */
	public Trabajador(String nombre, char marca, int turno) {
		super(nombre, 0, marca, turno);
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
	}
//	/**
//	 * Metodo para interaccionar con otros personajes
//	 */
//	public void Interaccion(){
//		/*
//		Planta A=Singleton.GetPlanta();
//		Sala B=A.getSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho());
//		int num_per=B.cuantasPersonas();
//		boolean Intruso = false;
//		Queue <Personas> enSala = B.ObtenerColaPersonas();
//		while(num_per>0 && !Intruso){
//			if(B.getPersonas().getCodigo()==2){   
//				Intruso=true;
//			}
//			enSala.add(B.getPersonas());
//			B.desencolarPersonaje();
//		}
//		if(Intruso){
//			this.insertarLlave(enSala.peek().ObtenerCima());
//			enSala.peek().Desapilar();
//		}
//		while(B.cuantasPersonas()>0){
//			enSala.add(B.getPersonas());
//			B.desencolarPersonaje();
//		}
//		while(enSala.size()>0){
//			B.insertarPersonaje(enSala.poll());
//		}
//		A.modificarSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho(), B);
//		*/
//	}
	
	/**
	 * Crea los movimientos para el trabajador
	 * 
	 * @param turnos numero de turnos totales en la simulacion
	 */
	public void crearMovimiento(){
    	Planta planta = Singleton.GetPlanta();
    	planta.getGrafo().floyd();
    	Queue<Integer> caminos = new LinkedList<Integer>();
		caminos.add(0); // seria origen
		planta.getGrafo().CaminoMinimo(planta.getSalaInicio(), planta.getSalaSalida(),caminos);
		caminos.add(planta.getSalaSalida()); //seria destino
		super.setCola(caminos);
	}

	
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

	@Override
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