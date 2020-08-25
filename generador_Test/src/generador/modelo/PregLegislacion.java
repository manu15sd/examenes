package generador.modelo;

import java.sql.Date;

public class PregLegislacion extends Pregunta {

	private Ley ley;
	private int art;
	private boolean disposicion;
	
	

	public PregLegislacion(String enunciado, Autor autor, Date fecha, String img, Ley ley, int art,
			boolean disposicion) {
		super(enunciado, autor, fecha, img);
		this.ley = ley;
		this.art = art;
		this.disposicion = disposicion;
	}


	public Ley getLey() {
		return ley;
	}

	public void setLey(Ley ley) {
		this.ley = ley;
	}

	public int getArt() {
		return art;
	}


	public void setArt(int art) {
		this.art = art;
	}


	public boolean isDisposicion() {
		return disposicion;
	}


	public void setDisposicion(boolean disposicion) {
		this.disposicion = disposicion;
	}
	
	

	@Override
	public String toString() {
		return "{" + super.toString() + " 'ley'=" + ley + "}";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof PregLegislacion) {
				PregLegislacion p = (PregLegislacion) obj;
				return p.getIdPregunta() ==  this.getIdPregunta();
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}  
	
	
	
	
	
}
