import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	
 *	@since	
 */
public class SortMethods {
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}
	
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param first	first index of arr to sort
	 *	@param last		last index of arr to sort
	 */
	public void mergeSortRecurse(List<String> arr, int first, int last) {
		if (first == last) return;
		int mid =(first + last)/2;
		List<String> arr1 = new ArrayList<>();
		List<String> arr2 = new ArrayList<>();
		for (int i = first; i <= last; i++){
			if (i <= mid) arr1.add(arr.get(i));
			else arr2.add(arr.get(i));
		}
		mergeSortRecurse(arr, first, mid);
		mergeSortRecurse(arr, mid+1, last);
		merge(arr, first, mid, last);
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	public void merge(List<String> arr, int first, int mid, int last) {
		List<String> arr1 = new ArrayList<>();
		List<String> arr2 = new ArrayList<>();
		for (int i = first; i <= last; i++){
			if (i <= mid) arr1.add(arr.get(i));
			else arr2.add(arr.get(i));
		}
        int arrow1 = 0;
        int arrow2 = 0;
        int curr = first;
        while (arrow1 < arr1.size() || arrow2 < arr2.size()){
            if (arrow1 < arr1.size() && arrow2 < arr2.size()){
                if (arr1.get(arrow1).compareTo(arr2.get(arrow2)) < 0){
                    arr.set(curr, arr1.get(arrow1));
                    arrow1++;
                }
                else{
                    arr.set(curr, arr2.get(arrow2));
                    arrow2++;
                }
            }
            else if (arrow1< arr1.size()){
                arr.set(curr, arr1.get(arrow1));
                arrow1++;
            }
            else{
                arr.set(curr, arr2.get(arrow2));
                arrow2++;
            }
            curr++;
        }
	}

	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
