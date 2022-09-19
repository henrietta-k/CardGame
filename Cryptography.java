import java.util.*;
import sun.tools.jar.resources.jar_pt_BR;
import java.lang.*; 

public class Cryptography {
    
    static int lettersPerGroup; 
    static String groupifiedText;
    static int lengthOfText; 
    static ArrayList<Integer> spaceIndices = new ArrayList<>();  
    static ArrayList<Integer> obIndices = new ArrayList<>();


    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        //The main method asks user for their inputs then gives the encrypted and decrypted string

       System.out.println("\nWelcome to the Cryptography program!");
       System.out.print("\nPlease input the text that you would like to be crypted: ");
        String text = input.nextLine();

        System.out.print("Please choose a random integer. This will be used in the Ceasarify portion of this program. ");
        int shift = input.nextInt();

        System.out.print("Please choose another random integer. This will be used for the Groupify portion of this program. ");
        lettersPerGroup = input.nextInt();

        String encryptedString = encryptString(text, shift, lettersPerGroup);
        System.out.println("\nHere is the encrypted text: " + encryptedString); 

        String decryptedText = decryptString(encryptedString,shift); 
        System.out.println("\nHere is the decrypted text: " + decryptedText);

        System.out.println("\nThank you for using the Cryptography Program!");
       
    }


    public static String normalizeText(String text) { 
//These two lines get rid of any punctuation and turns the code into all uppercase 
        String noPunctuation = text.replaceAll("\\p{Punct}", "");
        String noLowerCase = noPunctuation.toUpperCase();

 /* Loop through the noLowercase String and adds the indices of the spaces to the global ArrayList spaceIndices 
 to be used in the addingSpaces method later */      
        for (int i=0; i < noLowerCase.length(); i++) {
            if (noLowerCase.charAt(i) == ' ') {
                spaceIndices.add(i);
            }
        }

//Gets rid of spaces 
        String normalizedText = noLowerCase.replaceAll(" ","");

 //Loops through the String to add indices to the global ArrayList obIndices to be used in addingOB method later 
        for (int i=0; i< normalizedText.length() - 1; i++) {
            if (normalizedText.charAt(i) == 'O' && normalizedText.charAt(i+1) == 'B') {
                obIndices.add(i); 
            }
        }
        return normalizedText;
    }


    public static String obify(String text) {

        String obifiedText = "";
        
//Loops through the input string and adds chars to the obifiedText String 
//Adds "OB" before any vowel 
        for (int i=0; i < text.length(); i++) {
            if (text.charAt(i) == 'A' || text.charAt(i) == 'E' || text.charAt(i) == 'I' || text.charAt(i) == 'O' || text.charAt(i) == 'U' || text.charAt(i) == 'Y') {
                obifiedText += "OB"; 
                obifiedText += text.charAt(i); 
            }
            else {
                obifiedText += text.charAt(i); 
            }
        }
        return obifiedText; 
    }

    

    
    public static String caesarify(String text, int shiftValue) {

        //Loops through input text and adds chars to the caesarifiedText string depending on the shiftValue 
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZX"; 
        String shiftedLetters = shiftAlphabet(shiftValue);
        String caesarifiedText = "";

        for (int i=0; i < text.length();i++) {
            char letter = text.charAt(i);
            int letterIndex = alphabet.indexOf(String.valueOf(letter)); 
            
            caesarifiedText += shiftedLetters.charAt(letterIndex); 
        }
        
        return caesarifiedText;

    }


//This was the shiftAlphabet method provided by ClanEd and used in the caesarify method 
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift; 
        }

        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }

        return result; 
    }


//This method groups the letters in the string by adding spaces between them 
public static String groupify(String text, int numbersPerGroup) {

//Add x until text.length() is divisible by numbersPerGroup 
    while (text.length() % numbersPerGroup != 0 ) {
        text += "x";
    }

    groupifiedText = "";

//Adding letters from the input string into groupifiedText one by one and adding spaces in between
    for (int i=0; i < text.length(); i++) {
        groupifiedText += text.charAt(i);

//Creates substrings, get its length and checks if it's divisible by numbersPerGroup, if it is, a space is added 
        if ((groupifiedText.substring(0,i+1)).length() % numbersPerGroup== 0 && i >=1) {
            groupifiedText += " ";
        }
    }

    return groupifiedText; 
   
}

