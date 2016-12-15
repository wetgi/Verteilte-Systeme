package de.hska.repository;


import org.springframework.data.repository.CrudRepository;
import de.hska.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {


}
