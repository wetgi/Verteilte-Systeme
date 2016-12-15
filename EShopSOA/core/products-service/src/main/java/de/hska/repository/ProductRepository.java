package de.hska.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.hska.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	 public List<Product> findByNameContainingAndPriceBetween(String name, Double min, Double max);

}
