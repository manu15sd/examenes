package generador.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tema {


	private String nombre;
	private int idTema;
	private List<String> listaSubtema;
	
	
	public Tema(String nombre, int idTema) {
		super();
		this.nombre = nombre;
		this.idTema = idTema;
		this.listaSubtema = new ArrayList<String>();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<String> getSubTema() {
		return listaSubtema;
	}


	public void setSubTema(List<String> subTema) {
		this.listaSubtema = subTema;
	}
	
	public int getIdTema() {
		return idTema;
	}


	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	
	public void anhadirSubtema(String subtema) {
		listaSubtema.add(subtema);
	}
	
	public void quitarSubtema(String subtema) {
		listaSubtema.remove(subtema);
	}
	


	@Override
	public String toString() {
		return "( Tema : nombre=" + nombre + ", subTema=" + listaSubtema + " )";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof Tema) {
				Tema t = (Tema) obj;
				return t.nombre.equals(this.nombre);
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}  
	
}
