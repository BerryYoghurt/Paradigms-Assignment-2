import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class Main {

    private static String[] args;
    private static ArrayList<HeapObject> heapArray;

    public static void main(String[] args){
       Main.args = args;
       collectGarbage(MarkCompact::clean);
       //print
       //other algorithm
    }

    private static void collectGarbage(BiConsumer<ArrayList<HeapObject>, ArrayList<HeapObject>> garbage_collector){
        try(
                FileReader heap = new FileReader(args[0]);
                FileReader roots = new FileReader(args[2]);
                FileReader pointers = new FileReader(args[1]);

        ){

            Parser parser = new Parser(heap, pointers, roots);
            garbage_collector.accept(parser.getStackArray(), parser.getHeapArray());
            heapArray = parser.getHeapArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
