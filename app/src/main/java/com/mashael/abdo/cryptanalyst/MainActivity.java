package com.mashael.abdo.cryptanalyst;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MainActivity extends Activity {

    Spinner cryptionMethod;
    Spinner cryptionMode;
    EditText key;
    EditText text;
    TextView keyWord;
    TextView result;
    TextView pOrC;

    String cryptionMethodsValue;
    String cryptionModeValue;
    String keyValue;
    String textValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cryptionMethod = (Spinner) findViewById(R.id.selected_method);
        cryptionMode = (Spinner) findViewById(R.id.selected_cryption);
        key = (EditText) findViewById(R.id.key);
        text = (EditText) findViewById(R.id.text);
        keyWord = (TextView) findViewById(R.id.keyWord);
        result = (TextView) findViewById(R.id.result);
        pOrC = (TextView) findViewById(R.id.pOrc);


        cryptionMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cryptionMethodsValue = String.valueOf(cryptionMethod.getSelectedItem());
                if ((cryptionMethodsValue.equals("Caesar Cipher") || cryptionMethodsValue.equals("Playfair Cipher")|| cryptionMethodsValue.equals("Use of permutation") || cryptionMethodsValue.equals("Polyalphabtic Ciphers")|| cryptionMethodsValue.equals("Des")) && key.getVisibility() == View.GONE) {

                    key.setVisibility(View.VISIBLE);
                    keyWord.setVisibility(View.VISIBLE);

                } else if ((cryptionMethodsValue.equals("Rail Fence") ) && key.getVisibility() == View.VISIBLE) {

                    key.setText("");
                    key.setVisibility(View.GONE);
                    keyWord.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void onClickOk(View view) throws InvalidKeyException {

        cryptionMethodsValue = String.valueOf(cryptionMethod.getSelectedItem());
        cryptionModeValue = String.valueOf(cryptionMode.getSelectedItem());
        keyValue = String.valueOf(key.getText());
        textValue = String.valueOf(text.getText());

        // Check if no view has focus:
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        //Check the text not empty
        if (!textValue.equals("")) {

            //Encryption Conditions
            if (cryptionModeValue.equals("Encryption")) {

                pOrC.setText("Ciphertext is :");

                    textValue = textValue.replace(" ", "");

                //Check the Key not empty
                if (!keyValue.equals("")) {

                    if (cryptionMethodsValue.equals("Caesar Cipher")) {

                        if (!StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            Caesar caesar = new Caesar(1, textValue, keyValue);
                            result.setText(caesar.getResult());
                        }

                    } else if (cryptionMethodsValue.equals("Playfair Cipher")) {

                        PlayFair playFair = new PlayFair(1, textValue, keyValue);
                        result.setText(playFair.getResult());

                    } else if (cryptionMethodsValue.equals("Polyalphabtic Ciphers")) {

                        if (StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as letters not Numbers ", Toast.LENGTH_LONG).show();
                        } else {

                            Polyalphabtic polyalphabtic = new Polyalphabtic(1, textValue, keyValue);
                            result.setText(polyalphabtic.getResult());
                        }
                    }else if (cryptionMethodsValue.equals("Use of permutation")) {


                        if (!StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            UseOfPermutation useOfPermutation = new UseOfPermutation(1, textValue, keyValue,this);
                            result.setText(useOfPermutation.getResult());
                        }
                    }else if (cryptionMethodsValue.equals("Des")) {

                        String password = keyValue;
                        SecretKeyFactory keyFactory = null;
                        try {
                            DESKeySpec key = new DESKeySpec(password.getBytes());
                            keyFactory = SecretKeyFactory.getInstance("DES");
                            //Instantiate the encrypter/decrypter
                            Des crypt = new Des(keyFactory.generateSecret(key));
                            String unencryptedString = textValue;
                            String encryptedString = crypt.encryptBase64(unencryptedString);
                            result.setText(encryptedString);
                        } catch (NoSuchAlgorithmException e ) {
                        } catch (InvalidKeySpecException e) {
                        } catch (Exception e) {
                        }


                    } else {
                        Toast.makeText(this, "plz Insert Key ", Toast.LENGTH_LONG).show();
                    }


                    //Decryption Conditions
                } else if (cryptionMethodsValue.equals("Rail Fence")) {
                    key.setVisibility(View.GONE);
                    keyWord.setVisibility(View.GONE);

                    RailFence railFence = new RailFence(1, textValue);
                    result.setText(railFence.getResult());

                }
            } else if (cryptionModeValue.equals("Decryption")) {

                pOrC.setText("Plaintext is :");

                //Check the Key not empty
                if (!keyValue.equals("")) {

                    if (cryptionMethodsValue.equals("Caesar Cipher")) {

                        if (!StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            Caesar caesar = new Caesar(2, textValue, keyValue);
                            result.setText(caesar.getResult());
                        }
                    } else if (cryptionMethodsValue.equals("Playfair Cipher")) {
                        if (StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as letters not Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            PlayFair playFair = new PlayFair(2, textValue, keyValue);
                            result.setText(playFair.getResult());
                        }


                    } else if (cryptionMethodsValue.equals("Polyalphabtic Ciphers")) {

                        if (StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as letters not Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            Polyalphabtic polyalphabtic = new Polyalphabtic(2, textValue, keyValue);
                            result.setText(polyalphabtic.getResult());
                        }
                    } else if (cryptionMethodsValue.equals("Use of permutation")) {

                        if (!StringUtils.isNumeric(keyValue)) {
                            Toast.makeText(this, "plz Insert Key as Numbers ", Toast.LENGTH_LONG).show();
                        } else {
                            UseOfPermutation useOfPermutation= new UseOfPermutation(2, textValue, keyValue,this);
                            result.setText(useOfPermutation.getResult());
                        }

                    } else if (cryptionMethodsValue.equals("Des")) {

                        String password = keyValue;
                        SecretKeyFactory keyFactory = null;

                        try {
                            DESKeySpec key = new DESKeySpec(password.getBytes());
                            keyFactory = SecretKeyFactory.getInstance("DES");
                            //Instantiate the encrypter/decrypter
                            Des crypt = new Des(keyFactory.generateSecret(key));
                            String encryptedString = textValue;
                            String unencryptedString = crypt.decryptBase64(encryptedString);
                            result.setText(unencryptedString);
                        } catch (NoSuchAlgorithmException e ) {
                        } catch (InvalidKeySpecException e) {
                        } catch (Exception e) {
                        }


                    }else {
                        Toast.makeText(this, "plz Insert Key ", Toast.LENGTH_LONG).show();
                    }

                } else if (cryptionMethodsValue.equals("Rail Fence")) {


                    RailFence railFence = new RailFence(2, textValue);
                    result.setText(railFence.getResult());

                    key.setVisibility(View.GONE);
                    keyWord.setVisibility(View.GONE);


                } else {
                    Toast.makeText(this, "plz Insert Key ", Toast.LENGTH_LONG).show();

                }

            }

        } else {
            Toast.makeText(this, "plz Insert Text ", Toast.LENGTH_LONG).show();
        }


    }
}
