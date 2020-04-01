package plain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Plaintext {

    protected String plaintext;
    //protected String ciphertext;


    private char[] az={' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public Plaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public int transformLetterToNumber(char x)
    {

        if (x==' ') return 0;
        else for(char c='a';c<='z';c++)
        {
            if(c==x) return c+1-'a';
        }
        return -1;
    }

    public ArrayList<Integer> transformPlaintextToNumbers()
    {
        char[] str=this.plaintext.toCharArray();
        ArrayList<Integer> convertedVector = new ArrayList<Integer>();
        for(int i=0;i<str.length;i++)
        {
            convertedVector.add(transformLetterToNumber(str[i]));
        }
        return convertedVector;
    }

    String getString(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }



    /*
     The function encrypts the plaintext using the permutation cipher
     IN: the key (i.e the permutation, written as an array of integers
     OUT: a, string representing the encrypted text
     */
    public String encrypt(ArrayList<Integer> permutation)
    {
        int m = permutation.size();

        // converts the plaintext into numbers
        ArrayList<Integer> convertedVector=transformPlaintextToNumbers();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        //if the size of the plaintext is not divisible by m, then it adds 0 until this condition is met
        while (convertedVector.size()%m!=0) convertedVector.add(0);

        //transform the array of numbers into blocks of arrays (creates a matrix)
        for(int i=0;i<((convertedVector.size())/m);i++)
        {
            matrix.add(new ArrayList<Integer>());
            for(int j=0;j<m;j++)
            {
                matrix.get(i).add(convertedVector.get(m*(i)+j));
            }
        }

        //encrypts the matrix by adding an element on the position given by the permutation
        ArrayList<ArrayList<Integer>> encryptedmatrix = new ArrayList<>();
        for(int i=0;i<((convertedVector.size())/m);i++)
        {
            encryptedmatrix.add(new ArrayList<Integer>());
            for (int j = 0; j < m; j++) {
                encryptedmatrix.get(i).add(matrix.get(i).get(permutation.get(j) - 1));
            }
        }

        //transforms the array of numbers back into a string
        ArrayList<Character> toText=new ArrayList<>();
        for(int i=0;i<((convertedVector.size())/m);i++)
            for (int j = 0; j < m; j++)
            {
                toText.add(az[encryptedmatrix.get(i).get(j)]);

            }

        return getString(toText);
    }

    /*
     The function decrypts the plaintext using the permutation cipher
     IN: the key (i.e the permutation, written as an array of integers)
     OUT: a, string representing the decrypted text
     */
    public String decrypt(ArrayList<Integer> permutation)
    {
        int m = permutation.size();
        // converts the plaintext into numbers
        ArrayList<Integer> convertedVector=transformPlaintextToNumbers();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        //if the size of the plaintext is not divisible by m, then it adds 0 until this condition is met
        while (convertedVector.size()%m!=0) convertedVector.add(0);

        // transforms the array into a matrix (for working in blocks)
        for(int i=0;i<((convertedVector.size())/m);i++)
        {
            matrix.add(new ArrayList<Integer>());
            for(int j=0;j<m;j++)
            {
                matrix.get(i).add(convertedVector.get(m*(i)+j));
            }
        }

        ArrayList<ArrayList<Integer>> decryptedmatrix = new ArrayList<>();
        //creates a copy of the matrix
        for(int i=0;i<((convertedVector.size())/m);i++)
        {
            decryptedmatrix.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                decryptedmatrix.get(i).add(matrix.get(i).get(j));
            }
        }

        //decrypts the matrix
        for(int i=0;i<((convertedVector.size())/m);i++) {

            for (int j = 0; j < m; j++) {
                decryptedmatrix.get(i).set(permutation.get(j)-1,matrix.get(i).get(j));
            }
        }

        ArrayList<Character> toText=new ArrayList<>();
        for(int i=0;i<((convertedVector.size())/m);i++)
            for (int j = 0; j < m; j++)
            {
                toText.add(az[decryptedmatrix.get(i).get(j)]);

            }

        return getString(toText);
    }

}
