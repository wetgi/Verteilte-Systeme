package de.hska.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.hska.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Api(value = "products", description = "the products API")
public interface ProductsApi {

    @ApiOperation(value = "All products", notes = "", response = Product.class, responseContainer = "List", authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of product", response = Product.class),
        @ApiResponse(code = 404, message = "Unexpected error", response = Product.class) })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Product>> productsGet(@ApiParam(value = "Is contained in product name?") @RequestParam(value = "searchString", required = false) String searchString,
        @ApiParam(value = "Does product cost at least x?") @RequestParam(value = "minPrice", required = false) Double minPrice,
        @ApiParam(value = "Does product cost at max x?") @RequestParam(value = "maxPrice", required = false) Double maxPrice);


    @ApiOperation(value = "Creates a new product.", notes = "", response = Product.class, authorizations = {
        @Authorization(value = "AdminSecurity")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Created the product.", response = Product.class),
        @ApiResponse(code = 405, message = "product already in system.", response = Product.class) })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Product> productsPost(@ApiParam(value = "Information about the new product." ,required=true ) @RequestBody Product newProduct,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId);


    @ApiOperation(value = "Delete a certain product.", notes = "", response = Void.class, authorizations = {
        @Authorization(value = "AdminSecurity")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Product not found", response = Void.class) })
    @RequestMapping(value = "/products/{productId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> productsProductIdDelete(@ApiParam(value = "Deletes a product",required=true ) @PathVariable("productId") Integer productId,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId);


    @ApiOperation(value = "Get details to a certain product.", notes = "", response = Product.class, authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the product", response = Product.class),
        @ApiResponse(code = 404, message = "Product not found", response = Product.class) })
    @RequestMapping(value = "/products/{productId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Product> productsProductIdGet(@ApiParam(value = "Get details for product",required=true ) @PathVariable("productId") Integer productId);

}
