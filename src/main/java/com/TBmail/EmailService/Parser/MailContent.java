package com.TBmail.EmailService.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MailContent {
	//mockmvc faker jokoko
	public static String getHtml(String website) {
		String page=new String();
        try {
            String parseLine; 
                        
            URL URL = new URL(website); 
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));  
            /*
              URL.openStream() returns InputStream
              constructor of InputStreamReader class -> InputStreamReader( InputStream x )  
             constructor of BufferedReader class -> BufferedReader(InputStreamReader x  ) 
             */
            while ((parseLine = br.readLine()) != null) { 
               
            	page=page+"/n"+parseLine;
            }
            br.close();

        } catch (MalformedURLException me) {
            System.out.println(me);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        page=page.replace("&#8217;","'");
        page=page.replace("&#8230","...");
        page=page.replace("&#8216;","'");
        page=page.replace("&#8220;","\"");
        page=page.replace("&#8221;","\"");
        page=page.replace("&nbsp;","	");
        page=page.replace("&#x27a1;", "➡");
        page=page.replace("&#xfe0f;","");
        
        
        
        return page;
	}
	
	public static String getContent(String website ) {
		String content=new String();
        
		String html = getHtml(website);
        
		
		Document document = Jsoup.parse(html);
		        
		        Elements headers=document.select("h4");
		        // Step 2: Select <p> elements
		        Elements paragraphs = document.select("p");
		
		        // Step 3: Loop through the selected elements and print their content
		       
		            //System.out.println(headers.get(0).text());
		        content+=headers.get(0).text();
		        content+="\n";
		        content+="\n";
		        content+="\n";
		        for (int i=0; i<paragraphs.size()-6; i++) {
		        	content+=paragraphs.get(i).text();
		            //System.out.println(paragraphs.get(i).text());
		            //System.out.println();
		        	content+="\n";
		        	content+="\n";
		        }
        
        
        content+="Tags: ";
        content+=GetTags.get(html);
        //System.out.print("Tags: ");
        //System.out.println(GetTags.get(page));
        //System.out.print(mailContent);
        
        
        
        return content;
	}
	
}
