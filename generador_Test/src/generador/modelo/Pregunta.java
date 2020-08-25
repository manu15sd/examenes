package generador.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pregunta {

	private String enunciado;
	private Autor autor;
	private Date fecha;
	private String img;
	private List<Respuesta> listaRespuestas;
	private int idPregunta;
	private String temaLey;
	private String nombreOposicion;
	
	public Pregunta(String enunciado, Autor autor, Date fecha, String img) {
		this.enunciado = enunciado;
		this.autor = autor;
		this.fecha = fecha;
		this.img = img;
		this.listaRespuestas = new ArrayList<Respuesta>();
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public List<Respuesta> getListaRespuestas() {
		return listaRespuestas;
	}
	public void setListaRespuestas(List<Respuesta> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}
	public int getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public String getTemaLey() {
		return temaLey;
	}

	public void setTemaLey(String temaLey) {
		this.temaLey = temaLey;
	}

	
	public String getNombreOposicion() {
		return nombreOposicion;
	}

	public void setNombreOposicion(String nombreOposicion) {
		this.nombreOposicion = nombreOposicion;
	}

	public void anhadirListaRespuestas(Respuesta r){
		if(r != null && r.getTexto() != "") {
			listaRespuestas.add(r);
		}
		
	}
	
	public void quitarListaRespuestas(Respuesta r){
		listaRespuestas.remove(r);
	}

	@Override
	public String toString() {
		return "'enunciado'=" + enunciado + ", 'autor'=" + autor + ", 'fecha'=" + fecha + ", 'img'=" + img
				+ ", 'listaRespuestas'=" + listaRespuestas + ", 'idPregunta'=" + idPregunta;
	}

	
	
	
	
	
}
