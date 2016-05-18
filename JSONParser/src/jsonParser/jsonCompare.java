package jsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;




public class jsonCompare {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException, IOException{
		String filelocation="/home/webonise/json/jsonFile.json";
		String filelocation2="/home/webonise/json/jsonFile2.json";
		//JsonParser parser = new JsonParser();
		System.out.println("File location " + filelocation);
		/*try {
			Object obj = parser.parse(new FileReader(filelocation));

			JSONObject jsonObject = (JSONObject) obj;
			int size=jsonObject.size();
			System.out.println("Size "+size);
			Set keyList=jsonObject.keySet();
			System.out.println(""+keyList);
			Iterator setIterator=keyList.iterator();
			while(setIterator.hasNext()){
				try{
					String data=(String) jsonObject.get(setIterator.next());
					System.out.println(data);
				}
				catch(Exception ex){
					JSONObject responseObject =(JSONObject) jsonObject.get(setIterator.next());
					System.out.println(responseObject);
				}
			}
		}
		catch(Exception ex){
			System.out.println("Error "+ex);
		}*/
		/*try {
			Object obj = parser.parse(new FileReader(filelocation));
			JSONObject jsonObject = (JSONObject) obj;
			
			obj = parser.parse(new FileReader(filelocation2));
			JSONObject jsonObject2 = (JSONObject) obj;
			Assert.assertEquals(jsonObject, jsonObject2);
			System.out.println("Same ");
		}
		catch(Exception ex){
			System.out.println("not equal");
		}*/
		String dataJson1,dataJson2;
		BufferedReader br = new BufferedReader(new FileReader(filelocation));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		           // sb.append("\n");
		            line = br.readLine();
		        }
		        System.out.println(sb.toString());
		        dataJson1=sb.toString();
		    } finally {
		        br.close();
		    }
		    
		   br = new BufferedReader(new FileReader(filelocation2));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		           // sb.append("\n");
		            line = br.readLine();
		        }
		        System.out.println(sb.toString());
		        dataJson2=sb.toString();
		    } finally {
		        br.close();
		    }
		    
	    
		    JsonFactory factory = new JsonFactory();
		    int match=0;
		    int totalKeys=0;
		       ObjectMapper mapper = new ObjectMapper(factory);
		       JsonNode rootNode1 = mapper.readTree(dataJson1);  
		       JsonNode rootNode2= mapper.readTree(dataJson2);  
		       Iterator<Map.Entry<String,JsonNode>> fieldsIterator1 = rootNode1.getFields();
		       while (fieldsIterator1.hasNext()) {
		    	   totalKeys+=1;
		           Map.Entry<String,JsonNode> outterField = fieldsIterator1.next();
		           String outterKey=outterField.getKey();
		           JsonNode outterValue=outterField.getValue();
		          /* Iterator<Map.Entry<String,JsonNode>> fieldsIterator2 = rootNode2.getFields();
		           while (fieldsIterator2.hasNext()) {
		        	   Map.Entry<String,JsonNode> innerfield = fieldsIterator2.next();
		        	   String innerKey=innerfield.getKey();
			           JsonNode innerValue=innerfield.getValue();
			           if(outterKey.equalsIgnoreCase(innerKey) && outterValue.equals(innerValue)){
			        	   System.out.println(outterValue);
			        	   System.out.println(innerValue);
			        	   match+=1;
			        	   break;
			           }
		           }*/
		           //System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
		           JsonNode innerValue=rootNode2.findValue(outterKey);
		            if(outterValue.equals(innerValue)){
		        	   System.out.println(outterValue);
		        	   System.out.println(innerValue);
		        	   match+=1;
		           }
		       }
		       if(totalKeys==match){
		    	   System.out.println("match found");
		       }
		       else{
		    	   System.out.println("json string do not match");
		       }
		    
	}
}
