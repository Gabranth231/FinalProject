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
}
