package generador.controlador;

import generador.modelo.Autor;

public class TemaPorcentajeAux {
	
	private String tema;
	private float porcentaje;
	private int numPreguntas;
	
	public TemaPorcentajeAux(String tema, float porcentaje) {
		super();
		this.tema = tema;
		this.porcentaje = porcentaje;
	}
	
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	
	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(float numPreguntas) {
		this.numPreguntas = Math.round(numPreguntas);
	}

	@Override
	public String toString() {
		return "TemaPorcentajeAux [tema=" + tema + ", porcentaje=" + porcentaje + "]";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof TemaPorcentajeAux) {
				TemaPorcentajeAux a = (TemaPorcentajeAux) obj;
				return a.tema.equals(this.tema);
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	} 

}
