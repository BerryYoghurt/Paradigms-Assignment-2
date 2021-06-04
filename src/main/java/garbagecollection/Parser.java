package garbagecollection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    private HashMap<Integer, HeapObject> map;
    private ArrayList<HeapObject> heapArray, stackArray;
    private static final String BOM = "\uFEFF";

    private void readHeap(FileReader heap){
        try(BufferedReader bufferedReader = new BufferedReader(heap)){
            String line;
            HeapObject heapObject;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(BOM);
                line = line.replaceAll("[^0-9,]","");
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                heapObject = new HeapObject(id, Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                map.put(id, heapObject);
                heapArray.add(heapObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPointers(FileReader pointers){
        try(BufferedReader br = new BufferedReader(pointers)){
            String line;
            while((line = br.readLine()) != null){
                line = line.replaceAll("[^0-9,]","");
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
                line = line.replaceAll("[^0-9,]","");
                stackArray.add(map.get(Integer.parseInt(line)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Parser(FileReader heap, FileReader pointers, FileReader roots){
        map = new HashMap<>();
        stackArray = new ArrayList<>();
        heapArray = new ArrayList<>();
        readHeap(heap);
        readPointers(pointers);
        readRoots(roots);
        /*this.heap = new ArrayList<garbagecollection.HeapObject>(map.values()); not in the same order as read*/
    }

    public ArrayList<HeapObject> getStackArray() {
        return stackArray;
    }

    public ArrayList<HeapObject> getHeapArray(){
        return heapArray;
    }
}
