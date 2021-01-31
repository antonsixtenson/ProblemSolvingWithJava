import java.util.Arrays;
import java.util.Random;

/*
*   PROBLEM:
*   Take two sorted arrays (a & b) and merge them into
*   one sorted with length a.length+b.length
*
 */

public class SortArray {

    Random rand = new Random();

    public int[] genArray(int len){
        int [] arr = new int[len];
        for(int i = 0; i < len; i++){
            arr[i] = rand.nextInt(100);
        }

        return arr;
    }

    // Takes two sorted arrays and merge them into one sorted of length a.length+b.length
    public int[] mergeArrays(int[] a, int[] b){
        if(a == null || b == null){
            throw new NullPointerException();
        }

        int [] c = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while(i < a.length && j < b.length){
            if(a[i] > b[j]){
                c[k] = b[j];
                j++;
            } else {
                c[k] = a[i];
                i++;
            }
            k++;
        }

        while(j < b.length){
            c[k] = b[j];
            k++;
            j++;
        }

        while(i < a.length){
            c[k] = a[i];
            k++;
            i++;
        }
        return c;
    }

    public int [] bubbleSort(int [] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length - 1; j++){
                if(arr[i] > arr[j+1]){
                    int temp = arr[i];
                    arr[i] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        SortArray sa = new SortArray();
        int [] a = sa.genArray(5);
        int [] b = sa.genArray(9);
        a = sa.bubbleSort(a);
        b = sa.bubbleSort(b);
        System.out.println(Arrays.toString(sa.mergeArrays(a, b)));
    }
}
