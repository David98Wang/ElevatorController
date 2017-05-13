package backend;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.*;
import sun.misc.IOUtils;

import java.io.FileReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.Global.println;

/**
 * Created by whcda on 5/12/2017.
 */
public class ControllerMain {

    private int floors;
    private int elevators;

    private ArrayList<Event> eve = new ArrayList<Event>();

    private ArrayList<Elevator> history = new ArrayList<Elevator>();
    private ArrayList<Event> per = new ArrayList<Event>();

    private ArrayList<ArrayList<Integer> >sche = new ArrayList<ArrayList<Integer> >();
    private ArrayList<Integer>pass = new ArrayList<Integer>();
    private ArrayList<Integer>curF = new ArrayList<Integer>();

    private ArrayList<Integer>curState = new ArrayList<>(Integer);
    //First digit 1 == up, -1 == down, 0 == stop
    //Second digit time in this state;
    public ControllerMain(String fileName) throws FileNotFoundException{
        //System.out.println("tes");
        //JSONParser parser = new JSONParser();
        try{
            System.out.println("Start try");
            String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\whcda\\OneDrive\\Spring2017\\Web\\ElevatorController\\src\\backend\\test.json")));
            System.out.println("C: "+content);
            JSONObject obj = new JSONObject(content);
            System.out.println("File Found");
            floors = obj.getInt("floors");
            elevators = obj.getInt("elevators");
            System.out.println("end get");
            JSONArray arr = obj.getJSONArray("events");
            Event temp = new Event(0,0,0);
            for(int i=0;i<arr.length();i++){
                temp = new Event(0,0,0);
                temp.setTime(arr.getJSONObject(i).getInt("time"));
                temp.setStart(arr.getJSONObject(i).getInt("start"));
                temp.setEnd(arr.getJSONObject(i).getInt("end"));
                eve.add(temp);
                System.out.println(temp.getTime()+" "+temp.getStart()+" "+temp.getEnd());
            }
        }catch (org.json.JSONException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("ERROR:"+e.getMessage());
        }
        //System.out.println(floors);
        //System.out.println(elevators);
        initi();
        simulate();
    }
    private void initi(){
        for(int i=0;i<elevators;i++){
            history.add(new Elevator());
            curF.add(0);
            curState.add(0);
        }
        for(int i=0;i<elevators;i++){
            pass.add(0);
            sche.add(new ArrayList<Integer>());
        }
    }
    private void insertSchedule(int ele, Event event){
        ArrayList<Integer>cur = new ArrayList<Integer>();
        cur = sche.get(ele);
        if(sche.get(ele).size()>0&&(cur.get(0)>0)^(curF.get(ele).intValue()>0))
            cur.add(0,event.getEnd());
        for(int i=0;i<cur.size()-1;i++){
            if((cur.get(i)>0)^(cur.get(i+1)>0)){
                cur.add(i+1,event.getEnd());
            }
        }
    }
    public void simulate(){

        for(int time = 0; time < 1000; time++){
            System.out.println(time);
            for(int i =0;i<elevators;i++){
                if(sche.get(i).size()>0)
                history.get(i).curDest.add(sche.get(i).get(0));
                history.get(i).curFloor.add(curF.get(i));
                history.get(i).curPass.add(pass.get(i));
                //System.out.println(history.get(i).curPass.get(history.get(i).curDest.size()));
            }

            int closest=Integer.MAX_VALUE;
            int closestEle=0;
            ArrayList<Event> cur = new ArrayList<Event>();
            //System.out.println(eve.size());
            for(int i=0;i<eve.size();i++){
                if(eve.get(i).getTime()==time){
                    //System.out.println("IN if"+time+" "+eve.get(i).getTime());
                    cur.add(eve.get(i));

                    for(int j=0;j<elevators;j++){
                        if(pass.get(j)<5){
                            closest = Math.min(closest,Math.abs(curF.get(j).intValue()-eve.get(i).getStart()));
                            closestEle = j;
                        }
                    }
                    //System.out.println(closestEle);
                    insertSchedule(closestEle,eve.get(i));
                }

            }
            System.out.println("Cur: "+cur.size());
            for(int i=0;i<elevators;i++){
                boolean contWait = sche.get(i).get(0)==curF.get(i);
                if(curState.get(i).intValue()/10==0&&curState.get(i).intValue()%10<=10){
                    curState.set(i,Integer.valueOf(0));
                    continue;
                }

                else if(curState.get(i).intValue()%10<=3)
                    continue;
                if(sche.get(i).size()==0)continue;
                //if(sche.get(i).get)
            }
        }
    }
}
