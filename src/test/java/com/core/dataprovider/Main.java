package com.core.dataprovider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.MacBook;

public class Main {

	public static void main(String[] args)
			throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub

		// Path inputparms =
		// Paths.get(ClassLoader.getSystemResource("testData/Search.json.json").toURI());

		URI uri = ClassLoader.getSystemResource("testData/Search.json").toURI();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(uri.getPath()));
		JSONObject jsonObject1 = (JSONObject) jsonObject.get("dev");
		System.out.println(jsonObject1.get("MacBook").toString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		MacBook mac = objectMapper.readValue(jsonObject1.get("MacBook").toString(), MacBook.class);
		
		System.out.println(mac.getTest1());
	}

}
