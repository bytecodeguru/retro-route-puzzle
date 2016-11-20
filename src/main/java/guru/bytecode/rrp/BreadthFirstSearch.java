package guru.bytecode.rrp;

import java.util.Deque;
import java.util.Set;

class BreadthFirstSearch extends Search {

    @Override
    protected void addRoomsToVisit(Set<Room> neighbors, Deque<Room> roomsToVisit) {
        roomsToVisit.addAll(neighbors);
    }
    
}
