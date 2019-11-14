package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import controlador.ControlarDatosIntroducidos;

public class GestionLibreriasConAnotaciones {

	ArrayList<LibroConAnotaciones> listalibros = new ArrayList<>();

	public void generarXmlConAnotaciones() throws JAXBException, PropertyException {
		File f = new File("libros.xml");
		GestionLibreriasConAnotaciones glca = new GestionLibreriasConAnotaciones();
		ArrayList<LibroConAnotaciones> incorporar = glca.objetosDelXml();

		ControlarDatosIntroducidos cdi = new ControlarDatosIntroducidos();
		LinkedList<String> datos = cdi.crearLibro();

		LibroConAnotaciones lca = new LibroConAnotaciones();
		ArrayList<LibroConAnotaciones> allca = new ArrayList<>();
		LibroConAnotaciones libroconanotaciones = new LibroConAnotaciones();

		libroconanotaciones.setPrecio(Double.parseDouble(datos.get(8)));

		libroconanotaciones.setCategoria(datos.get(7));

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(datos.get(6), dtf);

		FechaDePublicacion fecha = new FechaDePublicacion(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear());
		libroconanotaciones.setFecha(fecha);

		int indice = 9;

		ArrayList<Autor> listaaut = new ArrayList<>();
		while (indice < datos.size()) {
			Autor pedro = new Autor();
			pedro.setPrimer_nombre(datos.get(indice));
			indice++;
			pedro.setSegundo_nombre(datos.get(indice));
			indice++;
			pedro.setApellido(datos.get(indice));
			indice++;
			listaaut.add(pedro);
		}

		libroconanotaciones.setAutores(listaaut);

		libroconanotaciones.setEdicion(datos.get(5));
		Titulo t = new Titulo();
		t.setName(datos.get(2));
		t.setTipo(datos.get(1));

		libroconanotaciones.setTitulo(t);
		libroconanotaciones.setIsbn(datos.get(0));

		allca.add(libroconanotaciones);
		allca.addAll(incorporar);

		Libros libross = new Libros(allca);
		JAXBContext jaxbcontext = JAXBContext.newInstance(Libros.class);
		Marshaller jaxbMarshaller = jaxbcontext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		JAXBElement<Libros> jaxbelement = new JAXBElement<Libros>(new QName("", "libros"), Libros.class, libross);
		jaxbMarshaller.marshal(jaxbelement, f);
		jaxbMarshaller.marshal(jaxbelement, System.out);
	}

	public ArrayList<LibroConAnotaciones> objetosDelXml() throws JAXBException {
		listalibros.clear();
		JAXBContext jaxbcontext = JAXBContext.newInstance(Libros.class);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
		Source s = new StreamSource(new File("libros.xml"));
		JAXBElement<Libros> root = (JAXBElement<Libros>) unmarshaller.unmarshal(s, Libros.class);
		Libros lib = (Libros) root.getValue();
		ArrayList<LibroConAnotaciones> total = (ArrayList<LibroConAnotaciones>) lib.getLibros();

		for (int i = 0; i < total.size(); i++) {
			LibroConAnotaciones libanot = (LibroConAnotaciones) total.get(i);
			String isbnobjeto = libanot.getIsbn();
			Titulo tituloobjeto = (Titulo) libanot.getTitulo();
			ArrayList<Autor> autoresobjeto = (ArrayList<Autor>) libanot.getAutores();
			String edicionobj = libanot.getEdicion();
			FechaDePublicacion fechadepublicacionobj = (FechaDePublicacion) libanot.getFecha();
			String categoriaobj = libanot.getCategoria();
			double precioobj = (double) libanot.getPrecio();
			LibroConAnotaciones miobjeto = new LibroConAnotaciones(isbnobjeto, tituloobjeto, autoresobjeto, edicionobj,
					fechadepublicacionobj, categoriaobj, precioobj);
			listalibros.add(miobjeto);

		}
		return listalibros;
	}

	public void crearArchivoObjetos() throws JAXBException {
		GestionLibreriasConAnotaciones glca = new GestionLibreriasConAnotaciones();
		ArrayList<LibroConAnotaciones> total = glca.objetosDelXml();
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("salidaclasesconanotaciones.dat")))) {

			total.forEach(i -> {
				try {
					oos.writeObject(i);
				} catch (IOException e) {

				}
			});
			System.out.println("Archivo actualizado salidaclasesconanotaciones.dat");
		} catch (IOException e) {

		}

		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(new File("salidaclasesconanotaciones.dat")))) {
			while (true) {
				try {
					try {
						LibroConAnotaciones objleido = (LibroConAnotaciones) ois.readObject();
						System.out.println(objleido);
					} catch (ClassNotFoundException e) {

					}
				} catch (EOFException eofe) {

					break;
				}
			}

		} catch (IOException e) {

		}

	}



}
