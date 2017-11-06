package com.mashael.abdo.cryptanalyst;

import java.util.Arrays;

/**
 * Created by Mashael on 11/2/2017.
 */

public class RailFence {
    private int cryption;
    char[] plaintextArray;

    private String text, plaintext, ciphertext;

    //cryption = 1 then mode is Encryption
    //cryption = 2 then mode is Decryption


    RailFence(int cryption, String text) {
        this.cryption = cryption;
        this.text = text;
        setResult();
    }

    private void setResult() {




        if (cryption == 1) {
            plaintext = text;

            plaintextArray = plaintext.toCharArray();
            char[] cipher = new char[plaintextArray.length];


            int i = 0,c;
            for (c = 0; c < plaintextArray.length; c++) {

                if (c % 2 == 0) {
                    cipher[i] = plaintextArray[c];
                    ++i;
                }
            }

            for (c = 0; c < plaintextArray.length; c++) {
                if (c % 2 != 0) {
                    cipher[i] = plaintextArray[c];
                    ++i;
                }
            }

            ciphertext = String.valueOf(cipher);

        } else if (cryption == 2) {

            ciphertext = text;

            char[] ciphertextArray = ciphertext.toCharArray();
            char[] plain = new char[ciphertext.length()];


            int c;
            int l = (int)Math.ceil(ciphertextArray.length/2);
            if(ciphertextArray.length%2!=0){
                ++l;
            }
            for (c = 0; c < l; c++) {
                    plain[c*2] = ciphertextArray[c];
            }
            for (c = l; c < ciphertextArray.length; c++) {
                    --l;
                    plain[c-l] = ciphertextArray[c];
            }



            plaintext = String.valueOf(plain);
        }
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

