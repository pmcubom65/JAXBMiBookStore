package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlType(propOrder= {"titulo","edicion","autores", "fecha", "categoria", "precio"})
public class LibroConAnotaciones implements Serializable {

private	String isbn;

	private Titulo titulo;
	private ArrayList<Autor> autores = new ArrayList<>();

	private String edicion;

	private String categoria;
	private double precio;
	private FechaDePublicacion fecha;

	@XmlElement(name = "precio")
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LibroConAnotaciones() {
	}

	public LibroConAnotaciones(String isbn, Titulo titulo, ArrayList<Autor> autores, String edicion,
			FechaDePublicacion fechadepublicacion, String categoria, double precio) {
		this.isbn = isbn;

		this.titulo = titulo;
		this.autores = autores;

		this.edicion = edicion;
		this.fecha = fechadepublicacion;
		this.categoria = categoria;
		this.precio = precio;
	}
	
	@XmlElement(name = "fecha_publicacion")
	public FechaDePublicacion getFecha() {
		return fecha;
	}

	public void setFecha(FechaDePublicacion fecha) {
		this.fecha = fecha;
	}

	@XmlElement(name = "tipo")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LibroConAnotaciones(String isbn) {

		this.isbn = isbn;
	}

	@XmlAttribute(name = "isbn")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@XmlElement(name = "autor")
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	@XmlElement(name = "edicion")
	public String getEdicion() {
		return edicion;
	}

	@XmlElement(name = "titulo")
	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	@Override
	public String toString() {
		return "LibroConAnotaciones [isbn=" + isbn + ", titulo=" + titulo + ", autores=" + autores + ", edicion="
				+ edicion + ", categoria=" + categoria + ", precio=" + precio + ", fecha=" + fecha + "]";
	}

}
