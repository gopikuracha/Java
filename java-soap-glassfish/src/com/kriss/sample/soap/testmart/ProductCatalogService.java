package com.kriss.sample.soap.testmart;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogService {
	
	List<String> categories;
	List<String> books;
	List<String> music;
	List<String> movies;
	
	public ProductCatalogService() {
		categories = new ArrayList<String>();
		categories.add("Books");
		categories.add("Music");
		categories.add("Movies");
		
		books = new ArrayList<String>();
		books.add("Books");
		books.add("Music");
		books.add("Movies");
		
		music = new ArrayList<String>();
		music.add("Books");
		music.add("Music");
		music.add("Movies");
		
		movies = new ArrayList<String>();
		movies.add("Books");
		movies.add("Music");
		movies.add("Movies");
	}

	public List<String> getProductCategories() {
		return categories;
	}
	
	public List<String> getProducts(String category) throws InvalidInputException {
		switch (category.toLowerCase()) {
		case "books":
			return books;
		case "music":
			return music;
		case "movies":
			return movies;
		default :
			throw new InvalidInputException("Invalid Input", "Category is not a valid input");
		}
	}
	
	public boolean addProduct(String category, String product) {
		switch (category.toLowerCase()) {
		case "books":
			books.add(product);
			return true;
		case "music":
			music.add(product);
			return true;
		case "movies":
			movies.add(product);
			return true;
		}
		return false;
	}
	
	public List<Product> getProductV2(String category) {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Gopi Book", "1234", 55));
		products.add(new Product("DB Book", "1224", 45));
		products.add(new Product("Game Of Thrones", "1223", 60));
		return products;
		
	}
}
