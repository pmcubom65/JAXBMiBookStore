package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

public class Libro implements Serializable {
	
	String isbn;
	String tipo;
	String titulo;
	ArrayList<String> autores=new ArrayList<>();
	
	String edicion;
	String fechadepublicacion;
	String categoria;
	double precio;
	
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Libro() {}
	
	
	public Libro(String isbn, String tipo, String titulo, ArrayList<String> autores, 
	String edicion, String fechadepublicacion, String categoria, double precio) {
		this.isbn=isbn;
		this.tipo=tipo;
		this.titulo=titulo;
		this.autores=autores;
		
		this.edicion=edicion;
		this.fechadepublicacion=fechadepublicacion;
		this.categoria=categoria;
		this.precio=precio;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Libro(String isbn) {
	
		this.isbn = isbn;
	}
	@XmlAttribute
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getFechadepublicacion() {
		return fechadepublicacion;
	}

	public void setFechadepublicacion(String fechadepublicacion) {
		this.fechadepublicacion = fechadepublicacion;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", tipo=" + tipo + ", titulo=" + titulo + ", autores=" + autores + ", edicion="
				+ edicion + ", fechadepublicacion=" + fechadepublicacion + ", categoria=" + categoria + ", precio="
				+ precio + "]";
	}











	
	
	

}