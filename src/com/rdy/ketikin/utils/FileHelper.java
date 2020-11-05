package com.rdy.ketikin.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileHelper {

	public String readConfigFile() {
		StringBuilder finalString = new StringBuilder();
		try {
			File myObj = getFileFromPath(Configs.CONFIG_FILE_PATH);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				finalString.append(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return finalString.toString();
	}
	
	public JSONArray getConfigObject() {
		String configText = readConfigFile();
		JSONArray finalConfigObject = null;
		try {
			finalConfigObject = new JSONArray(configText);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalConfigObject;
	}
	
	public File getFileFromPath(String Path) throws FileNotFoundException {
		File configFile = new File(Path);
		if(configFile.exists()) {
			return configFile;
		}
		throw new FileNotFoundException();
	}

}
