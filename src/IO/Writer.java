package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public interface Writer {
    int stock();

    void discount(Integer numberCard, Double totalSum, PrintWriter pw);

    void print(Integer numberCard) throws FileNotFoundException;

    Double find(PrintWriter pw);
}
