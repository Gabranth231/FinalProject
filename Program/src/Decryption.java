public class Decryption implements SharedAES{
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
    Decryption(char[] myKey){
        KeyExpansion(myKey);
    }

    public char[] decrypt(char[] cipherText){
        state = new char[16];
        char[] roundKey =new char[16];

        for(int i = 0;i<16;i++){
            state[i] = cipherText[i];
        }
        printStatePerRound(state,-1);
        for(int a = 0;a<16;a++){
            roundKey[a] = ExpandedKey[(ExpandedKey.length-16) + a];//key 10
        }
        ShiftRowsInverse(state);
        SubBytes(state);
        for(int round=0;round < rounds;round++){
            for(int a = 0;a<16;a++){
                roundKey[a] = ExpandedKey[(ExpandedKey.length-16*(round+1)) + a];//key 9-1
            }
            /*
            https://crypto.stackexchange.com/questions/2569/how-does-one-implement-the-inverse-of-aes-mixcolumns
            MixColumns inverse function math
             */
            ShiftRowsInverse(state);
            SubBytes(state);
        }

        return state;
    }
    public void SubBytes(char[] state){
        for(int i = 0;i<16;i++){
            state[i] = sBox[state[i]];
        }
    }
    public void ShiftRowsInverse(char[] state){    //inverse
        char[] temp = new char[16];

        temp[0] = state[0];
        temp[1] = state[13];
        temp[2] = state[10];
        temp[3] = state[7];

        temp[4] = state[4];
        temp[5] = state[1];
        temp[6] = state[14];
        temp[7] = state[11];

        temp[8] = state[8];
        temp[9] = state[5];
        temp[10] = state[2];
        temp[11] = state[15];

        temp[12] = state[12];
        temp[13] = state[9];
        temp[14] = state[6];
        temp[15] = state[3];

        for (int i = 0;i<16;i++){
            state[i] = temp[i];
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
