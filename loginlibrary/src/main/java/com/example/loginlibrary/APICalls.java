package com.example.loginlibrary;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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


    //Function to upload data on cloud (Sign up)

    public void postData(String user,String hashOfPass){

        Map<String,String> signupData = new HashMap<>();
        signupData.put("UserName",user);
        signupData.put("Password",hashOfPass);

        PostData callAPI = new PostData(signupData);
        callAPI.execute("https://api.mlab.com/api/1/databases/testing/collections/testCollection?apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB",user,hashOfPass);
    }

    //token updation
    public void putData(String username,int randomNumber){
        PutData putData = new PutData();
        putData.execute("https://api.mlab.com/api/1/databases/testing/collections/tokenCollection?q={'UserName':'"+username+"'}&apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB", username, String.valueOf(randomNumber));
    }

    public String getTokenValue(String UserName){
        GetData getData = new GetData();
        String url = "https://api.mlab.com/api/1/databases/testing/collections/tokenCollection?q={'UserName':'"+UserName+"'}&apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB";
        try{
            String res = getData.execute(url).get();
            JSONArray jsonArray = new JSONArray(res);
            JSONObject arrayObject = jsonArray.getJSONObject(0);
            String fetchedValue = arrayObject.getString("value");
            return fetchedValue;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    public void putDefaultToken(String user){
        int randomNumber = new Random().nextInt(10000)+50000;
        PostToken postToken = new PostToken();
        postToken.execute("https://api.mlab.com/api/1/databases/testing/collections/testCollection?apiKey=aPlggAYx179AckoNvdrZPyMvc0jrt-qB",user,String.valueOf(randomNumber));
    }
}
