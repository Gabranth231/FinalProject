
import java.util.Random;
import java.util.Scanner;

public class Encryption128 implements SharedAES{
    private char state[];
    char[] ExpandedKey;


    @Override
    public char[] KeyExpansionCore(char[] in, int num){
        //rotate left
        char temp = in[0];
        in[0] = in[1];
        in[1] = in[2];
        in[2] = in[3];
        in[3] = temp;
        //sBox lookup
        for(int i = 0;i<4;i++){
            in[i] = sBox[in[i]];
        }
        //RCon look up table
        in[0] = (char) (in[0] ^ rCon[num]);

        return in;
    }
    @Override
    public void KeyExpansion(char[] key){
        ExpandedKey = new char[176];
        //First 16 bytes are the original key in the first add round key
        for (int i = 0;i<16;i++){
            ExpandedKey[i] = key[i];    //copy first 16 bytes of random key for Expansion
        }
        int bytesDone = 16;
        int rConNum = 1;
        char[] temp = new char[4];  //storing for the core
        while(bytesDone<176){
            for(int i = 0;i<4;i++){
                temp[i] = ExpandedKey[i + bytesDone-4];
            }
            //once every key do core.
            if(bytesDone % 16 == 0){
                temp = KeyExpansionCore(temp,rConNum++);
            }
            for(int a = 0;a<4;a++){
                ExpandedKey[bytesDone] = (char) (ExpandedKey[bytesDone - 16] ^ temp[a]);
                bytesDone++;
            }
        }
        for(int i = 0;i<176;i++){
            if(i%16==0&&i!=0){
                System.out.printf(" Key %d\n\r",(i/16)-1);
            }
            System.out.printf("0x%02x ",(int)ExpandedKey[i]);
        }
        System.out.printf(" Key 10\n\r");
    }
    Random rand = new Random();

    public void setKey() {
        for(int x = 0;x<16;x++){
            key[x] = (char) ('a' + rand.nextInt(26));
        }

    }

    Encryption128(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for generated key or 2 for random key: ");
        int mynum = sc.nextInt();
        if(mynum == 1){
            for(int i = 1;i<=16;i++){
                key[i-1] = (char)i; // set key
            }
        }
        else if(mynum == 2){
            setKey();       //random key of characters
        }
        KeyExpansion(key);
    }
    public char[] encrypt(char[] text){
        state = new char[16];
        char[] roundKey = new char[16];

        for(int i = 0;i<16;i++){
             state[i] = text[i];
        }
        printStatePerRound(state,-1);   //round 0, preRound
        AddRoundKey(state,key);
        printStatePerRound(state,0);   //round 0, preRound
        for(int i = 0;i<rounds;i++){        //rounds 1-9
            SubBytes(state);
            printStatePerRound(state,-1);
            ShiftRows(state);
            printStatePerRound(state,-1);
            MixColunms(state);
            printStatePerRound(state,-1);
            for(int a = 0;a<16;a++){
                roundKey[a] = ExpandedKey[a+16*(i+1)];
            }
            AddRoundKey(state,roundKey);
            printStatePerRound(state,i+1);
        }
        SubBytes(state);
        printStatePerRound(state,-1);
        ShiftRows(state);
        printStatePerRound(state,-1);
        for(int a = 0;a<16;a++){
            roundKey[a] = ExpandedKey[a+160];
        }
        AddRoundKey(state,roundKey);
        printStatePerRound(state,10);       //final round
        return state;
    }

    public void SubBytes(char[] state){
        for(int i = 0;i<16;i++){
            state[i] = sBox[state[i]];
        }
    }
    public void ShiftRows(char[] state){
        char[] temp = new char[16];

        temp[0] = state[0];
        temp[1] = state[5];
        temp[2] = state[10];
        temp[3] = state[15];

        temp[4] = state[4];
        temp[5] = state[9];
        temp[6] = state[14];
        temp[7] = state[3];

        temp[8] = state[8];
        temp[9] = state[13];
        temp[10] = state[2];
        temp[11] = state[7];

        temp[12] = state[12];
        temp[13] = state[1];
        temp[14] = state[6];
        temp[15] = state[11];

        for (int i = 0;i<16;i++){
            state[i] = temp[i];

        }

    }
    public void MixColunms(char[] state){
       char[] temp = new char[16];

        temp[0] = (char)(mul2[state[0]] ^ mul3[state[1]] ^ state[2] ^ state[3]);    //Dot products and matrix multiplication
        temp[1] = (char)(state[0] ^ mul2[state[1]] ^ mul3[state[2]] ^ state[3]);    //Got stuck on this for quite some time
        temp[2] = (char)(state[0] ^ state[1] ^ mul2[state[2]] ^ mul3[state[3]]);    //Galois mul tables
        temp[3] = (char)(mul3[state[0]] ^ state[1] ^ state[2] ^ mul2[state[3]]);

        temp[4] = (char)(mul2[state[4]] ^ mul3[state[5]] ^ state[6] ^ state[7]);
        temp[5] = (char)(state[4] ^ mul2[state[5]] ^ mul3[state[6]] ^ state[7]);
        temp[6] = (char)(state[4] ^ state[5] ^ mul2[state[6]] ^ mul3[state[7]]);
        temp[7] = (char)(mul3[state[4]] ^ state[5] ^ state[6] ^ mul2[state[7]]);

        temp[8] = (char)(mul2[state[8]] ^ mul3[state[9]] ^ state[10] ^ state[11]);
        temp[9] = (char)(state[8] ^ mul2[state[9]] ^ mul3[state[10]] ^ state[11]);
        temp[10] = (char)(state[8] ^ state[9] ^ mul2[state[10]] ^ mul3[state[11]]);
        temp[11] = (char)(mul3[state[8]] ^ state[9] ^ state[10] ^ mul2[state[11]]);

        temp[12] = (char)(mul2[state[12]] ^ mul3[state[13]] ^ state[14] ^ state[15]);
        temp[13] = (char)(state[12] ^ mul2[state[13]] ^ mul3[state[14]] ^ state[15]);
        temp[14] = (char)(state[12] ^ state[13] ^ mul2[state[14]] ^ mul3[state[15]]);
        temp[15] = (char)(mul3[state[12]] ^ state[13] ^ state[14] ^ mul2[state[15]]);

        for(int i = 0;i<16;i++){
            state[i] = temp[i];
        }

    }
    public void AddRoundKey(char[] state, char[] key){
        for (int i = 0;i<16;i++){
            state[i] = (char) (state[i] ^ key[i]);
        }
    }

    public void printStatePerRound(char[] state,int round){
        switch(round){
            case -1:
                System.out.println("State bytes");
                break;
            case 0:
                System.out.println("PreRound");
                break;
            default:
                System.out.printf("Round Number %d\n\r",round);
        }
        for(char a: state){
            System.out.printf(String.format("0x%02x ",(int) a));
        }
        System.out.println("\n\r");
    }

}

