package dto;

public class InventoryDTO {
	
	private int item_id;
    private String item_name;
    private double cost_price;
    private double selling_price;
    public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	private int quantity;
    double profit;
    boolean done;
    
	@Override
	public String toString() {
		return "InventoryDTO [item_id=" + item_id + ", item_name=" + item_name + ", cost_price=" + cost_price
				+ ", selling_price=" + selling_price + ", quantity=" + quantity + ", profit=" + profit + ", done="
				+ done + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (done ? 1231 : 1237);
		result = prime * result + item_id;
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		temp = Double.doubleToLongBits(profit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(selling_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryDTO other = (InventoryDTO) obj;
		if (Double.doubleToLongBits(cost_price) != Double.doubleToLongBits(other.cost_price))
			return false;
		if (done != other.done)
			return false;
		if (item_id != other.item_id)
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (Double.doubleToLongBits(profit) != Double.doubleToLongBits(other.profit))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(selling_price) != Double.doubleToLongBits(other.selling_price))
			return false;
		return true;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getCost_price() {
		return cost_price;
	}
	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}
	public double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}


}
	
	



