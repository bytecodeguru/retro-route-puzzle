package guru.bytecode.rrp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class RetroRoutePuzzle {

    static enum Strategy {
        BFS {
            @Override
            Search getSearch() {
                return new BreadthFirstSearch();
            }
        },
        DFS {
            @Override
            Search getSearch() {
                return new DepthFirstSearch();
            }
        };

        abstract Search getSearch();
    }

    private final Map<Integer, ? extends Room> mazeMap;

    RetroRoutePuzzle(Map<Integer, ? extends Room> mazeMap) {
        this.mazeMap = mazeMap;
    }

    List<Move> solve(Strategy strategy, Integer roomId, Set<Item> itemsToCollect) {
        Room startRoom = Optional.ofNullable(mazeMap.get(roomId))
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        Quester player1 = new Quester(strategy.getSearch());
        return player1.findRoute(startRoom, itemsToCollect);
    }

    public static void main(String[] args) throws IOException {
        Cli cli = Cli.parse(args);
        if (cli == null) {
            System.exit(1);
        }

        Map<Integer, ? extends Room> mazeMap = new MapLoader()
                .loadFrom(cli.getMapFile());

        RetroRoutePuzzle puzzle = new RetroRoutePuzzle(mazeMap);

        Integer startRoomId = cli.getRoomId();

        Set<Item> itemsToCollect = Arrays.asList(cli.getItems()).stream()
                .map(JsonItem::new)
                .collect(toSet());

        List<Move> route = puzzle.solve(
                cli.getStrategy(),
                startRoomId,
                itemsToCollect
        );
        
        new CsvRoute(route).printTo(System.out);

    }

}
