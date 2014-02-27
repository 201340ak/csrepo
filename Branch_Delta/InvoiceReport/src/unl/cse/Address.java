package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The <b>Address</b> class was designed to be used for multiple types
 * of objects. This is because Companies, and People will have addresses.
 * All values in this Object run risks of being null!
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */
@XmlRootElement(namespace = "unl.cse")
@XmlType(propOrder = { "street", "city", "state", "zip", "country"})
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	public Address() { }

	/**
	 * 
	 * @param street - Accepts a String
	 */
	@XmlElement(name = "street")
	public void setStreet(String street) {
		
		if ((street != null)  && (!street.trim().isEmpty())) {
			this.street = street.trim();
		} else {
			this.street = "N/A";
		}
	}

	/**
	 * 
	 * @param city - Accepts a String
	 */
	@XmlElement(name = "city")
	public void setCity(String city) {
		
		if ((city != null) && (!city.trim().isEmpty())) {
			this.city = city.trim();
		} else {
			this.city = "N/A";
		}
	}

	/**
	 * 
	 * @param state - Accepts a String
	 */
	@XmlElement(name = "state")
	public void setState(String state) {
		
		if ((state != null) && (!state.trim().isEmpty())) {
			this.state = state.trim();
		} else {
			this.state = "N/A";
		}
	}

	/**
	 * 
	 * @param zip - Accepts a String, please leave it as a String. Zip codes
	 * can also contain "-" 
	 */
	@XmlElement(name = "zip")
	public void setZip(String zip) {
		
		if ((zip != null) && (!zip.trim().isEmpty())) {
			this.zip = zip.trim();
		} else {
			this.zip = "N/A";
		}
	}

	/**
	 * 
	 * @param country - Accepts a String
	 */
	@XmlElement(name = "country")
	public void setCountry(String country) {

		if ((country != null) && (!country.trim().isEmpty())) {
			this.country = country.trim();
		} else {
			this.country = "N/A";
		}
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCountry() {
		return country;
	}
}