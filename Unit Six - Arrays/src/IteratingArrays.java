public class IteratingArrays {
    public static void main(String[] args) {
        int[] arr = { 4, 6, 3, 2, 65, 3, 2, 1 };

        int sum = getSum(arr);
        System.out.println(sum);

        double sum2 = getBetterAverage(arr);
        System.out.println(sum2);

        displayBackwards(arr);

        int index = linearSearch(arr, 65);
        System.out.println("\n" + index);
        index = linearSearch(arr, 7);
        System.out.println(index);
    }

    private static int linearSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == val)
                return i;
        }

        return -1;
    }

    private static void displayBackwards(int[] arr) {
        for (int i = arr.length - 1; i > -1; i--) {
            System.out.print(arr[i] + " ");
        }
    }

    private static double getBetterAverage(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            min = arr[i] > min ? arr[i] : min;
            max = arr[i] < max ? arr[i] : max;

            sum += arr[i];
        }

        return (sum - max - min) / (double) (arr.length - 2);
    }

    private static int getSum(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) sum += arr[i];
        return sum;
    }
}