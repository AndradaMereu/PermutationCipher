package Test;
import org.junit.jupiter.api.Test;
import plain.*;

import java.util.ArrayList;

class PlaintextTest {


    @org.junit.jupiter.api.Test
    void getPlaintext() {
    }

    @org.junit.jupiter.api.Test
    void setPlaintext() {
    }

    @org.junit.jupiter.api.Test
    void transformLetterToNumber() {
    }

    @org.junit.jupiter.api.Test
    void transformPlaintextToNumbers() {
        Plaintext str=new Plaintext("abb bcd");
        ArrayList<Integer> nrs=new ArrayList<>();
        nrs.add(1);
        nrs.add(2);
        nrs.add(2);
        nrs.add(0);
        nrs.add(2);
        nrs.add(3);
        nrs.add(4);
        assert(str.transformPlaintextToNumbers().equals(nrs));

    }

    @org.junit.jupiter.api.Test
    void encrypt() {
        Plaintext str=new Plaintext("abb bcd");

        ArrayList<Integer> conv=str.transformPlaintextToNumbers();
        ArrayList<ArrayList<Integer>> matrix=new ArrayList<>();
        ArrayList<Integer> permutation=new ArrayList<>();
        permutation.add(2);
        permutation.add(3);
        permutation.add(1);
        System.out.println(permutation.size());
        System.out.println(conv.size());
        String encrmatrix=str.encrypt(permutation);
        String bb="bbabc   d";
        assert(bb.equals(encrmatrix));
    }

    @Test
    void decrypt() {
        Plaintext str=new Plaintext("babb c");

        ArrayList<Integer> conv=str.transformPlaintextToNumbers();
        ArrayList<ArrayList<Integer>> matrix=new ArrayList<>();
        ArrayList<Integer> permutation=new ArrayList<>();
        permutation.add(2);
        permutation.add(1);
        permutation.add(3);
        System.out.println(permutation.size());
        System.out.println(conv.size());
        String decrmatrix=str.decrypt(permutation);
        String bb="abb bc";
        assert(bb.equals(decrmatrix));
    }
}