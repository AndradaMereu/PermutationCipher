package ui;

import plain.Plaintext;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Plaintext ptxt;
    public Ui(Plaintext ptxt) {this.ptxt=ptxt;}

    public void printMenu()
    {
        System.out.println("Choose your option: ");
        System.out.println("1. Encrypt a text");
        System.out.println("2. Decrypt a text");
    }

    public void run()
    {
        while(true)
        {
            printMenu();
            Scanner s=new Scanner(System.in);
            int command=s.nextInt();
            if(command==1)
            {
                System.out.println("Enter the message: ");
                s.nextLine();
                String str=s.nextLine().toLowerCase();
                this.ptxt.setPlaintext(str);
                System.out.println("Enter the key length ");
                int m=s.nextInt();
                s.nextLine();
                ArrayList<Integer> permutation=new ArrayList<>();
                int i=0;
                while(i<m)
                {
                    System.out.println("v["+(i+1)+"]= ");
                    int nr=s.nextInt();
                    s.nextLine();
                    while(nr<=0 || nr>m || permutation.contains(nr) ) {System.out.println("Invalid number. Try again: "); nr=s.nextInt();s.nextLine();}
                    permutation.add(nr);
                    i++;
                }
                System.out.println(ptxt.encrypt(permutation));
            }
            if(command==2)
            {
                System.out.println("Enter the message: ");
                s.nextLine();
                this.ptxt.setPlaintext(s.nextLine().toLowerCase());
                System.out.println("Enter the key length ");
                int m=s.nextInt();
                s.nextLine();
                ArrayList<Integer> permutation=new ArrayList<>();
                int i=0;
                while(i<m)
                {
                    System.out.println("v["+(i+1)+"]= ");
                    int nr=s.nextInt();
                    while(nr<=0 || nr>m || permutation.contains(nr)) {System.out.println("Invalid number. Try again: "); nr=s.nextInt();s.nextLine();}
                    permutation.add(nr);
                    i++;
                }
                System.out.println(ptxt.decrypt(permutation));
            }
        }
    }

}
