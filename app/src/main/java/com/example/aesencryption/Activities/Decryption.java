package com.example.aesencryption.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aesencryption.Functions.Decrypt;
import com.example.aesencryption.Functions.Utils;
import com.example.aesencryption.R;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Decryption extends Activity {

    @BindView(R.id.decBtn)
    Button decBtn;
    @BindView(R.id.etEncText)
    EditText etEncText;
    @BindView(R.id.etAnahtar)
    EditText etAnahtar;
    @BindView(R.id.tvOrjText)
    TextView tvOrjText;
    @BindView(R.id.etSecretKey)
    EditText etSecretKey;
    String decryptedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decryption);
        ButterKnife.bind(this);

        List<String> list = Utils.getData(this);
        if (list.size() > 0) {
            etEncText.setText(list.get(0));
            etSecretKey.setText(list.get(1));
            etAnahtar.setText(list.get(2));
        }

    }
    @OnClick(R.id.decBtn)
    public void btnDecodeClick() {
        if ((TextUtils.isEmpty(etEncText.getText()) || (TextUtils.isEmpty(etAnahtar.getText()) || (TextUtils.isEmpty(etSecretKey.getText()))))) {
            Toast t = Toast.makeText(this, "Fill empty fields.", Toast.LENGTH_SHORT);
            t.show();
        } else {
            try {
                String strEncText=etEncText.getText().toString().trim();
                byte[] encText = decoderfun(strEncText);
                String strAnahtar=etAnahtar.getText().toString().trim();
                byte[] iv = decoderfun(strAnahtar);
                String strSecretKey=etSecretKey.getText().toString().trim();
                byte[] encodedSecretKey = decoderfun(strSecretKey);
                SecretKey originalSecretKey = new SecretKeySpec(encodedSecretKey, 0, encodedSecretKey.length, "AES");

                decryptedText = Decrypt.decrypt(encText,originalSecretKey,iv);
                tvOrjText.setText(decryptedText);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static byte[] decoderfun(String enval) {
        byte[] conVal = Base64.decode(enval,Base64.DEFAULT);
        return conVal;

    }
}
