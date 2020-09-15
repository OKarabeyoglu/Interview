package com.trendyol.shoppingcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcard.ApiEndpoints;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.request.ProductDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiEndpoints.PRODUCT_API, produces = { ApiEndpoints.RESPONSE_CONTENTTYPE },
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Api(value = "product-api")
public class ProductApiController {
	
	private ProductService productService;
	
	@Autowired
    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }
	
	@PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "saveProduct", notes = "save product")
    public SaveResponse saveProduct(@RequestBody ProductDTORequest request) {
        return new SaveResponse(productService.createProduct(request.getProductDTO()));
    }

}
