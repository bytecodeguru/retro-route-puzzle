package guru.bytecode.rrp;

import java.util.Collections;
import java.util.HashSet;
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
    private Room room;
    
    @Mock
    private Item item;
    
    @Test
    public void findRoutePreconditions() {
        softly.assertThatThrownBy(() -> {
            player1.findRoute(room, null);
        }).isInstanceOf(NullPointerException.class);

        softly.assertThatThrownBy(() -> {
            player1.findRoute(null, Collections.emptySet());
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void emptyRoute() {
        when(room.getNeighbors()).thenReturn(Collections.emptySet());
        when(room.getItems()).thenReturn(Collections.emptySet());
        Set<Item> itemsToCollect = Collections.singleton(item);
        List<Move> route = player1.findRoute(room, itemsToCollect);
        assertThat(route).isEmpty();
    }

    @Test
    public void nothingToCollect() {
        Set<Item> nothingToCollect = Collections.emptySet();
        List<Move> route = player1.findRoute(room, nothingToCollect);
        assertThat(route).containsOnly(new Move(room, nothingToCollect));
    }
    
    @Test
    public void allItemsAreInStartingRoom() {
        Set<Item> itemsToCollect = Collections.singleton(item);
        when(room.getItems()).thenReturn(itemsToCollect);
        List<Move> route = player1.findRoute(room, itemsToCollect);
        assertThat(route).containsOnly(new Move(room, itemsToCollect));
    }

}
