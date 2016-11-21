package guru.bytecode.rrp;

import java.util.ArrayDeque;
import static java.util.Collections.*;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Quester {

    private final Search search;

    Quester(Search search) {
        this.search = search;
    }

    List<Move> findRoute(Room startRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startRoom);
        Objects.requireNonNull(itemsToCollect);

        if (itemsToCollect.isEmpty()) {
            return singletonList(new Move(startRoom, emptySet()));
        }

        HashSet<Item> remainingItems = new HashSet<>(itemsToCollect);
        List<Move> route = greedyFindRoute(remainingItems, startRoom);
        return remainingItems.isEmpty() ? route : emptyList();
    }

    private List<Move> greedyFindRoute(Set<Item> remainingItems, Room startRoom) {
        LinkedList<Move> route = new LinkedList<>();
        LinkedList<Move> routeToNextItems;

        do {
            routeToNextItems = findRouteToNextItems(startRoom, remainingItems);
            join(route, routeToNextItems);
            startRoom = route.isEmpty() ? startRoom : route.getLast().getRoom();
        } while (!remainingItems.isEmpty() && !routeToNextItems.isEmpty());

        return route;
    }

    private LinkedList<Move> findRouteToNextItems(Room startRoom, Set<Item> itemsToCollect) {
        Deque<Room> roomsToVisit = new ArrayDeque<>();
        roomsToVisit.offerFirst(startRoom);
        return search.findNextMoves(roomsToVisit, itemsToCollect);
    }

    private void join(LinkedList<Move> left, LinkedList<Move> right) {
        if (left.isEmpty()) {
            left.addAll(right);
        } else if (!right.isEmpty()) {
            if (left.getLast().getRoom().equals(right.getFirst().getRoom())) {
                right.removeFirst();
            }
            left.addAll(right);
        }
    }

}
