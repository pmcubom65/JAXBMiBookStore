//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.10 at 03:38:46 PM CET 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tipoPublicacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoPublicacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mes">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="anyo" type="{http://www.w3.org/2001/XMLSchema}gYear"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoPublicacion", propOrder = {

})
public class TipoPublicacion {

    protected int dia;
    protected int mes;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar anyo;

    /**
     * Gets the value of the dia property.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets the value of the dia property.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Gets the value of the anyo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnyo() {
        return anyo;
    }

    /**
     * Sets the value of the anyo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnyo(XMLGregorianCalendar value) {
        this.anyo = value;
    }

}
