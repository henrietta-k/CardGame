import java.util.*; 

class Deck { 

    public static void main(String[] args) {
        Card[] deck = new Card[52];
        deck.shuffleCards();
        deck.sort();
        deck.deal();

        //Put cards in deck
        int index = 0;
        for (int suit = 0; suit <= 3; suit++){
            for (int rank = 1; rank <= 13; rank++) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }

        if (deck[0] == null){
            System.out.println("No cards");
        }
        else {
            System.out.println("Full deck");
        }
        System.out.println(deck[51]);
    }

    public static int cardsLeftOver(Card[] deck) {
        int counter = 0;
        for (int i =0; i < deck.length; i++) {
            if (deck[i] != null) {
                counter++;
            }
        }
        return count; 
    }

    public static Card shuffleCards(Card[] deck) {
        Random randomizer = new Random();
        Card temporary; 
        for (int i = deck.length - 1; i > 1; i--) {
            int j = randomizer.nextInt(i);
            temporary = deck[j];
            deck[j] = deck[i];
            deck[i] = temporary;
        }
        return deck[1]; //this is only to test it out 
    }
}
