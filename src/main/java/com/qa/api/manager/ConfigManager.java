package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	/*
	 * Reflection - loading from file and get the peoperties
	 */

	private static Properties properties = new Properties();
	// Properties is class , help to load the properties from the congif file

	// static - when we load this class , static will be executed first ,before the
	// main class
	static {

		// create the object of InputStream,Load this class and getResource using
		// concept reflections -getclassLoader().getResourceAsStream
		// input- is called InputStream Object

		/*
		 * With the help of reflection we can read the properties ,supply input in
		 * loadMethod
		 * 
		 */

		try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")) {
			if (input != null) {
				properties.load(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Get the property value based on the Key using -properties.getProperty(key)
	 * 
	 */
	public static String get(String key) {

		return properties.getProperty(key);

	}

	public static void set(String string, String baseUrl) {

		properties.setProperty(string, baseUrl);

	}

}
