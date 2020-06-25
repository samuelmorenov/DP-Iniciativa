package Personaje;

import java.util.LinkedList;
import java.util.Queue;

import Lugares.Planta;
import Lugares.Singleton;
import Objetos.Llaves;


/**
 * Implementacion de los metodos de la clase Intruso.
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
public class Intruso extends Personas{

	private int actual;
	private Queue<Integer> copiaCola;

	/**Constructor parametrizado de la clase intruso
	 * @param nombre nombre del intruso
	 * @param i Codigo: en este caso seria siempre 2
	 * @param marca identificador
	 * @param turno turno en el que empieza
	 */
	public Intruso(String nombre, char marca, int turno) {
		super(nombre, 2, marca, turno);
		Llaves [] ListaLlaves = {new Llaves(1), new Llaves(3), new Llaves(5),
				new Llaves(7),new Llaves(9), new Llaves(11),new Llaves(13),
				new Llaves(15),new Llaves(17),new Llaves(19),new Llaves(21),
				new Llaves(23),new Llaves(25), new Llaves(27),new Llaves(29)};
		
		for (int i = 0; i < ListaLlaves.length; i++) {
			super.insertarLlave(ListaLlaves[i]);
		}
	}
	/**
	 * Metodo para interaccionar con la puerta
	 */
	public void puerta(){
		Planta planta= Singleton.GetPlanta();
		int actual = super.getSala();
		int salida = planta.getSalaSalida();
		if(actual==salida && planta.getPuerta().getEstadoPuerta()==1){
		planta.getPuerta().recombinar();
		}
	}
