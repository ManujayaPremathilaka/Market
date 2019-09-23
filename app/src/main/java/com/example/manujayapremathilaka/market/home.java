package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;

import com.spark.submitbutton.SubmitButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    private EditText uname,pswd;
    private  TextView info;
    private Button login;
    private   int  counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uname = (EditText)findViewById(R.id.et_name);
        pswd = (EditText)findViewById(R.id.et_name6);
        info  = (TextView)findViewById(R.id.info);
        login  = (Button)findViewById(R.id.button17);

        info.setText("Remaning no of attempts are 3 !");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatelogin(uname.getText().toString(),pswd.getText().toString());
            }
        });


    }
    private void validatelogin(String username, String password){
        if((username.equals("admin")) && (password.equals("admin123"))){
            Intent dash = new Intent(home.this,dashboard.class);
                startActivity(dash);
            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();

        }
        else{
            counter --;
            info.setText("Remaning no of attempts "+ String.valueOf(counter)+"!");
            Toast.makeText(getApplicationContext(),"Remaning"+String.valueOf(counter)+"!",Toast.LENGTH_SHORT).show();

            if(counter == 0){
                login.setEnabled(false);
                Toast.makeText(getApplicationContext(),"No more Remanings",Toast.LENGTH_SHORT).show();

            }
        }
    }



    
    }

