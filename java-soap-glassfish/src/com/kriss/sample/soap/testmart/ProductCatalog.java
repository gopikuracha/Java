package com.kriss.sample.soap.testmart;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ProductCatalog {
	
	public ProductCatalogService service = new ProductCatalogService();

	public List<String> getProductCategories() {
		return service.getProductCategories();
	}
	
	public List<String> getProducts(String category) throws InvalidInputException {
		return service.getProducts(category);
	}
	
	public boolean addProduct(String category, String product) {
		return service.addProduct(category, product);
	}
	
	public List<Product> getProductsV2(String category) {
		return service.getProductV2(category);
	}
}
