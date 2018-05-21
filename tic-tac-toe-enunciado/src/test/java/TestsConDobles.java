import es.codeurjc.ais.tictactoe.Connection;
import es.codeurjc.ais.tictactoe.Player;
import es.codeurjc.ais.tictactoe.TicTacToeGame;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 *
 * @author sito
 */
public class TestsConDobles {
    
    //Creamos el objeto TicTacToeGame
    TicTacToeGame ttt = new TicTacToeGame();
    Connection connection1;
    Connection connection2;
    Player p1;
    Player p2;
    
    @Before
    public void setUpClass()
    {
        //Creamos los dos dobles de la clase Connection, uno para cada jugador
        connection1 = mock(Connection.class);
        connection2 = mock(Connection.class);
        //Añadimos los dobles de las conexiones al objeto TicTacToeGame
        ttt.addConnection(connection1);
        ttt.addConnection(connection2);
        //Creamos los dos jugadores
        p1= new Player(1,"cruz","Juan");
        p2 = new Player(2,"circulo","Pedro");
        
        //Añadimos los jugadores al objeto TicTacToeGame y comprobamos que se mandan los eventos correspondientes
        ttt.addPlayer(p1);
        verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1)));
        verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1)));
        reset(connection1);
        reset(connection2);
        
        ttt.addPlayer(p2);
        verify(connection1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));
        verify(connection2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(p1,p2)));

        
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
    }
    
    @Test
    public void ganaJugador1Test()
    {
        System.out.println("\n\nComprobacion del test en el que gana el Jugador 1");
        int[] posiciones = {0,3,6};
        
        //Insertamos las fichas correspondientes de tal modo que gane el jugador 1 y comprobamos que se mandan los eventos correspondientes
        ttt.mark(0);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(1);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(3);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(2);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(6);
        
        //Obtenemos el valor del objeto mandado por el servidor tras haber encontrado un ganador
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        WinnerValue event = argument.getValue();
        
        //Comprobamos que el identificador del jugador ganador es igual al del jugador que deberia haber ganado
        assertEquals("Identificador del jugador", event.player.getId(), 1);
        System.out.println("Identificador del ganador obtenido: " + event.player.getId() + " y identificador deseado: " + 1);
        //Comprobamos que las posiciones de las fichas ganadoras obtenidas son las mismas que se esperaban
        Assert.assertArrayEquals("Posiciones con las fichas ganadoras", event.pos, posiciones);
        System.out.println("Posiciones de las fichas ganadoras obtenidas: " + Arrays.toString(event.pos) + " y posiciones deseadas: " + Arrays.toString(posiciones));
    }
    
    @Test
    public void ganaJugador2Test()
    {
        System.out.println("\n\nComprobacion del test en el que gana el Jugador 2");
        int[] posiciones = {6,4,2};
        
        //Insertamos las fichas correspondientes de tal modo que gane el jugador 2 y comprobamos que se mandan los eventos correspondientes
        ttt.mark(0);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(6);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(1);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(2);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(5);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(8);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(7);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(4);
        
        //Obtenemos el valor del objeto mandado por el servidor tras haber encontrado un ganador
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        WinnerValue event = argument.getValue();
        
        //Comprobamos que el identificador del jugador ganador es igual al del jugador que deberia haber ganado
        assertEquals("Identificador del jugador", event.player.getId(), 2);
        System.out.println("Identificador del ganador obtenido: " + event.player.getId() + " y identificador deseado: " + 2);
        //Comprobamos que las posiciones de las fichas ganadoras obtenidas son las mismas que se esperaban
        Assert.assertArrayEquals("Posiciones con las fichas ganadoras", event.pos, posiciones);
        System.out.println("Posiciones de las fichas ganadoras obtenidas: " + Arrays.toString(event.pos) + " y posiciones deseadas: " + Arrays.toString(posiciones));
    }
    
    @Test
    public void empateTest()
    {
        System.out.println("\n\nComprobacion del test en el que se empata");
        //Insertamos las fichas correspondientes de tal modo que se produzca un empate y comprobamos que se mandan los eventos correspondientes
        ttt.mark(0);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(4);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(3);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(6);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(2);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(8);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(7);
        verify(connection1).sendEvent(EventType.SET_TURN, p2);
        verify(connection2).sendEvent(EventType.SET_TURN, p2);
        reset(connection1);
        reset(connection2);
        ttt.mark(1);
        verify(connection1).sendEvent(EventType.SET_TURN, p1);
        verify(connection2).sendEvent(EventType.SET_TURN, p1);
        ttt.mark(5);
        
        //Obtenemos el valor del objeto mandado por el servidor tras estar el tablero completo
        ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
        verify(connection1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
        WinnerValue event = argument.getValue();
        
        //Comprobamos que el valor es null, que quiere decir que se ha producido un empate
        assertEquals("Empate producido", event, null);
        System.out.println("Evento obtenido con valor: " + null + " y el evento deseado tenia valor: " + null);
    }
}
