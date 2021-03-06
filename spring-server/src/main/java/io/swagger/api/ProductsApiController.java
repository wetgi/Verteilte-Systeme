package io.swagger.api;

import io.swagger.model.Product;
import io.swagger.model.Error;

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
public class ProductsApiController implements ProductsApi {

    public ResponseEntity<List<Product>> productsGet(@ApiParam(value = "Is contained in product name?") @RequestParam(value = "searchString", required = false) String searchString,
        @ApiParam(value = "Does product cost at least x?") @RequestParam(value = "minPrice", required = false) Double minPrice,
        @ApiParam(value = "Does product cost at max x?") @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        // do some magic!
        return new ResponseEntity<List<Product>>(HttpStatus.OK);
    }

    public ResponseEntity<Product> productsPost(@ApiParam(value = "Information about the new product." ,required=true ) @RequestBody Product newProduct,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId) {
        // do some magic!
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    public ResponseEntity<Void> productsProductIdDelete(@ApiParam(value = "Deletes a product",required=true ) @PathVariable("productId") Integer productId,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Product> productsProductIdGet(@ApiParam(value = "Get details for product",required=true ) @PathVariable("productId") Integer productId) {
        // do some magic!
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

}
