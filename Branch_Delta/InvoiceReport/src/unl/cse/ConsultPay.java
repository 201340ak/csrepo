package unl.cse;

public class ConsultPay {
	private String code;
	private double hours;
	private double total;
	private double taxed;
	private double cost;
	
	public ConsultPay() {}

	public void setCode(String code) {
		this.code = code;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setTaxed(double taxed) {
		this.taxed = taxed;
	}
	
	public String getCode() {
		return this.code;
	}

	public double getHours() {
		return this.hours;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getTaxed() {
		return this.taxed;
	}
}