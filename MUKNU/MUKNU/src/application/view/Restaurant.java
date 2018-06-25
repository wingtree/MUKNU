package application.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Restaurant {
	
	String[][] restaurantList = new String[82][6];
	
    void Array() throws ParseException, FileNotFoundException, IOException {
    	JSONParser jsonParser = new JSONParser();
    
    	JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src\\application\\model\\restaurant.txt")); //파일 입력
    	
    	JSONArray restaurantArray = (JSONArray) jsonObject.get("restaurant");
    	
    	for(int i = 0; i < restaurantArray.size(); i++) {
    		JSONObject restaurantObject = (JSONObject) restaurantArray.get(i);
    		
    		restaurantList[i][0] = (String) restaurantObject.get("name");
    		restaurantList[i][1] = (String) restaurantObject.get("gate");
    		restaurantList[i][2] = (String) restaurantObject.get("price");
    		restaurantList[i][3] = (String) restaurantObject.get("category");
    		restaurantList[i][4] = (String) restaurantObject.get("tel");
    		restaurantList[i][5] = (String) restaurantObject.get("menu");
    	}
    }
    
    public String show(String name) {
        for (int index = 0; index < 82; index++) {
        	String compare = restaurantList[index][0];
        	if (name.equals(compare)) {
            	return restaurantList[index][0] + " 가격대 : " + restaurantList[index][2] + " 메뉴 : " + restaurantList[index][5] + " 전화번호 : " + restaurantList[index][4];
        	}
        }
		return name;
    }
    
    public int[] index(String food) {
    	int count = 0;
		int counter = 0;
    	
    	for (int i = 0; i <82; i++) {
    		String compare = restaurantList[i][3];
    		if (compare.equals(food)) {
    			count++;
    		}
    	}
    	
    	int[] index = new int[count];
    	
    	for (int i = 0; i <82; i++) {
    		String compare = restaurantList[i][3];
    		if (compare.equals(food)) {
        	    index[counter] = i;
    			counter++;
    		}
    	}
    	return index;
    }
}