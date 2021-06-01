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

    /**used when passing from IO module to GC modules,
     * so that the 2 GC algorithms will not interfere with each other
     * ON SECOND THOUGHTS:: do not clone, just reread.. simpler that way
     * THIS WILL CAUSE BUGS
     * because references is a shallow copy and it will be complicated to make it a deep copy*/
    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        HeapObject new_object = new HeapObject(this.ID, this.starting_address, this.ending_address);
        new_object.references = (ArrayList<HeapObject>) this.references.clone();/*shallow copy*/
        return new_object;
    }

    public boolean isVisited(){
        return visited > 0;
    }
}
