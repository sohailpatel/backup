package jsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parse {
	static Document document ;
	static String xmlData="";
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("/home/webonise/json/jsonFile.json");
		//writeJSON(file);
		String filelocation = file.getAbsolutePath();
		//readJSON(filelocation);
		filelocation = "/home/webonise/json/jsonXml.xml";
		readXml(filelocation);
	}

	public static void writeJSON(File filelocation) {
		JSONObject obj = new JSONObject();
		obj.put("name", "user");
		obj.put("age", new Integer(25));
		JSONArray addressArray = new JSONArray();
		addressArray.add("Pune");
		addressArray.add("Sangli");
		obj.put("address", addressArray);
		JSONObject obj1 = new JSONObject();
		obj1.put("obj1", "1");
		obj.put("mul", obj1);
		try {
			FileWriter file = new FileWriter(filelocation);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			System.out.println("File written successfully");
		} catch (Exception ex) {
			System.out.println("Error while writting " + ex.getMessage());
		}
	}

	public static void readJSON(String filelocation) {
		JSONParser parser = new JSONParser();
		System.out.println("File location " + filelocation);
		try {
			Object obj = parser.parse(new FileReader(filelocation));

			JSONObject jsonObject = (JSONObject) obj;
			String status = (String) jsonObject.get("status");
			System.out.println(status);
			String message = (String) jsonObject.get("message");
			System.out.println(message);

			JSONObject responseObject = (JSONObject) jsonObject.get("responseObject");
			/*
			 * Iterator<JSONObject> iterator = addressArray.iterator(); while
			 * (iterator.hasNext()) { System.out.println(iterator.next()); }
			 */
			System.out.println(responseObject);
			String openTicketsCount = (String) jsonObject.get("openTicketsCount");
		} catch (Exception ex) {
			System.out.println("Error while reading " + ex);
		}
	}

	/*
	 * public static void readXml(String filelocation) { try { File fXmlFile =
	 * new File(filelocation); DocumentBuilderFactory dbFactory =
	 * DocumentBuilderFactory.newInstance(); DocumentBuilder dBuilder =
	 * dbFactory.newDocumentBuilder(); Document doc = dBuilder.parse(fXmlFile);
	 * 
	 * doc.getDocumentElement().normalize(); System.out.println("Root element :"
	 * + doc.getDocumentElement().getNodeName()); if (doc.hasChildNodes()) {
	 * printNote(doc.getChildNodes()); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * private static void printNote(NodeList nodeList) { for (int count = 0;
	 * count < nodeList.getLength(); count++) { Node tempNode =
	 * nodeList.item(count); // make sure it's element node. if
	 * (tempNode.getNodeType() == Node.ELEMENT_NODE) { // get node name and
	 * value System.out.println("Node Name =" + tempNode.getNodeName() +
	 * " [OPEN]"); if (tempNode.hasAttributes()) { // get attributes names and
	 * values NamedNodeMap nodeMap = tempNode.getAttributes(); for (int i = 0; i
	 * < nodeMap.getLength(); i++) { Node node = nodeMap.item(i);
	 * System.out.println("attr name : " + node.getNodeName());
	 * System.out.println("attr value : " + node.getNodeValue()); } } if
	 * (tempNode.hasChildNodes() && tempNode.getChildNodes().getLength()>1) { //
	 * loop again if has child nodes printNote(tempNode.getChildNodes()); }
	 * else{ try{ System.out.println("VAlue : " + tempNode.getNodeValue());
	 * }//System.out.println("Node Value =" + tempNode.getNodeValue());
	 * catch(Exception ex){ System.out.println("Exception Value : "); } }
	 * System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	 * } } }
	 */
	public static void readXml(String fileLocation) throws ParserConfigurationException, SAXException, IOException {
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		document = builder.parse(new File(fileLocation));

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		// Get all employees
		NodeList nList = document.getChildNodes();
		visitChildNodes(nList);
		int PRETTY_PRINT_INDENT_FACTOR = 4;
		System.out.println("Data "+xmlData);
		 try {
	            org.json.JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
	            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	            System.out.println(jsonPrettyPrintString);
	        } catch (JSONException je) {
	            System.out.println(je.toString());
	        }
	}

	// This function is called recursively
	private static void visitChildNodes(NodeList nList) {
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			//System.out.println("-- "+node);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// System.out.println("Node Name = " + node.getNodeName() + ";
				// Value = " + node.getTextContent());
				System.out.print("<" + node.getNodeName()+">");
				xmlData+="<" + node.getNodeName()+">";
				// Check all attributes
				if (node.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = node.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node tempNode = nodeMap.item(i);
						System.out.println(
								"Attr name : " + tempNode.getNodeName() + "; Value = " + tempNode.getNodeValue());
					}
				}
				if (node.hasChildNodes()) {
					// We got more childs; Let's visit them as well
					visitChildNodes(node.getChildNodes());
				}
			} else if(node.getTextContent() != "#text"){
				try{
					//System.out.println(node.toString());
					String data=node.toString().split(":")[1].trim().split("]")[0];
					if(data.equals("")){
						
					}
					else{
						xmlData+=data;
						System.out.print(data);
					}
				//System.out.println("Node Value = " + document.getElementsByTagName(node.getNodeName()).item(0).getTextContent());
				}
				catch(Exception ex){
					//System.out.println(""+ex.getMessage());
				}
			}
			if(node.getNodeName()!="#text"){
				System.out.println("</"+ node.getNodeName()+">");
				xmlData+="</"+ node.getNodeName()+">";
			}
		}
		
	}

}
