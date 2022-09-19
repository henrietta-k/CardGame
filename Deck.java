import java.util.*; 
import sun.tools.jar.resources.jar_pt_BR; 
import java.lang.*;


class Deck { 

//The global variables 
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

        printingFullDeck(); //Use this method throughout this code to make it neater 
        dashedLines(); //Makes it easier for the user to read 
    }

    
    //Shuffle cards using the Fisher-Yates algorithm 
    public void shuffleCards() {

        Random random = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i); 
 
            Card temp = deck[j]; //Mix up the cards here 
            deck[j] = deck[i];
            deck[i] = temp; 
        }

        dashedLines(); 


    //Loops through the deck to show the user the shuffled deck 
        System.out.println("This is your shuffled deck: \n");

        for (int i = 0; i < deck.length; i++) {

    //The commas make it easier for user to read and they don't have to scroll as much
            if (i != deck.length - 1){ 
                System.out.print(deck[i] + ", ");
            } 

    //The last card doesn't need a comma after it
            if (i == deck.length - 1) { 
                System.out.print(deck[i]); 
            }

    //Prints the cards onto different lines with 4 cards on each line
            if ((i+1) % 4 ==0 && (i + 1) != 0) {
                System.out.println(); 
            }
        }

        dashedLines(); 

    } 

//Sorting the cards using bubbleSort 
    public void bubbleSort() {

        for (int i = 0; i < deck.length -1; i++) {
            for (int j = 0; j < deck.length -i-1; j++) {
                if (deck[j].rank > deck[j+1].rank) { //sorting by rank 

                    // Swaps deck[j+1] and deck[j] 
                    Card tempCard2 = new Card(5, 4);
                    tempCard2 = deck[j];
                    deck[j] = deck[j+1]; 
                    deck[j+1] = tempCard2; 
                } 
            }
        } 

//Print things out nicely by doing this 
        System.out.println("This is your sorted deck: \n");

        printingFullDeck();
        dashedLines(); 

    }

//Takes in three parameters 
//First is number of cards to take out, second is whether to print the final deck or not, 3rd is whether it's the mini game version or not 
    public String drawCards(int numberOfCards, String printDeck, String miniGame) {

        dashedLines(); 

        Random rand = new Random(52); //randomly generate numbers 
        Card[] playingCards = new Card[numberOfCards]; //create new Card 
            
        for (int j = 0; j < numberOfCards; j += 1) {
            int index = rand.nextInt(52-j);
            playingCards[j] = deck[index];
        }

        //Creating ArrayLists to add elements to it while using a for loop 
        final ArrayList<Card> cardsTakenOut = new ArrayList<>(); //This list will contain the cards that have been drawn out of the deck
        final ArrayList<Card> finalDeck = new ArrayList<>(); //This list will contain the final deck 


        if (miniGame.equals("Y")) {
            System.out.println("This is the card that has been randomly drawn out from the deck: \n");     

        }

        else {
            System.out.println("These are the cards that have been randomly drawn out from the deck: \n");     
        }

        for (int h = 0; h < playingCards.length; h++) {
            String str = String.valueOf(h + 1); //To use for making a numbered list so you don't have to count the number of cards

            if (miniGame.equals("Y")) {
                System.out.println(playingCards[h]); //for the mini game, it only prints out 1 card that has been drawn out 
            }

            else {
                System.out.println(str + ") " + playingCards[h]); //This will print out the cards that have been drawn out 
            }

            cardsTakenOut.add(playingCards[h]); //Add the cards that have been taken out to the ArrayList 
        } 

        if (miniGame.equals("Y")) {

//For mini game, need a StringBuffer to convert from ArrayList to Strings to use .contains() in the Main class
            StringBuffer sb = new StringBuffer(); 
            sb.append(cardsTakenOut); //Add the card taken out to the StringBuffer

            cardTakenOut = sb.toString(); //convert StringBuffer to a String 
        }

//This is if the user wants to print out what their final deck looks like 
        if (printDeck.equals("Y")) {

            dashedLines();

            System.out.println("\nHere is your final deck: \n");
        
        for (int i = 0; i < deck.length; i++) { 
                    finalDeck.add(deck[i]); //Adding all 52 cards to an ArrayList
        }

        for (int i = 0; i < finalDeck.size(); i++) {
            for (int j = 0; j < numberOfCards; j++) {
                if (finalDeck.get(i) == cardsTakenOut.get(j)) {
                    finalDeck.remove(finalDeck.get(i)); 
                    //Removing a card from the ArrayList if it is one of the cards that have been randomly drawn out 
                }
                }
            } 

        for (int i = 0; i < finalDeck.size(); i++) {
            String s = String.valueOf(i + 1); 
            System.out.println(s + ") " + finalDeck.get(i)); //Prints out a numbered list of the final deck of cards
        }
        }

        else if (printDeck.equals("N")){
            System.out.println("Thank you for using deck of cards!"); 
        }


        dashedLines(); 

        return cardTakenOut; //Returns a String which will be needed for the mini game 

    } 


//Method to print out dashed lines to separate printed deck 
    public static void dashedLines() {

        System.out.println(); 
        for (int i = 1; i <=150; i++) {
            System.out.print("-");
        }
        System.out.println();

    }

    //Method to print out a full deck since it's done multiple times in this code 
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
       

