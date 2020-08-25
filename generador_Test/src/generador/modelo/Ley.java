package generador.modelo;

public class Ley {

	private String nombre;
	private int maxArt;
	
	
	public Ley(String nombre, int maxArt) {
		super();
		this.nombre = nombre;
		this.maxArt = maxArt;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getMaxArt() {
		return maxArt;
	}


	public void setMaxArt(int maxArt) {
		this.maxArt = maxArt;
	}


	@Override
	public String toString() {
		return "( Ley : nombre=" + nombre + ", maxArt=" + maxArt +" )" ;
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof Ley) {
				Ley l = (Ley) obj;
				return l.nombre.equals(this.nombre);
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}  
	
	
}
