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

    List<Move> findRoute(Room startRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startRoom);
        Objects.requireNonNull(itemsToCollect);
        
        if (itemsToCollect.isEmpty()) {
            return singletonList(new Move(startRoom, emptySet()));
        }

        HashSet<Item> remainingItems = new HashSet<>(itemsToCollect);
        LinkedList<Move> route = new LinkedList<>();
        LinkedList<Move> routeToNextItems;
        
        do {
            routeToNextItems = findRouteToNextItems(startRoom, remainingItems);
            join(route, routeToNextItems);
            startRoom = route.isEmpty() ? startRoom : route.getLast().getRoom();
        } while (!remainingItems.isEmpty() && !routeToNextItems.isEmpty());
        
        return remainingItems.isEmpty() ? route : emptyList();
    }
    
    private void join(LinkedList<Move> left, LinkedList<Move> right) {
        if (left.isEmpty()){
            left.addAll(right);
        } else if (!right.isEmpty()) {
            if (left.getLast().getRoom().equals(right.getFirst().getRoom())) {
                right.removeFirst();
            }
            left.addAll(right);
        }
    }

    private LinkedList<Move> findRouteToNextItems(Room startRoom, Set<Item> itemsToCollect) {
        LinkedList<Move> route = new LinkedList<>();
        Deque<Room> roomsToVisit = new ArrayDeque<>();
        Set<Room> visitedRooms = new HashSet<>();
        roomsToVisit.offerFirst(startRoom);

        do {
            Room room = roomsToVisit.pollFirst();
            visitedRooms.add(room);
            Set<Item> collectedItems = new HashSet<>(room.getItems());
            collectedItems.retainAll(itemsToCollect);
            route.addLast(new Move(room, collectedItems));
            if (itemsToCollect.removeAll(collectedItems)) {
                return route;
            } else {
                HashSet<Room> neighbors = new HashSet<>(room.getNeighbors());
                neighbors.removeAll(visitedRooms);
                if (neighbors.isEmpty()) {
                    route.removeLast();
                } else {
                    roomsToVisit.addAll(neighbors);
                }
            }
        } while (!roomsToVisit.isEmpty());
        
        return new LinkedList<>();
    }

}
