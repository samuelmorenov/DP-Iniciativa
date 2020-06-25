package Lugares;

/**
 * 
 * Implementacion de Singleton.
 * 
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
 public class Singleton {
	 
	 /**
	  * Inicializa la planta a null
	  */
	 public static Planta planta=null;
	 
	 protected Singleton(){
	 }
	 /**
	  * Metodo para poner la planta creada
	  * Se utiliza unicamente en el constructor de planta
	  * @param P: la instancia de la panta
	  */
	 public static void SetPlanta(Planta P){
		 planta=P;
	 }
	 /**
	  * Metodo que devuelve la instancia de la planta
	  * @return planta: la instancia de la panta
	  */
	 public static Planta GetPlanta(){
		 if (planta == null){
			    System.err.println("ERROR: Se esta intentando acceder a una planta no creada");
			}
			return planta;

	 }
}
