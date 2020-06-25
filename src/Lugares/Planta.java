package Lugares;

import java.io.PrintWriter;
import java.util.ArrayList;

import Estructuras.Grafo;
import GenAleatorios.GenAleatorios;
import Objetos.Llaves;
import Objetos.Pared;
import Objetos.Puerta;
import Personaje.Personas;
import EscritorLog.Escritura;


/**
 * Implementacion de los metodos de la clase Planta.
 *
 * @version 1.7
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
public class Planta {
	/** Matriz de salas */
	private Sala[][] maSalas;
	/** Sala con las personas escapadas */
	private Sala salaEscapados;
	/** Atributo de tipo puerta */
	private Puerta puerta;
	/** Atributo con el entero que sera la casilla de salida */
	private int salaSalida;
	/** Atributo con el entero que sera la casilla de inicio */
	private int salaInicio;
	/** Atributo que sera el Identificador de planta */
	private int idPlanta;
	/** Entero donde guardar el ancho de la planta */
	private int ancho;
	/** Entero donde guardar el alto de la planta */
	private int alto;
	/**Entero que muestra la altura del arbol de la puerta */
	private int altura;
	/**Entero que muestra el turno actual*/
	private int turno;
	/** Matriz de paredes */
	private Grafo grafo;
	/** Array de paredes */
	private ArrayList<Pared> paredes;
	private Escritura log;
	/** Constructor por defecto de la clase Planta */

	public Planta(){
		this.ancho = 6;
		this.alto = 6;
		this.salaInicio = 0;
		this.salaSalida = ancho*alto-1;
		this.idPlanta = 0;
		inicializacionPlanta();
	}
	/** Constructor parametrizado de la clase planta */
	public Planta(int alto, int ancho) {
		this.ancho = ancho;
		this.alto = alto;
		this.salaInicio = 0;
		this.salaSalida = ancho*alto-1;
		this.idPlanta = 0;
		inicializacionPlanta();
	}
	/** Constructor parametrizado de la clase planta */
	public Planta(int inicio, int salida, int alto, int ancho, int idplanta, int altura_) {
		this.ancho = ancho;
		this.alto = alto;
		this.salaInicio = inicio;
		this.salaSalida = salida;
		this.idPlanta = idplanta;
		this.altura = altura_;
		inicializacionPlanta();
	}
	

	

	/**
	 * Metodo para poner el valor de salaInicio
	 * @param num que sera el valor a poner en salaInicio
	 */
	public void setSalaInicio(int num){
		this.salaInicio=num;
	}
	/**
	 * Metodo para poner el valor de salaSalida
	 * @param num que sera el valor a poner en salaSalida
	 */
	public void setSalaSalida(int num){
		this.salaSalida=num;
	}
	/**
	 * Metodo para poner el valor de ancho
	 * @param num que sera el valor a poner en ancho
	 */
	public void setAncho(int num){
		this.ancho=num;
	}
	/**
	 * Metodo para poner el valor de alto
	 * @param num que sera el valor a poner en alto
	 */
	public void setAlto(int num){
		this.alto=num;
	}
	/**
	 * Metodo para poner el valor de IdPlanta
	 * @param num que sera el valor a poner en idPlanta
	 */
	public void setIdPlanta(int num){
		this.idPlanta=num;
	}
	/**
	 * Metodo para obtener la sala de Inicio
	 * @return Un entero que sera la sala en la que comenzaran los personajes
	 */
	public int getSalaInicio (){
		return this.salaInicio;
	}
	/**
	 * Metodo para obtener la sala de Salida
	 * @return Un entero que sera la sala por donde saldran los personajes de la planta
	 */
	public int getSalaSalida (){
		return this.salaSalida;
	}
	/**
	 * Metodo para obtener el ancho de la planta
	 * @return Un entero que sera el ancho que tendra la planta
	 */
	public int getAncho (){
		return this.ancho;
	}
	/**
	 * Metodo para obtener el alto de la planta
	 * @return Un entero que sra el alto que tendra la planta
	 */
	public int getAlto (){
		return this.alto;
	}
	/**
	 * Metodo para obtener el identificador de planta
	 * @return Un entero que sera el identificador de la planta
	 */
	public int getIdPlanta (){
		return this.idPlanta;
	}
	
	/**
	 * Metodo devuelve el grafo
	 * @return grafo de la planta
	 */
	public Grafo getGrafo(){
		return this.grafo;
	}
	/**
	 * Metodo que devuelve la puerta de la sala
	 * @return La puerta de la sala
	 */
	public Puerta getPuerta(){
		return this.puerta;
	}
	/**
	 * Metodo para obtener la sala dado el ancho y el alto
	 * @param a alto de la matriz
	 * @param b anchi de la matriz
	 * @return La sala con alto a y ancho b
	 */
	public Sala getSala(int a, int b){
		return maSalas[a][b];
	}
	public int getTurno(){
		return turno;
	}
	public Sala getSalaEscapados(){
		return salaEscapados;
	}
	/**
	 * Metodo de inicializacion de la planta que se llama desde el constructor
	 */
	private void inicializacionPlanta(){
	//Metemos la planta en el Singleton, de no ser asi al acceder a la planta del singleton desde
	//otra clase este crearia una planta por defecto que no seria esta
		Singleton.SetPlanta(this);
		
	//Creacion de la clase Escritura para poder escribir todo en el log
		log = new Escritura();

	//Creacion de la matriz
		this.maSalas = new Sala[ancho][alto];
			int cont=0;//los numeros irian de arriba a abajo, siendo 1 la de la esquina inferior derecha
			for(int j=0;j<alto;j++){
				for (int i=0;i<ancho;i++){
					Sala s= new Sala();
					s.setIdentificador(cont);
					s.setMarca(cont);
					maSalas[i][j]=s;
					cont=cont+1;
				}
			}
		salaEscapados = new Sala();
		salaEscapados.setIdentificador(1111);
		salaEscapados.setMarca(1111);
		
	//Creacion de paredes
		this.paredes = new ArrayList<Pared>();
			for (int t=0;t<ancho*alto;t++){
				//creacion de la pared de la sala actual con la de arriba
				Pared pared0= new Pared();
				pared0.setX(t);
				pared0.setY(t-ancho);
				if((t-ancho)>-1){
//				System.out.println("Pared (" + t + ", " + (t-ancho) + ")");
					paredes.add(pared0);
				}
				
				//creacion de la pared de la sala actual con la de la derecha
				Pared pared1= new Pared();
				pared1.setX(t);
				pared1.setY(t+1);
				if(t%ancho!=ancho-1){	
//				System.out.println("Pared (" + t + ", " + (t+1) + ")");
					paredes.add(pared1);
					
				}
				//creacion de la pared de la sala actual con la de abajo
				Pared pared2= new Pared();
				pared2.setX(t);
				pared2.setY(t+ancho);
				if((t+ancho)<((ancho*alto))){
//				System.out.println("Pared (" + t + ", " + (t+ancho) + ")");
					paredes.add(pared2);
				}
				//creacion de la pared de la sala actual con la de izquierda
				Pared pared3= new Pared();
				pared3.setX(t);
				pared3.setY(t-1);
				if(t%ancho!=0){	
//				System.out.println("Pared (" + t + ", " + (t-1) + ")");
					paredes.add(pared3);
					
				}
			}

	//Creacion de nodos
		this.grafo = new Grafo();
		    int [] nodos = new int[ancho*alto];
		    for(int i=0;i<ancho*alto;i++){
				nodos[i]=i;
		        if (!grafo.nuevoNodo(nodos[i]))
					System.out.println("Error en la insercion del nodo " + i);
			}

	//Tirar paredes y creacion de atajos
		tirarParedes();
		crearAtajos();

		
		
	//Repartimos las llaves en la sala
		Llaves [] ListaLlaves = {new Llaves(0),new Llaves(1),new Llaves(1),new Llaves(2),
				new Llaves(3),new Llaves(3),new Llaves(4),new Llaves(5),new Llaves(5),new Llaves(6),
				new Llaves(7),new Llaves(7),new Llaves(8),new Llaves(9),new Llaves(9),new Llaves(10),
				new Llaves(11),new Llaves(11),new Llaves(12),new Llaves(13),new Llaves(13),new Llaves(14),
				new Llaves(15),new Llaves(15),new Llaves(16),new Llaves(17),new Llaves(17),new Llaves(18),
				new Llaves(19),new Llaves(19),new Llaves(20),new Llaves(21),new Llaves(21),new Llaves(22),
				new Llaves(23),new Llaves(23),new Llaves(24),new Llaves(25),new Llaves(25),new Llaves(26),
				new Llaves(27),new Llaves(27),new Llaves(28),new Llaves(29),new Llaves(29)};
		int salasConLlaves = 9;
		int llavesPorSala = 5;
		repartirLlaves(salasConLlaves, llavesPorSala, ListaLlaves);

		
		//Creacion de la puerta
		Llaves[] llavesPuerta = {new Llaves(1), new Llaves(3), new Llaves(5),
				new Llaves(7),new Llaves(9), new Llaves(11),new Llaves(13),
				new Llaves(15),new Llaves(17),new Llaves(19),new Llaves(21),
				new Llaves(23),new Llaves(25), new Llaves(27),new Llaves(29)};
		this.puerta = new Puerta(llavesPuerta, altura);

		//Pintar planta
		out().println("(planta "+idPlanta+")");
		pintarPlanta();
		out().println("(distribucion llaves)");
		mostrarLlaves();
	}
	public PrintWriter out(){
		return log.getPW();
	}
	private void cerrarFichero(){
		log.cerrarFichero();
	}
