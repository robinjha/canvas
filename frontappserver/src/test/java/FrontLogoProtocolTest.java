import org.junit.Test;
import server.FrontLogoProtocol;

import static org.junit.Assert.*;


public class FrontLogoProtocolTest {

    FrontLogoProtocol flp;
    FrontLogoProtocol flp1;

    @Test
    public void canvasSizeShouldBe30X30(){
        flp = new FrontLogoProtocol();
        assertEquals(flp.getLength(), 30);
        assertEquals(flp.getWidth(), 30);
    }

    @Test
    public void initialModeShouldBeDraw(){
        flp = new FrontLogoProtocol();
        assertEquals(flp.getMode(), "draw");
    }

    @Test
    public void clearCommandResetsCanvas(){
        flp = new FrontLogoProtocol();
        flp1 = new FrontLogoProtocol();

        flp.setCurrentDirIndex(1);
        flp.processInput("steps 5");
        assertEquals("(20,10)", flp.processInput("coord"));
        assertNotEquals(flp.getBoard(), flp1.getBoard());
        flp.processInput("clear");
        assertArrayEquals(flp.getBoard(), flp1.getBoard());
    }

    @Test
    public void switchBetweenModesAndMoveAroundCanvas(){
        flp = new FrontLogoProtocol();

        assertEquals("(15,15)", flp.processInput("coord"));
        flp.setMode("draw");
        flp.processInput("steps 5");
        assertEquals("(15,10)", flp.processInput("coord"));
        flp.setMode("hover");
        flp.processInput("steps 1");
        assertEquals("(15,9)", flp.processInput("coord"));
        flp.setMode("eraser");
        flp.setCurrentDirIndex(4);
        flp.processInput("steps 6");
        assertEquals("(15,15)", flp.processInput("coord"));
    }



}