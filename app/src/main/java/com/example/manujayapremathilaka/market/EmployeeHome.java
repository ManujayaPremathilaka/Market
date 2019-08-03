package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmployeeHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);
    }

    public void onDeliveryButtonPushed(View view){
        Intent delivery = new Intent(EmployeeHome.this, SignUpHome.class);
        startActivity(delivery);
    }
}
