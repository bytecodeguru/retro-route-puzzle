package guru.bytecode.rrp;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class PuzzleTest {
    
    @Test
    public void testApi() {
        
        Map<Integer, Room> mazeMap = new HashMap<>();
        
        mazeMap.put(0, new Room());
        
        Room startingRoom = mazeMap.get(0);
        Set<Item> itemsToCollect = Collections.emptySet();
        
        Quester player1 = new Quester(new BreadthFirstSearch());
        
        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

    }
}
