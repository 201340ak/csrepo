package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Persons</b> is used to construct a model for every individual.
 * References to this class are used very often in all the data models.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */
@XmlRootElement(name = "person")
@XmlType(propOrder = {"id", "firstName", "lastName", "addressList", "emailsList"} )
public class Persons {

	private String id;
	private String firstName;
	private String lastName;
	
	@XmlElementWrapper(name = "emails")
	@XmlElement(name = "email")
	private ArrayList<String> emailsList = new ArrayList<String>();
	
	@XmlElement(name = "address")
	private ArrayList<Address> addressList = new ArrayList<Address>();
	
	public Persons() { }
	
	/**
	 * <i>setId</i> sets the primary reference to an instance of a <b>Persons</b>
	 * 
	 * @param id - Must be a String
	 */
	@XmlElement(name = "id")
	public void setId(String id) {
		this.id = id.trim();
	}

	/**
	 * 
	 * @param name - Must be a String
	 */
	@XmlElement(name = "firstName")
	public void setFirstName(String name) {
		this.firstName = name.trim();
	}
	
	/**
	 * 
	 * @param name - Must be a String
	 */
	@XmlElement(name = "lastName")
	public void setLastName(String name) {
		this.lastName = name.trim();
	}

	/**
	 * <i>setAddress</i> will reference an instance of an <b>Address</b> class
	 * by putting the object into the addressList
	 * 
	 * @param address - Accepts the <b>Address</b> object
	 */
	public void setAddress(Address address) {
		this.addressList.add(address);
	}

	/**
	 * <i>setEmails</i> will take an array of Strings and add them into an <b>ArrayList</b>.
	 * A person can have any number of emails or none at all, if their are none "N/A" is indicated.
	 * 
	 * @param emails - Will take an array of emails
	 */
	public void setEmails(String[] emails) {
		for (String s : emails) {
			if (s != null) {
				this.emailsList.add(s);
			} else {
				this.emailsList.add("N/A");
			}
		}
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @return - Returns an instance of a <b>Persons Address</b>
	 */
	public List<Address> getAddress() {
    	return Collections.unmodifiableList(this.addressList);
	}
	
	/**
	 * 
	 * @return - Returns an <b>ArrayList</b> of emails
	 */
	public List<String> getEmails() {
		return Collections.unmodifiableList(this.emailsList);
	}
}