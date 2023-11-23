package seleniumScript;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWebEditarCurso {

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
    public void crearCurso() throws InterruptedException {
    	
    	// Navegar a la página de creación de cursos
    	driver.get("http://127.0.0.1:8000/Docente/indexCursosPublicadosDocente");
    	Thread.sleep(5000);
    	
    	//Busca el boton de mas informacion y da click
        driver.findElement(By.id("MasInfoCursoDisp")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.id("btnEditarCurso")).click();

        // Esperar a que el elemento "titulo" esté presente en la página
        WebElement tituloElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("titulo")));

        // Rellenar el formulario de creación de cursos
        tituloElement.sendKeys("Curso de Testing - Selenium3");

        // Seleccionar una imagen desde tu computadora
        WebElement inputArchivo = driver.findElement(By.id("archivo-cargado"));
        
        // Ruta local del archivo en tu computadora
        String rutaLocalImagen = "C:\\Users\\Diego\\OneDrive\\Pictures\\Saved Pictures\\5L.png";

        // Configurar el controlador para detectar archivos locales
        inputArchivo.sendKeys(rutaLocalImagen);
        
        // Seleccionar fecha de inicio y finalización
        driver.findElement(By.id("fechaInicio")).sendKeys("11-19-2023");
        driver.findElement(By.id("fechaFin")).sendKeys("11-20-2023");

        // Seleccionar modalidad
        WebElement modalidadDropdown = driver.findElement(By.id("modalidad"));
        modalidadDropdown.click();
        WebElement modalidadOption = driver.findElement(By.xpath("//option[text()='Virtual']"));
        modalidadOption.click();

        // Seleccionar categoría
        WebElement categoriaDropdown = driver.findElement(By.id("categoria"));
        categoriaDropdown.click();
        WebElement categoriaOption = driver.findElement(By.xpath("//option[text()='Taller']"));
        categoriaOption.click();

        // Rellenar horarios y cupos
        driver.findElement(By.id("horarios")).sendKeys("8:00 A.M hasta las 3:00 P.M de lunes a miercoles");
        driver.findElement(By.id("cupos")).sendKeys("12");

        driver.findElement(By.id("descripcionC")).sendKeys("Descripcion de curso testing, mediante Selenium");
        // Rellenar el resto de los campos según la estructura de tu formulario

        // Hacer clic en el botón para agregar contenido
        driver.findElement(By.id("btn-AgregarContenido")).click();

        
        // Esperar a que el elemento sea presente en el DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modalAggContenido")));
        // Rellenar el formulario de contenido en el modal
        WebElement tituloModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tituloModal")));
        tituloModal.click();
        tituloModal.sendKeys("Titulo 1 - testing con selenium");
        
        driver.findElement(By.id("descripcionModal")).sendKeys("Este es un contenido de prueba, mediante el testing de selenium3.");

        // Hacer clic en el botón de crear en el modal
        driver.findElement(By.id("btn-Crear")).click();
        
        Thread.sleep(5000); // Espera 5 segundos

        driver.findElement(By.id("btnCrearCurso")).click();
        // Puedes agregar aserciones aquí para verificar si el curso se creó correctamente
        
        Thread.sleep(3000);
        
        WebElement okCategoria = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("swal2-confirm")));
        okCategoria.click();
        
    }
}
     
