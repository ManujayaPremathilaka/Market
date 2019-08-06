package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MarketHome extends AppCompatActivity {

    TextView textView;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_home);

        textView = findViewById(R.id.register);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MarketHome.this, CustomerRegistration.class);
                startActivity(register);
            }

        });
    }

    public void onLoginButtonPushed(View view){
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        if(((userName.getText().toString()).equalsIgnoreCase("admin")) && ((password.getText().toString()).equalsIgnoreCase("admin"))){
            Intent adminLogin = new Intent(MarketHome.this, EmployeeHome.class);
            startActivity(adminLogin);

        }
        else{
            Intent login = new Intent(MarketHome.this, ItemMenu.class);
            startActivity(login);
        }

    }

    public void onRegisterButtonPushed(View view){
        Intent register = new Intent(MarketHome.this, CustomerRegistration.class);
        startActivity(register);
    }
}
