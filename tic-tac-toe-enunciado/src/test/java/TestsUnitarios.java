import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sito
 */
public class TestsUnitarios {
    
    @Test
    public void ganarJugador1Test()
    {
        
    }
    
    @Test
    public void ganarJugador2Test()
    {
        
    }
    
    //No creo que deba ser asi, pero gueno, por lo menos funciona
    @Test
    public void empatarTest()
    {
        //Creamos dos Jugadores
        Player p1 = new Player(1,"cruz","Juan");
        Player p2 = new Player(2,"circulo","Pedro");
        //Creamos la clase que maneja el juego
        TicTacToeGame ttt = new TicTacToeGame();
        //Añadimos los dos jugadores creados a la clase del juego
        ttt.addPlayer(p1);
        ttt.addPlayer(p2);
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
    }
}
