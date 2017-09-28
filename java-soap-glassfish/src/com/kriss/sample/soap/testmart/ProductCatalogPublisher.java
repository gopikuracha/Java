package com.kriss.sample.soap.testmart;

import javax.xml.ws.Endpoint;

public class ProductCatalogPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:2729/ProductCatalog", new ProductCatalog());
	}
}
