// Max_ecludian_sum


public class Test {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int result = maxEcludianSum(arr);
        System.out.println("Max Ecludian Sum: " + result);
    }

    public static int maxEcludianSum(int[] arr) {
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int ecludianSum = arr[i] + arr[j];
                if (ecludianSum > maxSum) {
                    maxSum = ecludianSum;
                }
            }
        }
        return maxSum;
    }
}
