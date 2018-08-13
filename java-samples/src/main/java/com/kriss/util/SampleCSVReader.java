package com.kriss.util;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class SampleCSVReader {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		try {
			Reader in = new FileReader("C:/Users/krishgo/Downloads/search-results-2018-07-20T08_51_55.081-0700.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("_messagetimems", "_messagetime", 
					"_raw", "_collector", "_size", "_source", "_sourcecategory", "_sourcehost", "_sourcename").parse(in);
			for (CSVRecord record : records) {
			    String raw = record.get("_raw");
			    set.add(parseRawData(raw));
			}
			
			System.out.println(set.size());
			System.out.println(set);
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String parseRawData(String raw) {
		String account = null;
		if(raw.contains("LoggingInterceptor:86")) {
			if(raw.contains("Authenticate")) {
				int index = raw.indexOf("<wsse:Username>");
				account = raw.substring(index+15, index+15+8);
				System.out.print("Account: " + account);
				int passIndex = raw.indexOf("PasswordText");
				System.out.println(" PIN: " + raw.substring(passIndex+14, passIndex+14+4));
			}
		}
		return account;
	}

}
