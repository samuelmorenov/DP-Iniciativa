package Objetos;


/**
 * Implementacion de los metodos de la clase Lider.
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


public class Llaves implements Comparable <Llaves>{
	/**
	 * Indentificador de la llave
	 */
	private Integer identificador;
	/**
	 * Identificador que dice en que planta esta la llave.
	 */
	private Integer id_Planta;
	/**
	 * Constructor por defecto de la clase.
	 */
	Llaves(){
		this.identificador = 0;
		this.id_Planta=0;
	}
	/**
	 * Constructor parametrizado de la clase.
	 * @param s Valor que tendra el identificador.
	 */
	public Llaves(Integer s){
		this.identificador=s;
		this.id_Planta=0;
	}
	/**
	 * Constructor parametrizado de la clase.
	 * @param s Valor que tendra el identificador.
	 * @param t Valor que tendra id_Planta.
	 */
	public Llaves(Integer s,Integer t){
		this.identificador=s;
		this.id_Planta=t;
	}
	/**
	 * Modifica el identificador a valor
	 * @param valor, nuevo valor del atributo identificador.
	 */
	public void setIdentificador(Integer valor){
		this.identificador=valor;
	}
	/**
	 * Devuelve el identificador de la llave.
	 * @return identificador del objeto.
	 */
	public Integer getIdentificador(){
		return this.identificador;
	}
	/**
	 * Modifica el id_Planta a valor.
	 * @param valor, nuevo calor del atributo id_Planta.
	 */
	public void setId_Planta(Integer valor){
		this.id_Planta=valor;
	}
	/**
	 * Devuelve el id_Planta de la llave.
	 * @return id_Planta del objeto.
	 */
	public Integer getId_Planta(){
		return this.id_Planta;
	}
	/**
	 * Comparador de la clase Llaves.
	 */
	public int compareTo(Llaves l){
		if(this.identificador==l.getIdentificador()){
			return 0;	
		}
		if(this.identificador<l.getIdentificador()){
			return -1;
		}
		return 1;
	}
	/**
	 * Metodo que pasa el identificador de la clase a String.
	 */
	public String toString(){
		return  Integer.toString(identificador);
	}
}
