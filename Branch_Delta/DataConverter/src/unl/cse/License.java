package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "price", "annualPrice"})

/**
 * <b>License</b> is a very simple product that requires no objects
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

public class License {

	private String code;
	private String name;
	public Double price;
	public Double annualPrice;
	
	public License() { }
	
	/**
	 * 
	 * @param code - Primary indicator for a license, requires String
	 */
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	/**
	 * 
	 * @param name - Accepts a String
	 */
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name.trim();
	}
	
	/**
	 * 
	 * @param price - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	@XmlElement(name="price")
	public void setPrice(String price) {
		Double temp;
		
		if (price == null) {
			price = "0.0";
		}
		
		try {
			temp = Double.parseDouble(price.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.price = temp;
	}
	
	/**
	 * 
	 * @param annualPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	@XmlElement(name="annualPrice")
	public void setAnnualPrice(String annualPrice) {
		Double temp;
		
		if (annualPrice == null) {
			annualPrice = "0.0";
		}
		
		try {
			temp = Double.parseDouble(annualPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.annualPrice = temp;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return - Returns a Double
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return - Returns a Double
	 */
	public Double getAnnualPrice() {
		return annualPrice;
	}
}