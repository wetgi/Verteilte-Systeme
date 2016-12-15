package de.hska.repository;


import org.springframework.data.repository.CrudRepository;

import de.hska.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
