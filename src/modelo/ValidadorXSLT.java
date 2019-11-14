package modelo;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidadorXSLT {
	
	
	public boolean validateXMLSchema() {
		try {
			javax.xml.validation.SchemaFactory factory=javax.xml.validation.SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema=factory.newSchema(new File("esquema.xsd"));
			Validator validator=schema.newValidator();
			validator.validate(new StreamSource("libros.xml"));
		} catch (SAXException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	



}
