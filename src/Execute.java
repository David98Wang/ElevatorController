import backend.ControllerMain;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.IOException;

/**
 * Created by whcda on 5/12/2017.
 */
public class Execute {
    public static void main(String[] args){
        try{
            //JSONParser parser = new JSONParser();

            ControllerMain test = new ControllerMain("test.json");
            System.out.println("fin");
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }
}
