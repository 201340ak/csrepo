package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>CustomersHub</b> Is the main Handler for interfacing with the
 * <b>GovComp</b> and <b>PubComp</b> objects. Currently, their is no
 * difference between the two, and perhaps might be merged into one
 * company class. For now, it is easier to have the separate classes
 * to parse XML data.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlRootElement(name="customers", namespace="unl.cse")
@XmlType(propOrder = {"govList", "pubList"})
public class CustomersHub {

	@XmlElement(name="govInfo")
	private final List<GovComp> govList;
	@XmlElement(name="companyInfo")
	private final List<PubComp> pubList;
	
	/**
	 * The <b>CustomersHub</b> constructor creates an <b>ArrayList</b> for 
	 * each Company type. This is where each instance can be stored for
	 * later use.
	 */
	public CustomersHub() {
		this.govList = new ArrayList<GovComp>();
		this.pubList = new ArrayList<PubComp>();
	}
	
	/**
	 * 
	 * @param newGovList - Adds an instance of <b>GovComp</b> to the hub.
	 */
	public void addGovList(GovComp newGovList) {
		this.govList.add(newGovList);
	}
	
	/**
	 * 
	 * @param newPubList - Adds an instance of <b>PubComp</b> to the hub.
	 */
	public void addPubList(PubComp newPubList) {
		this.pubList.add(newPubList);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>GovComp</b> instances
	 */
	public List<GovComp> getGovList() {
		return Collections.unmodifiableList(this.govList);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>PubComp</b> instances
	 */
	public List<PubComp> getPubList() {
		return Collections.unmodifiableList(this.pubList);
	}
}