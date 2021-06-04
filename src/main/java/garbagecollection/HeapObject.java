package garbagecollection;

import java.util.ArrayList;

public class HeapObject {
    public int ID;
    public int starting_address;
    public int ending_address; //up to and including ending_Address
    public int visited;
    public ArrayList<HeapObject> references;

    public HeapObject(int ID, int starting_address, int ending_address){
        this.ID = ID;
        this.ending_address = ending_address;
        this.starting_address = starting_address;
        visited = 0;
        references = new ArrayList<>();
    }


    public boolean isVisited(){
        return visited > 0;
    }
}
