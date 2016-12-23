package de.hska.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains details about roles.
 */
@Entity(name = "role")
public class Role {
	@JsonProperty("id")
	@Id
	private int id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("level")
	private int level;

	public Role() {
	}

	public Role(int id, String type, int level) {
		this.id = id;
		this.type = type;
		this.level = level;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
