package modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Titulo  implements Serializable  {

	String name;
	String tipo;

	public Titulo(String name) {
		
		this.name = name;
	}
	
	
	public Titulo() {}

	@XmlValue
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="tipo")
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "Titulo [name=" + name + ", tipo=" + tipo + "]";
	}
	
	
	
}
