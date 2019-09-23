package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manujayapremathilaka.market.com.market.model.RegisteredCustomer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegistration extends AppCompatActivity {

    EditText txtFName, txtLName, txtNIC, txtMobileNo, txtAddress, txtEmail, txtPassword, txtRePassword;
    Button btnRegisterMe;

    RegisteredCustomer registeredCustomer;
    DatabaseReference databaseReference;
    private String contactNo = "^[0-9]*$";
    private String Nic= "[0-9]+[vV]";
    private String Email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        txtFName = findViewById(R.id.txtFirstName);
        txtLName = findViewById(R.id.txtLastName);
        txtNIC = findViewById(R.id.txtNIC);
        txtMobileNo = findViewById(R.id.txtMobileNo);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPwd);
        txtRePassword = findViewById(R.id.txtRePwd);
        btnRegisterMe = findViewById(R.id.btnRegisterMe);

        registeredCustomer = new RegisteredCustomer();

        btnRegisterMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsOnClick();
                clearFields();


            }
        });
    }

    /**
     * Validates all the fields, all the fields are required
     * Will insert to the database if no issues in the fields
     */
     void actionsOnClick(){
             if (TextUtils.isEmpty(txtFName.getText().toString())) {
                 Toast.makeText(getApplicationContext(), "Please enter First name", Toast.LENGTH_SHORT).show();
             } else if (TextUtils.isEmpty(txtLName.getText().toString())) {
                 Toast.makeText(getApplicationContext(), "Please enter Last name", Toast.LENGTH_SHORT).show();
             } else if ((TextUtils.isEmpty(txtNIC.getText().toString())) || (txtNIC.getText().toString().matches(Nic) == false)) {
                 if (TextUtils.isEmpty(txtNIC.getText().toString())){
                     Toast.makeText(getApplicationContext(), "Please enter NIC number", Toast.LENGTH_SHORT).show();
                 } else{
                     Toast.makeText(getApplicationContext(), "Please enter Valid NIC number", Toast.LENGTH_SHORT).show();
                 }

             } else if ((TextUtils.isEmpty(txtMobileNo.getText().toString())) || (txtMobileNo.getText().toString().length() != 10)) {
                 if (TextUtils.isEmpty(txtMobileNo.getText().toString())) {
                     Toast.makeText(getApplicationContext(), "Please enter Mobile number", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(getApplicationContext(), "Invalid Mobile number", Toast.LENGTH_SHORT).show();
                 }

             }
             else if(!txtMobileNo.getText().toString().trim().matches(contactNo)){
                 Toast.makeText(getApplicationContext(), "Please enter numbers for contact", Toast.LENGTH_SHORT).show();
             }
             else if (TextUtils.isEmpty(txtAddress.getText().toString())) {
                 Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_SHORT).show();
             } else if ((TextUtils.isEmpty(txtEmail.getText().toString())) || (txtEmail.getText().toString().trim().matches(Email) == false)) {
                 if (TextUtils.isEmpty(txtEmail.getText().toString())){
                     Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();
                 } else{
                     Toast.makeText(getApplicationContext(), "Please enter a Valid Email", Toast.LENGTH_SHORT).show();
                 }

             } else if (TextUtils.isEmpty(txtPassword.getText().toString()) || (!(txtPassword.getText().toString()).equalsIgnoreCase(txtRePassword.getText().toString()))) {
                 Toast.makeText(getApplicationContext(), "Password not matching", Toast.LENGTH_SHORT).show();
             } else {
                 registeredCustomer.setFirstName(txtFName.getText().toString().trim());
                 registeredCustomer.setLastName(txtLName.getText().toString().trim());
                 registeredCustomer.setNIC(txtNIC.getText().toString().trim());
                 registeredCustomer.setMobileNumber(txtMobileNo.getText().toString().trim());
                 registeredCustomer.setAddress(txtAddress.getText().toString().trim());
                 registeredCustomer.setEmail(txtEmail.getText().toString().trim());
                 registeredCustomer.setPassword(txtPassword.getText().toString().trim());

                 databaseReference = FirebaseDatabase.getInstance().getReference().child("RegisteredCustomers");
                 databaseReference.child(registeredCustomer.getNIC()).setValue(registeredCustomer);

                 Intent login = new Intent(CustomerRegistration.this, MarketHome.class);
                 startActivity(login);
             }
    }

    /**
     * clears all the fields in registration form
     */
    void clearFields(){
        txtFName.setText("");
        txtLName.setText("");
        txtNIC.setText("");
        txtMobileNo.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtRePassword.setText("");
    }
}