//	public void modificarSala(int i, int j, Sala b) {
//		maSalas[i][j]=b;
//	}
	public void insertarPuerta(Puerta puerta_) {
		this.puerta=puerta_;
	}
	public void insertarPersona(Personas P) {
		if (P.getCodigo()!=2){
			maSalas[0][0].insertarPersonaje(P);	
		}else{
			maSalas[ancho-1][0].insertarPersonaje(P);
		}
	}
	/**
	 * Metodo al que se le pasa por parametro el numero de la sala y devuelve la sala
	 * @param X es la posicion en la que estaria una sala en un vector
	 * @return Devuelve la sala que esta en la posicion X del vector
	 */
	public Sala calcularSala(int X){
		if(X==salaEscapados.getIdentificador())return salaEscapados;
		return maSalas[X%ancho][X/ancho];
	}
	/**
	 * Metodo que quita paredes para crear arcos
	 */
	private void tirarParedes(){
//		System.out.println("Ejecutado metodo tirar paredes.");
		while(!paredes.isEmpty()){
			int A= GenAleatorios.generarNumero(paredes.size());
//			System.out.print("tamaño: "+paredes.size());
//			System.out.println(" aleatorio: "+A);
			int X = paredes.get(A).getX();
			int Y = paredes.get(A).getY();
//			System.out.println("Pared elegida: "+X+" - "+Y);
			int MarcaX = calcularSala(X).getMarca();
			int MarcaY = calcularSala(Y).getMarca();

			if(MarcaX != MarcaY){
//				System.out.println("Se tira la pared. Marca: "+MarcaY);
				this.grafo.nuevoArco(X,	Y, 1);
				this.grafo.nuevoArco(Y,	X, 1);
				for(int j=0;j<alto;j++){
					for(int i=0;i<ancho;i++){
						if(maSalas[i][j].getMarca()== MarcaX){
							maSalas[i][j].setMarca(MarcaY);
						}
					}
				}
				
			}
			paredes.remove(A);
		}
	}

