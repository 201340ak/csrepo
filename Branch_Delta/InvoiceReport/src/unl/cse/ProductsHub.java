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
	
	public List<Equipment> getEquipListById(String id) {
		List<Equipment> results = new ArrayList<Equipment>();
		for (Equipment e : this.equipList) {
			if (id.equals(e.getCode())) {
				results.add(e);
			}
		}
		return results;
	}
	
	public double getEquipPrice (String id) {
		double result = 0.0;
		for (Equipment e : this.equipList) {
			if (id.equals(e.getCode())) {
				result = e.getUnitPrice();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>Consultation</b> instances
	 */
	public List<Consultation> getConsultList() {
		return Collections.unmodifiableList(this.consultationList);
	}
	
	public List<Consultation> getConsultListById(String id) {
		List<Consultation> results = new ArrayList<Consultation>();
		for (Consultation c : this.consultationList) {
			if (id.equals(c.getCode())) {
				results.add(c);
			}
		}
		return results;
	}
	
	public double getConsultPrice (String id) {
		double result = 0.0;
		for (Consultation c : this.consultationList) {
			if (id.equals(c.getCode())) {
				result = c.getHourPrice();
			}
		}
		return result;
	}
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>License</b> instances
	 */
	public List<License> getLiceList() {
		return Collections.unmodifiableList(this.liceList);
	}
	
	public List<License> getLiceListById(String id) {
		List<License> results = new ArrayList<License>();
		for (License l : this.liceList) {
			if (id.equals(l.getCode())) {
				results.add(l);
			}
		}
		return results;
	}
	
	public double getLiceYrPrice(String id) {
		double result = 0.0;
		for(License l : this.liceList) {
			if (id.equals(l.getCode())) {
				result = l.getAnnualPrice();
			}
		}
		return result;
	}
	
	public double getLicePrice(String id) {
		double result = 0.0;
		for(License l : this.liceList) {
			if (id.equals(l.getCode())) {
				result = l.getPrice();
			}
		}
		return result;
	}
	
	public String getNameById(String id) {
		String result = "N/A";
		boolean found = false;
		for(License l : this.liceList) {
			if (id.equals(l.getCode())) {
				result = l.getName();
				found = true;
			}
		}
		
		if (found == false) {
			for (Consultation c : this.consultationList) {
				if (id.equals(c.getCode())) {
					result = c.getName();
					found = true;
				}
			}
		}
		
		if (found == false) {
			for (Equipment e : this.equipList) {
				if (id.equals(e.getCode())) {
					result = e.getName();
					found = true;
				}
			}
		}
		return result;
	}
}