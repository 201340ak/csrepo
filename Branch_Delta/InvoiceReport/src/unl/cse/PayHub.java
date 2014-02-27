package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PayHub {

	private final List<LicensePay> liceList;
	private final List<EquipmentPay> equipList;
	private final List<ConsultPay> consultList;
	
	public PayHub() {
		this.liceList = new ArrayList<LicensePay>();
		this.equipList = new ArrayList<EquipmentPay>();
		this.consultList = new ArrayList<ConsultPay>();
	}

	public void addLicense(LicensePay newLicense) {
		this.liceList.add(newLicense);
	}
	
	public void addEquip(EquipmentPay newEquip) {
		this.equipList.add(newEquip);
	}
	
	public void addConsult(ConsultPay newConsult) {
		this.consultList.add(newConsult);
	}
	
	public List<LicensePay> getLiceList() {
		return Collections.unmodifiableList(this.liceList);
	}
	
	public List<EquipmentPay> getEquipList() {
		return Collections.unmodifiableList(this.equipList);
	}
	
	public List<ConsultPay> getConsultList() {
		return Collections.unmodifiableList(this.consultList);
	}
}
