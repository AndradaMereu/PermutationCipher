import plain.Plaintext;
import ui.*;


public class main {

    public static void main(String[] args)
    {
        String str="";
        Plaintext pp=new Plaintext(str);
        Ui ui= new Ui(pp);
        ui.run();
    }
}
