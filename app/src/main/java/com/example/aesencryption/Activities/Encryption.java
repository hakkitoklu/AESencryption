package com.example.aesencryption.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aesencryption.Functions.Encrypt;
import com.example.aesencryption.R;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Encryption extends Activity {

    @BindView(R.id.etOrjText)
    EditText etOrjText;
    @BindView(R.id.secretkey)
    TextView tvSecretKey;
    @BindView(R.id.sonuc)
    TextView tvSonuc;
    @BindView(R.id.anahtar)
    TextView tvAnahtar;
    @BindView(R.id.encBtn)
    Button encBtn;

    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] secretKeyen;
    String strSecretKey;
    byte[] IV = new byte[16];
    byte[] cipherText;
    SecureRandom random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encryption);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.encBtn)
    public void btnEncodeClick() {
        if (TextUtils.isEmpty(etOrjText.getText())) {
            Toast t = Toast.makeText(this, "Fill empty field.", Toast.LENGTH_SHORT);
            t.show();
        } else {
            try {
                /*Keygenerator simetrik şifreleme anahtarı üretmek için kullanılan bir kütüphanedir.Öncelikle KeyGenerator kurulumu yapılır
                 * getInstance ile algoritma isminin bir parametre olarak alınması engellenir.*/
                keyGenerator = KeyGenerator.getInstance("AES");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            keyGenerator.init(256);// init yöntemiyle oluşturulan KeyGenerator örneği başlatılır.Burada 256bit değer kullanıldı.
            secretKey = keyGenerator.generateKey();//Kurulum tamamlandıktan sonra generateKey() ile anahtar üretilir.
            secretKeyen=secretKey.getEncoded();
            strSecretKey = encoderfun(secretKeyen);
            tvSecretKey.setText(strSecretKey);

            /*IV, Başlatma Vektörü anlamına gelir, şifreleme sırasında SecretKey ile birlikte kullanılacak isteğe bağlı bir sayıdır. IV, şifreleme işleminin başlangıcına rastgelelik ekler, aynı zamanda sadece bir kez kullanılacağından bağımsızlık olarak da adlandırılır.
             * */
            random = new SecureRandom();
            random.nextBytes(IV);
            try {
                cipherText = Encrypt.encrypt(etOrjText.getText().toString().trim().getBytes(), secretKey, IV);

                String sonuc = encoderfun(cipherText);
                tvSonuc.setText(sonuc);


                String tvIV = encoderfun(IV);
                tvAnahtar.setText(tvIV);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static String encoderfun(byte[] decval) {
        String conVal= Base64.encodeToString(decval,Base64.DEFAULT);
        return conVal;
    }
}
