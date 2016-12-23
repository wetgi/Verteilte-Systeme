package de.hska.repository;

import org.springframework.data.repository.CrudRepository;

import de.hska.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Role findByLevel(int level);
}
