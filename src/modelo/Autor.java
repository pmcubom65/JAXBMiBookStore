package modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder= {"primer_nombre","segundo_nombre","apellido"})
public class Autor  implements Serializable {
	private String primer_nombre;
	private String segundo_nombre;
	private String apellido;


	
	public Autor(String primer_nombre, String segundo_nombre, String apellido) {
		super();
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.apellido = apellido;
	}



	public Autor() {}
	
	
	
	@XmlElement(name="primer_nombre")
	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}
	
	
	@XmlElement(name="segundo_nombre")
	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	
	@XmlElement(name="apellido")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	@Override
	public String toString() {
		return "Autor [primer_nombre=" + primer_nombre + ", segundo_nombre=" + segundo_nombre + ", apellido=" + apellido
				+ "]";
	}
	
	
	
	
}
