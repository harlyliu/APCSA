/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 
 *	@since	
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length-1; outer >= 0; outer--){
			for (int inner = 0; inner < outer; inner++){
				if (arr[inner].compareTo(arr[inner+1]) > 0)
					swap(arr, inner, inner+1);
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		for (int outer = arr.length-1; outer >= 0; outer--){
			int pointer = 0;
			for (int inner = 0; inner <= outer; inner++){
				if (arr[inner].compareTo(arr[pointer]) > 0)
					pointer = inner;
			}
			swap(arr, pointer, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for (int outer = 1; outer < arr.length; outer++){
			for (int inner = outer; inner > 0; inner--){
				if (arr[inner].compareTo(arr[inner-1]) < 0)
					swap(arr, inner, inner-1);
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		Integer[] newArr = recurMerge(arr);
		for (int i = 0; i < arr.length; i++){
			arr[i] = newArr[i];
		}
	}
	
	private Integer[] recurMerge(Integer[] arr){
		printArray(arr);
		if (arr.length == 1)return arr;
		int midpoint = arr.length/2;
		Integer[] leftArr = new Integer[midpoint];
		Integer[] rightArr = new Integer[arr.length-midpoint];
		for (int i = 0; i < arr.length; i++){
			if (i < midpoint)leftArr[i] = arr[i];
			else rightArr[i-midpoint] = arr[i];
		}
		return merge(recurMerge(leftArr), recurMerge(rightArr));
	}
	
	private Integer[] merge(Integer[] arr1, Integer[] arr2){
		Integer[] ans = new Integer[arr1.length + arr2.length];
		System.out.println("merge");
		printArray(arr1);
		printArray(arr2);
		int newIndex = 0;
		int arrow1 = 0;
		int arrow2 = 0;
		while (arrow1 < arr1.length || arrow2 < arr2.length){
			if (arrow1 < arr1.length && arrow2 < arr2.length){
				if (arr1[arrow1].compareTo(arr2[arrow2]) < 0){
					ans[newIndex] = arr1[arrow1];
					arrow1++;
				}
				else{
					ans[newIndex] = arr2[arrow2];
					arrow2++;
				}
			}
			else if (arrow1< arr1.length){
				ans[newIndex] = arr1[arrow1];
				arrow1++;
			}
			else{
				ans[newIndex] = arr2[arrow2];
				arrow2++;
			}
			newIndex++;
		}
		printArray(ans);
		System.out.println("endmerge");
		return ans;
	}
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}
