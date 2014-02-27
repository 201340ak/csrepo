package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <b>DataConvert</b> is currently the main class.
 * The functions provided here will parse old data, and
 * convert them into either Json or XML.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

public class DataConvert {
	
	private final String PERSONS_DATA_FILE = "data/Persons.dat";
	private final String CUSTOMERS_DATA_FILE = "data/Customers.dat";
	private final String PRODUCTS_DATA_FILE = "data/Products.dat";
	private final String PERSONS_XML_FILE = "data/Persons.xml";
	private final String CUSTOMERS_XML_FILE = "data/Customers.xml";
	private final String PRODUCTS_XML_FILE = "data/Products.xml";
	private final String PERSONS_JSON_FILE = "data/Persons.json";
	private final String PRODUCTS_JSON_FILE = "data/Products.json";
	private final String CUSTOMERS_JSON_FILE = "data/Customers.json";
	private PersonsHub phub = new PersonsHub();
	private ProductsHub prhub = new ProductsHub();
	private CustomersHub chub = new CustomersHub();

	/**
	 * <b>DataConvert</b> constructor requires functionality parameters
	 * that determines which functions to use.
	 * 
	 * @param xmlOut - If true, will output XML in the console
	 * @param genXML - If true, will generate an XML file
	 * @param jsonOut - If true, will output JSON in the console
	 * @param genJson - If true, will generate an JSON file
	 */
	public DataConvert(boolean xmlOut, boolean genXML, boolean jsonOut, boolean genJson) {

		System.out.println("Parsing Persons data file...");
		parsePersonsDat();
		System.out.println("Parsing Customers data file...");
		parseCustomersDat();
		System.out.println("Parsing Product data file...");
		parseProductsDat();

		if (genXML == true) {

			System.out.println("\n**** Converting data files into XML ****");
			parsePersonsXml(xmlOut);
			parseCustomersXml(xmlOut);
			parseProductsXml(xmlOut);
			System.out.println("Conversion complete");
		}

		if (genJson == true) {
			
			System.out.println("\n**** Converting data files into JSON ****");
			parsePersonsJson(jsonOut);
			parseCustomersJson(jsonOut);
			parseProductsJson(jsonOut);
			System.out.println("Conversion complete");
		}
	}

