package backend;

/**
 * Created by whcda on 5/12/2017.
 */
public class Event {
    private int time,start,end;
    public Event(int time,int start,int end){
        this.time=time;
        this.start=start;
        this.end=end;
    }

    public int getTime() {
        return time;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
