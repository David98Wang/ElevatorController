package backend;
import org.json.*;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by whcda on 5/12/2017.
 */
public class ControllerMain {

    private int Floors;
    private int Elevators;
    private ArrayList<Event> Events = new ArrayList<Event>();
    public ControllerMain(String fileName) throws FileNotFoundException{
        try{
            JSONObject obj = new JSONObject(fileName);
            JSONArray arr = obj.getJSONArray("events");
            Event temp = new Event(0,0,0);
            for(int i=0;i<arr.length();i++){
                temp.setTime(arr.getJSONObject(i).getInt("time"));
                temp.setStart(arr.getJSONObject(i).getInt("start"));
                temp.setEnd(arr.getJSONObject(i).getInt("end"));
                Events.add(temp);
            }
        }catch (org.json.JSONException e){
            System.out.println(e.getCause());
        }
    }

}
