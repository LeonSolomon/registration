package com.awesome.stttt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Always use camel case when naming your functions and methods
 * i.e senddata -> sendData. check_credentials->checkCredentials
 *
 */
public class Database {
    RequestQueue requestQueue;
    private static final String TAG = "Database";
    //
    Context ctx;

    public Database(Context context) {
        ctx = context;
        requestQueue = Volley.newRequestQueue(ctx);
    }

    Boolean check_credentials(JSONObject object) {
        senddata("user", "check_client", object);
        return true;
    }

    public void senddata(final String classname, final String method, final JSONObject obj) {
        //
        String url = "https://mutall.co.ke/volley/alter.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Response " + response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
                Log.d(TAG, "onErrorResponse: Error" + error.toString());
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("class", classname);
                params.put("method", method);
                params.put("data", String.valueOf(obj));
                return params;
            }
        };
        requestQueue.add(request);
    }
}
