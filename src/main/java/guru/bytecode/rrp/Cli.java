package guru.bytecode.rrp;

import guru.bytecode.rrp.RetroRoutePuzzle.Strategy;
import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class Cli {

    private static final Option MAP = Option.builder("m").longOpt("map")
            .argName("mapFile")
            .desc("File containing the rooms map in JSON format")
            .hasArg()
            .type(File.class)
            .required()
            .build();

    private static final Option ROOM = Option.builder("r").longOpt("room")
            .argName("roomId")
            .desc("ID of the room to start searching from")
            .hasArg()
            .type(Number.class)
            .required()
            .build();

    private static final Option ITEMS = Option.builder("i").longOpt("items")
            .argName("itemsToCollect")
            .desc("List of items to search for")
            .numberOfArgs(Option.UNLIMITED_VALUES)
            .build();

    private static final Option STRATEGY = Option.builder("s").longOpt("strategy")
            .argName("searchStrategy")
            .desc("One of {BFS, DFS}. Default is BFS.")
            .hasArg()
            .build();

    private Cli(Options options, String[] args) throws ParseException {
        Stream.of(MAP, ROOM, ITEMS, STRATEGY).forEach(options::addOption);
        CommandLineParser parser = new DefaultParser();
        CommandLine cli = parser.parse(options, args);
        mapFile = (File) cli.getParsedOptionValue(MAP.getOpt());
        roomId = ((Number) cli.getParsedOptionValue(ROOM.getOpt())).intValue();
        items = Optional.ofNullable(cli.getOptionValues(ITEMS.getOpt()))
                .orElse(new String[0]);
        strategy = Strategy.valueOf(
                cli.getOptionValue(STRATEGY.getOpt(), Strategy.BFS.name())
        );
    }
    
    static Cli parse(String[] args) {
        Options options = new Options();
        try {
            return new Cli(options, args);
        } catch (ParseException ex) {
            System.err.printf("Can't parse arguments. %s%n", ex.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(RetroRoutePuzzle.class.getName(), options, true);
            return null;
        }
    }

    private final File mapFile;
    private final Integer roomId;
    private final String[] items;
    private final Strategy strategy;

    File getMapFile() {
        return mapFile;
    }

    Integer getRoomId() {
        return roomId;
    }

    String[] getItems() {
        return items;
    }

    Strategy getStrategy() {
        return strategy;
    }
}
