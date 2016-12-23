package hska.iwi.eShopMaster.model.database.dataobjects;

import java.util.Objects;

/**
 * Product
 */
public class Product {
	private Integer productId = null;

	private String details = null;

	private String name = null;

	private Double price = null;

	private Integer categoryId = null;
	
	private String categoryName = null;

	public Integer getProductId() {
		return productId;
	}

	public Product details(String details) {
		this.details = details;
		return this;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Product name(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product price(Double price) {
		this.price = price;
		return this;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product categoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(this.productId, product.productId) && Objects.equals(this.details, product.details)
				&& Objects.equals(this.name, product.name) && Objects.equals(this.price, product.price)
				&& Objects.equals(this.categoryId, product.categoryId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, details, name, price, categoryId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");

		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    price: ").append(toIndentedString(price)).append("\n");
		sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
