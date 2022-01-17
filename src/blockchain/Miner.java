package blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;



import static java.lang.String.valueOf;


public class Miner {
    long magicNumber;
    Block block;
    long result;
    int currNumber;


    public Miner(ArrayList<Block> blockchain, int number) {
        this.currNumber = number;
        if(blockchain.isEmpty()) {
            block = new Block(blockchain);
            calculateMagic(calcZeros(number),block);
            result = (new Date().getTime() - block.getTimeStamp()) / 1000;
            blockchain.add(block);
            printInfo();
        }else{
            block = new Block(blockchain);
            calculateMagic(calcZeros(number),block);
            result = (new Date().getTime() - block.getTimeStamp()) / 1000;
            blockchain.add(block);
            printInfo();
        }
    }

    public String calcZeros(int n) {
        String res = "";
        for(int i = 0; i < n;i++) {
            res += "0";
        }
        return res;
    }


    public void calculateMagic(String zeros,Block block) {
        while(true) {
            magicNumber = (int)((Math.random() * 90000000)+10000000);
            block.setHash(applySha256(valueOf(block.getId()) + valueOf(block.getTimeStamp()) + block.getPreviousHash() + magicNumber));
            if(block.getHash().startsWith(zeros)){
                this.magicNumber = block.magicNumber;
                break;
            }
        }
    }

    public void printInfo() {
        System.out.println("Block:");
        System.out.println("Created by miner # " + Thread.currentThread().getId());
        System.out.println("Id: " + block.getId());
        System.out.println("Timestamp: " + block.getTimeStamp());
        System.out.println("Magic number: " + this.magicNumber);
        System.out.println("Hash of the previous block: \n" + block.getPreviousHash());
        System.out.println("Hash of the block: \n" + block.getHash());
        System.out.println("Block was generating for " + result + " seconds");

    }

    public int calcNumber(long res) {
        if(res < 10) {
            currNumber++;
            System.out.println("N was increased to " + currNumber  + "\n");
        }else if (result > 60) {
            currNumber--;
            System.out.println("N was decreased by " + currNumber  + "\n");
        }else {
            System.out.println("N stays the same"  + "\n");
        }
        return currNumber;
    }



    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


}
