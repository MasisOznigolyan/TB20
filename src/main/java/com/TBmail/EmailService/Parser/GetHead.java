package com.TBmail.EmailService.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetHead {
	public static String getHead(String page) {
		String head=new String();
        int start=page.indexOf("<h4><strong>"); 
        for(int i=start+12; i<page.indexOf("</strong></h4>"); i++)
        {
        	if(page.charAt(i)=='<' && page.charAt(i+1)=='a') {  //<a
        		while(page.charAt(i)!='"' ||  page.charAt(i+1)!='>')
        			i++;
        		i=i+2;
        	}
        	if(page.charAt(i)=='<' && page.charAt(i+1)=='/'&& page.charAt(i+2)=='a') {	//</a>
        		i=i+4;
        	}
        	head+=(page.charAt(i));
        }
        
        return head;
	}
	
	public static String getHeadStrong(String page) {
		String head=new String();
        int start=page.indexOf("<strong>"); 
        for(int i=start+12; i<page.indexOf("</strong>"); i++)
        {
        	if(page.charAt(i)=='<' && page.charAt(i+1)=='a') {  //<a
        		while(page.charAt(i)!='"' ||  page.charAt(i+1)!='>')
        			i++;
        		i=i+2;
        	}
        	if(page.charAt(i)=='<' && page.charAt(i+1)=='/'&& page.charAt(i+2)=='a') {	//</a>
        		i=i+4;
        	}
        	head+=(page.charAt(i));
        }
        
        return head;
	}
	
	public static String getHeadJsoup(String url) {
		String html = MailContent.getHtml(url);

        // Step 1: Parse the HTML
        Document document = Jsoup.parse(html);
        
        Elements headers=document.select("title");
        
        return headers.get(0).text();
	}
}
