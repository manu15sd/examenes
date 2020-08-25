package generador.modelo;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

	private int idOposicion;
	private String nombreOposicion;
	private List<Tema> listaTemas;
	private List<Ley> listaLey;
	
	
	public Grupo(int idOposicion, String nombreOposicion) {
		super();
		this.idOposicion = idOposicion;
		this.nombreOposicion = nombreOposicion;
		this.listaTemas = new ArrayList<Tema>();
		this.listaLey = new ArrayList<Ley>();
	}


	public int getIdOposicion() {
		return idOposicion;
	}


	public void setIdOposicion(int idOposicion) {
		this.idOposicion = idOposicion;
	}


	public String getNombreOposicion() {
		return nombreOposicion;
	}


	public void setNombreOposicion(String nombreOposicion) {
		this.nombreOposicion = nombreOposicion;
	}


	public List<Tema> getListaTemas() {
		return listaTemas;
	}


	public void setListaTemas(List<Tema> listaTemas) {
		this.listaTemas = listaTemas;
	}


	public List<Ley> getListaLey() {
		return listaLey;
	}


	public void setListaLey(List<Ley> listaLey) {
		this.listaLey = listaLey;
	}


	@Override
	public String toString() {
		return "( Grupo : idOposicion=" + idOposicion + ", nombreOposicion=" + nombreOposicion + ", listaTemas="
				+ listaTemas + ", listaLey=" + listaLey + " )";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof Grupo) {
				Grupo g = (Grupo) obj;
				return g.idOposicion ==  this.idOposicion;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	} 
	
	
	
	
}
