package controlador;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import generated.TipoLibro;
import generated.TipoLibros;

public class ControlarDatosIntroducidos {

	public LinkedList<String> crearLibro() {
		LinkedList<String> datos = new LinkedList<>();
		datos.clear();
		Scanner sc = new Scanner(System.in);
		boolean bucle = false;
		do {
			System.out.println("Introduzca isbn (restriccion I\\d{13}), no se puede repetir uno ya existente");
			String isbn = sc.nextLine();
			boolean isbncheck=false;
			try {
				isbncheck = verificarIsbn(isbn);
			} catch (Exception e) {
				System.out.println("error leyendo el xml");
			}
			
			
			if (isbncheck) {
				datos.add(isbn);
				bucle = false;
			} else {
				System.out.println("El isbn no cumple el formato I\\\\d{13} o ya esta repetido. Valdria por ejemplo I0000000000001");
				bucle = true;
			}
		} while (bucle);

		do {
			System.out.println("introduzca tipo puede ser o P o H");
			String tipo = sc.nextLine();
			if (comprobarTipo(tipo)) {
				datos.add(tipo.toUpperCase());
				bucle = false;
			} else {
				System.out.println("El tipo solo puede ser o P o H");
				bucle = true;

			}
		} while (bucle);

		System.out.println("Introduzca titulo");
		String titulo = sc.nextLine();
		datos.add(titulo);

		datos.add(" ");
		datos.add(" ");

		bucle = false;
		do {

			System.out.println("Introduzca edicion (tiene que ser un numero)");
			String edicion = sc.nextLine();

			if (comprobarEdicion(edicion)) {
				datos.add(edicion);
				bucle = false;
			} else {
				bucle = true;
			}

		} while (bucle);

		System.out.println("Introduzca fechapublicacion yyyy-MM-dd (Si no es correcta, se pondra la de hoy)");
		String fechapublicacion = sc.nextLine();
		LocalDate ld;

		try {
			ld = LocalDate.parse(fechapublicacion);
		} catch (Exception e) {
			ld = LocalDate.now();

		}
		datos.add(ld.toString());
		bucle = false;
		do {

			System.out.println("Introduzca categoria (tiene que ser o fiction o nonfiction)");
			String categoria = sc.nextLine();

			if (fictionOno(categoria)) {
				datos.add(categoria.toLowerCase());
				bucle = false;
			} else {
				bucle = true;
			}

		} while (bucle);
		boolean bucledos = false;
		do {
			System.out.println("Introduzca precio, tiene que ser tipo double");
			String precio = sc.nextLine();
			if (comprobarPrecio(precio)) {
				datos.add(precio);
				bucledos = false;
			} else {
				bucledos = true;
			}

		} while (bucledos);

		String numeroautores;
		do {

			System.out.println("Introduzca el numero de autores que tendra el libro 1, 2...");
			numeroautores = sc.nextLine();

			if (comprobarAutores(numeroautores)) {

				bucle = false;
			} else {
				bucle = true;
			}

		} while (bucle);

		int autoresnumero = Integer.parseInt(numeroautores);
		int miindice = 9;

		while (autoresnumero > 0) {
			System.out.println("Introduzca el primer nombre del autor");
			String primer_nombre = sc.nextLine();

			datos.add(miindice, String.format("%s", primer_nombre));
			miindice++;

			System.out.println("Introduzca el segundo nombre del autor\nSolo puede ser una letra (Se cogera la inicial) ");
			String segundo_nombre = sc.nextLine();

			datos.add(miindice, String.format("%s", segundo_nombre.toUpperCase().substring(0, 1)));
			miindice++;

			System.out.println("Introduzca el apellido del autor");
			String apellido = sc.nextLine();
			datos.add(miindice, String.format("%s", apellido));
			miindice++;
			autoresnumero--;
		}

		return datos;

	}

	private boolean comprobarPrecio(String precio) {
		try {
			Double.parseDouble(precio);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private boolean fictionOno(String categoria) {
		if (categoria.equalsIgnoreCase("fiction") || categoria.equalsIgnoreCase("nonfiction")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comprobarEdicion(String edicion) {
		try {
			Integer.parseInt(edicion);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean comprobarAutores(String edicion) {
		try {
			int totalautores = Integer.parseInt(edicion);
			if (totalautores > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	private boolean comprobarTipo(String tipo) {

		if (tipo.equalsIgnoreCase("P") || tipo.equalsIgnoreCase("H")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verificarIsbn(String isbn) throws Exception {
		if ((isbn.charAt(0) == 'I') || (isbn.charAt(0) == 'i')) {
			for (int i = 1; i < isbn.length(); i++) {
				if (!Character.isDigit(isbn.charAt(i))) {
					return false;
				}
			}

			JAXBContext jaxbcontext = JAXBContext.newInstance(generated.ObjectFactory.class);

			XMLInputFactory xif = XMLInputFactory.newInstance();
			FileInputStream fis = new FileInputStream("libros.xml");

			Source source = new StreamSource(fis);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

			JAXBElement<TipoLibros> root = (JAXBElement<TipoLibros>) unmarshaller.unmarshal(source, TipoLibros.class);

			ArrayList<TipoLibro> l = (ArrayList<TipoLibro>) ((TipoLibros) root.getValue()).getLibro();

			for (int lr = 0; lr < l.size(); lr++) {
				if (l.get(lr).getIsbn().equalsIgnoreCase(isbn)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
