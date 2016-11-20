package guru.bytecode.rrp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import static java.util.Collections.*;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Quester {

    List<Move> findRoute(Room startRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startRoom);
        Objects.requireNonNull(itemsToCollect);
        
        HashSet<Item> remainingItems = new HashSet<>(itemsToCollect);
        List<Move> route = new ArrayList<>();
        Deque<Room> rooms = new ArrayDeque<>();
        rooms.offerFirst(startRoom);
        findRouteWithGreedyDepthFirstSearch(rooms, remainingItems, route);
        return remainingItems.isEmpty() ? route : emptyList();
    }

    private void findRouteWithGreedyDepthFirstSearch(Deque<Room> rooms, Set<Item> itemsToCollect, List<Move> route) {
        Room room = rooms.pollFirst();
        Set<Item> collectedItems = new HashSet<>(room.getItems());
        collectedItems.retainAll(itemsToCollect);
        route.add(new Move(room, collectedItems));
        itemsToCollect.removeAll(collectedItems);
        
        if (!itemsToCollect.isEmpty()) {
            room.getNeighbors().forEach((neighbor) -> {
                rooms.offerFirst(neighbor);
            });
            if (!rooms.isEmpty()) {
                findRouteWithGreedyDepthFirstSearch(rooms, itemsToCollect, route);
            }
        }
    }

}
