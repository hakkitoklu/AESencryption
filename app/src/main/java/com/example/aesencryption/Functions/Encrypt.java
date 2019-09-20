package com.example.aesencryption.Functions;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception
    {   /*Şifreleme sınıfı, gerçek şifreleme ve şifre çözme işlemlerini yapan sınıftır. Cipher sınıfı örneği, Cipher adını parametre olarak geçiren getInstance () yöntemi çağrılarak oluşturulur, biz AES/CBC/PKCS5Padding kullandık.*/
        Cipher cipher = Cipher.getInstance("AES");
        /*SecretKeySpec, bayt verilerini Cipher sınıfının init () yöntemine aktarılmaya uygun bir gizli anahtara dönüştürme mekanizması sağlar.*/
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        /*ivParameterSpec, bir başlatma vektörü için bir sarıcıdır, IV, IvParameterSpec'in konfigüre edilme şeklindeki rastgeleliğini alır.*/
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        /*Cipher örneği oluşturulduktan sonra, init () yöntemini çağırarak şifreli örneğini başlatmamız gerekir. 3 parametreyi init () yöntemine geçirmemiz gerekir.
        * Cipher.ENCRYPT_MODE
        * keySpec
        * ivSpec*/
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }
}


