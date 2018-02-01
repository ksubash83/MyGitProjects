package resources;

public class PayLoad {

	public static String getPostBody() {
		
		String body="\\r\\n\" + \r\n" + 
				"				\"{\\r\\n\" + \r\n" + 
				"				\"  \\\"location\\\": {\\r\\n\" + \r\n" + 
				"				\"    \\\"lat\\\": -33.8669710,\\r\\n\" + \r\n" + 
				"				\"    \\\"lng\\\": 151.1958750\\r\\n\" + \r\n" + 
				"				\"  },\\r\\n\" + \r\n" + 
				"				\"  \\\"accuracy\\\": 50,\\r\\n\" + \r\n" + 
				"				\"  \\\"name\\\": \\\"Google Shoes!\\\",\\r\\n\" + \r\n" + 
				"				\"  \\\"phone_number\\\": \\\"(02) 9374 4000\\\",\\r\\n\" + \r\n" + 
				"				\"  \\\"address\\\": \\\"48 Pirrama Road, Pyrmont, NSW 2009, Australia\\\",\\r\\n\" + \r\n" + 
				"				\"  \\\"types\\\": [\\\"shoe_store\\\"],\\r\\n\" + \r\n" + 
				"				\"  \\\"website\\\": \\\"http://www.google.com.au/\\\",\\r\\n\" + \r\n" + 
				"				\"  \\\"language\\\": \\\"en-AU\\\"\\r\\n\" + \r\n" + 
				"				\"}";
		return body;
		
	}
}
