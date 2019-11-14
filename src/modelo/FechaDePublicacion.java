package modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class FechaDePublicacion  implements Serializable  {

	int dia;
	int mes;
	int anyo;
	public FechaDePublicacion(int dia, int mes, int anyo) {
		
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}
	public FechaDePublicacion() {}
	
	
	@XmlElement(name="dia")
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	@XmlElement(name="mes")
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	@XmlElement(name="anyo")
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	@Override
	public String toString() {
		return "FechaDePublicacion [dia=" + dia + ", mes=" + mes + ", anyo=" + anyo + "]";
	}
	
	
}
