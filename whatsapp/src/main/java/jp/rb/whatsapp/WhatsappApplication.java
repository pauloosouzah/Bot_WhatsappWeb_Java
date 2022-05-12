package jp.rb.whatsapp;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;


public class WhatsappApplication {
	
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		
		
		//CONFIGURAÇÃO DE SESSÃO E COOKIES
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:/Users/pauloosouzah/AppData/Local/Google/Chrome/User Data"); //NECESSÁRIO COLOCAR O DESTINO DO APPDATA DO CHROME		
		WebDriver driver = new ChromeDriver(options);
		
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); //NECESSÁRIO COLOCAR O DESTINO DO CHROME.EXE
		
		
        driver.get("https://web.whatsapp.com/");
        
		//VERIFICA SE JÁ ESTÁ LOGADO
        System.out.println("Carregando..");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Boolean qrCode = driver.findElements(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div[2]/div/canvas")).size() > 0;
		 if (qrCode == false) {	
		 envioMsg(driver);	
		} else {
			System.out.println("Faça o Scan do QRCODE e aperte ENTER!");
	        entrada.nextLine();  	
		}
        
        

      
      
        
	}
	
	public static void envioMsg(WebDriver driver) throws InterruptedException {
		
		//FILTRA O CAMPO DE PESQUISA DO NOME
        WebElement campoPesquisa = driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/label/div/div[2]"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        campoPesquisa.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        
        //PEGA O NOME DO CONTATO E ENTRA NA CONVERSA
        System.out.print("Para quem você deseja enviar a mensagem (NOME EXATAMENTE COMO NO WHATSAPP): ");
        String nomeContado = entrada.nextLine();
        campoPesquisa.sendKeys(nomeContado);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        campoPesquisa.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        
        //FILTRA O CAMPO E DIGITA A MENSAGEM
        System.out.print("Qual mensagem você deseja enviar: ");
        String envioMensagem = entrada.nextLine();
        
        //VERIFICA QUANTAS MENSAGENS SERÃO ENVIADAS
        System.out.print("Quantas vezes você deseja enviar a mensagem: ");
        int qMensagens = entrada.nextInt();
        
        
        //CRIA O LAÇO
        for(int i=0; i<qMensagens; i++) {
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]")).sendKeys(envioMensagem);
        
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //ENVIA A MENSAGEM
        driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button")).click();
        }
        
        System.out.println("Mensagem(s) enviadas com sucesso!");
	}

}
