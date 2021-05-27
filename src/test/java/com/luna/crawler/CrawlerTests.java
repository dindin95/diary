package com.luna.crawler;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luna.common.config.RootConfig;
import com.luna.crawler.config.CrawlerConfig;
import com.luna.crawler.service.Crawler;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, CrawlerConfig.class }) // 경로를 적어주면 컴피그로 등록돼서 실행
@Log4j

public class CrawlerTests {
	

	@Autowired
	 Crawler crawler;
	
	@Test
	public void crawler() throws IOException {
		crawler.doGet();
		
	}
	
	 @Test
    public void testSelenium()throws Exception  {


		ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\zzz\\chromedriver.exe");

        //Convenient
        driver.get("https://together.kakao.com/magazines");

        Thread.sleep(1000);
        
        driver.executeScript("window.scrollTo(0, 3000);", new String[] {});
        
        
        Thread.sleep(3000);
        
        List<WebElement> replyList = driver.findElements(By.className("list_official"));

        for (WebElement webElement : replyList) {

            System.out.println(webElement.getText());
            System.out.println("-----------------------------------");
        }
        
     
    }
	 @Test
	    public void testSelenium2()throws Exception  {


			ChromeDriver driver = new ChromeDriver();
	        System.setProperty("webdriver.chrome.driver", "C:\\zzz\\chromedriver.exe");

	        //Convenient
	        driver.get("https://together.kakao.com/magazines");

	        Thread.sleep(1000);
	        
	        driver.executeScript("window.scrollTo(0, 3000);", new String[] {});
	        
	        
	        Thread.sleep(3000);
	        
	        List<WebElement> replyList = driver.findElements(By.className("list_official"));
	        for (WebElement webElement : replyList) {

	            System.out.println(webElement.getText());
	            WebElement tage =  webElement.findElement(By.tagName("a"));
	            WebElement href = tage.findElement(By.linkText("/magazines"));
	            href.click();

	          
		        }
	            
	        }
	        
	        
	        

	      
	  
	     
	        
	        
	 }


