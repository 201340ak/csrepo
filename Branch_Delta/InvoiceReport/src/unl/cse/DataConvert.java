package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	private final String INVOICES_DATA_FILE = "data/Invoices.dat";
	private final String PERSONS_XML_FILE = "data/Persons.xml";
	private final String CUSTOMERS_XML_FILE = "data/Customers.xml";
	private final String PRODUCTS_XML_FILE = "data/Products.xml";
	private final String PERSONS_JSON_FILE = "data/Persons.json";
	private final String PRODUCTS_JSON_FILE = "data/Products.json";
	private final String CUSTOMERS_JSON_FILE = "data/Customers.json";
	private PersonsHub phub = new PersonsHub();
	private ProductsHub prhub = new ProductsHub();
	private CustomersHub chub = new CustomersHub();
	private InvoicesHub ihub = new InvoicesHub();

	/**
	 * <b>DataConvert</b> constructor requires functionality parameters
	 * that determines which functions to use.
	 * 
	 * @param xmlOut - If true, will output XML in the console
	 * @param genXML - If true, will generate an XML file
	 * @param jsonOut - If true, will output JSON in the console
	 * @param genJson - If true, will generate an JSON file
	 */
	public DataConvert(boolean xmlOut, boolean genXML, boolean jsonOut, boolean genJson, boolean genTxt) {

		System.out.println("Parsing Persons data file...");
		parsePersonsDat();
		System.out.println("Parsing Customers data file...");
		parseCustomersDat();
		System.out.println("Parsing Product data file...");
		parseProductsDat();
		System.out.println("Parsing Invoice data file...");
		parseInvoicesDat();
		

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
		
		if (genTxt == true) {
			System.out.println("\n**** Generating Invoices ****");
			parseInvoicesTxt();
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

	public void parseInvoicesTxt() {
		System.out.println("~=====================~");
		System.out.println("| Summary of Invoices |");
		System.out.println("~=====================~");
		
		//Printing Table Header for Invoice Summary
		System.out.print("\n+------+"); //ID
		System.out.print("----------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("-------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("-------------+\n"); // Total
		
		System.out.print("|");
		System.out.printf("%2s%2s%2s","","ID","");
		System.out.print("|");
		
		System.out.printf("%19s%8s%19s","", "Customer", "");
		System.out.print("|");
		
		System.out.printf("%9s%12s%9s", "", "Sales person", "");
		System.out.printf("|");
		
		System.out.printf("%2s%8s%3s", "", "Subtotal", "");
		System.out.print("|");
		
		System.out.printf("%4s%4s%5s", "", "Fees", "");
		System.out.print("|");
		
		System.out.printf("%4s%5s%4s", "", "Taxes", "");
		System.out.print("|");
		
		System.out.printf("%4s%5s%4s", "", "Total", "");
		System.out.print("|\n");
		
		System.out.print("+------+"); //ID
		System.out.print("----------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("-------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("-------------+\n"); // Total
		
		// Iterate through Invoice list and print summary details
		for (Invoices i : ihub.getCollection()) {
			double size = 0.0;
			System.out.print("|");
			System.out.print(i.getInCode());
			
			//Company name
			String company = chub.getCompName(i.getCustomCode());
			size = company.length();
			int align = 0;
			if (((46.0 - size) / 2.0) != (Math.floor((46.0 - size) / 2.0))) {
				align = 1;
			}
			String format = "%"+(Math.floor((((46 - company.length()) / 2)+align)))+"s%"+company.length()+"s%"+
			(Math.floor(((46 - company.length()) / 2)))+"s";
			System.out.print("|");
			System.out.printf(format, "", company, "");
			
			//Sales person name
			String name = phub.getLastName(i.getSalesCode())+" "+phub.getFirstName(i.getSalesCode());
			size = name.length();
			align = 0;
			if (((30.0 - size) / 2.0) != (Math.floor((30.0 - size) / 2.0))) {
				align = 1;
			}
			format = "%"+(Math.floor((((30 - name.length()) / 2)+align)))+"s%"+name.length()+"s%"+
			(Math.floor(((30 - name.length()) / 2)))+"s";
			System.out.print("|");
			System.out.printf(format, "", name, "");
			
			//Subtotal
			double subtotal = i.getSubTotal();
			String subtotalF = "N/A";
			try {
				subtotalF = String.format("%.2f", subtotal).trim();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			size = subtotalF.length();
			align = 0;
			
			if (((12.0 - size) / 2.0) != (Math.floor((12.0 - size) / 2.0))) {
				align = 1;
			}

			format = "%"+(Math.floor((((12 - subtotalF.length()) / 2)+align)))+"s%"+subtotalF.length()+"s%"+
			(Math.floor(((12 - subtotalF.length()) / 2)))+"s";
			System.out.print("|");
			
			try {
				System.out.printf(format, "", "$"+subtotalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Fees
			boolean complyFee = i.getHasComplyFee();
			double fee = i.getFee();
			
			if (complyFee == true) {
				fee += 125.00;
			}
			String feeF = "N/A";
			try {
				feeF = String.format("%.2f", fee).trim();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			size = feeF.length();
			align = 0;
			
			if (((12.0 - size) / 2.0) != (Math.floor((12.0 - size) / 2.0))) {
				align = 1;
			}

			format = "%"+(Math.floor((((12 - feeF.length()) / 2)+align)))+"s%"+feeF.length()+"s%"+
			(Math.floor(((12 - feeF.length()) / 2)))+"s";
			System.out.print("|");
			
			try {
				System.out.printf(format, "", "$"+feeF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Taxes
			double taxes = 0.00;
			
			if (complyFee == false) {
				taxes = i.getTaxes();
			}
			
			String taxF = "N/A";
			try {
				taxF = String.format("%.2f", taxes).trim();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			size = taxF.length();
			align = 0;
			
			if (((12.0 - size) / 2.0) != (Math.floor((12.0 - size) / 2.0))) {
				align = 1;
			}

			format = "%"+(Math.floor((((12 - taxF.length()) / 2)+align)))+"s%"+taxF.length()+"s%"+
			(Math.floor(((12 - taxF.length()) / 2)))+"s";
			System.out.print("|");
			
			try {
				System.out.printf(format, "", "$"+taxF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Total
			double total = subtotal + fee + taxes;
			String totalF = "N/A";
			try {
				totalF = String.format("%.2f", total).trim();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			size = totalF.length();
			align = 0;
			
			if (((12.0 - size) / 2.0) != (Math.floor((12.0 - size) / 2.0))) {
				align = 1;
			}

			format = "%"+(Math.floor((((12 - totalF.length()) / 2)+align)))+"s%"+totalF.length()+"s%"+
			(Math.floor(((12 - totalF.length()) / 2)))+"s";
			System.out.print("|");
			
			try {
				System.out.printf(format, "", "$"+totalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			System.out.print("|");
			
			
			System.out.print("\n");
			System.out.print("+------+"); //ID
			System.out.print("----------------------------------------------+"); //Customer
			System.out.print("------------------------------+"); // Sales person
			System.out.print("-------------+"); // Subtotal
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxes
			System.out.print("-------------+\n"); // Total
		}
				
		System.out.print("\n");
		System.out.println("~============================~");
		System.out.println("| Individual Invoice Reports |");
		System.out.println("~============================~");
		System.out.print("\n");

		// Iterate through Invoice list and print details
		for (Invoices i : ihub.getCollection()) {
			System.out.println("++--------++");
			System.out.printf("%3s%6s%3s", "|| ", i.getInCode()," ||\n");
			System.out.println("++--------++");
			
			String name = phub.getLastName(i.getSalesCode())+" "+phub.getFirstName(i.getSalesCode());
			System.out.printf("\n\t%14s%-30s", "Sales person: ", name);
			System.out.print("\n");
			
			String company = chub.getCompName(i.getCustomCode());
			System.out.println("\t[]---------------[]");
			System.out.printf("\t%3s%13s%3s", "|| ", "Customer Data"," ||\n");
			System.out.println("\t[]---------------[]\n\t:");
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Name: ", company+" ("+i.getCustomCode()+")");
	
			//customer data
			List<Address> tempAddr = new ArrayList<Address>();
			List<Persons> tempDude = new ArrayList<Persons>();
			tempAddr = chub.getCompAddr(i.getCustomCode());
			tempDude = chub.getHumanRep(i.getCustomCode());
			String street = "N/A";
			String city = "N/A";
			String country = "N/A";
			String state = "N/A";
			String zip = "N/A";
			String firstName = "N/A";
			String lastName = "N/A";
			String dudeId = "N/A";
			for (Address s : tempAddr) {
				street = s.getStreet();
				city = s.getCity();
				country = s.getCountry();
				state = s.getState();
				zip = s.getZip();
			}
			for (Persons p : tempDude) {
				firstName = p.getFirstName();
				lastName = p.getLastName();
				dudeId = p.getId();
			}
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Country: ", country);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "State: ", state);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "City: ", city);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Street: ", street);
			System.out.printf("\t%1s==> %-10s%-46s\n\t:\n", ":", "Zip Code: ", zip);
			System.out.printf("\t%1s=====> %-16s%-46s\n", ":", "Human Resource: ", lastName+", "+firstName+" ("+dudeId+")");
			System.out.print("\n");
			
			//invoice data
			System.out.println("\t[]---------------[]");
			System.out.printf("\t%3s%12s%3s", "|| ", "Invoice Data"," ||\n");
			System.out.println("\t[]---------------[]\n\t:");
			
			List<String> codes = i.getCodes(i.getCustomCode());
			for (String c : codes) {
				System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Code: ", c);
				System.out.printf("\t%1s==> %-10s%-46s\n\t:\n", ":", "Product: ", prhub.getNameById(c));
				//System.out.printf("\t%1s=====> %-7s%-14s%-7s%-14s\n\t:\n", ":", "Fee: ", i.getFee(), "Total: ", "7");
			}
			
			System.out.print("\n");
		}
		
	}
		
	/**
	 * <i>parsePersonsDat</i> will take the data from the old system
	 * and generate an instance of an <b>Invoice</b> based on the business model
	 */
	public void parseInvoicesDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(INVOICES_DATA_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");
				String inCode = tokens[0]; //invoiceCode
				String customCode = tokens[1]; //customers code
				String salesCode = tokens[2]; //salesCode
				String[] prodString = tokens[3].split(",");
				List<String> prodList = Arrays.asList(prodString);
				
				Invoices i = new Invoices();
				i.setInCode(inCode);
				i.setCustomCode(customCode);
				char customType = chub.getCompType(customCode);
				i.setCustomType(customType);
				i.setSalesCode(salesCode);
				
				if (customType == 'G') {
					i.setHasComplyFee(true);
				} else {
					i.setHasComplyFee(false);
				}
				
				for (String prods : prodList) {
					List<String> info = Arrays.asList(prods.split(":"));
					
					if(info.size() == 3) {
						i.setProdLicense(prhub.getLiceYrPrice(info.get(0)),prhub.getLicePrice(info.get(0)), info, customType);
					} else {
						if (!(prhub.getConsultListById(info.get(0)).isEmpty())) {
							i.setProdConsult(prhub.getConsultPrice(info.get(0)), info, customType);
						} else if (!(prhub.getEquipListById(info.get(0)).isEmpty())){
							i.setProdEquip(prhub.getEquipPrice(info.get(0)), info, customType);
						} else {
							System.out.println("Error: Product ID not found for: "+info.get(0));
						}
					}
				}
				ihub.addInvoices(i);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Main method, will be moved in phase 3!
	 * 
	 * @param args - This parameter is not used
	 */
	public static void main(String[] args) {
		
		boolean XML_CONSOLE_OUTPUT = false;
		boolean XML_GENERATE_DATA = false;
		boolean JSON_CONSOLE_OUTPUT = false;
		boolean JSON_GENERATE_DATA = false;
		boolean TXT_GENERATE_DATA = true;

		new DataConvert(XML_CONSOLE_OUTPUT, XML_GENERATE_DATA,
				JSON_CONSOLE_OUTPUT, JSON_GENERATE_DATA, TXT_GENERATE_DATA);
	}
}