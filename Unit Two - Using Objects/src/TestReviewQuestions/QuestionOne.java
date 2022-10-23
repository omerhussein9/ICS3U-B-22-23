package TestReviewQuestions;

public class QuestionOne {
    public static String replaceString(String str, String replaceMe, String addMe) {
        int index = str.indexOf(replaceMe);
        String s = str.substring(0,index) + addMe + str.substring(index+replaceMe.length());
        return s;
    }
    public static void main(String[] args) {
        System.out.println(replaceString("RedSox,Yankees", "Yankees", "BlueJays"));
    }
}
