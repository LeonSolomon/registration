package com.awesome.stttt;

import org.json.JSONObject;

public class User {
    private static final String TAG = "User";
    public User() {
    }
    //
    //Create a function that adds a new client into the database.
    public void signup(JSONObject object) {
        //Get the form data for registration.
        //Before creating a new record, run a function that checks whether there`s a similar record in the database.
        check_client(object);
        //
        //if there`s none, add the client.

    }
    public void check_client(JSONObject jsonObject){
        //this function should have a boolean return type.
    }
    //
    //Login into the app.
    public void login(JSONObject object){
        //Before logging in check whether the user credentials exist.
        //
        check_client(object);
    }

}
