package guru.bytecode.rrp;

import static java.util.Collections.*;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuesterTest {

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private final Quester player1 = new Quester();

    @Mock
    private Room startingRoom;
    
    @Mock
    private Room neighborRoom;
    
    @Mock
    private Item item;
    
    @Test
    public void findRoutePreconditions() {
        softly.assertThatThrownBy(() -> {
            player1.findRoute(startingRoom, null);
        }).isInstanceOf(NullPointerException.class);

        softly.assertThatThrownBy(() -> {
            player1.findRoute(null, emptySet());
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void emptyRoute() {
        when(startingRoom.getNeighbors()).thenReturn(emptySet());
        when(startingRoom.getItems()).thenReturn(emptySet());
        Set<Item> itemsToCollect = singleton(item);
        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);
        assertThat(route).isEmpty();
    }

    @Test
    public void nothingToCollect() {
        Set<Item> nothingToCollect = emptySet();
        List<Move> route = player1.findRoute(startingRoom, nothingToCollect);
        assertThat(route).containsOnly(new Move(startingRoom, nothingToCollect));
    }
    
    @Test
    public void allItemsAreInStartingRoom() {
        Set<Item> itemsToCollect = singleton(item);
        when(startingRoom.getItems()).thenReturn(itemsToCollect);
        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);
        assertThat(route).containsOnly(new Move(startingRoom, itemsToCollect));
    }
    
    @Test
    public void route2() {
        Set<Item> itemsToCollect = singleton(item);
        when(startingRoom.getItems()).thenReturn(emptySet());
        when(startingRoom.getNeighbors()).thenReturn(singleton(neighborRoom));
        when(neighborRoom.getItems()).thenReturn(itemsToCollect);
        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);
        assertThat(route).containsExactly(
                new Move(startingRoom, emptySet()),
                new Move(neighborRoom, itemsToCollect)
        );
    }

}
