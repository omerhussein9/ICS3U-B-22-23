package TestReviewQuestions;

public class QuestionTwo {
    public static double sqrtSum(int number) {
        int n1 = Integer.parseInt(String.valueOf(number).substring(0,1));
        int n2 = Integer.parseInt(String.valueOf(number).substring(2,3));
        int n3 = Integer.parseInt(String.valueOf(number).substring(4));

        return Math.sqrt(n1 + n2 + n3);
    }
    public static void main(String[] args) {
        System.out.println(sqrtSum(72341));
    }
}
