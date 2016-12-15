package de.hska.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hska.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Api(value = "categories", description = "the categories API")
public interface CategoriesApi {

    @ApiOperation(value = "Delete a certain category.", notes = "", response = Void.class, authorizations = {
        @Authorization(value = "AdminSecurity")
    }, tags={ "category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 404, message = "Category not found", response = Void.class) })
    @RequestMapping(value = "/categories/{categoryId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> categoriesCategoryIdDelete(@ApiParam(value = "Deletes a category",required=true ) @PathVariable("categoryId") Integer categoryId,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId);


    @ApiOperation(value = "Get details to a certain category.", notes = "", response = Category.class, authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the category", response = Category.class),
        @ApiResponse(code = 404, message = "Category not found", response = Category.class) })
    @RequestMapping(value = "/categories/{categoryId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Category> categoriesCategoryIdGet(@ApiParam(value = "Get details for category",required=true ) @PathVariable("categoryId") Integer categoryId);


    @ApiOperation(value = "All categories", notes = "", response = Category.class, responseContainer = "List", authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An array of categories", response = Category.class),
        @ApiResponse(code = 404, message = "Unexpected error", response = Category.class) })
    @RequestMapping(value = "/categories",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Category>> categoriesGet();


    @ApiOperation(value = "Creates a new category.", notes = "", response = Category.class, authorizations = {
        @Authorization(value = "AdminSecurity")
    }, tags={ "category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Created the category.", response = Category.class),
        @ApiResponse(code = 405, message = "Category already in system.", response = Category.class) })
    @RequestMapping(value = "/categories",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Category> categoriesPost(@ApiParam(value = "Information about the new category." ,required=true ) @RequestBody Category newCategory,
        @ApiParam(value = "The requesting user" ,required=true ) @RequestHeader(value="userId", required=true) Integer userId);

}
