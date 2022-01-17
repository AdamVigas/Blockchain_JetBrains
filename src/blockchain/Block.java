package blockchain;


import java.util.ArrayList;
import java.util.Date;



public class Block {
    private int id;
    private long timeStamp = new Date().getTime();
    String hash;
    String previousHash;
    long magicNumber;


    public Block(ArrayList<Block> blockchain) {
        if(blockchain.isEmpty()) {
            setId(1);
            setPreviousHash("0");
        }else {
            this.setId(blockchain.get(blockchain.size() - 1).getId() + 1);
            this.setPreviousHash(blockchain.get(blockchain.size() -1 ).getHash());
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }



}
