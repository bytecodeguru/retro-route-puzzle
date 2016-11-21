package guru.bytecode.rrp;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

abstract class Search {

    LinkedList<Move> findNextMoves(Deque<Room> roomsToVisit, Set<Item> itemsToCollect) {
        LinkedList<Move> route = new LinkedList<>();
        Set<Room> visitedRooms = new HashSet<>();
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
                    addRoomsToVisit(neighbors, roomsToVisit);
                }
            }
        } while (!roomsToVisit.isEmpty());

        return route;
    }
    
    protected abstract void addRoomsToVisit(Set<Room> neighbors, Deque<Room> roomsToVisit);

}
