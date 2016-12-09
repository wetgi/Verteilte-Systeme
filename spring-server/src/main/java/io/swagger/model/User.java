package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * User
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

public class User   {
  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("firstname")
  private String firstname = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("role")
  private Integer role = null;

  public User userId(Integer userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Id of the user.
   * @return userId
  **/
  @ApiModelProperty(value = "Id of the user.")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public User firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

   /**
   * First name of the user.
   * @return firstname
  **/
  @ApiModelProperty(value = "First name of the user.")
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Last name of the user.
   * @return name
  **/
  @ApiModelProperty(value = "Last name of the user.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Username of the user.
   * @return username
  **/
  @ApiModelProperty(value = "Username of the user.")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Password of the user.
   * @return password
  **/
  @ApiModelProperty(value = "Password of the user.")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User role(Integer role) {
    this.role = role;
    return this;
  }

   /**
   * The role of the user.
   * @return role
  **/
  @ApiModelProperty(value = "The role of the user.")
  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.userId, user.userId) &&
        Objects.equals(this.firstname, user.firstname) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, firstname, name, username, password, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

