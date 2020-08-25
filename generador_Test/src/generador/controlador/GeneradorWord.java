package generador.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import generador.modelo.Pregunta;
import generador.modelo.Respuesta;

public class GeneradorWord {

	
	public void generarWord(List<Pregunta> listaPreguntas) throws IOException, InvalidFormatException{
		

		XWPFDocument document = new XWPFDocument(OPCPackage.open("D:/ProyectosWeb/proyectos/generador_Test/WebContent/Plantilla.dotx"));
		
		File archivo = new File("D:/ProyectosWeb/proyectos/generador_Test/WebContent/examen.docx");
		FileOutputStream out = new FileOutputStream( archivo);
		 
		
		 for(Pregunta preg :listaPreguntas) {
			 
			 XWPFParagraph pregunta = document.createParagraph();
			 pregunta.setAlignment(ParagraphAlignment.LEFT);
			 XWPFRun preguntaRun = pregunta.createRun();
			 pregunta.setStyle("Pregunta");
			 preguntaRun.setText(preg.getEnunciado());
			 
			 char letra = 'A';
			 
			 for(int i=0;i<preg.getListaRespuestas().size();i++) {
				 
				 preg.getListaRespuestas().get(i).setLetra(letra);
				 letra = (char)(letra + 1) ;
				 XWPFParagraph resp = document.createParagraph();
				 resp.setAlignment(ParagraphAlignment.LEFT);
				 XWPFRun respuestaRun = resp.createRun();
				 resp.setStyle("Respuesta");
				 respuestaRun.setText(preg.getListaRespuestas().get(i).getTexto());
				 respuestaRun.addCarriageReturn();
	
				 if(i== (preg.getListaRespuestas().size()-1)) {
					 respuestaRun.addCarriageReturn();
				 }
			 }

		 }
		 
		 XWPFParagraph solucionario = document.createParagraph();
		 solucionario.setAlignment(ParagraphAlignment.CENTER);
		 XWPFRun solRun = solucionario.createRun();
		 solRun.addBreak(BreakType.PAGE);
		 solucionario.setStyle("solucionario");
		 solRun.setText("Solucionario");
		 
		 for(int j=0;j<listaPreguntas.size();j++) {
			 for(Respuesta resp : listaPreguntas.get(j).getListaRespuestas()) {
				 if(resp.isEsCorrecta() == true) {
					 XWPFParagraph respSol = document.createParagraph();
					 respSol.setAlignment(ParagraphAlignment.LEFT);
					 XWPFRun respuestaSolRun = respSol.createRun();
					 respSol.setStyle("respSolu");
					 respuestaSolRun.setText(resp.getLetra()+"");
				 }
			 }
		 }
		 
	     document.write(out);
	     out.flush();
	     out.close();
	     document = new XWPFDocument();
	    
		
	}

	public List<Pregunta> leerWord(InputStream fileContent) throws InvalidFormatException, IOException {
		
		XWPFDocument document = new XWPFDocument(OPCPackage.open(fileContent));
		XWPFWordExtractor extractor = new XWPFWordExtractor(document);
		List<XWPFParagraph> paragraphList = document.getParagraphs();
		List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
		Pregunta pregunta = null;
		
		for (XWPFParagraph paragraph : paragraphList) {
			if(paragraph.getStyle().equals("Pregunta")) {
				
				if(pregunta != null) {
					listaPreguntas.add(pregunta);
				}
				
				String enunciado = paragraph.getText();
				long date = System.currentTimeMillis();
				Date fecha = new Date(date);
				
				pregunta = new Pregunta(enunciado, null,fecha , null);
				
			}
			if(paragraph.getStyle().equals("Respuesta")) {
				
				String texto = paragraph.getText();
				Respuesta respuesta = new Respuesta(texto, false);
				pregunta.anhadirListaRespuestas(respuesta);
				
			}
			
		}
		
		if(pregunta != null) {
			listaPreguntas.add(pregunta);
		}
		
		return listaPreguntas;
		
	}
}
