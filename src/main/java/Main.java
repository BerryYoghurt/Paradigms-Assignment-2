import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try(
                FileReader heap = new FileReader(args[0]);
                FileReader roots = new FileReader(args[2]);
                FileReader pointers = new FileReader(args[1]);

        ){

            Parser parser = new Parser(heap, pointers, roots);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