//I made another groupify method that regroups the first groupified method using 2D arrays
public static String groupifyPartTwo(String text, int lettersPerColumn) {
    
    //To get rid of leading and trailing white spaces 
    String newText= text.trim();

    //Splitting the string up into an array according to whitespace (so the spaces from the previous groupify method) 
    String [] splitString = newText.trim().split("\\s+"); 

    int lettersPerRow = lengthOfText / lettersPerColumn; 

    //Creating a 2D array using the previous splitString array
    char [][] array = new char[splitString.length][];
    for (int i = 0; i < splitString.length; i++) {
        array[i] = splitString[i].toCharArray(); 
    }
    
    //I used this to print out and test if my array was working 
    /*for (int i = 0; i< lettersPerRow; i++) {
        for (int j=0; j< lettersPerColumn;j++) {
           System.out.print(array[i][j]);
        }
        System.out.println();
    } */ 

    String regroupifiedText = ""; 

    for (int i = 0; i < lettersPerColumn; i++) {
        for (int j = 0; j<lettersPerRow; j++) {
            regroupifiedText += array[j][i]; //This line addes the rearranged chars to the regroupifiedText String
        }
    }

    return regroupifiedText; 
}

//This method converts each letter of the input string into two numbers and adds it to a new string
public static String polybiusSquareCipher(String text) {

//Creating an empty map 
    Map< String, String> squareMap = new HashMap < String,String> ();
//Adding keys and values to squareMap 
    squareMap.put("D","11"); squareMap.put("C","21"); squareMap.put("O","31"); squareMap.put("E","41");
    squareMap.put("A","51"); squareMap.put("B","12"); squareMap.put("F","22"); squareMap.put("G","32");
    squareMap.put("H","42"); squareMap.put("I","52"); squareMap.put("K","13"); squareMap.put("L","23");
    squareMap.put("M","33"); squareMap.put("N","43"); squareMap.put("P","53"); squareMap.put("Q","14");
    squareMap.put("R","24"); squareMap.put("S","34"); squareMap.put("T","44"); squareMap.put("U","54");
    squareMap.put("V","15"); squareMap.put("W","25"); squareMap.put("X","35"); squareMap.put("Y","45");
    squareMap.put("Z","55"); squareMap.put("J","00"); //Put this because the alphabet has 25 letters so J is usually the odd one out 

    String polybiusSquareText = ""; 

//Looping through every letter in the input String and adding its equivalent numbers to the polybiusSquareText String 
    for (int i=0; i < text.length(); i++) {
        char charLetter = text.charAt(i);
        String stringLetter = String.valueOf(charLetter); //convert char to String so it can be used with the map 

        if (squareMap.containsKey(stringLetter)) {
            polybiusSquareText += squareMap.get(stringLetter); 
        }
    }

    //This will be needed for the groupifyPartTwo method, it sets a value to the global variable lengthOfText 
    String trimmedText = polybiusSquareText.trim(); 
    lengthOfText = trimmedText.length(); 
    return polybiusSquareText; 
}

//This method calls the other methods to put together the encrypted String 
public static String encryptString(String text, int shiftValue, int codeGroupSize) {

    String normText = normalizeText(text);
        //System.out.println("This is the normalized text: " + normText);
    String obiText = obify(normText); 
        //System.out.println("This is the obified text: " + obiText); 
    String caesarifiedText = caesarify(obiText, shiftValue); 
        //System.out.println("This is the caesarified text: " + caesarifiedText); 
    String polybiusText = polybiusSquareCipher(caesarifiedText); 
        //System.out.println("This is the polybiusSquareCiphered text: " + polybiusText); 
    String groupifiedPartOne = groupify (polybiusText,codeGroupSize); 
        //System.out.println("This is the groupified text: " + groupifiedPartOne); 
    String groupifiedPartTwo = groupifyPartTwo(groupifiedPartOne, codeGroupSize);
        //System.out.println("This is the groupified part two text: " + groupifiedPartTwo); 
    String encryptedString = groupify(groupifiedPartTwo, codeGroupSize);

    return encryptedString; 
}          

//Gets rid of spaces in between the string to ungroupify it
public static String ungroupify(String text) { 

    String ungroupifiedText = text.replaceAll(" ","");
    return ungroupifiedText; 
}

//Gets rid of all OB in the string to deobify it
public static String deobify(String text) {

    String deobifiedText = text.replaceAll("OB",""); 
    return deobifiedText; 
    
}

