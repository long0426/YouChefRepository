package model;

public class Cart implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String[] summary;
	private int price;
	private int quantity;
	private String image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSummary() {
		return summary;
	}
	public void setSummary(String[] summary) {
		this.summary = summary;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", summary=" + summary + ", price=" + price + ", quantity="
				+ quantity + ", image=" + image + ", toString()=" + super.toString() + "]";
	}


	
	
	
}
