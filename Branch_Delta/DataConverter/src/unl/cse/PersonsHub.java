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

@XmlRootElement(name = "persons", namespace = "unl.cse")
public class PersonsHub {
	
	@XmlElement(name = "person")
	private final List<Persons> personsList;
	
	/**
	 * Creates an ArrayList for <b>Persons</b> Object
	 */
	public PersonsHub() {
		this.personsList = new ArrayList<Persons>();
	}
	
	/**
	 * 
	 * @param newPersons - Must be an instance of <b>Persons</b> Object, will be added to 
	 * the hub.
	 */
	public void addPersons(Persons newPersons) {
		this.personsList.add(newPersons);
	}
	
	/**
	 * 
	 * @return - Gives the entire <b>PersonsHub</b> Object as an 
	 * <i>unmodifiableList</i>
	 */
	public List<Persons> getCollection() {
    	return Collections.unmodifiableList(this.personsList);
	}
	
	/**
	 * <i>getPersonInfo</i> is specifically used to match an ID with
	 * an existing person from the <b>Persons</b> Object stored in the
	 * <b>PersonsHub</b> ArrayList
	 * 
	 * @param id - The id is used to find a specific Person
	 * @return - Returns an ArrayList of an entire <b>Persons</b> Object
	 */
	public List<Persons> getPersonInfo(String id) {
		boolean flag = false;
		List<Persons> results = new ArrayList<Persons>();
		for(Persons p : this.personsList) {
			if (p.getId().equals(id.trim())) {
				results.add(p);
				flag = true;
				break;
			}
		}
		
		if (flag == false) {
			System.out.println("Error: Customer owner not found: "+id);
		}
		
		return results;
	}
}