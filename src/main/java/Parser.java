import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Parser {

    private HashMap<Integer, HeapObject> map;
    private ArrayList<HeapObject> heap, stack;

    private void readHeap(FileReader heap){
        try(BufferedReader bufferedReader = new BufferedReader(heap)){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                map.put(id, new HeapObject(id, Integer.parseInt(values[1]),Integer.parseInt(values[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPointers(FileReader pointers){
        try(BufferedReader br = new BufferedReader(pointers)){
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                int id1 = Integer.parseInt(values[0]), id2 = Integer.parseInt(values[1]);
                map.get(id1).references.add(map.get(id2));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readRoots(FileReader roots){
        try(BufferedReader bufferedReader = new BufferedReader(roots)){
            String line;
            while((line = bufferedReader.readLine()) != null){
                stack.add(map.get(Integer.parseInt(line)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parser(FileReader heap, FileReader pointers, FileReader roots){
        map = new HashMap<>();
        stack = new ArrayList<>();
        readHeap(heap);
        readPointers(pointers);
        readRoots(roots);
        this.heap = new ArrayList<HeapObject>(map.values());
    }

    public ArrayList<HeapObject> getStackArray() {
        return stack;
    }

    public ArrayList<HeapObject> getHeapArray(){
        return heap;
    }
}
