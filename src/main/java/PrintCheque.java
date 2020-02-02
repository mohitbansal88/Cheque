
public class PrintCheque {

  public static void main(String[] args) {
    // 2.00, 999999999.01, 1234567890.98, 0.81, 1.01, 1001000111.09
    PrintCheque pC = new PrintCheque();
    pC.printCheque(args[0]);
  }
  
  private void printCheque(String number) {
    String[] dollarCents = number.split("\\.");
    String dollars = dollarCents[0].trim();
    
    if(dollarCents[1].length() != 2) {
      System.out.println("Invalid input, decimal point are not 2");
      return;
    }
    
    boolean one_dollar = dollars.length() == 1 ? true : false; 
    String display = "AND " + printCent(dollarCents[1]) + "CENTS";
    
    if(dollars.equals("0")) {
      System.out.println("ZERO Dollar " + display);
      return;
    }
      
    
    int counter = 0;
    while(!dollars.equals("")) {
      String threeDigits = "";
      String hundreds = "";
      String tens = "";
      String ones = "";
      
      if(dollars.length() < 3) {
        if(dollars.length() == 2) {
          tens = getTens(Character.getNumericValue(dollars.charAt(0)));
          ones = getOnes(Character.getNumericValue(dollars.charAt(1)));
        } else {
          ones = getOnes(Character.getNumericValue(dollars.charAt(0)));
        }
        dollars = "";
      } else {
        threeDigits = dollars.substring(dollars.length() -3 , dollars.length());
        dollars = dollars.substring(0, dollars.length() -3);
          hundreds = getHundreds(Character.getNumericValue(threeDigits.charAt(0)));
          tens = getTens(Character.getNumericValue(threeDigits.charAt(1)));
          ones = getOnes(Character.getNumericValue(threeDigits.charAt(2)));
      }
      
      if(counter == 0) {
        String DOLLAR = one_dollar ? "DOLLAR " : "DOLLARS ";
        String space = one_dollar ? "" : " ";
        display = space + generateWords(hundreds, tens, ones) + DOLLAR + display;
      } else if(counter == 1 && !threeDigits.equals("000")) {
          String comma = dollars.length() >  1 ? ", " : "";
          display = comma + generateWords(hundreds, tens, ones) + "thousand," + display;
      } else if(counter == 2 && !threeDigits.equals("000")) {
        String comma = dollars.length() ==  1 ? ", " : "";
        display = comma + generateWords(hundreds, tens, ones) + "million" + display;
      } else if(counter == 3) {
        display = ones + " " + "billion" + display;
      }
      counter++;
    }
    System.out.println(display);
  }
  
  private String generateWords(String hundreds, String tens, String ones) {
    String tempHundreds= hundreds.equals("") ? "" : hundreds + " ";
    String tempTens= tens.equals("") ? "" : "and " + tens + " ";
    String tempOnes= ones.equals("") ? "" : ones + " ";
    return tempHundreds + tempTens + tempOnes ;
  }
  
  private String printCent(String cents) {
    if(Character.getNumericValue(cents.charAt(0)) == 1) {
      return getTenSeries(cents);
    } else {
      String space = Character.getNumericValue(cents.charAt(0)) == 0? "" : " ";
      String tens = getTens(Character.getNumericValue(cents.charAt(0))) + space;
      String ones = getOnes(Character.getNumericValue(cents.charAt(1)));
      String tempOnes= ones.equals("") ? "Zero " : ones + " ";
      return tens + tempOnes;
      
    }
  }
  
  private String getTenSeries(String cents) {
    switch(Integer.valueOf(cents)) {
    case 10 : return "ten";
    case 11 : return "eleven";
    case 12 : return "twelve";
    case 13 : return "thirteen";
    case 14 : return "fourteen";
    case 15 : return "fifteen";
    case 16 : return "sixteen";
    case 17 : return "seventeen";
    case 18 : return "eighteen";
    case 19 : return "nineteen";
    }
    return "";
  }
  
  
  private String getTens(int number) {
    switch(number) {
    case 2 : return "twenty";
    case 3 : return "thirty";
    case 4 : return "forty";
    case 5 : return "fifty";
    case 6 : return "sixty";
    case 7 : return "seventy";
    case 8 : return "eighty";
    case 9 : return "ninty";
    
    }
    return "";
  }
  
  private String getHundreds(int number) {
    return number == 0 ? "" : getOnes(number) + " hundred";
  }
  
  private String getOnes(int number) {
    switch(number) {
    case 1 : return "one";
    case 2 : return "two";
    case 3 : return "three";
    case 4 : return "four";
    case 5 : return "five";
    case 6 : return "six";
    case 7 : return "seven";
    case 8 : return "eight";
    case 9 : return "nine";
    }
    return "";
  }
  
}
