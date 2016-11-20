package guru.bytecode.rrp;

import static java.util.Collections.*;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuesterTest {

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    private final Quester player1 = new Quester();

    @Test
    public void findRoutePreconditions() {
        softly.assertThatThrownBy(() -> {
            player1.findRoute(mockRoom(), null);
        }).isInstanceOf(NullPointerException.class);

        softly.assertThatThrownBy(() -> {
            player1.findRoute(null, emptySet());
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void emptyRoute() {
        Room startingRoom = mockRoom();
        Set<Item> itemsToCollect = singleton(mock(Item.class));

        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route).isEmpty();
    }

    @Test
    public void nothingToCollect() {
        Room startingRoom = mockRoom();

        List<Move> route = player1.findRoute(startingRoom, emptySet());

        assertThat(route).containsOnly(new Move(startingRoom, emptySet()));
    }

    @Test
    public void allItemsAreInStartingRoom() {
        Item item = mock(Item.class);
        Room startingRoom = mockRoom(item);
        Set<Item> itemsToCollect = singleton(item);

        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route).containsOnly(new Move(startingRoom, itemsToCollect));
    }

    @Test
    public void route2() {
        Item item = mock(Item.class);
        Room startingRoom = mockRoom();
        Room neighborRoom = mockRoom(item);
        when(startingRoom.getNeighbors()).thenReturn(singleton(neighborRoom));
        Set<Item> itemsToCollect = singleton(item);

        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route).containsExactly(
                new Move(startingRoom, emptySet()),
                new Move(neighborRoom, itemsToCollect)
        );
    }

    @Test
    public void route3() {
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        Room startingRoom = mockRoom();
        Room neighbor1 = mockRoom(item1);
        Room neighbor2 = mockRoom(item2);
        connect(startingRoom, neighbor1);
        connect(neighbor1, neighbor2);
        Set<Item> itemsToCollect = setOf(item1, item2);

        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route).containsExactly(
                new Move(startingRoom, emptySet()),
                new Move(neighbor1, singleton(item1)),
                new Move(neighbor2, singleton(item2))
        );
    }

    @Test
    public void routeWithBackTracking() {
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        Room startingRoom = mockRoom();
        Room neighbor1 = mockRoom(item1);
        Room neighbor2 = mockRoom(item2);
        connect(startingRoom, neighbor1, neighbor2);
        Set<Item> itemsToCollect = setOf(item1, item2);

        List<Move> route = player1.findRoute(startingRoom, itemsToCollect);

        assertThat(route)
//                .hasSize(4)
                .containsSequence(
                        new Move(startingRoom, emptySet()),
                        new Move(neighbor1, singleton(item1))
                ).containsSequence(
                        new Move(startingRoom, emptySet()),
                        new Move(neighbor2, singleton(item2))
                );
    }

    private void connect(Room room, Room... neighbors) {
        when(room.getNeighbors()).thenReturn(setOf(neighbors));
    }

    private Room mockRoom(Item... items) {
        Room room = mock(Room.class);
        when(room.getItems()).thenReturn(setOf(items));
        return room;
    }

    private static <T> Set<T> setOf(T... elements) {
        return Stream.of(elements).collect(toSet());
    }

}
