import java.util.*; 

class Card {
    
    public int rank;
    public int suit; 

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit; 
    }

    //Creates Strings using the ranks and suits 
    public String toString() {
        String[] ranks = {null,"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        String[] suits = {"Clubs","Diamonds","Hearts","Spades"};    
        
        String s = ranks[this.rank] + " of " + suits[this.suit];
        return s;
    }

    //Rank getter method
    public int getRank() { 
        return this.rank;
    }

    //Suit getter method
    public int getSuit(){ 
        return this.suit; 
    }   
}
