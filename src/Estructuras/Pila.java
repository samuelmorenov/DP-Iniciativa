package Estructuras;

// TODO: Auto-generated Javadoc

/**
 * Implementacion de los metodos de la clase Pila.
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
public class Pila <T extends Comparable<T>>{
	
	/** Dato almacenado en cada nodo */
	private T dato;
	
	/** Varible esvacia indica si la pila esta vacia o no*/
	private boolean esvacia;
	
	/** Puntero al siguiente elemento*/
	private Pila <T> siguiente;
	
	/**
	 * Metodo constructor por defecto de la clase Pila
	 */
	public Pila() {
		esvacia = true;
		siguiente = null;
	}

	
	/**
	 * Metodo constructor parametrizado de la clase Pila
	 *
	 * @param valor es el nuevo elemento en la pila
	 */
	Pila(T valor) {
		dato=valor;
		esvacia = false;
		siguiente = null;
	}
	
	/**
	 * Metodo que devuelve el elemento en la cima de la pila
	 *
	 * @return la cima de la pila
	 */
	public T getDatoCima(){
		return dato;
	}

	/**
	 * Metodo para comprobar si la pila esta vacia o no
	 *
	 * @return true si esta vacia o false en caso contrario
	 */
	public boolean estaVacia (){
		return esvacia;
	}
	

	/**
	 * Permite insertar un dato
	 *
	 * @param valor valor que se va a insertar
	 * @return referencia a la pila despues de insertar el nuevo valor
	 */
	public Pila <T>insertarDato(T valor ) {
		Pila <T>aux;
		if(estaVacia() == true) {
			dato = valor;
			esvacia=false;
			aux=this;
		}
		else {
				aux = new Pila<T>(valor);
				aux.siguiente = this;
		}
		return aux;
	}


	/**
	* Elimina un dato de la pila. Se elimina el dato que esta en la cima
	 * @return referencia a la pila despues de eliminar el valor el nuevo valor
	*/
	public Pila <T>sacarDato() {
		Pila <T>resultado=this;
		if(!estaVacia() ) {
			if(siguiente == null){
				esvacia = true;
			}
			else{
				resultado = siguiente;
			}
		}	
		return resultado;
	}

    /*
	public static void main (String args[]) {
//		Pila pila1 = new Pila();
		//Pruebas de inserciones al final y al inicio
		Pila <Llaves>lista1 = new Pila<Llaves>();
		Llaves a = new Llaves(51);
		Llaves b = new Llaves(10);
//		TipoDato c = new TipoDato(3);
//		TipoDato d = new TipoDato(7);
//		TipoDato e = new TipoDato(12);
//		TipoDato f = new TipoDato(1);
		
		lista1=lista1.insertarDato(a);
		lista1=lista1.insertarDato(b);
//		lista1=lista1.insertarOrdenadoLista(c);
//		lista1=lista1.insertarOrdenadoLista(7);
//		lista1=lista1.insertarOrdenadoLista(12);
//		lista1=lista1.insertarOrdenadoLista(1);
		
		
		//Mostrando la lista
		System.out.println(lista1.getDatoCima());
		lista1=lista1.sacarDato();
		System.out.println(lista1.getDatoCima());
//		System.out.println(lista1.siguiente.siguiente.consultarDato());
//		System.out.println(lista1.siguiente.siguiente.siguiente.consultarDato());
//		System.out.println(lista1.siguiente.siguiente.siguiente.siguiente.consultarDato());
//		System.out.println(lista1.siguiente.siguiente.siguiente.siguiente.siguiente.consultarDato());

	}
	*/
}
