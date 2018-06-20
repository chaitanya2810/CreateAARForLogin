package com.example.loginlibrary;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APICalls {
    public String getData(String user){
        GetData getData = new GetData();
        String url = "https://api.mlab.com/api/1/databases/testing/collections/testCollection?q={'UserName':'"+user+"'}&apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB";
        try{
            String res = getData.execute(url).get();
            JSONArray jsonArray = new JSONArray(res);
            JSONObject arrayObject = jsonArray.getJSONObject(0);
            return arrayObject.getString("Password");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void postData(String user,String hashOfPass){

        Map<String,String> signupData = new HashMap<>();
        signupData.put("UserName",user);
        signupData.put("Password",hashOfPass);

        PostData callAPI = new PostData(signupData);
        callAPI.execute("https://api.mlab.com/api/1/databases/testing/collections/testCollection?apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB",user,hashOfPass);
    }
}
