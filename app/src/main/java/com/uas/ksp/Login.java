package com.uas.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    private TextInputLayout textInputUsername, textInputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputUsername= (TextInputLayout) findViewById(R.id.edtUNameLogin);
        textInputPassword= (TextInputLayout) findViewById(R.id.edtPassLogin);


    }

    //proses login dan memverifikasi user
    public void onLogin(View view) throws ExecutionException, InterruptedException {
        Intent homeIntent = new Intent(this,Home.class);
        String username = textInputUsername.getEditText().getText().toString();
        String password = textInputPassword.getEditText().getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        String checkLogin = backgroundWorker.execute(type,username,password).get();
        String[] valuesLogin = checkLogin.split(",");
        if (valuesLogin[0].equals("success")){

            homeIntent.putExtra("id", valuesLogin[1]);
            homeIntent.putExtra("name", valuesLogin[2]);
            startActivity(homeIntent);
        }else{
            Toast.makeText(getApplicationContext(),"Username atau Password Salah",Toast.LENGTH_LONG).show();
        }

    }

}
