import java.util.Scanner;

public class Menu {
    private int controlNum;
    private boolean run = true;
    private Encryption128 obj = new Encryption128();
    private Decryption Dobj;
    private char[] temp = new char[16];
    private char[] encryptedText;
    private char[] decryptedText;
    private char[] paddedDecryptedText;

    public void startMenu(){
        while(run){
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\rStart Menu\n\rEnter 1 for Encryption\n\rEnter 2 for Decryption\n\rEnter 3 to stop");
            controlNum = sc.nextInt();
            switch (controlNum){
                case 1:
                    Encryption();
                    break;
                case 2:
                    Dobj = new Decryption(SharedAES.key);
                    paddedDecryptedText = Decryption();
                    truncate();
                    break;
                case 3:
                    System.out.println("Cancel operation?\n\r 1 for yes\t2 for no");
                    controlNum = sc.nextInt();
                    if(controlNum == 1){
                        run = false;
                    }
                    break;
                default:
                    System.out.println("Wrong Number try again");
                    break;
            }
        }
    }

    public void Encryption(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String myText = sc.nextLine();

        int originalLength = myText.length();
        int paddedTextLen = originalLength;

        if(paddedTextLen % 16 != 0){                    //pads the message to make sure the message is a multiple of 16
            paddedTextLen = (paddedTextLen / 16 + 1) * 16;
        }
        char[] paddedText = new char[paddedTextLen];
        for(int i = 0;i<paddedTextLen;i++){
            if(i>=originalLength){
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
        System.out.println("Encrypted text");
        int count = 0;
        for(char a: encryptedText){
            if(count % 16 == 0 && count != 0){
                System.out.println();
            }
            System.out.printf("%c",a);    //print as hex/ ("%c", a)
            count++;
        }

    }
    public char[] Decryption(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt: ");
        String myText = sc.nextLine();

        int i = 0;
        decryptedText = new char[myText.length()];
        while (i < myText.length()){
            for(int a = 0;a<16;a++){
                temp[a] = myText.charAt(a+i);
            }
            char[] t = Dobj.decrypt(temp);
            for(int a = 0;a<16;a++){
                encryptedText[i+a] = t[a];
            }
            i+=16;
        }

    return encryptedText;
    }
    public void truncate(){
        int check = paddedDecryptedText.length;
        if(paddedDecryptedText[check] == 0){
            for (int i = 0;i<paddedDecryptedText.length;i++){
                if(paddedDecryptedText[i] != 0){
                    decryptedText[i] = paddedDecryptedText[i];
                }
            }
        }
        else{
            decryptedText = paddedDecryptedText;
        }
    }
}
