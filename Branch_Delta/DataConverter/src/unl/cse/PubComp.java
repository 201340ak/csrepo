package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * <b>PubComp</b> is a type of company where the instances live in
 * the <b>CustomersHub</b> class. This class will make use of the
 * <b>Persons</b> and <b>Address</b> class is it has a primary 
 * human representation and also owns an address.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

public class PubComp {
	
	private String code;
	private String name;
	
	@XmlElement(name="humanRep")
	private final ArrayList<Persons> humanRepList;
	@XmlElement(name="address")
	private final ArrayList<Address> adrList;
	
	/**
	 * <b>PubComp</b> constructor creates an <b>ArrayList</b> for the
	 * human reference (<b>Persons</b> class), and for its location in
	 * the <b>Address</b> class.
	 */
	public PubComp() { 
		this.humanRepList = new ArrayList<Persons>();
		this.adrList = new ArrayList<Address>();
	}
	
	/**
	 * 
	 * @param code - Accepts a String for the primary indicator for
	 * the company
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
	 * @param human - Accepts the <b>Persons</b> instance of the
	 * corresponding id to indicate which person if any owns this
	 * company.
	 */
	public void addHumanRep(List<Persons> human) {
		this.humanRepList.addAll(human);
	}
	
	/**
	 * 
	 * @param adr - Accepts the instance of the companies 
	 * <b>Address</b>
	 */
	public void addAddress(Address adr) {
		this.adrList.add(adr);
	}
	
	/**
	 * 
	 * @return - Returns a String that is the primary 
	 * indicator for the company
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
	 * @return - Returns the <b>Persons</b> reference to
	 * the company.
	 */
	public List<Persons> getHumanRep() {
		return Collections.unmodifiableList(this.humanRepList);
	}
	
	/**
	 * 
	 * @return - Returns the <b>Address</b> object for the company
	 */
	public List<Address> getAddress() {
    	return Collections.unmodifiableList(this.adrList);
	}
}