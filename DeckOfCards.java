import java.util.*; 


class Cards {

    private int rank;
    private int suit; 

    //constructor
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit; 
    }

    public String toString(){
        String[] ranks = {null,"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        String[] suits = {"Clubs","Diamonds","Hearts","Spades"};

        String s = ranks[this.rank] + " of " + suits[this.suit];

        return s;
    }

    public int getRank() {
        return this.rank;

    }

    public int getSuit(){
        return this.suit; 
    }


    
}