//	/**
//	 * Metodo que nos dice si todas las marcas son iguales
//	 * Se utilizaba en el metodo tirar paredes, pero se cambio por preguntar si ya no habia paredes que tirar
//	 * @return devuelve true si son iguales y false si son distintas
//	 */
//	private boolean todasMarcasIguales(){
//		for(int i =0;i<ancho;i++){
//			for(int j=1; j<alto;j++){
//				if(maSalas[0][0].getMarca()!=maSalas[i][j].getMarca()){
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	/** Metodo que crea los atajos borrando un 5% de las paredes 
	 * @return */
	private void crearAtajos(){
//		System.out.println("Ejecutado metodo crear atajos");
		int nodo=0;
		int cont= 0;
		float i;
		i=5*(ancho*alto)/100;
		while(i>cont){
			
			nodo=GenAleatorios.generarNumero(alto*ancho);
//			System.out.println("nodo generado " + nodo + " ancho*alto = "+alto*ancho);
			if(atajoCreado(nodo)){
//				paredes.remove(nodo);
				cont++;
			}	
		}
	}
	
	/**
	 * Crea el atajo
	 * @param nodo nodo en el que se cre el atajo
	 * @return devuelvi si se ha podido crear o no
	 */
	private boolean atajoCreado(int nodo) {
		if(!atajoNorte(nodo) && !grafo.adyacente(nodo,nodo-ancho) && (nodo-ancho)>=0){
			grafo.nuevoArco(nodo,nodo-ancho,1);
			grafo.nuevoArco(nodo-ancho,nodo,1);
		}
		else{
			if(!atajoSur(nodo) && !grafo.adyacente(nodo,nodo+ancho) && nodo+ancho<ancho*alto){
				grafo.nuevoArco(nodo,nodo+ancho,1);
				grafo.nuevoArco(nodo+ancho,nodo,1);
			}
			else{
				if(!atajoOeste(nodo) && !grafo.adyacente(nodo,nodo-1) && nodo%ancho>0){
					grafo.nuevoArco(nodo,nodo-1,1);
					grafo.nuevoArco(nodo-1,nodo,1);
				}
				else{
					if(!atajoEste(nodo) && !grafo.adyacente(nodo,nodo+1) && nodo%ancho<ancho-1){
						grafo.nuevoArco(nodo,nodo+1,1);
						grafo.nuevoArco(nodo+1,nodo,1);
					}
					else return false;
				}
			}
		}
		return true;
	}
	private boolean atajoNorte(int nodo){
		return ((grafo.adyacente(nodo,nodo-1) && grafo.adyacente(nodo-1,nodo-1-ancho) && grafo.adyacente(nodo-1-ancho,nodo-ancho)) || (grafo.adyacente(nodo,nodo+1) && grafo.adyacente(nodo+1,nodo+1-ancho) && grafo.adyacente(nodo+1-ancho,nodo-ancho)));
	}
	private boolean atajoSur(int nodo){
		return((grafo.adyacente(nodo,nodo-1) && grafo.adyacente(nodo-1,nodo-1+ancho) && grafo.adyacente(nodo-1+ancho,nodo+ancho)) || (grafo.adyacente(nodo,nodo+1) && grafo.adyacente(nodo+1,nodo+1+ancho) && grafo.adyacente(nodo+1+ancho,nodo+ancho)));
	}
	private boolean atajoEste(int nodo){
		return((grafo.adyacente(nodo,nodo+ancho) && grafo.adyacente(nodo+ancho,nodo+ancho+1) && grafo.adyacente(nodo+ancho+1,nodo+1)) || (grafo.adyacente(nodo,nodo-ancho) && grafo.adyacente(nodo-ancho,nodo-ancho+1) && grafo.adyacente(nodo-ancho+1,nodo+1)));
	}
	private boolean atajoOeste(int nodo){
		return((grafo.adyacente(nodo,nodo-ancho) && grafo.adyacente(nodo-ancho,nodo-ancho-1) && grafo.adyacente(nodo-ancho-1,nodo-1)) || (grafo.adyacente(nodo,nodo+ancho) && grafo.adyacente(nodo+ancho,nodo+ancho-1) && grafo.adyacente(nodo+ancho-1,nodo-1)));
	}
	




	/**
	 * Metodo que mete las llaves en las salas que le pasamos por el vector
	 * @param salasConLlaves Es el numero de salas que van a tener llaves
	 * @param llavesPorSala Es el numero de llaves que va a ir en cada sala
	 * @param ListaLlaves Es la lista de llaves que vamos a repartir
	 */
	private void repartirLlaves(int salasConLlaves, int llavesPorSala, Llaves [] ListaLlaves){
		int[]SalasConLlave = calcularFrecuencia(salasConLlaves);
		int k = 0;
		int Nllaves =0;
		for (int i = 0; i < SalasConLlave.length; i++) {
			if(SalasConLlave[i]>0){
				Nllaves++;
			}
		}
		for (int i = 0; i < Nllaves; i++){
			for (int j = 0; j<llavesPorSala; j++){
				if (k >= ListaLlaves.length){
					System.err.println("Se han repartido todas las llaves, pero no se han llenado todas las salas");
					return;
				}
				Sala sala = calcularSala(SalasConLlave[i]);
				Llaves llave = ListaLlaves[k];
				sala.insertarLlave(llave);
//				System.out.println("Introducida la llave con id: "+llave.getIdentificador()+" en la sala: "+sala.getIdentificador());
				k++;
			}

		}
		if(k != ListaLlaves.length){
			System.err.println("Se han llenado todas las salas, pero no se han repartido todas las llaves");
		}
}

	
	/**
	 * Metodo que calcula las salas mas frecuentadas
	 * @param N Es el numero de salas que van a tener llaves
	 * @return devuelve un vector con las salas N mas frecuentadas
	 */
    private int[] calcularFrecuencia(int N){
      int[] frecuencia = new int[N];
  	  //Inicializacion de la matriz de frecuencia
  	  int[][]salasV = new int[ancho][alto];
  	  for(int j=0;j<alto;j++){
			for (int i=0;i<ancho;i++){
				salasV[i][j]=0;
			}
  	  }
  	  //ArrayList usado por el metodo matrizFrecuenciamatrizFrecuencia para saber por donde se ha pasado
  	  ArrayList<Integer> Visitados = new ArrayList<Integer>();
  	  
  	  //Calculo de la matriz frecuencia mediante el metodo matrizFrecuencia
  	  matrizFrecuencia(this.salaInicio, this.salaSalida, Visitados, salasV);
  	  //Para que no se reparta ni en la primera ni en la ultima las ponemos a 0
  	  salasV[salaInicio%ancho][salaInicio/ancho]=0;
  	  salasV[salaSalida%ancho][salaSalida/ancho]=0;

  	  
  	  
  	  //Calculo del vector de salas mas visitadas mediante la matriz de frecuencia
  	  for(int k=0; k<N;k++){
  		  int max=0,X=0,iF=0,jF=0,sala=0;
  		  for (int j = 0; j < alto; j++) {
  			  for (int i = 0; i < ancho; i++) {
				if(salasV[i][j]>max){
					max = salasV[i][j];
					sala = X;
					iF = i;
					jF = j;
				}
  				X++;
  			  }
  		  }
  		  if(max>0){
  	  		  frecuencia[k]=sala;
  		  }
  		  salasV[iF][jF]=-1; //Para que no vuelva a salir la misma ponemos la sala que hemos tomado a -1
  	  }
  	  return frecuencia;
    }
	/**
	 * Metodo que escribe el numero de caminos que pasa por de cada sala
	 * @param v Es la sala de inicio de el grafo, en cada iteracion de el metodo es el nodo en el que esta
	 * @param f Es la sala final, cuando llegue a esta sala terminara el camino
	 * @param Visitados es el ArrayList usado para saber por donde se ha pasado
	 * @param salaV es la matriz de salas con el numero de caminos que pasa por cada sala
	 */
    private void matrizFrecuencia(Integer actual, Integer fin, ArrayList<Integer> Visitados, int[][] salaV){
    	//añadimos la sala actual a visitados
    	Visitados.add(actual);

        salaV[actual%ancho][actual/ancho]=salaV[actual%ancho][actual/ancho]+1;//incrementamos el valor de la sala actual

//    	System.out.println("incrementamos sala "+actual);
//    	for (int j = 0; j < alto; j++) {for (int i = 0; i < ancho; i++) System.out.print(salaV[i][j]+" ");
//		  System.out.println();}
        
		//Vemos las salas adyacentes
    	ArrayList<Integer> Adyacentes = new ArrayList<Integer>();
    	this.grafo.adyacentes(actual, Adyacentes);
    	
    	
    	//mientras que haya adyacentes seguimos avanzando
    	while(!Adyacentes.isEmpty()){
    		//tomamos el primer adyacente y lo borramos de la lista
    		int salaAdy = Adyacentes.get(0);
    		Adyacentes.remove(0);
    		//si no lo hemos visitado seguismo avanzando
    		if(!Visitados.contains(salaAdy))
    			matrizFrecuencia(salaAdy, fin, Visitados, salaV);

    	}
    	//cuando no queden mas adyacentes es que estamos en un callejon sin salida, entonces
    	// tenemos que quitar la sala actual de visitados, para que no la contemos como camino
        if(actual==fin){
        	guardarCamino(Visitados, salaV);
        }

       	salaV[actual%ancho][actual/ancho]=salaV[actual%ancho][actual/ancho]-1;//decrementamos el valor de la sala actual


    	//Adyacentes.remove(actual);
//    	System.out.println("decrementamos la sala "+actual);
//    	for (int j = 0; j < alto; j++) {for (int i = 0; i < ancho; i++) System.out.print(salaV[i][j]+" ");
//		  System.out.println();}
        Visitados.remove(actual);

        
    }
    private void guardarCamino(ArrayList<Integer> Visitados, int[][] salaV){
    	for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if(Visitados.contains(new Integer((ancho*i)+j))){
					salaV[j][i]++;
				}
			}
		}
    }

    
	public void mostrarLlaves(){
		int X=0;
		for (int j = 0; j < alto; j++) {
			for (int i = 0; i < ancho; i++) {
				if(!maSalas[i][j].vaciaLlaves()){
					out().print("(sala:"+X+":");
					maSalas[i][j].mostrarLlaves();
					out().println(")");
				}
				X++;
			}
			
		}
	}
	
