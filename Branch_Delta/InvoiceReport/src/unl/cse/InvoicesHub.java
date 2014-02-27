package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Main Persons class to interface with the Persons data
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlRootElement(name = "invoices", namespace = "unl.cse")
public class InvoicesHub {
	
	@XmlElement(name = "invoices")
	private final List<Invoices> invoicesList;
	
	/**
	 * Creates an ArrayList for <b>Persons</b> Object
	 */
	public InvoicesHub() {
		this.invoicesList = new ArrayList<Invoices>();
	}
	
	/**
	 * 
	 * @param newInvoices - Must be an instance of <b>Invoices</b> Object, will be added to
	 * the hub.
	 */
	
	public void addInvoices(Invoices newInvoices) {
		this.invoicesList.add(newInvoices);
	}
	
	/**
	 * 
	 * @return - Gives the entire <b>InvoicesHub</b> Object as an 
	 * <i>unmodifiableList</i>
	 */
	public List<Invoices> getCollection() {
    	return Collections.unmodifiableList(this.invoicesList);
	}
	
	/**
	 * <i>getInvoiceInfo</i> is specifically used to match a Customer's Code with
	 * an existing customer and a SalesPerson's Code with an existing Sales person
	 * from the <b>Invoices</b> Object stored in the
	 * <b>InvoicesHub</b> ArrayList
	 * 
	 * @param customCode, salesCode - These codes are used to find a specific Customer and salesPerson
	 * @return - Returns an ArrayList of an entire <b>Persons</b> Object
	 */
	public List<Invoices> getInvoiceInfo(String customCode, String salesCode) {
		boolean cflag = false; //flag for customers
		boolean sflag = false; //flag for salesPersons
		List<Invoices> results = new ArrayList<Invoices>();
		for(Invoices i : this.invoicesList) {
			if (i.getCustomCode().equals(customCode.trim())) {
				results.add(i);
				cflag = true;
			}
			
			if (i.getSalesCode().equals(salesCode.trim())) {
				results.add(i);
				sflag = true;
			}
		}
		
		if (cflag == false) {
			System.out.println("Error: Customer owner not found: "+customCode); //not sure how to reword this.
		}
		
		if (sflag == false) {
			System.out.println("Error: Sales Person owner not found: "+salesCode); //not sure how to reword this.
		}
		
		return results;
	}
}