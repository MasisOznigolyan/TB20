package com.TBmail.EmailService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.TBmail.EmailService.Parser.LastNews;
import com.TBmail.EmailService.Parser.MailContent;

@SpringBootTest
class EmailServiceApplicationTests {
	
		@Autowired
		private EmailSenderService senderService;

	    
	    @Test
	    public void lastNewsTimeTest() {
	    	
	    	String page=LastNews.getLastNewsTime("https://trendbasket.net/tag/anadolu-efes/");
	    	
	    	String expected="2023-07-26T11:00:25";
	    	
	    	Assertions.assertEquals(expected, page);
	    }
	    
	    @Test
	    public void lastNewsTimeTest2() {
	    	
	    	String page=LastNews.getLastNewsTime("https://trendbasket.net/category/ligler/turkiye/bsl/");
	    	
	    	String expected="2023-07-26T11:00:25";
	    	
	    	Assertions.assertEquals(expected, page);
	    }
	    
	    @Test
	    public void getLastNewsTitleTest() {
	    	String page =LastNews.getLastNewsTitle("https://trendbasket.net/tag/fenerbahce-beko/");
	    	String expected="Türkiye Sigorta Basketbol Süper Ligi 2023-24 | Kesinleşen kadrolar";
	    	
	    	Assertions.assertEquals(expected, page);
	    }
	    
	    @Test
	    public void getLastNewsTitleTest2() {
	    	String page =LastNews.getLastNewsTitle("https://trendbasket.net/tag/panathinaikos/");
	    	String expected="Juancho Hernangomez, Panathinaikos'ta!";
	    	
	    	Assertions.assertEquals(expected, page);
	    }
	    
	    @Test
	    public void getNewsUrlTest() {
	    	String page =LastNews.getNewsUrl(MailContent.getHtml("https://trendbasket.net/tag/panathinaikos/"));
	    	String expected="https://trendbasket.net/juancho-hernangomez-panathinaikosta/";
	    	
	    	Assertions.assertEquals(expected, page);
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
	    	senderService.sendEmail("oznigolyan3@gmail.com", "Test", "MailSender is working");
	    }*/
}
