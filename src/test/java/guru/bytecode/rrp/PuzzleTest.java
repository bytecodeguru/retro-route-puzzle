package guru.bytecode.rrp;

import java.util.Optional;
import java.util.Set;
import org.junit.Test;

public class PuzzleTest {

    @Test
    public void testApi() {
        
        MazeMap mazeMap;
        
        Room startingRoom = null;
        Set<Item> itemsToCollect = null;
        
        Walker player1 = new Walker();
        
        Optional<Route> validRoute = player1.findRoute(startingRoom, itemsToCollect);

    }
}
