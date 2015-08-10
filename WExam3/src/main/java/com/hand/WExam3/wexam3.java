package com.hand.WExam3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class wexam3 {

	public static void main(String[] args) {
		
		System.out.println("访问,获取数据:");
        new  Readhq().run();
        System.out.println("-----------------");
		System.out.println("xml数据:");
         new xml().creatxml();
         System.out.println("json数据:");
         new json().creatjson();
 	
	}

}

 class xml{

	public void creatxml() {
		// TODO Auto-generated method stub
     try {
    	 
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder=factory.newDocumentBuilder();
	   Document document=builder.newDocument();
	   Element root=document.createElement("XML");
	   //root.setAttribute("cat", "it");
	   root.getAttribute("xml");
	   
	   Element stock=document.createElement("stock");
	   Element name=document.createElement("name");
	   name.setTextContent("汉得信息");
	   Element open=document.createElement("open");
	   open.setTextContent("20.000");
	   Element close=document.createElement("close");
	   close.setTextContent("19.400");
	   Element current=document.createElement("current");
	   current.setTextContent("21.130");
	   Element high=document.createElement("high");
	   high.setTextContent("21.340");
	   Element low=document.createElement("low");
	   low.setTextContent("19.540");
	   stock.appendChild(name);
	   stock.appendChild(open);
	   stock.appendChild(close);
	   stock.appendChild(current);
	   stock.appendChild(high);      
	   stock.appendChild(low); 
	   root.appendChild(stock);
	   document.appendChild(root);
	  
	   //------------------------------
	   TransformerFactory transformerFactory=TransformerFactory.newInstance();
	   Transformer transformer=transformerFactory.newTransformer();
	   StringWriter writer=new StringWriter();
	   transformer.transform(new DOMSource(document), new StreamResult(writer));
	   System.out.println(writer.toString());  //控制台输出
	   
	   transformer.transform(new DOMSource(document), new StreamResult(new File("newxml.xml")));
     } catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (TransformerConfigurationException e) {
		e.printStackTrace();
	} catch (TransformerException e) {
		e.printStackTrace();
	}
     
	}
}
 
class json{

		public  void creatjson(){
			
	        JsonObject lan=new JsonObject();
	        lan.addProperty("name", "汉得信息");
	        lan.addProperty("open", "20.000");
	        lan.addProperty("close", "19.400");
	        lan.addProperty("current", "21.130");
	        lan.addProperty("high", "21.340");
	        lan.addProperty("low", "19.540");
	       // array.add(lan);
	     
	        //object.add(array);
	        //object.add(array);
	       // System.out.println(object);  //直接在系统输出
	        System.out.println(lan);
		}

	}
class Readhq extends Thread{
	
	@Override
	public void run() {
		
		try {
	            //访问
			URL url=new URL("http://hq.sinajs.cn/list=sz300170");
		    URLConnection connection=url.openConnection();
		    InputStream is=connection.getInputStream();
		    InputStreamReader isr=new InputStreamReader(is,"UTF-8");
		    BufferedReader br=new BufferedReader(isr);  //包装
		    
		    String line;
		    StringBuilder builder=new StringBuilder();
		    while((line=br.readLine())!=null){
		    	builder.append(line);
		    }
		    br.close();
		    isr.close();
		    is.close();
		    
		    System.out.println(builder.toString());
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
