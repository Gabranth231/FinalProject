import java.util.Random;

public class Encryption128 {
    private static int rounds = 10;
    private String Text = "Hello my name is martin and this is my plaintext";
    private String cipherText;
    private char[][] key;
    static private char[][] SBox;
    static int objCount = 0;
    Random rand = new Random();

    public Encryption128 (){
        setKey();
        if(objCount<1) {
            setSBox();
        }
        objCount++;
    }

    public void setKey() {
        char[][] key = new char[4][4];

        for(int x = 0;x<4;x++){
            for(int y = 0;y<4;y++){
                key[y][x] = (char) ('a' + rand.nextInt(26));
            }
        }
        this.key = key;

    }
    public void setSBox() {
        char[][] result = new char[16][16];
        //Need to read file and set sBox.
        SBox = result;
    }

    public String Encrypt() {
        char[] arr = this.Text.toCharArray();
        char[][] state = new char[4][4];
        char[][] roundText = new char[4][4];
        StringBuilder cipherText = new StringBuilder();
        int position = 0;
        int count = 0;
        while(position != arr.length){
            for(int x = 0;x<4;x++){
                for(int y = 0;y<4;y++){
                    state[y][x] = arr[count++];
                }
            }
            count = 0;
            roundText = AddRoundKey(state);
            for(int i = 0;i<rounds-1;i++){
                roundText = subBytes(roundText);
                //ShiftRows
                //MixColumns
                //AddRoundKey
            }
            //subBytes
            //shiftRows
            roundText = AddRoundKey(state);
            cipherText.append(roundText);

        }
        return "Hello";
    }

    public char[][] AddRoundKey(char[][] state){
        char[][] result = new char[4][4];
        for(int x = 0;x<4;x++){
            for(int y = 0;y<4;y++){
                result[y][x] = (char) (state[y][x] ^ key[y][x]);
            }
        }
        return result;
    }
    public char[][] subBytes(char[][] state){
        char[][] result = new char[4][4];
        String[] temp;
        String row = "";
        char ch;
        for(int x = 0;x<4;x++){
            for(int y = 0;y<4;y++){

            }
        }
        return result;
    }
}

