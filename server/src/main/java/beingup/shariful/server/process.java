package beingup.shariful.server;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class process extends AsyncTask<String,Void,String> {
    // rn = requestNumber, pn = postName, sd = serverData
    // snr = serverNoResult, se = serverError
    int rid = 0;
    String pn = "app_data", sd = "", snr = "", se = "_error";

    @Override
    protected String doInBackground(String... strings) {
        try {
            String su = strings[0];
            URL url = new URL(su);

            HttpURLConnection huc = (HttpURLConnection)url.openConnection();
            huc.setRequestMethod("POST");
            huc.setDoOutput(true);
            huc.setDoInput(true);

            OutputStream os = huc.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String post_data = URLEncoder.encode(pn,"UTF-8")+"="+URLEncoder.encode(sd,"UTF-8");

            bw.write(post_data);
            bw.flush();
            bw.close();
            os.close();
            InputStream is = huc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuffer out = new StringBuffer();
            String s;
            while((s = br.readLine())!= null) out.append(s);
            br.close();
            is.close();
            huc.disconnect();

            return out.toString().trim();
        }
        catch (Exception ignored) {}
        return null;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onPostExecute(String result) {
        if (result == null){
            ifRequestFailed(rid);
            return;
        }

        if (result.equals(snr)){
            ifNoResult(rid);
            return;
        }

        if (result.equals(se)){
            ifError(rid);
            return;
        }

        JSONArray ar = new JSONArray();
        JSONObject ob = new JSONObject();
        try {
            ar = new JSONArray(result);
        } catch (JSONException j){
            try {
                ob = new JSONObject(result);
            } catch (JSONException j1){}
        }
        result(rid, result, ar, ob);
    }

    @Override
    protected void onProgressUpdate(Void values[]) {
        super.onProgressUpdate(values);
    }

    // i = requestNumber s = result, ob = object, ar = array
    public void ifRequestFailed(int i){}
    public void ifNoResult(int i){}
    public void ifError(int i){}
    public void result (int i, String s, JSONArray ar, JSONObject ob){}
}
