package com.mashael.abdo.cryptanalyst;

import android.widget.Toast;

/**
 * Created by Mashael on 10/31/2017.
 */


public class Caesar {
    private int cryption;
    private String text, key, plaintext, ciphertext;

    //cryption = 1 then mode is Encryption
    //cryption = 2 then mode is Decryption

    private static char[] chars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};

    Caesar(int cryption, String text, String key) {
        this.cryption = cryption;
        this.text = text;
        this.key = key;
        setResult();
    }

    private void setResult() {

        try {

            int shift;
            if (cryption == 1) {
                plaintext = text;

                int key2 = Integer.parseInt(key);
                char[] plain = plaintext.toCharArray();
                char[] cipher = new char[plain.length];

                for (int i = 0; i < plain.length; i++) {
                    if (plain[i] == ' ') {
                        cipher[i] = ' ';
                    } else {
                        for (int x = 0; x < 26; x++) {
                            if (plain[i] == chars[x]) {
                                shift = (x + key2) % 26;
                                cipher[i] = chars[shift];

                            } else if (plain[i] == chars[x + 26]) {
                                shift = ((x + key2) % 26) + 26;

                                cipher[i] = chars[shift];

                            }
                        }//End of For loop

                    }

                } //End of For loop
                ciphertext = String.valueOf(cipher);

            } else if (cryption == 2) {
                ciphertext = text;
                int key2 = Integer.parseInt(key);
                char[] cipher = ciphertext.toCharArray();
                char[] plain = new char[cipher.length];

                for (int i = 0; i < cipher.length; i++) {
                    if (cipher[i] == ' ') {
                        plain[i] = ' ';
                    } else {
                        for (int x = 0; x < 26; x++) {
                            if (cipher[i] == chars[x]) {
                                shift = (x - key2) % 26;
                                if(shift <0){
                                    shift =26+ shift;
                                }
                                plain[i] = chars[shift];

                            } else if (cipher[i] == chars[x + 26]) {
                                shift = ((x - key2) % 26) + 26;
                                if(shift <26){
                                    shift =26+ shift;
                                }
                                plain[i] = chars[shift];

                            }
                        }//End of For loop

                    }

                } //End of For loop
                plaintext = String.valueOf(plain);
            }
        } catch (Exception e) {

           text= "ERROR!!!!!!!!!!!!!!!!!!";
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
