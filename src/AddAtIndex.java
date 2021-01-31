import java.util.Arrays;

/*
*   PROBLEM:
*   Insert element into array and shift all
*   elements from inserted index to the right.
*
 */

public class AddAtIndex {

    public int[] addInt(int[] arr, int index, int value){
        int temp = arr[index];
        for(int i = arr.length - 1; i > index ; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = value;
        return arr;
    }


    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        AddAtIndex a = new AddAtIndex();
        System.out.println(Arrays.toString(a.addInt(arr, 2, 10)));

    }

}
