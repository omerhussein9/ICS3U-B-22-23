package algebraicDominoes;

import java.util.Scanner;

public class Factor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter an expression: ");
        System.out.println(diffSquare(in.nextLine()));

        in.close();
    }

    /*
     * Known Bugs/Issues:
     * Fix spaghetti code. it is so bad :(
     * Actually fix all code. just get better at coding.
     * Fix term sometimes showing up as x^1
     * Fix division on exponents only using the last digit
     */
    public static String diffSquare(String exp) {
        exp = remSpaces(exp);
        if(exp.indexOf("-")>0) {
            for(String op : operators) {
                if(exp.substring(exp.indexOf("-")).indexOf(op)>0)
                    return exp;
            }
            String[] terms = {exp.substring(0,exp.indexOf("-")),exp.substring(exp.indexOf("-")+1)};
            for(int i = 0; i<terms.length; i++) {
                int coLength = 0, expoAt = 0;
                boolean hasVar = false, isOne = false;
                for(int h = 0; h<terms[i].length(); h++) {
                    try {
                        Double.parseDouble(terms[i].substring(h,h+1));
                        coLength = h>coLength ? coLength : coLength+1;
                        expoAt = h;
                        isOne = Double.parseDouble(terms[i].substring(h,h+1))/2==1; 
                    }
                    catch(Exception e) {
                        hasVar = true;
                    }
                }
                if(expoAt==0 && hasVar)
                    return exp;
                if(!hasVar)
                    terms[i] = "" + remFloat(Math.sqrt(Double.parseDouble(terms[i])));
                else if(coLength>0)
                    terms[i] = remFloat(Math.sqrt(Double.parseDouble(terms[i].substring(0,coLength)))) 
                    + terms[i].substring(coLength,expoAt) + remFloat(Double.parseDouble(terms[i].substring(expoAt))/2);
                else if(!isOne) {
                    terms[i] = terms[i].substring(coLength,expoAt) + remFloat(Double.parseDouble(terms[i].substring(expoAt))/2);
                }
                else 
                    terms[i] = terms[i].substring(0,1);
            }
            return String.format("(%s + %s)(%s - %s)",terms[0],terms[1],terms[0],terms[1]);
        }
        return exp;
    }

    private static String[] operators = {"-","+"}; //expandable

    private static String remSpaces(String exp) {
        String out = new String();
        for(int i = 0; i<exp.length(); i++) {
            if(exp.substring(i,i+1).equals(" "))
                continue;
            out += exp.charAt(i);
        }
        return out;
    }

    private static String remFloat(double num) {
        String exp = num + "";
        if(exp.substring(exp.indexOf(".")+1).indexOf("0")==0)
            return exp.substring(0,exp.indexOf("."));
        return exp;
    }

    // private static boolean isInteger(String exp) {

    // }

    // private static int boolConv(boolean conv) {
    //     if(conv)
    //         return 1;
    //     return 0;
    // }
}
