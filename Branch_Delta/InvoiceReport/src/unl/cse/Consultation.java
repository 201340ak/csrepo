package unl.cse;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Consultation</b> is a type of product where the instances live in
 * the <b>ProductsHub</b> class. This class will make use of the
 * <b>Persons</b> object as someone has to be a consultant.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlType(propOrder={"code", "name", "hourPrice", "consultList"})
public class Consultation {
	
	private String code;
	private String name;
	public double hourPrice;
	
	@XmlElement(name="consultant")
	private final ArrayList<Persons> consultList;
	
	public Consultation() {
		this.consultList = new ArrayList<Persons>();	
	}
	
	/**
	 * 
	 * @param code - Primary indicator for a consultation, requires String
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
	 * @param hourPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	@XmlElement(name="hourPrice")
	public void setHourPrice(String hourPrice) {
		double temp;
		
		if (hourPrice == null) {
			hourPrice = "0.0";
		}
		
		try {
			temp = Double.parseDouble(hourPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.hourPrice = temp;
	}
	
	/**
	 * 
	 * @param consult - Accepts the <b>Persons</b> instance of the
	 * corresponding id to indicate which person if any owns this
	 * consultation
	 */
	public void addConsultList(List<Persons> consult) {
		this.consultList.addAll(consult);
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
	public double getHourPrice() {
		return hourPrice;
	}
	
	/**
	 * 
	 * @return - Returns the consultant which is a <b>Persons</b> object
	 */
	public List<Persons> getConsultList() {
		return Collections.unmodifiableList(this.consultList);
	}
}