import java.util.*; 


class Card {

//creating variables 
    public int rank;
    public int suit; 

    //constructor
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit; 
    }

//Creates Strings using the ranks and suits 
public String toString(){

        String[] ranks = {null,"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
        String[] suits = {"Clubs","Diamonds","Hearts","Spades"};    

        String s = ranks[this.rank] + " of " + suits[this.suit];
        return s;
    }
    
    public int getRank() { //getter method
        return this.rank;
    }

    public int getSuit(){ //getter method 
        return this.suit; 
    }


    
}