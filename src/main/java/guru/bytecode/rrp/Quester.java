package guru.bytecode.rrp;

import java.util.ArrayList;
import static java.util.Collections.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Quester {

    List<Move> findRoute(Room startingRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startingRoom);
        Objects.requireNonNull(itemsToCollect);

        return findRouteWithDepthFirstSearch(startingRoom, new HashSet<>(itemsToCollect));
    }

    private List<Move> findRouteWithDepthFirstSearch(Room startingRoom, Set<Item> itemsToCollect) {
        Set<Item> startingRoomItems = new HashSet<>(startingRoom.getItems());
        if (itemsToCollect.isEmpty() || startingRoomItems.containsAll(itemsToCollect)) {
            itemsToCollect.removeAll(startingRoomItems);
            return singletonList(new Move(startingRoom, startingRoomItems));
        } else {
            startingRoomItems.retainAll(itemsToCollect);
            itemsToCollect.removeAll(startingRoomItems);
            List<Move> route = new ArrayList<>();
            route.add(new Move(startingRoom, startingRoomItems));
            startingRoom.getNeighbors().forEach((neighbor) -> {
                route.addAll(findRouteWithDepthFirstSearch(neighbor, itemsToCollect));
            });
            return itemsToCollect.isEmpty() ? route : emptyList();
        }
    }

}
