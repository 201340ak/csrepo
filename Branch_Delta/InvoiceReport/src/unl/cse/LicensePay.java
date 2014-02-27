package unl.cse;

public class LicensePay {
	
	private String code;
	private String startDate;
	private String endDate;
	private int days;
	private double total;
	private double taxed;
	private double cost;
	private double fee;
	
	public LicensePay() {}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setTaxed(double taxed) {
		this.taxed = taxed;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getEndDate() {
		return this.endDate;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public int getDays() {
		return this.days;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getTaxed() {
		return this.taxed;
	}
	
	public double getFee() {
		return this.fee;
	}
}