package generador.modelo;

public class Autor {

	private String nombre;
	private int idAutor;

	public Autor(String nombre, int idAutor) {
		super();
		this.nombre = nombre;
		this.idAutor = idAutor;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	public int getIdAutor() {
		return idAutor;
	}


	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}


	@Override
	public String toString() {
		return "( nombre=" + nombre + " id:" + idAutor + ")";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof Autor) {
				Autor a = (Autor) obj;
				return a.nombre.equals(this.nombre);
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}  
	
	
}
