package guru.bytecode.rrp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class PuzzleTest {
    
    @Test
    public void testApi() {
        
        Map<Integer, Room> mazeMap = new HashMap<>();
        
        mazeMap.put(0, new Room());
        
        Room startingRoom = mazeMap.get(0);
        Set<Item> itemsToCollect = Collections.emptySet();
        
        Quester player1 = new Quester();
        
        Optional<Route> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route).isEmpty();

    }
}
