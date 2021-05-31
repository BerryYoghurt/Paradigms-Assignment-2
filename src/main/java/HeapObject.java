import java.util.ArrayList;

public class HeapObject {
    public int ID;
    public int starting_address;
    public int size;
    public boolean visited;
    public ArrayList<HeapObject> references;
    public HeapObject(int ID, int starting_address, int size){
        this.ID = ID;
        this.size = size;
        this.starting_address = starting_address;
        visited = false;
        references = new ArrayList<>();
    }

    /**used when passing from IO module to GC modules,
     * so that the 2 GC algorithms will not interfere with each other*/
    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        HeapObject new_object = new HeapObject(this.ID, this.starting_address, this.size);
        new_object.references = (ArrayList<HeapObject>) this.references.clone();
        return new_object;
    }
}
