// Max product


public class Test {
    static int max(int n1, int n2, int n3){
        if (n1 > n2 && n1 > n3) return n1;
        if (n2 > n1 && n2 > n3) return n2;
        return n3;
    }
    static int min(int n1, int n2, int n3){
        if (n1 < n2 && n1 < n3) return n1;
        if (n2 < n1 && n2 < n3) return n2;
        return n3;
    }

    public static void main(String []args){
        int[] arr = { -2, 3 ,5 , 1};
        int arrMax = arr[0] , arrMin=arr[0];
        for (int i = 1; i < 4; i++){
            int temp = max(arr[i], arr[i]*arrMax, arr[i]*arrMin);
            arrMin = min(arr[i], arr[i]*arrMax, arr[i]*arrMin);
            arrMax = temp;
        }
        System.err.println(arrMax);
    }
}
