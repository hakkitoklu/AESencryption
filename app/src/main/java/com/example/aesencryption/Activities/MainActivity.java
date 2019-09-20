package com.example.aesencryption.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.aesencryption.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gotoenc)
    Button gotoenc;
    @BindView(R.id.gotodec)
    Button gotodec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.gotoenc)
    public void setGotoenc(){
        Intent i=new Intent(MainActivity.this,Encryption.class);
        startActivity(i);
    }


    @OnClick(R.id.gotodec)
    public void setGotodec(){
        Intent i=new Intent(MainActivity.this,Decryption.class);
        startActivity(i);
    }

}
