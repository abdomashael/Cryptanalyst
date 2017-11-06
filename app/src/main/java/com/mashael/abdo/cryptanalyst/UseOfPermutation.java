package com.mashael.abdo.cryptanalyst;

import android.content.Context;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Created by Mashael on 11/3/2017.
 */

public class UseOfPermutation {

    private final Context context;
    private int cryption;
    private String text, key, plaintext, ciphertext;


    UseOfPermutation(int cryption, String text, String key,Context c) {
        this.cryption = cryption;
        this.text = text;
        this.key = key;
        this.context = c;
        setResult();
    }

    private void setResult() {

        try {

            if (cryption == 1) {
                plaintext = text;
                int plainLenght = plaintext.length();
                char[] plainArray = plaintext.toCharArray();
                int keyLenght = key.length();

                int r = (plainLenght / keyLenght);
                if (plainLenght % keyLenght != 0) {
                    ++r;
                }

                char[][] cipherBefore = new char[r][keyLenght];

                int z = 0;
                for (int i = 0; i < r; i++) {

                    for (int j = 0; j < keyLenght; j++) {
                        if (z >= plainLenght) {
                            break;
                        }
                        cipherBefore[i][j] = plainArray[z];
                        ++z;
                    }
                }

                for (int i = 0; i < r; i++) {

                    for (int j = 0; j < keyLenght; j++) {
                        if (cipherBefore[i][j] == '\u0000') {
                            cipherBefore[i][j] = 'x';
                        }
                    }
                }


                char[][] cipher = new char[keyLenght][r];

                int k;
                for (int i = 1; i <= keyLenght; i++) {
                    k = key.indexOf(String.valueOf(i));
                    for (int j = 0; j < r; j++) {
                        cipher[i - 1][j] = cipherBefore[j][k];
                    }
                }

                ciphertext = "";

                for (char[] aCipher : cipher) {
                    for (char anACipher : aCipher) {
                        ciphertext += anACipher;
                    }

                }


            } else if (cryption == 2) {
                ciphertext = text;
                int cipherLenght = ciphertext.length();
                char[] cipherArray = ciphertext.toCharArray();
                int keyLenght = key.length();

                int c = (cipherLenght / keyLenght);
                if (cipherLenght % keyLenght != 0) {
                    ++c;
                }


                char[][] plainBefore = new char[keyLenght][c];

                int z = 0;
                for (int i = 0; i < keyLenght; i++) {

                    for (int j = 0; j < c; j++) {
                        if (z >= cipherLenght) {
                            break;
                        }
                        plainBefore[i][j] = cipherArray[z];
                        ++z;
                    }
                }


                char[][] plain = new char[keyLenght][c];

                int k;
                for (int i = 1; i <= keyLenght; i++) {
                    k = key.indexOf(String.valueOf(i));

                    plain[k] = plainBefore[i - 1];
                }

                char[][] finalPlain = new char[c][keyLenght];


                for (int i = 0; i < c; i++) {
                    for (int j = 0; j < keyLenght; j++) {
                        finalPlain[i][j] = plain[j][i];
                    }
                }

                //plaintext =c+"\n\n"+ String.valueOf(Arrays.deepToString(plain))+"\n\n"+String.valueOf(Arrays.deepToString(finalPlain));
                plaintext = "";

                for (char[] aPlain : finalPlain) {
                    for (char anAPlain : aPlain) {
                        plaintext += anAPlain;
                    }

                }

            }


        } catch (Exception e) {
            showToastMethod(context);

        }
    }


    private static void showToastMethod(Context context) {
        Toast.makeText(context, "Plz, Ensure from your key ", Toast.LENGTH_SHORT).show();
    }

    String getResult() {
        if (cryption == 1) {
            text = ciphertext;
        } else if (cryption == 2) {
            text = plaintext;
        }

        return text;
    }

}
