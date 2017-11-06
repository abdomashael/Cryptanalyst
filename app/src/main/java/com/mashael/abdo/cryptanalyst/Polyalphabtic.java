package com.mashael.abdo.cryptanalyst;

/**
 * Created by Mashael on 11/2/2017.
 */

public class Polyalphabtic {

    private int cryption;
    private String text, key, plaintext, ciphertext;

    //cryption = 1 then mode is Encryption
    //cryption = 2 then mode is Decryption

    private static String chars = "abcdefghijklmnopqrstuvwxyz";
    private char[] charsArray = chars.toCharArray();

    Polyalphabtic(int cryption, String text, String key) {
        this.cryption = cryption;
        this.text = text;
        this.key = key;
        setResult();
    }

    private void setResult() {

        char[] keyArray = key.toCharArray();

        try {
            int x, y;

            if (cryption == 1) {
                plaintext = text;
                char[] plaintextArray = plaintext.toCharArray();
                char[] cipher = new char[plaintext.length()];

                for (int p = 0, k = 0; p < plaintext.length(); p++, k++) {

                    if (k == key.length()) {
                        k = 0;
                    }

                    x = chars.indexOf(plaintextArray[p]);
                    y = chars.indexOf(keyArray[k]);

                    cipher[p] = charsArray[(x + y) % 26];


                }

                ciphertext = String.valueOf(cipher);

            } else if (cryption == 2) {
                ciphertext = text;
                char[] ciphertextArray = ciphertext.toCharArray();
                char[] plain = new char[ciphertext.length()];

                for (int c = 0, k = 0; c < ciphertext.length(); c++, k++) {

                    if (k == key.length()) {
                        k = 0;
                    }

                    x = chars.indexOf(ciphertextArray[c]);
                    y = chars.indexOf(keyArray[k]);
                    x = (x - y) % 26;
                    if (x < 0) {
                        x = 26 + x;
                    }
                    plain[c] = charsArray[x];


                }

                plaintext = String.valueOf(plain);
            }
        } catch (Exception e) {

            text = "ERROR!!!!!!!!!!!!!!!!!!";
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
