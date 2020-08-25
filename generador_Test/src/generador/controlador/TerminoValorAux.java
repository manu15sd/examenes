package generador.controlador;

public class TerminoValorAux {

	@Override
	public String toString() {
		return "TerminoValorAux [termino=" + termino + ", valor=" + valor + "]";
	}
	private String termino;
	private String valor;
	
	
	public TerminoValorAux(String termino, String valor) {
		super();
		this.termino = termino;
		this.valor = valor;
	}
	public String getTermino() {
		return termino;
	}
	public void setTermino(String termino) {
		this.termino = termino;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
