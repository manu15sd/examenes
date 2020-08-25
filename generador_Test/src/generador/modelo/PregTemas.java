package generador.modelo;

import java.sql.Date;

public class PregTemas extends Pregunta {

	private Tema tema;

	public PregTemas(String enunciado, Autor autor, Date fecha, String img, Tema tema) {
		super(enunciado, autor, fecha, img);
		this.tema = tema;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "{"+ super.toString() + "'tema'=" + tema + "}";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof PregTemas) {
				PregTemas p = (PregTemas) obj;
				return p.getIdPregunta() ==  this.getIdPregunta();
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	} 
	
}
