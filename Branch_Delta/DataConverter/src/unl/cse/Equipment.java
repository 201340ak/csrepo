package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Equipment</b> is a very simple product that requires no objects
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "unitPrice"})
public class Equipment {
	
	private String code;
	private String name;
	public Double unitPrice;
	
	public Equipment() { }
	
	/**
	 * 
	 * @param code - Primary indicator for an equipment, requires String
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
	 * @param unitPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	@XmlElement(name="unitPrice")
	public void setUnitPrice(String unitPrice) {
		Double temp;
		
		if (unitPrice == null) {
			unitPrice = "0.0";
		}
		
		try {
			temp = Double.parseDouble(unitPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.unitPrice = temp;
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
	public Double getUnitPrice() {
		return unitPrice;
	}
}