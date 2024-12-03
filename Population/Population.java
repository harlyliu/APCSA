import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	
 *	@since	
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	public static void main(String[] args){
		Population pop = new Population();
		pop.run();
	}
	
	public void run(){
		Scanner infile = FileUtils.openToRead(DATA_FILE);
		while(infile.hasNext()){
			String cityName = infile.next();
			String stateName = infile.next();
			String cityType = infile.next();
			int cityPopulation = infile.nextInt();
			cities.add(new City(cityName, stateName, cityType, cityPopulation));
		}	
		printIntroduction();
		printMenu();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
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

    public void mergeSort(Integer [] arr) {
        Integer[] newArr = recurMerge(arr);
        for (int i = 0; i < arr.length; i++){
            arr[i] = newArr[i];
        }
    }

    private Integer[] recurMerge(Integer[] arr){
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
        return ans;
    }
	
}
