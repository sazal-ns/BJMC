package com.ns.siddiqui.sazal.bjmc_v20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ns.siddiqui.sazal.bjmc_v20.halpingHand.ConnectionDetector;
import com.ns.siddiqui.sazal.bjmc_v20.halpingHand.ShowDialogBox;
import com.ns.siddiqui.sazal.bjmc_v20.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameEditText, passwordEditText;
    private Button loginButton;

    private ConnectionDetector cd;
    private ShowDialogBox showDialogBox;

    private String user_name, password, error_message="Incorrect Credentials";
    private String url = "http://juteapp.flowermillsbd.org/auth/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cd = new ConnectionDetector(this);
        showDialogBox= new ShowDialogBox();

        init();
    }

    private void init() {
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        if (cd.isConnected()){
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validInput();
                }
            });
        }else{
            showNetDisabledAlertToUser(this);
        }
    }

    private void validInput() {
        if(userNameEditText.getText().length()==0){
            showDialogBox.showDialog(this,"No Input", "Please provide user name");
        }
        else if (passwordEditText.getText().length() == 0){
            showDialogBox.showDialog(this,"No Input", "Please provide password");
        }else{
            user_name = userNameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            doLogin();
        }
    }

    private void doLogin() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            UserModel.setStatus(jsonObject.getString("status"));
                            UserModel.setError(jsonObject.getBoolean("error"));
                            UserModel.setToken(jsonObject.getString("token"));
                            UserModel.setToken_expire(jsonObject.getInt("token_expire"));
                            UserModel.setId(jsonObject.getJSONObject("user").getString("id"));
                            UserModel.setUser_name(jsonObject.getJSONObject("user").getString("user_name"));
                            UserModel.setMill_id(jsonObject.getJSONObject("user").getString("mill_id"));
                            UserModel.setMill_name(jsonObject.getJSONObject("user").getString("mill_name"));
                            UserModel.setShort_name(jsonObject.getJSONObject("user").getString("short_name"));
                            UserModel.setPurchase_point_id(jsonObject.getJSONObject("user").getString("purchase_point_id"));
                            UserModel.setPp_name(jsonObject.getJSONObject("user").getString("pp_name"));
                            UserModel.setBuyer_id(jsonObject.getJSONObject("user").getString("buyer_id"));
                            UserModel.setUnit_id(jsonObject.getJSONObject("user").getString("unit_id"));

                            if (UserModel.getStatus().equals("ok") && !UserModel.isError() ){
                                Intent intent = new Intent(LoginActivity.this, PurchaseActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showDialogBox.showDialog(LoginActivity.this,"Login Failed!!!", error_message);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error_message = error.getMessage();
                showDialogBox.showDialog(LoginActivity.this,"Login Failed!!!", error_message);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("user_name", user_name);
                params.put("password", password);
                Log.e("params", params + BuildConfig.VERSION_NAME);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }



    public void showNetDisabledAlertToUser(final Context context){

        Drawable error_icon = getResources().getDrawable(R.mipmap.warning);

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title("Connection Error")
                .content("Unable to connect with the server. Check your internet connection.")
                .positiveText("Enable")
                .positiveColor(Color.parseColor("#6dc390"))
                .negativeText("Exit")
                .negativeColor(Color.RED)
                .neutralText("Try Again")
                .neutralColor(Color.BLUE)
                .icon(error_icon)
                .cancelable(false)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        context.startActivity(dialogIntent);
                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        init();
    }
}
