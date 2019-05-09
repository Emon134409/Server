package beingup.shariful.server;

import org.json.JSONArray;
import org.json.JSONObject;

public class Server extends process {
    public void data(boolean b) {
        sd = Boolean.toString(b);
    }
    public void data(int i) {
        sd = Integer.toString(i);
    }
    public void data(float f) {
        sd = Float.toString(f);
    }
    public void data(double d) {
        sd = Double.toString(d);
    }
    public void data(String s) {
        sd = s;
    }
    public void data(JSONArray ar) {
        sd = ar.toString();
    }
    public void data(JSONObject ob) {
        sd = ob.toString();
    }
    public void requestId (int i){
        rid = i;
    }
    public void postName(String s){
        pn = s;
    }
    public void errorMessage(String s){
        se = s;
    }
    public void connect(String link){
        execute(link);
    }
}
