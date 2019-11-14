package modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class Libros {
	
	private ArrayList<LibroConAnotaciones> libros;

	public Libros() {
		
	}
	public Libros(ArrayList<LibroConAnotaciones>  ml) {
		this.libros=ml;
	}
	@XmlElement(name="libro")
	public ArrayList<LibroConAnotaciones> getLibros() {
		return libros;
	}
	public void setLibros(ArrayList<LibroConAnotaciones> libros) {
		this.libros = libros;
	}
	
	
	
	
	
}
