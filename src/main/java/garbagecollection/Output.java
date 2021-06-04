package garbagecollection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    static void write(String fileName, ArrayList<HeapObject> heap) throws IOException {
        FileWriter csvWriter = new FileWriter(fileName);
        for (HeapObject heapObject : heap) {
            /*we are writing a csv so the separator should be a comma*/
            csvWriter.append(String.valueOf(heapObject.ID)).append(",").
                    append(String.valueOf(heapObject.starting_address)).
                    append(",").append(String.valueOf(heapObject.ending_address)).append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
