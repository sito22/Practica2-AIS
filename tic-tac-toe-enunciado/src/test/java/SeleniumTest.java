import es.codeurjc.ais.tictactoe.WebApp;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author sito
 */
public class SeleniumTest {
    
    //Declaramos los dos WebDriver que abriran los dos navegadores de Google Chrome
    private WebDriver driver1;
    private WebDriver driver2;
    //Declaramos dos strings en los cuales posteriormente guardaremos los nombres de los jugadores
    String nombre1;
    String nombre2;
    
    @BeforeClass
    public static void setUpClass() {
        ChromeDriverManager.getInstance().setup();
        WebApp.start();
    }
    
    @AfterClass
    public static void tearDownClass() {
        WebApp.stop();
    }
    
    @Before
    public void setupTest() {
        //Inicializamos los WebDriver y abrimos las página web indicadas
        driver1 = new ChromeDriver();
        driver2  = new ChromeDriver();
        driver1.get("http://localhost:8082");
        driver2.get("http://localhost:8082");
        
        //Inicializamos los dos strings con los nombres de los jugadores
        nombre1 = "Juan";
        nombre2 = "Pedro";

        //Introducimos en la primera pagina web, el nombre del primer jugador en el formulario y damos al boton para iniciar el juego
        driver1.findElement(By.id("nickname")).sendKeys(nombre1);
        driver1.findElement(By.id("startBtn")).click();
        //Hacemos lo mismo en la segunda pagina web para el segundo jugador
        driver2.findElement(By.id("nickname")).sendKeys(nombre2);
        driver2.findElement(By.id("startBtn")).click();
    }
    
    @After
    public void tearDown() {
        if (driver1 != null) 
        {
            driver1.quit();
        }
        if (driver2 != null) 
        {
            driver2.quit();
        }   
    }

    
    @Test
    public void ganaJugador1() throws InterruptedException
    {   
        //Cada jugador hace click sobre las siguientes casillas, de tal forma que se conseguira que el ganador sea el jugador 1
        driver1.findElement(By.id("cell-0")).click();
        driver2.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver2.findElement(By.id("cell-2")).click();
        driver1.findElement(By.id("cell-6")).click();

        Thread.sleep(1000);

        //Guardamos en el String alert el valor del alert generado por el Script tras encontrar un ganador        
        String alert = driver1.switchTo().alert().getText();
        String resultado = nombre1 + " wins! " + nombre2 + " looses.";
        
        //Comprobamos que el valor del alert es el que se esperaba
        assertEquals(alert, resultado);
        System.out.println("\n\nComprobacion del test en el que gana el jugador 1");
        System.out.println(alert + "\n\n");
    }
    
    @Test
    public void ganaJugador2() throws InterruptedException
    {       
        //Cada jugador hace click sobre las siguientes casillas, de tal forma que se conseguira que el ganador sea el jugador 2
        driver1.findElement(By.id("cell-0")).click();
        driver2.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver2.findElement(By.id("cell-6")).click();
        driver1.findElement(By.id("cell-7")).click();
        driver2.findElement(By.id("cell-4")).click();
        driver1.findElement(By.id("cell-8")).click();
        driver2.findElement(By.id("cell-2")).click();

        Thread.sleep(1000);

        //Guardamos en el String alert el valor del alert generado por el Script tras encontrar un ganador 
        String alert = driver1.switchTo().alert().getText();
        String resultado = nombre2 + " wins! " + nombre1 + " looses.";

        //Comprobamos que el valor del alert es el que se esperaba
        assertEquals(alert, resultado);
        System.out.println("\n\nComprobacion del test en el que gana el jugador 2");
        System.out.println(alert + "\n\n");
    }
    
    @Test
    public void empateTest() throws InterruptedException
    {
        //Cada jugador hace click sobre las siguientes casillas, de tal forma que se conseguira que el ganador sea el jugador 2
        driver1.findElement(By.id("cell-0")).click();
        driver2.findElement(By.id("cell-4")).click();
        driver1.findElement(By.id("cell-3")).click();
        driver2.findElement(By.id("cell-6")).click();
        driver1.findElement(By.id("cell-2")).click();
        driver2.findElement(By.id("cell-8")).click();
        driver1.findElement(By.id("cell-7")).click();
        driver2.findElement(By.id("cell-1")).click();
        driver1.findElement(By.id("cell-5")).click();

        Thread.sleep(1000);

        //Guardamos en el String alert el valor del alert generado por el Script tras encontrar un ganador
        String alert = driver1.switchTo().alert().getText();

        //Comprobamos que el valor del alert es el que se esperaba
        assertEquals(alert, "Draw!");
        System.out.println("\n\nComprobacion del test en el que se produce un empate");
        System.out.println(alert + "\n\n");
    }
}
