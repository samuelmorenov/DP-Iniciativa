package Estructuras;

import Lugares.Planta;
import Lugares.Singleton;



/**
 * Implementacion de arbol binario de busqueda.
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
public class Arbol <T extends Comparable<T>>{

	/** Dato almacenado en cada nodo del arbol. */
	private T datoRaiz;
	
	/** Atributo que indica si el arbol esta vacio. */
	boolean esVacio;
	
	/** Hijo izquierdo del nodo actual */
	private Arbol <T> hIzq;
	
	/** Hijo derecho del nodo actual */
	private Arbol <T> hDer;
	
	/**
	 * Constructor por defecto de la clase. Inicializa un arbol vacio.
	 */
	public Arbol(){
	    this.esVacio=true;
	    this.hIzq = null;
	    this.hDer = null;
	}

	/**
	 * Crea un nuevo arbol a partir de los datos pasados por parï¿½metro.
	 *
	 * @param hIzq El hijo izquierdo del arbol que se esta creando 
	 * @param datoRaiz Raiz del arbol que se esta creando
	 * @param hDer El hijo derecho del arbol que se esta creando
	 */
	public Arbol (Arbol <T> hIzq, T datoRaiz, Arbol <T> hDer){
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer=hDer;
	}
	
	/**
	 * Devuelve el hijo izquierdo del arbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol <T> getHijoIzq(){
		return hIzq;
	}
	
	/**
	 * Devuelve el hijo derecho del arbol
	 *
	 * @return Hijo derecho del arbol
	 */
	public Arbol <T> getHijoDer(){
		return hDer;
	}
	
	/**
	 * Devuelve la raiz del arbol
	 *
	 * @return La raiz del arbol
	 */
	public T getRaiz(){
		return datoRaiz;
	}
	
	/**
	 * Comprueba si el arbol esta vacio.
	 * @return verdadero si el arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio(){
		return esVacio;
	}
	
	/**
	 * Inserta un nuevo dato en el arbol.
	 *
	 * @param dato El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso contrario
	 */
	public boolean insertar(T dato){
	    boolean resultado=true;
	    if (vacio()) {
	    	datoRaiz = dato;
			esVacio = false;
		}
	    else {
	        if (!(this.datoRaiz.equals(dato))) {
	            Arbol<T> aux;
	            if (dato.compareTo(this.datoRaiz)<0) { //dato < datoRaiz
	                if ((aux=getHijoIzq())==null)
	                    hIzq = aux = new Arbol <T>();
	            }
	            else {									//dato > datoRaiz
	                if ((aux=getHijoDer())==null)
	                    hDer = aux = new Arbol <T>();
	            }
	            resultado = aux.insertar(dato);
	        }
	        else
	            resultado=false;
	    }
	    return resultado;
	}
	
	/**
	 * Comprueba si un dato se encuentra almacenado en el arbol
	 *
	 * @param dato El dato a buscar
	 * @return verdadero si el dato se encuentra en el arbol, falso en caso contrario
	 */
	public boolean pertenece(T dato){
	    Arbol <T> aux=null;
	    boolean encontrado=false;
	    if (!vacio()) {
	        if (this.datoRaiz.compareTo(dato)==0)
	            encontrado = true;
	        else {
	            if (dato.compareTo(this.datoRaiz)<0)	//dato < datoRaiz
	                aux=getHijoIzq();
	            else									//dato > datoRaiz
	                aux = getHijoDer();
	            if (aux!=null)
	                encontrado = aux.pertenece(dato);
	        }
	    }
	    return encontrado;
	}
	
	/**
	 * Borrar un dato del arbol.
	 *
	 * @param dato El dato que se quiere borrar
	 */
	public void borrar(T dato){
	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0){			//dato<datoRaiz
					hIzq = hIzq.borrarOrden(dato);
			}	
	        else
	            if (dato.compareTo(this.datoRaiz)>0) {		//dato>datoRaiz 
	            		hDer = hDer.borrarOrden(dato);
				}
	            else //En este caso el dato es datoRaiz
	            {
	                if (hIzq==null && hDer==null)
	                {
	                    esVacio=true;
	                }
	                else
	                    borrarOrden(dato);
	            }
	    }
	}
	

	/**
	 * Borrar un dato. Este metodo es utilizado por el metodo borrar anterior.
	 *
	 * @param dato El dato a borrar
	 * @return Devuelve el arbol resultante despues de haber realizado el borrado
	 */
	private Arbol <T> borrarOrden(T dato)
	{
	    T datoaux;
	    Arbol <T> retorno=this;
	    Arbol <T> aborrar, candidato, antecesor;

	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0){		// dato<datoRaiz
		    	if (hIzq != null)        
	        		hIzq = hIzq.borrarOrden(dato);
	        }
			else
	            if (dato.compareTo(this.datoRaiz)>0) {	// dato>datoRaiz
			    	if (hDer != null)        
	    	           hDer = hDer.borrarOrden(dato);
	            }
				else {
	                aborrar=this;
	                if ((hDer==null)&&(hIzq==null)) { /*si es hoja*/
	                    retorno=null;
	                }
	                else {
	                    if (hDer==null) { /*Solo hijo izquierdo*/
	                        aborrar=hIzq;
	                        datoaux=this.datoRaiz;
	                        datoRaiz=hIzq.getRaiz();
	                        hIzq.datoRaiz = datoaux;
	                        hIzq=hIzq.getHijoIzq();
	                        hDer=aborrar.getHijoDer();

	                        retorno=this;
	                    }
	                    else
	                        if (hIzq==null) { /*Solo hijo derecho*/
	                            aborrar=hDer;
	                            datoaux=datoRaiz;
	                            datoRaiz=hDer.getRaiz();
	                            hDer.datoRaiz = datoaux;
	                            hDer=hDer.getHijoDer();
	                            hIzq=aborrar.getHijoIzq();

	                            retorno=this;
	                        }
	                        else { /* Tiene dos hijos */
	                            candidato = this.getHijoIzq();
	                            antecesor = this;
	                            while (candidato.getHijoDer()!=null) {
	                                antecesor = candidato;
	                                candidato = candidato.getHijoDer();
	                            }

	                            /*Intercambio de datos de candidato*/
	                            datoaux = datoRaiz;
	                            datoRaiz = candidato.getRaiz();
	                            candidato.datoRaiz=datoaux;
	                            aborrar = candidato;
	                            if (antecesor==this)
	                                hIzq=candidato.getHijoIzq();
	                            else
	                                antecesor.hDer=candidato.getHijoIzq();
	                        } //Eliminar solo ese nodo, no todo el subarbol
	                    aborrar.hIzq=null;
	                    aborrar.hDer=null;
	                }
	            }
	    }
	    return retorno;
	}
	
	
	/**
	 * Recorrido inOrden del arbol.
	 */
	public void inOrden(){
		Planta planta= Singleton.GetPlanta();
	    Arbol <T> aux=null;
	    if (!vacio()) {
	        if ((aux=getHijoIzq())!=null) {
	            aux.inOrden();
	        }    
	      
	        planta.out().print(" "+this.datoRaiz);
	        
	        if ((aux=getHijoDer())!=null){
	            aux.inOrden();
	        }    
	    }
	}
	
	/**
	 * Profundidad del arbol
	 * 
	 * @return Devuelve el valor de la profundidad del arbol
	 */
	public int profundidad(){
		int P=0, pi=0, pd=0;
		if (!vacio()){
			P = P + 1;
	    	if (hIzq != null) pi = getHijoIzq().profundidad();
	    	if (hDer != null) pd = getHijoDer().profundidad();
	    	if (pi > pd) P = P + pi;
	    	else P = P + pd;
		}
		return P;
	}
	
	/**
	 * Elemento en un nodo hoja
	 * 
	 * @param dato El dato a borrar
	 * @return Devuelve true si el elemento a buscar es una hoja
	 */
	public boolean elementoHoja(T dato){
	    boolean esHoja=false;
		if (this.pertenece(dato)){
	        if (this.datoRaiz.equals(dato)){
	        	if (getHijoIzq() == null && getHijoDer() == null) esHoja = true;
	        }
	        else {
	        	if (getHijoIzq().elementoHoja(dato) == true || getHijoDer().elementoHoja(dato) == true){
	        		esHoja = true;
	        	}
	        }
		}
		return esHoja;
	}
	
	/**
	 * Numero de elementos hoja
	 *
	 * @return Devuelve el numero de hojas
	 */
	public int numeroHojas(){
	    int N=0, ni=0, nd=0;
	    Arbol <T> aux=null;
	    if (!vacio()) {
	        if ((aux=getHijoIzq())!=null) ni = aux.numeroHojas();
	        if ((aux=getHijoDer())!=null) nd = aux.numeroHojas();
	        if ((aux=getHijoDer())==null && (aux=getHijoIzq())==null)N = 1;
	        else N = ni + nd;
	    }
		return N;
	}
	/**
	 * Destruye el arbol
	 */
	//Nuevo metodo!!
	public void destruirArbol(){
		hDer=null;
		hIzq=null;
		esVacio=true;
	}
	// calcula la profundidad de un árbol
	public int calcularAltura() {
		if(esVacio) return 1;
		int AI = 0;
		if(hIzq != null) AI = hIzq.calcularAltura();
		int AD = 0;
		if(hDer != null) AD = hDer.calcularAltura();
		if(AI>AD) return AI+1;
		else return AD+1;
	}
}


