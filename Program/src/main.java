import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
	    Encryption128 obj = new Encryption128();
        char[] encryptedText;
        char[] temp = new char[16];

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String myText = sc.nextLine();

        int originalLenght = myText.length();
        int paddedTextLen = originalLenght;

        if(paddedTextLen % 16 != 0){                    //pads the message to make sure the message is a multiple of 16
            paddedTextLen = (paddedTextLen / 16 + 1) * 16;
        }
        char[] paddedText = new char[paddedTextLen];
        for(int i = 0;i<paddedTextLen;i++){
            if(i>=originalLenght){
                paddedText[i] = 0;
            }
            else{
                paddedText[i] = myText.charAt(i);
            }
        }
        int i = 0;
        encryptedText = new char[paddedTextLen];
        while (i < paddedTextLen){
            for(int a = 0;a<16;a++){
                temp[a] = paddedText[a+i];
            }
            char[] t = obj.encrypt(temp);
            for(int a = 0;a<16;a++){
                encryptedText[i+a] = t[a];
            }
            i+=16;
        }

        for(char a: encryptedText){
            System.out.printf(String.format("%02x ",(int) a));
        }
    }
}