//This method undoes the groupify part two method 
public static String ungroupifyPartTwo(String text, int lettersPerGroup) {

// Getting rid of white space in the String         
        String newText = text.replaceAll(" ","");
        String trimmedText = newText.trim();
//Using the groupify method to group the String up to make substrings
        String groupifiedText = groupify(trimmedText, trimmedText.length()/lettersPerGroup);
        
//Splitting the string up according to whitespace, the spaces from the previous grouping 
    String [] splitString = groupifiedText.trim().split("\\s+"); 

    int lettersPerColumn = lengthOfText / lettersPerGroup; 

//This is pretty much the same as the groupifyPartTwo method from before
    char [][] array = new char[splitString.length][];
    for (int i = 0; i < splitString.length; i++) {
        array[i] = splitString[i].toCharArray(); 
    }
    
    String ungroupifiedText = ""; 

    for (int i = 0; i < lettersPerColumn; i++) {
        for (int j = 0; j<lettersPerGroup; j++) {
            ungroupifiedText += array[j][i]; 

        }
    }
    
    return ungroupifiedText; 
}

//This method takes in a String of numbers this time and returns a string of letters
public static String unpolybiusCipher(String text) {

//Keys and values of this map have been switched around 
    Map< String, String> squareMap = new HashMap < String,String> ();
    squareMap.put("11","D"); squareMap.put("21","C"); squareMap.put("31","O"); squareMap.put("41","E");
    squareMap.put("51","A"); squareMap.put("12","B"); squareMap.put("22","F"); squareMap.put("32","G");
    squareMap.put("42","H"); squareMap.put("52","I"); squareMap.put("13","K"); squareMap.put("23","L");
    squareMap.put("33","M"); squareMap.put("43","N"); squareMap.put("53","P"); squareMap.put("14","Q");
    squareMap.put("24","R"); squareMap.put("34","S"); squareMap.put("44","T"); squareMap.put("54","U");
    squareMap.put("15","V"); squareMap.put("25","W"); squareMap.put("35","X"); squareMap.put("45","Y");
    squareMap.put("55","Z"); squareMap.put("00","J"); 


    //Getting rid of leading and trailing white spaces then splitting the String up into an array according to whitespace 
    String newText = text.trim();
    String [] splitString = newText.split("\\s+"); 

    String unpolybiusSquareText = ""; 

//Loops through every element in the splitString array and converts it to letters, then adds to the unpolybiusSquareText String
    for (int i=0; i < splitString.length; i++) {
        String letters = splitString[i]; 
    
        if (squareMap.containsKey(letters)) {
            unpolybiusSquareText += squareMap.get(letters); 
        }
    }
    return unpolybiusSquareText; 
}
       

//This method adds back the original spaces to the decrypted text 
public static String addingSpaces(String text) {

    int spaceIndex = 0;
    int i=0;

//Using a StringBuffer in order to use .insert()
    StringBuffer strBuf = new StringBuffer(text);

//Inserts spaces according to the indices saved in the global spaceIndices ArrayList
    while (spaceIndex < spaceIndices.size()) {
        if (i == spaceIndices.get(spaceIndex)) {
            strBuf.insert(i, " ");
            spaceIndex++; 
        }
        i++; 
    }

    return strBuf.toString(); //Returning a String  
  
}

//Basically the same as the addingSpaces method above
//This method is needed because there will sometimes be "ob" in the original input text
public static String addingOB(String text) {
    int obIndex = 0;
    int i=0;

    StringBuffer strBuf = new StringBuffer(text);

    while (obIndex < obIndices.size()) {
        if (i == obIndices.get(obIndex)) {
            strBuf.insert(i,"OB");
            obIndex++;
        }
        i++; 
    }
    return strBuf.toString();
}

//This calls the different decrypting methods together and decryptes the string 
public static String decryptString(String text, int shift) {

    String ungroupifiedStringPart2 = ungroupifyPartTwo(text, lettersPerGroup);
        //System.out.println("This is ungroupifying String Part 2: " + ungroupifiedStringPart2); 
    String groupifyForPolybius = groupify(ungroupifiedStringPart2, 2); 
        //System.out.println("This is groupify for Polybius: " + groupifyForPolybius);
    String unpolybiusCiphered = unpolybiusCipher(groupifyForPolybius);
        //System.out.println("This is unpolybius ciphered: " + unpolybiusCiphered);
    String uncaesarifiedString = caesarify(unpolybiusCiphered, -(shift));
        //System.out.println("This is uncaesarified String: " + uncaesarifiedString);
    String deobifiedString = deobify(uncaesarifiedString); 
        //System.out.println("This is deobified String: " + deobifiedString); 
    String addedOB = addingOB(deobifiedString);
        //System.out.println("This is adding OB: " + addedOB); 
    String decryptedString = addingSpaces(addedOB);


    return decryptedString; 


}

}





