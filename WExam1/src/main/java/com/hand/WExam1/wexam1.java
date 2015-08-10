package com.hand.WExam1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wexam1{

	public static void main(String[] args) {
		new wexam1().dowload("http://www.manning.com/gsmith/SampleChapter1.pdf");
	}
	
	
	public void dowload(String site) {			
		String regex = "\\<a[^\\<|^\\>]*href=[\\'|\\\"]([^\\<|^\\>]*\\.pdf)[\\'|\\\"][^\\<|^\\>]*[\\>|\\/\\>]";	
		Pattern p = Pattern.compile(regex);			
		List<String> pdfList = new ArrayList<String>();		
		try {			URL url = new URL(site);					
		InputStream is = url.openStream();		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));		
		String line;		
		while ((line = br.readLine()) != null) {		
			Matcher m = p.matcher(line);			
			while(m.find()){				
				pdfList.add(m.group(1));			
				}		
			}			
		br.close();		
		is.close();				
		String dir = "d://";		
		File file = new File(dir);			
		if (!file.exists()) {			
			file.mkdirs();			
			}						
		for (String pdf : pdfList) {				
			URL u = new URL(pdf);				
			InputStream i = u.openStream();			
			byte[] b = new byte[1024*1024];			
			int len;				
			String fileName = pdf.substring(pdf.lastIndexOf("/"));			
			OutputStream bos = new FileOutputStream(new File(dir + fileName));			
			while ((len = i.read(b)) != -1) {				
				bos.write(b, 0, len);				
				}				
			bos.flush();			
			bos.close();			
			i.close();		
			}	
		} catch (MalformedURLException e) {		
			e.printStackTrace();	
			} catch (IOException e) {	
				e.printStackTrace();	
				}	
	   }	
	
}