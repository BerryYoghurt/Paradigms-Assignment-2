import java.util.*;

public class Copy {
    //next empty position
    private static int position = 0;
    static void copy(ArrayList<HeapObject> stackArray) {
         ArrayList<HeapObject> newHeap = new ArrayList<>();
         //copy directly accessed elements from stack to new heap
         for (HeapObject heapObject : stackArray) {
             assignNewAddress(heapObject);
             newHeap.add(heapObject);
         }

         LinkedList<HeapObject> added;
         int startPointer = 0;
         do {
             //add all new heap objects to this list and append them after the loop to avoid
             // concurrent modification exception
             added = new LinkedList<>();
             ListIterator<HeapObject> start = newHeap.listIterator(startPointer);
             while (start.hasNext()){
                 HeapObject heapObject = start.next();
                 for (HeapObject child : heapObject.references) {
                     if (child.visited == 0) {
                         assignNewAddress(child);
                         added.add(child);
                     }
                 }
             }
             startPointer = newHeap.size();
             newHeap.addAll(added);
         } while (added.size() > 0); //while you didn't reach the pointer to end of the heap (still adding objects)

        //print new heap
         for (HeapObject heapObject : newHeap) {
             System.out.println(heapObject.ID + " " + heapObject.starting_address + " " + heapObject.ending_address);
         }
     }

     static private void assignNewAddress( HeapObject heapObject){
         int size = heapObject.ending_address - heapObject.starting_address;
         heapObject.starting_address = position;
         heapObject.ending_address = position + size;
         // in order to keep track of objects copied to the heap and not to copy them again
         heapObject.visited++;
         position += size + 1;
     }
    }
