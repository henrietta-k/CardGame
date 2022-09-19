import java.util.*;
import java.lang.*; 


public class Cryptography {
    

    static String text; 
    static int shift; 
    static int lettersPerGroup; 

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
/*
        System.out.println("Welcome to the Cryptography program!");

        System.out.print("Please input the text that you would like to be crypted: ");
        String text = input.nextLine();

        System.out.print("Please choose a random integer. This will be used in the Ceasarify portion of this program. ");
        shift = input.nextInt();

        System.out.print("Please choose another random integer. This will be used for the Groupify portion of this program. ");
        lettersPerGroup = input.nextInt();

        String encryptedString = encryptString(text);  
        System.out.println(encryptedString); 
        String decryptedText = decryptString(encryptedString,shift); 
        System.out.println(decryptedText); 

        */

        String tryout = addingSpaces("HELLOWHOWAREYOU?");
        System.out.println(tryout); 


  
        
    }


    public static String normalizeText(String text) { 


        String noSpace = text.replaceAll(" ","");
        String noPunctuation = noSpace.replaceAll("\\p{Punct}", "");
        String noLowerCase = noPunctuation.toUpperCase();
        String normalizedText = noLowerCase; 

        return normalizedText;

    }

    public static String obify(String text) {

        String obifiedText = text.replaceAll("(A|E|I|O|U|Y)", "OB$1"); //check what this does later 

        return obifiedText; 
    }
 
    
//CHANGE THIS UP LATER 
    public static String caesarify(String encrypt, int shiftValue) {

        System.out.println(encrypt); 

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZX";
        String shiftedLetters = shiftAlphabet(shiftValue);
        String caesarifiedText = "";

        
        for (int i=0; i < encrypt.length();i++) {
            char letter = encrypt.charAt(i);

            String letterValue = String.valueOf(letter);
            int letterIndex = alphabet.indexOf(letterValue); 
            caesarifiedText += shiftedLetters.charAt(letterIndex); 
        }
        
        return caesarifiedText;

    }


// see if the error is occuring here
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



//CHANGE THIS UP LATER AND USE ARRAYLISTS AND STUFF

public static String groupify(String text, int lettersPerGroup) {

    while (text.length() % lettersPerGroup != 0 ) {
        text += "x";
    }

    String groupifiedText = "";

    for (int i=0; i < text.length(); i++) {
        groupifiedText += text.charAt(i);
        if ((groupifiedText.substring(0,i+1)).length() % lettersPerGroup== 0 && i >=i ) {
            groupifiedText += " ";

        }
    }


    return groupifiedText; 
   


}

/*

public static String arrayList(String text, int lettersPerGroup) {
    int lengthOfText = text.length();
    ArrayList[lengthOfText] = new ArrayList[lengthOfText]();
    for (i=0; i<= text.length();i++) {
        char individualLetter = text.charAt(i);
        list.add(individualLetter);
        i++; 

    }

    System.out.println(ArrayList);




}

*/ 


public static String encryptString(String text) {

    String normText = normalizeText(text);
            //System.out.println("Normalized text: "+ normText); 
    String obiText = obify(normText); 
            //System.out.println("Obified text: " + obiText); 
    String caesarifiedText = caesarify(obiText, shift); 
            //System.out.println("Caesarified text: " + caesarifiedText); 
    String groupifiedText = groupify (caesarifiedText,lettersPerGroup); 
            //System.out.println(groupifiedText); 
    String encryptedString = groupifiedText; 

    return encryptedString; 
}          


public static String ungroupify(String text) { //CHECK TO SEE IF THE PARAMETERS ARE OK LATER

    String ungroupifiedText = text.replaceAll(" ","");
    return ungroupifiedText; 

}

// CHECK TO SEE IF THIS METHOD WORKS, NOT SURE IF IT DOES
public static String deobify(String text) {
    String deobifiedText = "";
    for (int i = 0; i < text.length(); i++) {
        if (text.charAt(i) == 'O' && text.charAt(i+1) == 'B') {
            i++; 
        }

        else{
            deobifiedText += text.charAt(i);

        }
    }

    //System.out.println(deobifiedText);
        return deobifiedText; 
}



public static String decryptString(String textToDecrypt, int shift) {

    String ungroupifiedText = ungroupify(textToDecrypt);
        //System.out.println("UngroupifiedText: " + ungroupifiedText); 

    String uncaesarifiedText = caesarify(ungroupifiedText, (-1) * shift); //FIX THIS LATER
        //System.out.println("Uncaesarified Text: " + uncaesarifiedText);

    String deobifiedText = deobify(uncaesarifiedText);

    return deobifiedText; 


}



/*

public static String addingSpaces(String textToAddSpaces) {
    System.out.print("Please list how many spaces after you want the space to be (for example, HELLOHOWAREYOU would be 533): ");
    String characterSpaces = input.nextLine();
    char[] charredString = new char[textToAddSpaces.length()];

    for (int i=0; i < textToAddSpaces.length(); i++) {
        charredString[i] = textToAddSpaces.charAt(i);

    }

    while (int i < characterSpaces.length()) {

        for (int x = 1; x < characterSpaces.length(); x++){

            if (x == )

        }
    }


//Don't need this later
    for (char textToPrint : charredString) {
        System.out.print(textToPrint); 
    }

    String spacedOutText = " "; 



    return spacedOutText; 
}



}




/*  THERE'S SOME STUFF IN HERE THAT CAN BE USDE LATER ON, LIKE THE ELSE STATEMENT

//FIX THIS UP LATER AND CHANGE SOME OF THE STUFF
 Things to fix with this: make sure shift can be a negative number, make sure to check that the string only contains letters
public static String ceasarify(String message, int shift)
{

    char shiftedChar;
    String encryptedMessage = "";

    if(!(shift > 26 && !(shift < 0))){

    for(int i = 0; i < message.length(); i++){

        char nextChar = message.charAt(i);

        if((0 <= (nextChar + shift)) && ('a' + 25 >= (nextChar + shift))){   
            shiftedChar = (char)(nextChar + (shift));
            encryptedMessage += shiftedChar;
        }

        else{
            shiftedChar = (char)((nextChar + shift) - 26);
            encryptedMessage += shiftedChar;
        }
    
    }

    return encryptedMessage;
    
}

    
    else {

        System.out.println("Please try again with a different text");
        return "Unable to Encrypt";
    }
}

*/ 





 