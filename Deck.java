import java.util.*; 
import sun.tools.jar.resources.jar_pt_BR; 
import java.lang.*;

class Deck { 
    static Scanner input = new Scanner(System.in);
    public static Card[] deck = new Card[52];
    public static String cardTakenOut; 
    
    public Deck() {

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
        } else {
            dashedLines(); 
            System.out.println("This is your full deck: \n");
        }   
        printingFullDeck(); 
        dashedLines(); 
    }

    
    //Shuffle cards using the Fisher-Yates algorithm 
    public void shuffleCards() {
        Random random = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i); 
 
            Card temp = deck[j];
            deck[j] = deck[i];
            deck[i] = temp; 
        }
        dashedLines(); 

    //Loops through the deck to show the user the shuffled deck 
        System.out.println("This is your shuffled deck: \n");
        for (int i = 0; i < deck.length; i++) {
            if (i != deck.length - 1){ 
                System.out.print(deck[i] + ", ");
            } 
            if (i == deck.length - 1) { 
                System.out.print(deck[i]); 
            }
            if ((i+1) % 4 ==0 && (i + 1) != 0) {
                System.out.println(); 
            }
        }
        dashedLines(); 
    } 

    //Sorting the cards using Bubble Sort
    public void bubbleSort() {

        for (int i = 0; i < deck.length -1; i++) {
            for (int j = 0; j < deck.length -i-1; j++) {
                if (deck[j].rank > deck[j+1].rank) { //sorting by rank 
                    Card tempCard2 = new Card(5, 4);
                    tempCard2 = deck[j];
                    deck[j] = deck[j+1]; 
                    deck[j+1] = tempCard2; 
                } 
            }
        } 

        System.out.println("This is your sorted deck: \n");
        printingFullDeck();
        dashedLines(); 
    }

    public String drawCards(int numberOfCards, String printDeck, String miniGame) {
        dashedLines(); 

        Random rand = new Random(52); 
        Card[] playingCards = new Card[numberOfCards]; 
            
        for (int j = 0; j < numberOfCards; j += 1) {
            int index = rand.nextInt(52-j);
            playingCards[j] = deck[index];
        }

        final ArrayList<Card> cardsTakenOut = new ArrayList<>();
        final ArrayList<Card> finalDeck = new ArrayList<>(); 

        if (miniGame.equals("Y")) {
            System.out.println("This is the card that has been randomly drawn out from the deck: \n");     
        }
        else {
            System.out.println("These are the cards that have been randomly drawn out from the deck: \n");     
        }

        for (int h = 0; h < playingCards.length; h++) {
            String str = String.valueOf(h + 1); 

            //Playing mini game
            if (miniGame.equals("Y")) {
                System.out.println(playingCards[h]);
            }
            else {
                System.out.println(str + ") " + playingCards[h]); 
            }
            cardsTakenOut.add(playingCards[h]);
        } 

        if (miniGame.equals("Y")) {
            
            //Convert from ArrayList to Strings to use .contains() in the Main class
            StringBuffer sb = new StringBuffer(); 
            sb.append(cardsTakenOut);

            cardTakenOut = sb.toString(); //convert StringBuffer to a String 
        }

        if (printDeck.equals("Y")) {
            dashedLines();
            System.out.println("\nHere is your final deck: \n");
            
            for (int i = 0; i < deck.length; i++) { 
                    finalDeck.add(deck[i]);
            }
            for (int i = 0; i < finalDeck.size(); i++) {
                for (int j = 0; j < numberOfCards; j++) {
                    if (finalDeck.get(i) == cardsTakenOut.get(j)) {
                        finalDeck.remove(finalDeck.get(i)); 
                    }
                }
            } 
            for (int i = 0; i < finalDeck.size(); i++) {
                String s = String.valueOf(i + 1); 
                System.out.println(s + ") " + finalDeck.get(i));
            }
        }
        else if (printDeck.equals("N")){
            System.out.println("Thank you for using deck of cards!"); 
        }
        dashedLines(); 
        return cardTakenOut;
    } 


    public static void dashedLines() {

        System.out.println(); 
        for (int i = 1; i <=150; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //Method to print out a full deck
    public static void printingFullDeck() {
        for (int i = 0; i < deck.length; i++) {
            if (i != deck.length - 1){ 
                System.out.print(deck[i] + ", "); //Separates cards by a comma 
            } 
            if (i == deck.length - 1) { 
                System.out.print(deck[i]); //For the last card, it doesn't need a comma after it 
            }
            if ((i+1) % 4 ==0 && (i + 1) != 0) { //Separates the cards into 4 cards per line 
                System.out.println(); 
            }
        }
    }
}
       
