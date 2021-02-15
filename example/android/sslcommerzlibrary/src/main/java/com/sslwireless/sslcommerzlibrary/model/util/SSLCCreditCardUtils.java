package com.sslwireless.sslcommerzlibrary.model.util;

import java.util.ArrayList;

public class SSLCCreditCardUtils {
    private static final SSLCCreditCardUtils ourInstance = new SSLCCreditCardUtils();
    public static SSLCCreditCardUtils getInstance() {
        return ourInstance;
    }

    public String setCardMasking(String ccNumber){
        String number = " **** **** **** ";
        if (ccNumber.length() > 4)
        {
            String lastFourDigits = ccNumber.substring(ccNumber.length() - 4);
            number+=lastFourDigits;

            return number;
        }else {
            return ccNumber;
        }
        //return ccNumber.replaceAll("\\w(?=\\w{4})", "*");
    }
    public ArrayList<String> cardTypeValidate() {
        ArrayList<String> listOfPattern = new ArrayList<String>();

        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        //String ptMasterCard = "^5[1-5][0-9]{5,}$";
        String ptMasterCard = "(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        //String ptMasterCard2 = "(?:222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}";
        //String ptMasterCard2 = "222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}";
        //listOfPattern.add(ptMasterCard2);
        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);

        return listOfPattern;
    }

    public String cardType(String ccNum) {
        ArrayList<String> patterns = cardTypeValidate();
        for (int i = 0; i < patterns.size(); i++) {
            String pattern = patterns.get(i);
            if (ccNum.matches(pattern)) {
                if (i == 0) {
                    return SSLCEnums.CardType.Visa.name();
                } else if (i == 0) {
                    return SSLCEnums.CardType.Master.name();
                } else if (i == 0) {
                    return SSLCEnums.CardType.Amex.name();
                } else {
                    return "";
                }
            }
        }

        return "";
    }

    // Return true if the card number is valid
    public boolean isValid(String ccNum)
    {
        ccNum = ccNum.replace(" ","");
        if(isDigitOnly(ccNum)) {
            long number = Long.valueOf(ccNum);

            return ((getSize(number) == 16 && prefixMatched(number, 2)) ||
                    (getSize(number) == 15 && prefixMatched(number, 37)) ||
                    (getSize(number) == 16 && prefixMatched(number, 4)) ||
                    (getSize(number) == 16 && prefixMatched(number, 5)) ||
                    (getSize(number) >= 16 && prefixMatched(number, 6))) &&
                    ((sumOfDoubleEvenPlace(number) +
                            sumOfOddPlace(number)) % 10 == 0);
        }

        return false;
    }

    private boolean isDigitOnly(String str){
        if(str.matches("[0-9]+") && str.length() > 0) {
            return true;
        }

        return false;
    }
    // Get the result from Step 2
    public int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    public int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
}