//  /**
//  * Metodo para probar la funcionalidad de el metodo matriz frecuencia
//  */
// public void mostrarSalaV(){
// 	  int[][]salasV = new int[ancho][alto];
// 	  for(int j=0;j<alto;j++)for(int i=0;i<ancho;i++)salasV[i][j]=0;
// 	  ArrayList<Integer> Visitados = new ArrayList<Integer>();
// 	  matrizFrecuencia(this.salaInicio, this.salaSalida, Visitados, salasV);
//		  for (int j = 0; j < alto; j++) {for (int i = 0; i < ancho; i++) System.out.print(salasV[i][j]+" ");
//			  System.out.println();}
//	}
	
//	/** 
//	 * Metodo que pinta todas las marcas de la matriz y todos los identificadores
//	 * </br>Se utiliza unicamente para la comprobacion de metodos de la Planta
//	 */
//	public void pintarMatriz(){
//		System.out.println("Metodo pintar marcas de la matriz: ");
//		for (int j=0;j<this.alto;j++){
//			for(int i=0;i<this.ancho;i++){
//				System.out.print(maSalas[i][j].getMarca() + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("Metodo pintar identificador de la matriz: ");
//		for (int j=0;j<this.alto;j++){
//			for(int i=0;i<this.ancho;i++){
//				System.out.print(maSalas[i][j].getIdentificador() + " ");
//			}
//			System.out.println();
//		}
//	}

