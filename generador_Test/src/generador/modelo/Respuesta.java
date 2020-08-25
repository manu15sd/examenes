package generador.modelo;

public class Respuesta {

	private int idRespuesta;
	private String texto;
	private boolean esCorrecta;
	private char letra;
	
	public Respuesta( String texto, boolean esCorrecta) {
		this.texto = texto;
		this.esCorrecta = esCorrecta;
	}
	
	public int getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isEsCorrecta() {
		return esCorrecta;
	}
	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}
	
	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	@Override
	public String toString() {
		return "( Respuesta : idRespuesta=" + idRespuesta + ", texto=" + texto + ", esCorrecta=" + esCorrecta + " )";
	}
	
	public boolean equals (Object obj) {
		if(obj != null) {
			if( obj instanceof Respuesta) {
				Respuesta r = (Respuesta) obj;
				return r.idRespuesta == this.idRespuesta ;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}  
	
}
