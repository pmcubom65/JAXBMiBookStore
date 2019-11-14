package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import controlador.ControlarDatosIntroducidos;
import generated.ObjectFactory;
import generated.TipoAutor;
import generated.TipoLibro;
import generated.TipoLibros;
import generated.TipoPublicacion;
import generated.TipoTipo;
import generated.TipoTitulo;

public class GestionLibreriasAugeneradas {

	LinkedList<Libro> listalibros = new LinkedList<>();

	@SuppressWarnings("unchecked")
	public void generaListaLibros() throws JAXBException, FactoryConfigurationError, FileNotFoundException {
		JAXBContext jaxbcontext = JAXBContext.newInstance(generated.ObjectFactory.class);

		XMLInputFactory xif = XMLInputFactory.newInstance();
		FileInputStream fis = new FileInputStream("libros.xml");

		Source source = new StreamSource(fis);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

		JAXBElement<TipoLibros> root = (JAXBElement<TipoLibros>) unmarshaller.unmarshal(source, TipoLibros.class);

		ArrayList<TipoLibro> l = (ArrayList<TipoLibro>) ((TipoLibros) root.getValue()).getLibro();
		l.forEach(i -> {
			Libro libro = new Libro();
			libro.setIsbn(i.getIsbn());
			String fecha = String.format("%d-%d-%d", i.getFechaPublicacion().getDia(), i.getFechaPublicacion().getMes(),
					i.getFechaPublicacion().getAnyo().getYear());
			libro.setFechadepublicacion(fecha);
			libro.setPrecio(i.getPrecio());
			libro.setEdicion(String.valueOf(i.getEdicion()));
			TipoTipo ttipo = (TipoTipo) i.getTipo();
			libro.setTipo(ttipo.name());
			TipoTitulo ttitulo = (TipoTitulo) i.getTitulo();
			String categoria = ttitulo.getTipo();
			libro.setCategoria(categoria);
			String mititulo = ttitulo.getValue();
			libro.setTitulo(mititulo);
			List<TipoAutor> losautores = (List<TipoAutor>) i.getAutor();
			ArrayList<String> nombres = new ArrayList<>();
			losautores.forEach(j -> {
				String autornuevo = String.format("%s %s %s", j.getPrimerNombre(), j.getSegundoNombre(),
						j.getApellido());
				nombres.add(autornuevo);

			});

			libro.setAutores(nombres);
			listalibros.add(libro);

		});
//		listalibros.forEach(ll -> System.out.println(ll));
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("salidaclasesautogenaradas.dat")))) {
			listalibros.forEach(r -> {
				try {
					oos.writeObject(r);
				} catch (IOException e) {
					System.out.println("Error en el archivo");
				}
			});
		} catch (IOException ioe) {
			System.out.println("Error en el archivo");
		}
		System.out.println("Archivo guardado. Revisar salidaclasesautogeneradas.dat");
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(new File("salidaclasesautogenaradas.dat")))) {
			while (true) {
				System.out.println((Libro) ois.readObject());
			}

		} catch (EOFException e) {

		}

		catch (IOException ioe) {
			System.out.println("error en el archivo");
		} catch (ClassNotFoundException c) {

		}

	}

	public void completarXml() throws Exception {

		ControlarDatosIntroducidos cdi = new ControlarDatosIntroducidos();
		LinkedList<String> datos = cdi.crearLibro();

		TipoTitulo tt = new TipoTitulo();
		tt.setTipo(datos.get(1));
		tt.setValue(datos.get(2));
		TipoTipo ttipo = (datos.get(7).equalsIgnoreCase(TipoTipo.FICTION.toString())) ? TipoTipo.FICTION
				: TipoTipo.NONFICTION;

		TipoPublicacion tipop = new TipoPublicacion();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse(datos.get(6), dtf);

		tipop.setDia(ld.getDayOfMonth());
		tipop.setMes(ld.getMonthValue());
		Calendar gc = GregorianCalendar.getInstance();
		gc.set(GregorianCalendar.YEAR, ld.getYear());
		tipop.setAnyo(
				DatatypeFactory.newInstance().newXMLGregorianCalendar(String.valueOf(gc.get(GregorianCalendar.YEAR))));
		

		List<TipoAutor> listaautores = new ArrayList<>();
		int computoautores=9;
		while (computoautores<datos.size()) {
			TipoAutor tautor = new TipoAutor();
			tautor.setPrimerNombre(datos.get(computoautores));
			computoautores++;
			tautor.setSegundoNombre(datos.get(computoautores));
			computoautores++;
			tautor.setApellido(datos.get(computoautores));
			computoautores++;
			listaautores.add(tautor);
		}
		
	
		
		TipoLibro tipolib = new TipoLibro();

		tipolib.setAutor(listaautores);
		tipolib.setEdicion(Integer.parseInt(datos.get(5)));
		tipolib.setFechaPublicacion(tipop);
		tipolib.setIsbn(datos.get(0));
		tipolib.setPrecio(Double.parseDouble(datos.get(8)));
		tipolib.setTipo(ttipo);
		tipolib.setTitulo(tt);

		TipoLibros tlibrost = new TipoLibros();
		tlibrost.getLibro().add(tipolib);

		JAXBContext jaxbcontext = JAXBContext.newInstance(generated.ObjectFactory.class);

		XMLInputFactory xif = XMLInputFactory.newInstance();
		FileInputStream fis = new FileInputStream("libros.xml");

		Source source = new StreamSource(fis);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

		JAXBElement<TipoLibros> root = (JAXBElement<TipoLibros>) unmarshaller.unmarshal(source, TipoLibros.class);

		ArrayList<TipoLibro> l = (ArrayList<TipoLibro>) ((TipoLibros) root.getValue()).getLibro();

		
		tlibrost.getLibro().addAll(l);

		ObjectFactory of = new ObjectFactory();

		JAXBContext jaxbContext = JAXBContext.newInstance(TipoLibros.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(of.createLibros(tlibrost), new FileOutputStream("libros.xml"));
		System.out.println("Xml compleatado de forma correcta. Revisar libros.xml");
	}

	
}
