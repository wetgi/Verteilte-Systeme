package de.hska.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Product
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")
@Entity(name = "product")
public class Product   {
  @JsonProperty("productId")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private String productId = null;

  @JsonProperty("details")
  private String details = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("categoryId")
  private Integer categoryId = null;

   /**
   * Unique identifier of the product.
   * @return productId
  **/
  @ApiModelProperty(value = "Unique identifier of the product.")
  public String getProductId() {
    return productId;
  }

  public Product details(String details) {
    this.details = details;
    return this;
  }

   /**
   * Description of product.
   * @return details
  **/
  @ApiModelProperty(value = "Description of product.")
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

   /**
   * Display name of product.
   * @return name
  **/
  @ApiModelProperty(value = "Display name of product.")
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

   /**
   * Price of the product.
   * @return price
  **/
  @ApiModelProperty(value = "Price of the product.")
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

   /**
   * Id of the related category.
   * @return categoryId
  **/
  @ApiModelProperty(value = "Id of the related category.")
  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
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
    return Objects.equals(this.productId, product.productId) &&
        Objects.equals(this.details, product.details) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.categoryId, product.categoryId);
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

