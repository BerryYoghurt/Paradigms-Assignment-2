package garbagecollection;

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
        try(
                FileReader heap = new FileReader(args[0]);
                FileReader roots = new FileReader(args[1]);
                FileReader pointers = new FileReader(args[2]);

        ){

            Parser parser = new Parser(heap, pointers, roots);
            //MarkCompact.clean(parser.getStackArray(),parser.getHeapArray());
            Copy.copy(parser.getStackArray());
            //heapArray = parser.getHeapArray();
            heapArray = Copy.getNewHeap();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Output.write(args[3],heapArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
