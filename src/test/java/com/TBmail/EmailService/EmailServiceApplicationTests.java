package com.TBmail.EmailService;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.TBmail.EmailService.Parser.LastNews;
import com.TBmail.EmailService.Parser.MailContent;
import com.TBmail.EmailService.Response.UserResponse;
import com.TBmail.EmailService.Service.EmailSenderService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailServiceApplicationTests {
	
		@Autowired
		private EmailSenderService senderService;

		
	    @Test
	    public void lastNewsTimeTest() {
	    	
	    	String got=LastNews.getLastNewsTime("https://trendbasket.net/tag/anadolu-efes/");
	    	
	    	String expected="2023-07-26T11:00:25";
	    	
	    	Assertions.assertEquals(expected, got);
	    }
	    
	    @Test
	    public void lastNewsTimeTest2() {
	    	
	    	String got=LastNews.getLastNewsTime("https://trendbasket.net/category/ligler/turkiye/bsl/");
	    	
	    	String expected="2023-07-26T11:00:25";
	    	
	    	Assertions.assertEquals(expected, got);
	    }
	    
	    @Test
	    public void getLastNewsTitleTest() {
	    	String got =LastNews.getLastNewsTitle("https://trendbasket.net/tag/fenerbahce-beko/");
	    	String expected="Türkiye Sigorta Basketbol Süper Ligi 2023-24 | Kesinleşen kadrolar";
	    	
	    	Assertions.assertEquals(expected, got);
	    }
	    
	    @Test
	    public void getLastNewsTitleTest2() {
	    	String got =LastNews.getLastNewsTitle("https://trendbasket.net/tag/panathinaikos/");
	    	String expected="Juancho Hernangomez, Panathinaikos'ta!";
	    	
	    	Assertions.assertEquals(expected, got);
	    }
	    
	    @Test
	    public void getNewsUrlTest() {
	    	String got =LastNews.getNewsUrl(MailContent.getHtml("https://trendbasket.net/tag/panathinaikos/"));
	    	String expected="https://trendbasket.net/juancho-hernangomez-panathinaikosta/";
	    	
	    	Assertions.assertEquals(expected, got);
	    }
	    
	    @Test
	    public void mailContentTest() {
	    	String url="https://trendbasket.net/gigi-datome-fenerbahcedeyken-bile-emeklilik-aklimdan-gecti/";
	    	
	    	String got=MailContent.getContent(url);
	    	
	    	String expected="İtalyan oyuncu Gigi Datome, kapsamlı açıklamalarda bulundu.\n"
	    			+ "\n"
	    			+ "\n"
	    			+ "Kısa bir süre önce basketbola veda edeceğini açıklayan Gigi Datome, Area 52 podcast yayınına katıldı ve birçok konuda kapsamlı bir şekilde konuştu.\n"
	    			+ "\n"
	    			+ "Aldığı emeklilik kararını değerlendiren Datome, bu kararla ilgili en üzücü sonucu şöyle açıkladı:\n"
	    			+ "\n"
	    			+ "\"Zor zamanlar geçirdim, o kadar kötüydü ki Fenerbahçe'deyken bile emeklilik aklımdan geçti. Geçen sezon sözleşmem bitiyordu. Bu yüzden düşünmeye başladım. Kariyerimi gerçek bir oyuncu olarak bitirmek istedim. Bence en travmatik kısım oyuncuların her zaman yaptığı gibi otobüse arka kapı yerine ön kapıdan binmek olacak.\"\n"
	    			+ "\n"
	    			+ "Koç olma ihtimali sorulduğunda İtalyan forvet, kendisini bu rolde düşünmediğini belirtti.\n"
	    			+ "\n"
	    			+ "\"Kendimi o rolde görmüyorum. Pek çok harika koçla çalışma ayrıcalığına sahip oldum. Bunun muazzam bir zihinsel güç gerektirdiğini biliyorum. Hatanın altını çizmektense hatayı anlamaya daha açık olduğumu düşünüyorum. Ayrıca sessiz bir adamım. Koçluk tarzım, daha önce çalıştığım koçlarla uyuşmayabilir.\"\n"
	    			+ "\n"
	    			+ "Eski takım arkadaşı Kostas Sloukas'ın Panathinaikos'a transferi sorulduğunda Datome, şu yanıtı verdi:\n"
	    			+ "\n"
	    			+ "\"Çok şaşırdım. Ama sonra onunla konuştum ve birçok şeyi daha iyi anladım. Birisi Kostas'ın mali nedenlerle bu hamleyi yaptığını iddia ediyorsa, bu doğru olabilir. Babama 'ben olsam Kostas'ın yaptığını yapmazdım' dedim. O da bana 'çünkü sen bir pisliksin' dedi.\n"
	    			+ "\n"
	    			+ "Turkish Airlines EuroLeague'de takımların yapmış olduğu hamlelerle ilgili Datome, şunları söyledi:\n"
	    			+ "\n"
	    			+ "\"Panathinaikos ve Kızılyıldız gerçekten büyük hamleler yaptı. Olympiacos'ta Sasha Vezenkov'un yerine Luke Sikma'nın etkisini çok merak ediyorum. Aynı zamanda Bayern ve Baskonia gibi çok değişen takımlar da var.\"\n"
	    			+ "\n"
	    			+ "Son olarak Datome, EuroLeague'de karşılaşılması en zor rakibi seçti.\n"
	    			+ "\n"
	    			+ "\"Will Clyburn, pozisyonumda karşılaştığım en eksiksiz oyuncuydu. Ama size en güçlü rakiplere karşı bile rahat olmanız için gereken gücü veren şey takım ve sistemdir.\"\n"
	    			+ "\n"
	    			+ "\n"
	    			+"\n"
	    			+ "Tags: euroleague, Fenerbahçe BEKO, gigi datome" ;
	    	
	    	
	    	Assertions.assertEquals(expected, got);
	    	
	    }
	    /*@Test
	    public void sendMail() {
	    	Boolean status=senderService.sendEmail("oznigolyan3@gmail.com", "Test", "MailSender is working");
	    	Assertions.assertTrue(status);
	    }*/
	    
	    @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;

	    @Test
	    public void getUserByIdTest() {
	        String id = "6d3eb93c-a7f3-4b14-a01e-b17004cdf386"; //change here
	        ResponseEntity<UserResponse> response = restTemplate.getForEntity("/users/{id}", UserResponse.class, id);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(id, response.getBody().getUserId());
	    }
	    
}
