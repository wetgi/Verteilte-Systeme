package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Category;
import io.swagger.model.Product;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Controller
public class CategoriesApiController implements CategoriesApi {

    public ResponseEntity<Void> categoriesCategoryIdDelete(@ApiParam(value = "Deletes a category",required=true ) @PathVariable("categoryId") Integer categoryId,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Category> categoriesCategoryIdGet(@ApiParam(value = "Get details for category",required=true ) @PathVariable("categoryId") Integer categoryId) {
        // do some magic!
        return new ResponseEntity<Category>(HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> categoriesGet() {
        // do some magic!
        return new ResponseEntity<List<Product>>(HttpStatus.OK);
    }

    public ResponseEntity<Category> categoriesPost(@ApiParam(value = "Information about the new category." ,required=true ) @RequestBody Category newProduct,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId) {
        // do some magic!
        return new ResponseEntity<Category>(HttpStatus.OK);
    }

}
