package csci310;
//Andrew Trice
import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.*;


//In This Program We Will Make 3 Partitions of a Sorted List
//And Search the Partitions to Find Our Key
public class search3Partition {
	
	public static int divide3Search(int low,int high, int x){
		int S[] = null;
		int mid1;
		int mid2; 
		while(low <= high && high-low > 1)
		{
			//Set Our Partitions
			Math.floor(mid1 = ((high - low) / 3) + low);
			Math.floor(mid2 = (2*(high - low)/3) + low);
			if(x == S[mid1])
				return mid1;
			else if(x == S[mid2])
				return mid2;
			if(x < S[mid1]){
				high = (mid1 -1);
				 divide3Search(low, high, x);
			}
			else if(x > S[mid2]){
				low = mid2 +1;
				 divide3Search(low, high, x);
			}
			else if(x > S[mid1] && x < S[mid2]){
				low = mid1 +1;
				high = mid2 -1;
			 divide3Search(low,high, x);
		}
		for(int i=low;i<=high;i++)
		{
			if(x==S[i])
			{
				return i;
			}
		}}
		return -1;
		}




	//Our Main Method Will Call the Divide3Search Method
		public static void main(String arg[]) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			        
			        System.out.println("Enter number of elements: ");
			        int num = Integer.parseInt(br.readLine());
			        int array[] = new int[num];
			       
			        //User Input Will Need to be Sorted
			        System.out.println("Enter number(Sort Them): ");
			        for (int i = 0; i < num; i++) {
			            array[i] = Integer.parseInt(br.readLine());
			        }
			        divide3Search(array[0], array[array.length-1], array[array.length/2]);
				}
		}


