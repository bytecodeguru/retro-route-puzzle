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
        
        HashSet<Item> remainingItems = new HashSet<>(itemsToCollect);
        List<Move> route = findRouteWithDepthFirstSearch(startingRoom, remainingItems);
        return remainingItems.isEmpty()? route : emptyList();
    }

    private List<Move> findRouteWithDepthFirstSearch(Room startingRoom, Set<Item> itemsToCollect) {
        Set<Item> startingRoomItems = new HashSet<>(startingRoom.getItems());
        if (itemsToCollect.isEmpty() || startingRoomItems.containsAll(itemsToCollect)) {
            System.out.println("if");
            itemsToCollect.removeAll(startingRoomItems);
            return singletonList(new Move(startingRoom, startingRoomItems));
        } else {
            System.out.println("else");
            startingRoomItems.retainAll(itemsToCollect);
            itemsToCollect.removeAll(startingRoomItems);
            List<Move> route = new ArrayList<>();
            route.add(new Move(startingRoom, startingRoomItems));
            System.out.printf("route: %s%n", route);
            startingRoom.getNeighbors().forEach((neighbor) -> {
                route.addAll(findRouteWithDepthFirstSearch(neighbor, itemsToCollect));
                System.out.printf("route: %s%n", route);
            });
            return route;
        }
    }

}