//	/**
//	 * Metodo que muestra las coordenadas X e Y de las todas pas paredes
//	 * </br>Se utiliza unicamente para la comprobacion de metodos de la Planta
//	 */
//	public void pintarParedes(){
//		System.out.println("Metodo pintar paredes: ");
//		for(int i=0; i<this.paredes.size(); i++){
//			System.out.println(this.paredes.get(i).getX() + " " + this.paredes.get(i).getY());
//		}
//	}
    
//	/**
//	 * Metodo que llama al metodo mostrarArcos de el grafo y al contarNodos1 del mismo
//	 * </br>Se utiliza unicamente para la comprobacion de metodos de la Planta
//	 */
//	public void pintarArcos(){
//		System.out.println("Metodo pintar arcos: ");
//		this.grafo.mostrarArcos();
//		this.grafo.contarNodos1();
//	}
	/**
	 * Metodo que muestra por pantalla un dibujo de las paredes de la planta
	 */
	public void mostrarPlanta(){
		//Esto muestra la primera linea de arriba
		for(int i= 0; i<ancho; i++){
			System.out.print(" _");
		}
		System.out.println();
		for(int j=0; j<alto;j++){
			System.out.print("|");
			for(int i=0; i<ancho;i++){
				int A = maSalas[i][j].getIdentificador();
				
				if(!maSalas[i][j].ObtenerColaPersonas().isEmpty()){
					if(maSalas[i][j].ObtenerColaPersonas().size()>1) System.out.print(maSalas[i][j].ObtenerColaPersonas().size());
					else System.out.print(maSalas[i][j].ObtenerColaPersonas().peek().getIdentificador());
				}
				else{
					if(j+1 < alto){
						int B = maSalas[i][j+1].getIdentificador();
	//					System.out.println("grafo.getArco(" + A + ", " + B + ") == " + grafo.getArco(A, B));
						if (grafo.getArco(A, B) == 1){
							System.out.print(" ");
						}else{
							System.out.print("_");
						}
					}else{
						System.out.print("_");
					}
				}
				
				if(i+1 < ancho){
					int C = maSalas[i+1][j].getIdentificador();
//					System.out.println("grafo.getArco(" + A + ", " + C + ") == " + grafo.getArco(A, C));
					if (grafo.getArco(A, C) == 1){
						System.out.print(" ");
						
					}else{
						System.out.print("|");
					}
				}


			}
			//esto muestra la ultima culumna
			System.out.println("|");
		}
	}
    
	/**
	 * Metodo que muestra por pantalla un dibujo de las paredes de la planta
	 */
	public void pintarPlanta(){
		//Esto muestra la primera linea de arriba
		for(int i= 0; i<ancho; i++){
			out().print("_ ");
		}
		out().println();
		for(int j=0; j<alto;j++){
			//esto muestra la primera columna
			out().print("|");
			for(int i=0; i<ancho;i++){
				int A = maSalas[i][j].getIdentificador();
				
				if(!maSalas[i][j].ObtenerColaPersonas().isEmpty()){
					if(maSalas[i][j].ObtenerColaPersonas().size()>1) out().print(maSalas[i][j].ObtenerColaPersonas().size());
					else out().print(maSalas[i][j].ObtenerColaPersonas().peek().getIdentificador());
				}
				else{
					if(j+1 < alto){
						int B = maSalas[i][j+1].getIdentificador();
	//					System.out.println("grafo.getArco(" + A + ", " + B + ") == " + grafo.getArco(A, B));
						if (grafo.getArco(A, B) == 1){
							out().print(" ");
						}else{
							out().print("_");
						}
					}else{
						out().print("_");
					}
				}
				
				if(i+1 < ancho){
					int C = maSalas[i+1][j].getIdentificador();
//					System.out.println("grafo.getArco(" + A + ", " + C + ") == " + grafo.getArco(A, C));
					if (grafo.getArco(A, C) == 1){
						out().print(" ");
						
					}else{
						out().print("|");
					}
				}


			}
			//esto muestra la ultima culumna
			out().println("|");
		}
	}
	
	/**
	 * Metodo que llamara al metodo simular de la clase sala que es la encargada de llevar a cabo las acciones de los personajes
	 */
	public void simulacion(){
		//Mostrar recorridos
		for (int j = 0; j < alto; j++) {
			for (int i = 0; i < ancho; i++) {
				if(maSalas[i][j].cuantasPersonas()>0){
					maSalas[i][j].mostrarRecorridos();
				}
			}
		}
		//Simulacion:
		turno=0;
		boolean finSimulacion = false;
		while(turno<99&& !finSimulacion) {
//			System.out.println("Truno: "+(turno+1));
			turno++;
			pintarSimulacion(finSimulacion);
			for(int j=0;j<alto;j++){
				for(int i=0;i<ancho;i++){
					maSalas[j][i].Simular();
				}
			}
			if(!QuedanPersonas()) finSimulacion = true;
		}
		pintarSimulacion(true);
		this.cerrarFichero();
//		System.out.print("Simulacion guardada en el fichero");
	}
	private boolean QuedanPersonas(){
		for (int j = 0; j < alto; j++) {
			for (int i = 0; i < ancho; i++) {
				if(maSalas[i][j].hayPersonas())return true;
			}
		}
		return false;
	}
	private void pintarSimulacion(boolean fin){
		if (!fin){
			//(turno:<turno>)
			out().println("(turno:"+(turno)+")");
			//(salaescapados:1111)
			out().println("(salaescapados:"+salaEscapados.getIdentificador()+")");
			//<para cada miembro en la sala de escapados>
			//(<tipo de personaje>:<marca>:<id sala actual>:<turno_de_captura>: <llaves>)
			salaEscapados.mostrarPersonas();
			//<para cada planta de la estación>
			out().println("(planta:"+idPlanta+":"+salaInicio+":"+salaSalida+")");//(planta:<numero de planta>:<id sala entrada>:<id sala salida>)
			//(puerta:<estado>:<altura de apertura>: <llaves probadas>)
			out().print("(puerta:"+puerta.getEstado()+":"+altura+":");
			puerta.mostrarLlavesProbadas();
			out().println(")");
			//<pintar mapa 2D de la planta incluyendo marca de personajes1>
			pintarPlanta();
			//<para cada sala de la planta que contenga llaves>
			//(sala:<id sala>:<llaves>)
			mostrarLlaves();
			//<para cada personaje en la planta (orden de turno)>
			//(<tipo de personaje>:<marca>:<id sala actual>:<turno>: <llaves>)
			for (int j = 0; j < alto; j++) {
				for (int i = 0; i < ancho; i++) {
					if(maSalas[i][j].cuantasPersonas()>0){
						maSalas[i][j].mostrarPersonas();
					}
				}
			}
		}
		else{
			out().println("(fin de la simulacion)");
			out().println("(planta:"+idPlanta+":"+salaInicio+":"+salaSalida+")");
			out().print("(puerta:"+puerta.getEstado()+":"+altura+":");
			puerta.mostrarLlavesProbadas();
			out().println(")");
			pintarPlanta();
			mostrarLlaves();
			for (int j = 0; j < alto; j++) {
				for (int i = 0; i < ancho; i++) {
					if(maSalas[i][j].cuantasPersonas()>0){
						maSalas[i][j].mostrarPersonas();
					}
				}
			}
			out().println("(miembrosescapados)");
			salaEscapados.mostrarPersonas();
		}
	}
	/*
	public static void main (String args[]) {
		Planta planta = new Planta(0, 30, 10, 4, 0, 5);
		planta.mostrarPlanta();
		Intruso I = new Intruso("Pepito", 'P', 1);
		Lider L = new Lider("Juanillo", 'J', 1);
		I.mostrarRecorridoPorPantalla();
		L.mostrarRecorridoPorPantalla();
	}
	*/
}