	/**
	 * <i>parsePersonsXml</i> will generate an XML file using the parsed
	 * data from the old system.
	 * 
	 * @param xmlOut - If true, will output the XML in the console
	 */
	public void parsePersonsXml(boolean xmlOut) {
		
		System.out.println("Generating XML file: " + PERSONS_XML_FILE +"\n");
		try {
			JAXBContext cont = JAXBContext.newInstance(PersonsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(PERSONS_XML_FILE);
			marsh.marshal(phub, f);
			if (xmlOut == true) {
				marsh.marshal(phub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <i>parsePersonsJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * 
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	private void parsePersonsJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + PERSONS_JSON_FILE +"\n");
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(phub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(PERSONS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parsePersonsDat</i> will take the data from the old system
	 * and generate an instance of a person based on the business model
	 */
	public void parsePersonsDat() {
		
		Scanner s = null;
		try {
			s = new Scanner(new File(PERSONS_DATA_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");
				String code = tokens[0];

				if (tokens.length > 2) {
					String[] names = tokens[1].split(",");
					String[] address = tokens[2].split(",");
					String[] derp = { "N/A" };
					String[] emails = (tokens.length == 4) ? tokens[3]
							.split(",") : derp;
					Persons dudes = new Persons();
					dudes.setId(code);
					dudes.setFirstName(names[0]);
					dudes.setLastName(names[1]);
					dudes.setEmails(emails);

					ArrayList<Address> addressList = new ArrayList<Address>();
					Address adr = new Address();
					adr.setStreet(address[0]);
					adr.setCity(address[1]);
					adr.setState(address[2]);
					adr.setZip(address[3]);
					adr.setCountry(address[4]);
					addressList.add(adr);
					dudes.setAddress(adr);
					phub.addPersons(dudes);
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * <i>parseCustomersXml</i> will generate an XML file using the parsed
	 * data from the old system.
	 * 
	 * @param xmlOut - If true, will output the XML in the console
	 */
	public void parseCustomersXml(boolean xmlOut) {
		
		System.out.println("Generating XML file: " + CUSTOMERS_XML_FILE +"\n");
		try {
			JAXBContext cont = JAXBContext.newInstance(CustomersHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(CUSTOMERS_XML_FILE);
			marsh.marshal(chub, f);
			if (xmlOut == true) {
				marsh.marshal(chub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <i>parseCustomersJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * 
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	public void parseCustomersJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + CUSTOMERS_JSON_FILE +"\n");
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(chub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(CUSTOMERS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parseCustomerDat</i> will parse all the data from the old
	 * system and create Customer objects from the business model.
	 */
	public void parseCustomersDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(CUSTOMERS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");

				if (tokens.length > 4) {
					String code = tokens[0];
					String type = tokens[1];
					String id = tokens[2];
					String name = tokens[3];
					String[] address = tokens[4].split(",");

					ArrayList<Address> adrList = new ArrayList<Address>();
					Address adr = new Address();
					adr.setStreet(address[0]);
					adr.setCity(address[1]);
					adr.setState(address[2]);
					adr.setZip(address[3]);
					adr.setCountry(address[4]);
					adrList.add(adr);

					if (type.equals("G") || type.equals("g")) {
						GovComp gov = new GovComp();
						gov.setCode(code);
						gov.setName(name);
						gov.addAddress(adr);
						gov.addHumanRep(phub.getPersonInfo(id));
						chub.addGovList(gov);

					} else if (type.equals("C") || type.equals("c")) {
						PubComp pub = new PubComp();
						pub.setCode(code);
						pub.setName(name);
						pub.addAddress(adr);
						pub.addHumanRep(phub.getPersonInfo(id));
						chub.addPubList(pub);

					} else {
						System.out.println("Error: Invalid Company Type for: "
								+ name);
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * <i>parseProductsXml</i> will generate an XML file using the parsed
	 * data from the old system.
	 * 
	 * @param xmlOut - If true, will output the XML in the console
	 */
	public void parseProductsXml(boolean xmlOut) {
		
		System.out.println("Generating XML file: " + PRODUCTS_XML_FILE +"\n");
		try {
			JAXBContext cont = JAXBContext.newInstance(ProductsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(PRODUCTS_XML_FILE);
			marsh.marshal(prhub, f);
			if (xmlOut == true) {
				marsh.marshal(prhub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <i>parseProductsJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * 
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	public void parseProductsJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + PERSONS_JSON_FILE +"\n");
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(prhub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(PRODUCTS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parseProductsDat</i> will read the data file from the old
	 * system and create instances of all the products from the
	 * business model.
	 */
	public void parseProductsDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(PRODUCTS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");

				if (tokens.length > 3) {
					String code = tokens[0];
					String type = tokens[1];
					String name = tokens[2];
					String valueOne = tokens[3];
					String valueTwo = (tokens.length == 5) ? tokens[4] : null;

					if (type.equals("E") || type.equals("e")) {
						Equipment e = new Equipment();
						e.setCode(code);
						e.setName(name);
						e.setUnitPrice(valueOne);
						prhub.addEquipment(e);

					} else if (type.equals("L") || type.equals("l")) {
						License l = new License();
						l.setCode(code);
						l.setName(name);
						l.setPrice(valueOne);
						l.setAnnualPrice(valueTwo);
						prhub.addLicense(l);

					} else if (type.equals("C") || type.equals("c")) {
						Consultation c = new Consultation();
						c.setCode(code);
						c.setName(name);
						c.setHourPrice(valueTwo);
						c.addConsultList(phub.getPersonInfo(valueOne));
						prhub.addConsutation(c);

					} else {
						System.out.println("Error: Invalid Product Type for: "
								+ name);
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Main method, will be moved in phase 2!
	 * 
	 * @param args - This parameter is not used
	 */
	public static void main(String[] args) {
		
		boolean XML_CONSOLE_OUTPUT = false;
		boolean XML_GENERATE_DATA = true;
		boolean JSON_CONSOLE_OUTPUT = false;
		boolean JSON_GENERATE_DATA = true;

		new DataConvert(XML_CONSOLE_OUTPUT, XML_GENERATE_DATA,
				JSON_CONSOLE_OUTPUT, JSON_GENERATE_DATA);
	}
}