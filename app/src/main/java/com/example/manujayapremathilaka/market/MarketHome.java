package com.example.manujayapremathilaka.market;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manujayapremathilaka.market.com.market.model.RegisteredCustomer;
import com.example.manujayapremathilaka.market.databinding.ActivityMarketHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MarketHome extends AppCompatActivity {

    TextView textView;
    EditText userName;
    EditText password;
    private ActivityMarketHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_home);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_market_home);

        textView = findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MarketHome.this, CustomerRegistration.class);
                startActivity(register);
            }

        });
    }

    public void load(View view){
        animateButtonWidth();
        fadeOutTextAndSetProgressDialog();
        nextAction();
    }

    private void animateButtonWidth(){

        ValueAnimator animator = ValueAnimator.ofInt(mBinding.loginButton.getMeasuredWidth(), getFinalWidth());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mBinding.loginButton.getLayoutParams();
                layoutParams.width = value;
                mBinding.loginButton.requestLayout();
            }
        });

        animator.setDuration(250);
        animator.start();
    }

    private void fadeOutTextAndSetProgressDialog(){

        mBinding.loginButtonText.animate().alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
            }
        }).start();
    }

    private void showProgressDialog(){

        mBinding.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void nextAction(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //revealButton();
                fadeOutProgressDialog();
                delayedStartNextActivity();
                //getUser();
            }
        }, 1000);
    }

    private void revealButton(){

        mBinding.loginButton.setElevation(0f);
        mBinding.realView.setVisibility(View.VISIBLE);

        int x = mBinding.realView.getWidth();
        int y = mBinding.realView.getHeight();

        int startX = (int) (getFinalWidth() / 2 + mBinding.loginButton.getX());
        int startY = (int) (getFinalWidth() / 2 + mBinding.loginButton.getY());

        float radius = Math.max(x,y) * 1.2f;

        Animator reveal = ViewAnimationUtils.createCircularReveal(mBinding.realView, startX, startY, getFinalWidth(), radius);
        reveal.setDuration(350);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                finish();
            }
        });

        reveal.start();
    }

    private void fadeOutProgressDialog(){
        mBinding.progressBar.animate().alpha(0f).setDuration(200).start();
    }

    private void delayedStartNextActivity(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                userName = findViewById(R.id.userName);
                password = findViewById(R.id.password);

                if(((userName.getText().toString()).equalsIgnoreCase("admin")) && ((password.getText().toString()).equalsIgnoreCase("admin"))){
                    Intent adminLogin = new Intent(MarketHome.this, EmployeeHome.class);
                    startActivity(adminLogin);
                    finish();

                }
                else{
                  getUser();
                }
            }
        }, 1);
    }


    private int getFinalWidth(){
        return (int) getResources().getDimension(R.dimen.get_width);
    }

    private void getUser(){
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        if(((userName.getText().toString()).equalsIgnoreCase("admin")) && ((password.getText().toString()).equalsIgnoreCase("admin"))){
            Intent adminLogin = new Intent(MarketHome.this, EmployeeHome.class);
            startActivity(adminLogin);

        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            Query query = databaseReference.child("RegisteredCustomers").orderByChild("email").equalTo(userName.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()){
                        for (DataSnapshot d: dataSnapshot.getChildren()){
                            RegisteredCustomer registeredCustomer = d.getValue(RegisteredCustomer.class);
                            if (registeredCustomer.getPassword().equals(password.getText().toString())) {
                                Intent login = new Intent(MarketHome.this, ItemMenu.class);
                                login.putExtra("NIC", registeredCustomer.getNIC());
                                startActivity(login);
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(MarketHome.this, MarketHome.class);
                                startActivity(login);
                                finish();
                            }
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please Register First", Toast.LENGTH_LONG).show();
                        Intent login = new Intent(MarketHome.this, MarketHome.class);
                        startActivity(login);
                        finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }
}
