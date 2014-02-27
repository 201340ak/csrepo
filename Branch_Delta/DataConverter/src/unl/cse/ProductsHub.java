package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>ProductsHub</b> will hold a instances of the 3 types of products.
 * See the constructor to view which objects those are.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlRootElement(name="products", namespace="unl.cse")
@XmlType(propOrder = {"equipList", "consultationList", "liceList"})
public class ProductsHub {
	
	@XmlElement(name="equipment")
	private final List<Equipment> equipList;
	@XmlElement(name="consultation")
	private final List<Consultation> consultationList;
	@XmlElement(name="license")
	private final List<License> liceList;
	
	/**
	 * <b>ProductsHub</b> will construct 3 <b>ArrayList</b> which
	 * will store instances of <b>Equipment, Consultation, </b>and 
	 * <b>License</b>
	 */
	public ProductsHub(){
		this.equipList = new ArrayList<Equipment>();
		this.consultationList = new ArrayList<Consultation>();
		this.liceList = new ArrayList<License>();
	}
	
	/**
	 * 
	 * @param newEquipment - Adds an instance of <b>Equipment</b> to the hub.
	 */
	public void addEquipment(Equipment newEquipment) {
		this.equipList.add(newEquipment);
	}
	
	/**
	 * 
	 * @param newConsultation - Adds an instance of <b>Consultation</b> to the hub.
	 */
	public void addConsutation(Consultation newConsultation) {
		this.consultationList.add(newConsultation);
	}
	
	/**
	 * 
	 * @param newLicense - Adds an instance of <b>License</b> to the hub.
	 */
	public void addLicense(License newLicense) {
		this.liceList.add(newLicense);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>Equipment</b> instances
	 */
	public List<Equipment> getEquipList() {
		return Collections.unmodifiableList(this.equipList);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>Consultation</b> instances
	 */
	public List<Consultation> getConsultList() {
		return Collections.unmodifiableList(this.consultationList);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>License</b> instances
	 */
	public List<License> getLiceList() {
		return Collections.unmodifiableList(this.liceList);
	}
}