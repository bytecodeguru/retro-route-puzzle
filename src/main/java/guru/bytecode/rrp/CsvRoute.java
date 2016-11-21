package guru.bytecode.rrp;

import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

class CsvRoute {

    private final List<Move> route;

    CsvRoute(List<Move> route) {
        this.route = route;
    }

    void printTo(Appendable stream) throws IOException {
        CSVPrinter csv = new CSVPrinter(stream, CSVFormat.DEFAULT);
        csv.printRecord("ID", "Room", "Object collected");
        for (Move m : route) {
            csv.printRecord(
                    m.getRoom().getId(),
                    m.getRoom().getName(), 
                    formatCollectedItems(m)
            );
        }
        csv.flush();
    }

    private static Object formatCollectedItems(Move m) {
        return m.getCollectedItems().isEmpty() ? "None" : m.getCollectedItems();
    }

}
