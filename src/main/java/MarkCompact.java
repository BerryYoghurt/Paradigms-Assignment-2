import java.util.ArrayList;

public class MarkCompact {

    private static void mark(ArrayList<HeapObject> stackArray){
        /*vroot is initialised as a dummy object just to give uniformity to the traversal algorithm..
         so that even the root objects have parents*/
        HeapObject vroot = new HeapObject(0,-1,0), p_parent, p_current, p_child;
        vroot.references = stackArray;
        int i; //temporary index variable

        for(HeapObject rootObject : stackArray){
            /*post-order traversal*/
            p_parent = vroot;
            p_current = rootObject;
            p_child = rootObject;

            do {
                p_current.visited++;
                if (p_current.visited <= p_current.references.size()) { //there are still some children to visit
                    i = p_current.visited - 1;
                    if (p_current.references.get(i).visited == 0) { //if child has never been visited
                        p_child = p_current.references.get(i); //set child
                        p_current.references.set(i, p_parent); //reverse pointer
                        p_parent = p_current; //get the pointers ready for next iteration
                        p_current = p_child;
                    }
                } else {//no more children to visit
                    p_current = p_parent; //return back to processing the parent
                    i = p_current.visited - 1;
                    p_parent = p_current.references.get(i); //get the (grand) parent
                    p_current.references.set(i, p_child); //return pointer to normal
                }

            } while (p_current != vroot); //end the loop if next iteration will process vroot as current
        }
    }

    private static void calculateAndMove(ArrayList<HeapObject> heapArray) {
        int free_pointer = 0, size;
        for(HeapObject heapObject : heapArray){
            if(heapObject.isVisited()){
                size = heapObject.ending_address - heapObject.starting_address;
                heapObject.starting_address = free_pointer;
                heapObject.ending_address = free_pointer + size;
                free_pointer = free_pointer + size + 1;
            }
        }
    }

    public static void clean(ArrayList<HeapObject> stackArray, ArrayList<HeapObject> heapArray){
        mark(stackArray);
        calculateAndMove(heapArray); //no need to update root objects
        // because everything is a reference to one and the same object after all
    }

    

}
