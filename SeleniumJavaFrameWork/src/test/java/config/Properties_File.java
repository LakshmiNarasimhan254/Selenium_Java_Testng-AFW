package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Properties_File {
	static Properties prop = new Properties();


	public static String getProperties(String strKey){
		InputStream input;
		try {
			input = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/config/config.properties");
			prop.load(input);
			return prop.getProperty(strKey);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strKey;
			}


	public static void setProperties(String strKey,String strKeyValue){
		OutputStream output;
		try {
			output = new FileOutputStream(System.getProperty("user.dir")+ "/src/test/java/config/config.properties");
			prop.setProperty(strKey, strKeyValue);
			prop.save(output, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
