import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sito
 */
public class UnitarioTest {
    
    //Creamos dos Jugadores
    Player p1;
    Player p2; 
    TicTacToeGame ttt = new TicTacToeGame();
    //WinnerResult w = new WinnerResult();
    
    @Before
    public void setUpClass()
    {
        p1 = new Player(1,"cruz","Juan");
        p2 = new Player(2,"circulo","Pedro");
        //Añadimos los dos jugadores creados a la clase del juego
        ttt.addPlayer(p1);
        ttt.addPlayer(p2);
    }
    
    @Test
    public void ganarJugador1Test()
    {
        System.out.println("\n\nComprobacion del test en el que gana el Jugador 1");
        int[] valores = {0, 3, 6};
        /*w.win = true;
        w.pos = valores;*/
        //Colocamos las fichas de tal manera que el ganador sea el jugador 1
        ttt.mark(0);
        ttt.mark(1);
        ttt.mark(3);
        ttt.mark(2);
        ttt.mark(6);
        //Finalmente comprobamos si el valor que devuelve la funcion checkDraw() es False, y checkWinner() nos devuelve el valor correcto
        assertEquals("No hay una linea ganadora", ttt.checkDraw(), false);
        System.out.println("El valor obtenido sobre el empate es: " + ttt.checkDraw() + " y el valor que se deberia obtener es: " + false);
        assertEquals("El ganador es el jugador dos", ttt.checkWinner().win, true);
        System.out.println("El valor obtenido de la clase es: " + ttt.checkWinner().win + " y el valor que se deberia obtener es: " +  true);
        Assert.assertArrayEquals("Las posiciones de las fichas ganadoras", ttt.checkWinner().pos, valores);
        System.out.println("Las posiciones de las fichas ganadoras son: " + Arrays.toString(ttt.checkWinner().pos) + " y las posiciones que se deberian obtener son: " + Arrays.toString(valores));
    }
    
    @Test
    public void ganarJugador2Test()
    {
        System.out.println("\n\nComprobacion del test en el que gana el Jugador 2");
        int[] valores = {6, 4, 2};
        /*w.win = true;
        w.pos = valores;*/
        //Colocamos las fichas de tal manera que el ganador sea el jugador 2
        ttt.mark(0);
        ttt.mark(1);
        ttt.mark(3);
        ttt.mark(6);
        ttt.mark(7);
        ttt.mark(4);
        ttt.mark(8);
        ttt.mark(2);
        //Finalmente comprobamos si el valor que devuelve la funcion checkDraw() es False, y checkWinner() nos devuelve el valor correcto
        assertEquals("No hay una linea ganadora", ttt.checkDraw(), false);
        System.out.println("El valor obtenido sobre el empate es: " + ttt.checkDraw() + " y el valor que se deberia obtener es: " + false);
        assertEquals("El ganador es el jugador dos", ttt.checkWinner().win, true);
        System.out.println("El valor obtenido de la clase es: " + ttt.checkWinner().win + " y el valor que se deberia obtener es: " +  true);
        Assert.assertArrayEquals("Las posiciones de las fichas ganadoras", ttt.checkWinner().pos, valores);
        System.out.println("Las posiciones de las fichas ganadoras son: " + Arrays.toString(ttt.checkWinner().pos) + " y las posiciones que se deberian obtener son: " + Arrays.toString(valores));
    }
    
    @Test
    public void empatarTest()
    {
        System.out.println("\n\nComprobacion del test en el que se empata");
        //Colocamos las fichas de tal manera que se produzca un empate
        ttt.mark(0);
        ttt.mark(4);
        ttt.mark(3);
        ttt.mark(6);
        ttt.mark(2);
        ttt.mark(8);
        ttt.mark(7);
        ttt.mark(1);
        ttt.mark(5);
        //Finalmente comprobamos si el valor que devuelve la funcion checkDraw() es True, como debería ser
        assertEquals("No hay ninguna linea ganadora", ttt.checkDraw(), true);
        System.out.println("El valor obtenido de la clase es: " + ttt.checkDraw() + " y el valor que se deberia obtener es: " + true);
    }
}
