package Objetos;
/**
 * Implementacion de los metodos de la clase Pared.
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


public class Pared {
	public Pared(){
		this.x=0;
		this.y=0;
	}
	
	/** sala de origen */
	private int x;
	/** sala destino */
	private int y;
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public boolean iguales(Pared p, Pared t){
		if(p.x==t.x&&p.y==t.y)
			return true;
		else{
			if(p.x==t.y&&p.y==t.x)
				return true;
			else
				return false;
		}	
	}
}
