package unl.cse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
public class Invoices {
	private String inCode;
	private String customCode;
	private String salesCode;
	private char customType;
	private PayHub payHub;
	private boolean hasComplyFee;
	
	private List<String> prodList = new ArrayList<String>();
	
	public Invoices() {
		this.payHub = new PayHub();
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getInCode() {
		return inCode;
	}
	
	/**
	 * 
	 * @param inCode - Accepts a String
	 */
	public void setInCode(String inCode) {
		if ((inCode != null) && (!inCode.trim().isEmpty())) {
			this.inCode = inCode.trim();
		} else {
			this.inCode = "N/A";
		}
	}
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCustomCode() {
		return customCode;
	}
	
	/**
	 * 
	 * @param customCode - Accepts a String
	 */
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	/**
	 * 
	 * @return - Returns a String
	 */
	
	public void setCustomType(char type) {
		this.customType = type;
	}
	
	public String getSalesCode() {
		return salesCode;
	}
	/**
	 * 
	 * @param salesCode - Accepts a String
	 */
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	
	/**
	 * 
	 * @return - Returns an <b>ArrayList</b> of products
	 */
	public List<String> getProdList() {
		return Collections.unmodifiableList(this.prodList);
	}
	/**
	 * <i>setProdList</i> will take an array of Strings and add them into an <b>ArrayList</b>.
	 * An invoice can have any number of products or none at all, if their are none "N/A" is indicated.
	 * 
	 * @param emails - Will take an array of products
	 */

	public void setProdLicense(double annualPrice, double fee, List<String> info, char type) {
		LicensePay lp = new LicensePay();
		int days = 0;
		double total = 0.0;
		double tax = 0.0;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
		    Date start = sdf.parse(info.get(1));
		    Date end = sdf.parse(info.get(2));
		    days = (int)((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		lp.setCode(info.get(0));
		lp.setStartDate(info.get(1));
		lp.setEndDate(info.get(2));
		lp.setDays(days);
		total = ((days * annualPrice) / 365);
		lp.setTotal(total);
		
		if (type == 'C') {
			tax = 0.0425;
		}
		lp.setFee(fee);
		
		double taxed = ((total + fee) * tax);
		double cost = taxed + total + fee;
		
		lp.setTaxed(taxed);
		lp.setCost(cost);
		payHub.addLicense(lp);
	}
	
	public void setProdConsult(double price, List<String> info, char type) {
		ConsultPay cp = new ConsultPay();
		double total = 0.0;
		double hours = 0.0;
		double tax = 0.0;
		try {
			hours = Double.parseDouble(info.get(1));
			total = price * (hours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (type == 'C') {
			tax = 0.0425;
		}
		
		double taxed = ((total + 150.0) * tax);
		System.out.println(taxed);
		double cost = taxed + total;
		
		cp.setCode(info.get(0));
		cp.setHours(hours);
		cp.setTotal(total);
		cp.setCost(cost);
		cp.setTaxed(taxed);
		payHub.addConsult(cp);
	}
	
	public void setProdEquip(double price, List<String> info, char type) {
		EquipmentPay ep = new EquipmentPay();
		double total = 0.0;
		double tax = 0.0;
		double cost = 0.0;
		int units = 0;
		try {
			total = price * (Double.parseDouble(info.get(1)));
			units = Integer.parseInt(info.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (type == 'C') {
			tax = 0.07;
		}
		
		double taxed = total * tax;
		cost = taxed + total;
		ep.setCode(info.get(0));
		ep.setTotal(total);
		ep.setUnits(units);
		ep.setCost(cost);
		ep.setTaxed(taxed);
		payHub.addEquip(ep);
	}
	
	public char getCustomType() {
		return this.customType;
	}
	
	public void setHasComplyFee(boolean bool) {
		this.hasComplyFee = bool;
	}
	
	public boolean getHasComplyFee() {
		return this.hasComplyFee;
	}
	
	public double getSubTotal() {
		double result = 0.00;
		for (LicensePay ph : payHub.getLiceList()) {
			result += ph.getTotal();
		}
		
		for (EquipmentPay ph : payHub.getEquipList()) {
			result += ph.getTotal();
		}
		
		for (ConsultPay ph : payHub.getConsultList()) {
			result += ph.getTotal();
		}
		return result;
	}
	
	public double getFee() {
		double result = 0.0;
		for (LicensePay ph : payHub.getLiceList()) {
			result += ph.getFee();
		}
		
		for (ConsultPay ph : payHub.getConsultList()) {
			result += 150.00;
		}
		return result;
	}
	
	public double getTaxes() {
		double result = 0.0;
		for (LicensePay ph : payHub.getLiceList()) {
			result += ph.getTaxed();
		}
		
		for (EquipmentPay ph : payHub.getEquipList()) {
			result += ph.getTaxed();
		}
		
		for (ConsultPay ph : payHub.getConsultList()) {
			result += ph.getTaxed();
		}
		return result;
	}
	
	public List<String> getCodes(String id) {
		List<String> result = new ArrayList<String>();
		for (LicensePay ph : payHub.getLiceList()) {
			result.add(ph.getCode());
		}
		for (EquipmentPay ph : payHub.getEquipList()) {
			result.add(ph.getCode());
		}
		
		for (ConsultPay ph : payHub.getConsultList()) {
			result.add(ph.getCode());
		}
		return result;
	}
}