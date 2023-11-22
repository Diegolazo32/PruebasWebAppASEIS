package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebEliminarComentario {
	
    ChromeDriver driver;
    WebDriverWait wait;
    String url = "http://127.0.0.1:8000/login";

    @BeforeTest
    public void invocarNavegador() {
        System.setProperty("webdriver.chrome.driver",
                "H:\\Descargas\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); // Espera hasta 10 segundos
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterTest
    public void cerrarNavegador() {
        driver.quit();
    }

    @Test(priority = 0)
    public void verificarLoginAseisNews() throws InterruptedException {
        WebElement userId = driver.findElement(By.name("email"));
        WebElement userPassword = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        userId.sendKeys("isp12345@ues.edu.sv");
        userPassword.sendKeys("Minerva.23");
        loginButton.click();
        
        // Esperar a que un elemento característico de la página principal esté presente
        Thread.sleep(5000);
    }
    
    @Test(priority = 1)
    public void EliminarComentario() throws InterruptedException {
    	
    	// Navegar a la página de creación de cursos
    	driver.get("http://127.0.0.1:8000/Docente/indexCursosPublicadosDocente");
    	Thread.sleep(5000);
    	
    	//Busca el primer boton con ese id
        driver.findElement(By.id("MasInfoCursoDisp")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.id("btnComentarioE")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.id("EliminarBtn")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.id("CloseModalBtn")).click();
        Thread.sleep(3000);
        
    	driver.get("http://127.0.0.1:8000/Docente/indexCursosPublicadosDocente");
    	Thread.sleep(5000);

    }

}

