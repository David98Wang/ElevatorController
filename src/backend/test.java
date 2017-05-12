package backend;

import java.io.FileNotFoundException;

/**
 * Created by whcda on 5/12/2017.
 */
public class test {
    public static void main(){
        try{
            ControllerMain test = new ControllerMain("test.json");



        }catch(FileNotFoundException e){
            System.out.println("File not found "+e.getMessage());
        }


    }
}
