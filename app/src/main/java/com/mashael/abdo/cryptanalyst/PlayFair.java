package com.mashael.abdo.cryptanalyst;

import android.util.ArraySet;
import android.util.Log;

import org.apache.commons.lang3.ArrayUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mashael on 10/31/2017.
 */

public class PlayFair {


    private int cryption, shift, keyLenght;
    private String text, key, plaintext, ciphertext;

    List<Character> textArraylist;
    char[][] textArray2;
    private char[][] newKey2 = new char[5][5];
    private char[] textArray;
    private char[] xChar = new char[]{' '};
    //cryption = 1 then mode is Encryption
    //cryption = 2 then mode is Decryption

    private static String chars = "abcdefghijklmnopqrstuvwxyz";
    private char[] charsArray = chars.toCharArray();

    PlayFair(int cryption, String text, String key) {
        this.cryption = cryption;
        this.text = text;
        this.key = key;
        setResult();
    }

    private void setResult() {

        char[] keyArray = key.toCharArray();
        keyLenght = key.length();
        textArray = text.toCharArray();
        char[] newKey = new char[keyLenght];
        // copy user key to array 26 index
        System.arraycopy(keyArray, 0, newKey, 0, keyLenght);
        // remove user key letters from array contains all lang letters

        for (int i = 0; i < keyLenght; i++) {
            if (Arrays.binarySearch(charsArray, keyArray[i]) >= 0) {
                charsArray = ArrayUtils.removeElement(charsArray, keyArray[i]);
            }
        }


        //concatenate user key with reminds letters for for create our matrix key
        char[] newKeye = new char[keyLenght];
        int xe = 0;
        for (int i = 0; i < keyLenght; i++) {
            if (!ArrayUtils.contains(newKeye, newKey[i])) {
//            if(Arrays.binarySearch(newKeye,newKey[i])<0){
                newKeye[xe] = newKey[i];
                ++xe;
            }
        }

        if (!(Arrays.binarySearch(newKeye, 'i') < 0)) {
            charsArray = ArrayUtils.removeElement(charsArray, 'j');
        } else if (!(Arrays.binarySearch(newKeye, 'j') < 0)) {
            charsArray = ArrayUtils.removeElement(charsArray, 'i');

        }

        newKey = ArrayUtils.addAll(newKeye, charsArray);


        if (!(Arrays.binarySearch(newKey, 'i') < 0)) {
            newKey = ArrayUtils.removeElement(newKey, 'j');
        } else if (!(Arrays.binarySearch(newKey, 'j') < 0)) {
            newKey = ArrayUtils.removeElement(newKey, 'i');
        }


        for (int r = 0, x = 0; r < newKey2.length; r++) {
            for (int c = 0; c < newKey2[r].length; c++, x++) {
                newKey2[r][c] = newKey[x];

            }
        }


        try {

            if (cryption == 1) {
                plaintext = text;

                textArray2 = new char[text.length() / 2][4];
                for (int i = 0, x = 0; x < text.length() - 1; i++, x += 2) {
                    textArray2[i][0] = textArray[x];
                    if (x >= text.length()) {
                        break;
                    }
                    textArray2[i][1] = textArray[x + 1];
                    if (textArray2[i][0] == textArray2[i][1]) {
                        textArray2[i][3] = textArray2[i][1];
                        textArray2[i][1] = textArray2[i][2] = 'x';
                    }
                }

                plaintext = "";

                for (char[] aPlain : textArray2) {
                    for (char anAPlain : aPlain) {
                        plaintext += anAPlain;
                    }

                }


                textArray = plaintext.toCharArray();
                textArraylist = new ArrayList<>();
                ArrayList<Character> cipherList = new ArrayList<>();
                for (char c : textArray) {
                    if (c != '\u0000') {
                        textArraylist.add(c);
                    }
                }
                int x = 0;
                for (int r = 0; r < newKey2.length; r++) {
                    for (int c = 0; c < newKey2[r].length; c++)
                        if (newKey2[r][c] == textArraylist.get(x)) {

                        }
                }

                if (textArraylist.size() / 2 == 0) {
                    textArraylist.add('x');
                }

                int t = 0, r1 = 0, r2 = 0, c1 = 0, c2 = 0;
                while (t < textArraylist.size() - 1) {
                    for (int i = 0; i < newKey2.length; i++) {
                        for (int j = 0; j < newKey2[i].length; j++) {

                            if (textArraylist.get(t).equals('j')) {
                                textArraylist.set(t, 'i');
                            }

                            if (textArraylist.get(t + 1).equals('j')) {
                                textArraylist.set(t + 1, 'i');
                            }

                            if (newKey2[i][j] == textArraylist.get(t)) {
                                r1 = i;
                                c1 = j;
                            }
                            if (newKey2[i][j] == textArraylist.get(t + 1)) {
                                r2 = i;
                                c2 = j;
                            }
                        }

                    }

                    if (r1 == r2) {

                        if (c1 == newKey2[r1].length - 1) {
                            cipherList.add(newKey2[r1][0]);
                        } else {
                            cipherList.add(newKey2[r1][c1 + 1]);

                        }
                        if (c2 == newKey2[r1].length - 1) {
                            cipherList.add(newKey2[r1][0]);
                        } else {
                            cipherList.add(newKey2[r1][c2 + 1]);

                        }

                    } else if (c1 == c2) {

                        if (r1 == newKey2.length - 1) {
                            cipherList.add(newKey2[0][c1]);
                        } else {
                            cipherList.add(newKey2[r1 + 1][c1]);

                        }
                        if (r2 == newKey2.length - 1) {
                            cipherList.add(newKey2[0][c1]);
                        } else {
                            cipherList.add(newKey2[r2 + 1][c1]);

                        }
                    } else {
                        cipherList.add(newKey2[r1][c2]);
                        cipherList.add(newKey2[r2][c1]);
                    }


                    t += 2;
                }


                ciphertext = "";

                for (char aCipher : cipherList) {
                    ciphertext += aCipher;

                }



            } else if (cryption == 2) {
                plaintext = text;

                textArray2 = new char[text.length() / 2][4];
                for (int i = 0, x = 0; x < text.length() - 1; i++, x += 2) {
                    textArray2[i][0] = textArray[x];
                    if (x >= text.length()) {
                        break;
                    }
                    textArray2[i][1] = textArray[x + 1];
                    if (textArray2[i][0] == textArray2[i][1]) {
                        textArray2[i][3] = textArray2[i][1];
                        textArray2[i][1] = textArray2[i][2] = 'x';
                    }
                }

                plaintext = "";

                for (char[] aPlain : textArray2) {
                    for (char anAPlain : aPlain) {
                        plaintext += anAPlain;
                    }

                }


                textArray = plaintext.toCharArray();
                textArraylist = new ArrayList<>();
                ArrayList<Character> cipherList = new ArrayList<>();
                for (char c : textArray) {
                    if (c != '\u0000') {
                        textArraylist.add(c);
                    }
                }
                int x = 0;
                for (int r = 0; r < newKey2.length; r++) {
                    for (int c = 0; c < newKey2[r].length; c++)
                        if (newKey2[r][c] == textArraylist.get(x)) {

                        }
                }

                if (textArraylist.size() / 2 == 0) {
                    textArraylist.add('x');
                }

                int t = 0, r1 = 0, r2 = 0, c1 = 0, c2 = 0;
                while (t < textArraylist.size() - 1) {
                    for (int i = 0; i < newKey2.length; i++) {
                        for (int j = 0; j < newKey2[i].length; j++) {

                            if (textArraylist.get(t).equals('j')) {
                                textArraylist.set(t, 'i');
                            }

                            if (textArraylist.get(t + 1).equals('j')) {
                                textArraylist.set(t + 1, 'i');
                            }

                            if (newKey2[i][j] == textArraylist.get(t)) {
                                r1 = i;
                                c1 = j;
                            }
                            if (newKey2[i][j] == textArraylist.get(t + 1)) {
                                r2 = i;
                                c2 = j;
                            }
                        }

                    }

                    if (r1 == r2) {

                        if (c1 == 0) {
                            cipherList.add(newKey2[r1][newKey2[r1].length - 1]);
                        } else {
                            cipherList.add(newKey2[r1][c1 - 1]);

                        }
                        if (c2 == 0) {
                            cipherList.add(newKey2[r1][newKey2[r1].length - 1]);
                        } else {
                            cipherList.add(newKey2[r1][c2 - 1]);

                        }

                    } else if (c1 == c2) {

                        if (r1 == 0) {
                            cipherList.add(newKey2[newKey2.length - 1][c1]);
                        } else {
                            cipherList.add(newKey2[r1 - 1][c1]);

                        }
                        if (r2 == 0) {
                            cipherList.add(newKey2[newKey2.length - 1][c1]);
                        } else {
                            cipherList.add(newKey2[r2 - 1][c1]);

                        }
                    } else {
                        cipherList.add(newKey2[r1][c2]);
                        cipherList.add(newKey2[r2][c1]);
                    }


                    t += 2;
                }


                plaintext = "";

                for (char aCipher : cipherList) {
                    plaintext += aCipher;

                }

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
        // return plaintext + "\n\n" + textArraylist + "\n\n";
    }

}
