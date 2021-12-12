package com.beadando.demo.APiTest;



/*
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import org.json.simple.parser.ParseException;

 */
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasKey;

//import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;


public class ApiTest {
    private final String BASE_URL = "http://localhost:8080";

    /*
    public JSONObject readJSON(String filename){
        Object obj = new Object();
        try{
            obj = new JSONObject().parse(new FileReader(System.getProperty("user.dir") + "src/teset/resources"));
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return (JSONObject) obj;
    }
*/
    /*
    public String getUserId(String username){
        return String.valueOf(get(BASE_URL + "/user/users").path("find {}"));
    }

    @Test
    public void When_UserListRe_Expect_StatusOk(){
        Response response = request("get",BASE_URL+"/users/users");

        response.then().statusCode(200);
    }

*/

}