//	/**
//	 * Metodo para interaccionar con otros personajes
//	 */
//	public void Interaccion(){
//		Planta A=Singleton.GetPlanta();
//		Sala B=A.getSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho());
//		int num_per=B.cuantasPersonas();
//		boolean Trabajador = false;
//		Queue <Personas> enSala = B.ObtenerColaPersonas();
//		while(num_per>0 && !Trabajador){
//			if(B.getPersonas().getCodigo()==0){
//				Trabajador=true;
//			}
//			enSala.add(B.getPersonas());
//			B.desencolarPersonaje();
//		}
//		if(Trabajador){
//			this.Carcel.add(enSala.poll());    //Carcel situada momentaneamente en el intruso, eso deberia ir en estacion o en planta.
//		}
//		while(B.cuantasPersonas()>0){
//			enSala.add(B.getPersonas());
//			B.desencolarPersonaje();
//		}
//		while(enSala.size()>0){
//			B.insertarPersonaje(enSala.poll());
//		}
//		A.modificarSala(this.getEnSala()%A.getAncho(), this.getEnSala()/A.getAncho(), B);
//	}
	/**
	 * Metodo donde los personajes suelta las llaves de una sala
	 * @param planta es la planta donde se encuentra la sala desde donde cogeran llaves los trabajadores
	 */
	public void Llaves(){
		Planta planta= Singleton.GetPlanta();
		int sala = super.getSala();
		if(sala%2==0&&!super.sinLlaves()){
		planta.calcularSala(sala).insertarLlave(super.ObtenerCima());
		super.DesapilarLlave();
		}
	}
	
	/**
	 * Metodo que crea el movimiento del intruso
	 * @param turnos numero de turnos
	 */
	
	public void crearMovimiento(){
		Planta planta= Singleton.GetPlanta();
		int ancho = planta.getAncho();
		int alto = planta.getAlto();
		int sala1 = 0;
		int sala2 = (ancho*alto)-ancho;
		int sala3 = (ancho*alto)-1;
		int sala4 = ancho-1;
		Queue<Integer> caminos = new LinkedList<Integer>();
		copiaCola = new LinkedList<Integer>();
		caminos.add(sala4);
		int restantes = 4;
		char anterior = 'O';
		int max = 0;
		actual = sala4;
		while (restantes != 0 && max < 1000){
			max++;
			if(anterior=='O'){
					 if(N(caminos))anterior='N';
				else if(O(caminos))anterior='O';
				else if(S(caminos))anterior='S';
				else if(E(caminos))anterior='E';
				else System.err.print("No se ha podido encontrar la ruta");
			}
			else if(anterior=='N'){
					 if(E(caminos))anterior='E';
				else if(N(caminos))anterior='N';
				else if(O(caminos))anterior='O';
				else if(S(caminos))anterior='S';
				else System.err.print("No se ha podido encontrar la ruta");
			}
			else if(anterior=='E'){
					 if(S(caminos))anterior='S';
				else if(E(caminos))anterior='E';
				else if(N(caminos))anterior='N';
				else if(O(caminos))anterior='O';
				else System.err.print("No se ha podido encontrar la ruta");
			}
			else if(anterior=='S'){
					 if(O(caminos))anterior='O';
				else if(S(caminos))anterior='S';
				else if(E(caminos))anterior='E';
				else if(N(caminos))anterior='N';
				else System.err.print("No se ha podido encontrar la ruta");
			}
			
			if(actual==sala1&&restantes==4){
				restantes--;
			}
			if(actual==sala2&&restantes==3){
				restantes--;
			}
			if(actual==sala3&&restantes==2){
				restantes--;
			}
			if(actual==sala4&&restantes==1){
				restantes--;
			}
		}
		if(max==1000&&restantes>0)System.err.println("ERROR:: Detectado bucle infinito en crearMovimiento de Intruso");

		ponerColas(caminos);
	
	}
	/*
	 if (actual+1 == siguiente) planta.out().print(" E");
		else if (actual-1 == siguiente) planta.out().print(" O");
		else if (actual-ancho == siguiente) planta.out().print(" N");
		else if (actual+ancho == siguiente) planta.out().print(" S");
		else System.err.print("No se ha podido encontrar la ruta");
		*/
	private boolean N(Queue<Integer> caminos){
		Planta planta= Singleton.GetPlanta();
		int ancho = planta.getAncho();
		if(planta.getGrafo().getArco(actual, actual-ancho) == 1){
			actual = actual-ancho;
			caminos.add(actual);
			return true;
		}
		else return false;
	}
	private boolean S(Queue<Integer> caminos){
		Planta planta= Singleton.GetPlanta();
		int ancho = planta.getAncho();
		if(planta.getGrafo().getArco(actual, actual+ancho) == 1){
			actual = actual+ancho;
			caminos.add(actual);
			return true;
		}
		else return false;
	}
	private boolean E(Queue<Integer> caminos){
		Planta planta= Singleton.GetPlanta();
		//int ancho = planta.getAncho();
		if(planta.getGrafo().getArco(actual, actual+1) == 1){
			actual = actual+1;
			caminos.add(actual);
			return true;
		}
		else return false;
	}
	private boolean O(Queue<Integer> caminos){
		Planta planta= Singleton.GetPlanta();
		//int ancho = planta.getAncho();
		if(planta.getGrafo().getArco(actual, actual-1) == 1){
			actual = actual-1;
			caminos.add(actual);
			return true;
		}
		else return false;
	}
	@Override
	public void hacerMovimiento() {
		if(super.getCola().size()>1){
			Planta planta = Singleton.GetPlanta();
			int salaActual = super.getCola().poll();
			int salaSiguiente = super.getCola().peek();
			planta.calcularSala(salaActual).desencolarPersonaje(this);
			planta.calcularSala(salaSiguiente).insertarPersonaje(this);
			super.setSala(salaSiguiente);
		}
		
		
		
		else{
			Planta planta= Singleton.GetPlanta();
			int ancho = planta.getAncho();
			int salaActual = super.getCola().peek();
			if(salaActual==ancho-1){
				ponerColas(copiaCola);
				hacerMovimiento();
			}else{
				System.err.println("ERROR:: El intruso termino el recorrido y no acabo en su sala de inicio.");
			}
		}
	}
	/**
	 * Metodo privado que toma la cola por parametro y la vacia poniendo su contenido tanto en la
	 * copia de la cola de Intruso como en la cola de movimientos de Personas.
	 * @param aPoner
	 */
	private void ponerColas(Queue<Integer> aPoner){
		Queue<Integer> colaAux1 = new LinkedList<Integer>();
		Queue<Integer> colaAux2 = new LinkedList<Integer>();
		while(!aPoner.isEmpty()){
			int aux = aPoner.poll();
			colaAux1.add(aux);
			colaAux2.add(aux);
		}
		super.setCola(colaAux1);
		copiaCola = colaAux2;
	}

}
