package controlador;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.stream.FactoryConfigurationError;

import modelo.*;

public class Menu {
		public void menu() {
			System.out.print("Elija la opcion deseada:\n\t1 - Actualizar el Xml añadiendo nuevos datos con anotaciones JaxB");
			System.out.print("\n\t2 - Comprobar si esta bien formado con esquema.xsd");
			System.out.print("\n\t3 - Leer Xml y guardar los objetos utilizando anotaciones JAXB");
			System.out.print("\n\t4 - Actualizar el Xml añadiendo nuevos datos con las clases generadas automaticamente con librerias JAXB");
			System.out.print("\n\t5 - Leer Xml y guardar los objetos utilizando las clases generadas automaticamente con librerias JAXB");
			System.out.println("\n\t6 - Salir");
			Scanner sc =new Scanner(System.in);
			
			try {
				int opcion=sc.nextInt();
				opciones(opcion);
			} catch (InputMismatchException e) {
				
				opciones(0);
			}
		}
		
		public void opciones(int opcion) {
			GestionLibreriasConAnotaciones glca=new GestionLibreriasConAnotaciones();
			GestionLibreriasAugeneradas ga=new GestionLibreriasAugeneradas();
			switch (opcion) {
			case 1:
		
				try {
					glca.generarXmlConAnotaciones();
				} catch (PropertyException e2) {
					System.out.println("Error en el Xml");
				} catch (JAXBException e2) {
					System.out.println("Error en el Xml");
							}
				menu();
				break;
			case 2: 
				ValidadorXSLT sf=new ValidadorXSLT();
				String salida=(sf.validateXMLSchema()) ?"Esta bien formado":"No esta bien formado";
				System.out.println(salida);menu();break;
			case 3: 
	
				try {
					glca.crearArchivoObjetos();
				} catch (JAXBException e2) {
					System.out.println("Error en el Xml");
				}
				menu();
				break;
			case 4:
				
				try {
					ga.completarXml();
				} catch (Exception e1) {
					System.out.println("Error en la generacion del xml");
				}
				menu();
				break;
			case 5: 
	
				try {
					ga.generaListaLibros();
				} catch (FileNotFoundException e) {
					System.out.println("Error en el archivo xml");
				} catch (JAXBException e) {
					System.out.println("Error en la generacion del xml");
				} catch (FactoryConfigurationError e) {
					System.out.println("Error en la generacion del xml");
				}
			
			menu();
			break;
			
			case 6: System.exit(0);break;
			default: System.out.println("opcion no valida");menu(); break;
			}
		}
		
		
		
		
}
