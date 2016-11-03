package csci310;
//Andrew Trice
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class LargeFind  {
	//Divide And Conquer Non-Recursive Searching Method 
	//Modified Binary Search
	//Return X to Main if Element Not In List
	public static int search(int array[], int find) {
        int start = 0;
        int last = array.length - 1;
        int middle;
        
        while (start <= last) {
            middle = (start + last) / 2;
            if (array[middle] == find) {
                return middle;
            } else if (array[middle] < find) {
                start = middle + 1;
            } else if (array[middle] > find) {
                last = middle - 1;
            }
        }
        return 'X';
    }
	//Main Method 
	//Number of Elements is User Input
	//Highest Element is also User Input
    public static void main(String arg[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Enter number of elements: ");
        int num = Integer.parseInt(br.readLine());
        int array[] = new int[num];
       
        
        System.out.println("Enter number: ");
        for (int i = 0; i < num; i++) {
            array[i] = Integer.parseInt(br.readLine());
 } 
        System.out.println("Enter Highest Element");
        int find = Integer.parseInt(br.readLine());
        
        //Initiate Search
        int index = search(array, find);
        if (index != 'X') {
            System.out.println("Element found : " + index);
        } else {
            System.out.println("Element not found");
        }}}




