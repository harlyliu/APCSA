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
		infile.useDelimiter("[\t\n]");
		while(infile.hasNext()){
			String cityName = infile.next();
			String stateName = infile.next();
			String cityType = infile.next();
			int cityPopulation = infile.nextInt();
			cities.add(new City(cityName, stateName, cityType, cityPopulation));
		}	
		printIntroduction();
		printMenu();
		int val = 0;
		while (int val != 9){
			System.out.println("1. Fifty least populous cities in USA (Selection Sort)\n"+
				"2. Fifty most populous cities in USA (Merge Sort)\n"+
				"3. First fifty cities sorted by name (Insertion Sort)\n"+
				"4. Last fifty cities sorted by name descending (Merge Sort)\n"+
				"5. Fifty most populous cities in named state\n"+
				"6. All cities matching a name sorted by population"+
				"9. Quit\n");
			val = Prompt.getInt("Enter selection ->");
			if(val == 1){
				selectionSort(cities);
				for (int i = 1; i <= 50; i++){
					System.out.println(i + ":" + cities.get(cities.size()-i));
				}
			}
			else if (val == 2){
				mergeSort(cities);
				for (int i = 0; i < 50; i++){
					System.out.println(i + ":" + cities.get(i));
				}
			}
			else if (val == 3){
				insertionSort(cities);
				for (int i = 0; i < 50; i++){
					System.out.println(i + ":" + cities.get(i));
				}
			}
			else if (val == 4){
				mergeSort(cities);
				for (int i = 0; i < 50; i++){
					System.out.println(i + ":" + cities.get(i));
				}
			}
		}
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
    public void bubbleSort(List<City> arr) {
        for (int outer = arr.size()-1; outer >= 0; outer--){
            for (int inner = 0; inner < outer; inner++){
                if (arr.get(inner).compareTo(arr.get(inner+1)) > 0)
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
    private void swap(List<City> arr, int x, int y) {
        City temp = arr.get(x);
        arr.get(x) = arr.get(y);
        arr.get(y) = temp;
    }

    /**
     *	Selection Sort algorithm - in ascending order (you implement)
     *	@param arr		array of Integer objects to sort
     */
    public void selectionSort(List<City> arr) {
        for (int outer = arr.size()-1; outer >= 0; outer--){
            int pointer = 0;
            for (int inner = 0; inner <= outer; inner++){
                if (arr.get(inner).compareTo(arr.get(pointer)) > 0)
                    pointer = inner;
            }
            swap(arr, pointer, outer);
        }
    }

    /**
     *	Insertion Sort algorithm - in ascending order (you implement)
     *	@param arr		array of Integer objects to sort
     */
    public void insertionSort(List<City> arr) {
        for (int outer = 1; outer < arr.size(); outer++){
            for (int inner = outer; inner > 0; inner--){
                if (arr.get(inner).compareTo(arr[.get(inner-1)) < 0)
                    swap(arr, inner, inner-1);
            }
        }
    }

    public void mergeSort(List<City> arr) {
        List<City> newArr = recurMerge(arr);
        for (int i = 0; i < arr.size(); i++){
            arr.get(i) = newArr.get(i);
        }
    }

    private List<City> recurMerge(List<City> arr){
        if (arr.size() == 1)return arr;
        int midpoint = arr.size()/2;
        List<City> leftArr;
        List<City> rightArr;
        for (int i = 0; i < arr.size(); i++){
            if (i < midpoint)leftArr.add(arr.get(i));
            else rightArr.add(arr.get(i));
        }
        return merge(recurMerge(leftArr), recurMerge(rightArr));
    }

    private List<City> merge(List<City> arr1, List<City> arr2){
        List<City> ans;
        int arrow1 = 0;
        int arrow2 = 0;
        while (arrow1 < arr1.size() || arrow2 < arr2.size()){
            if (arrow1 < arr1.size() && arrow2 < arr2.size()){
                if (arr1.get(arrow1).compareTo(arr2.get(arrow2)) < 0){
                    ans.add(arr1.get(arrow1));
                    arrow1++;
                }
                else{
                    ans.add(arr2.get(arrow2));
                    arrow2++;
                }
            }
            else if (arrow1< arr1.size()){
                ans.add(arr1.get(arrow1));
                arrow1++;
            }
            else{
                ans.add(arr2,get(arrow2));
                arrow2++;
            }
        }
        return ans;
    }
	
}
