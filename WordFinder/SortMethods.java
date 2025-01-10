import java.util.ArrayList;
import java.util.List;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author 
 *	@since	
 */
public class SortMethods {	
	public void mergeSort(List<String> arr) {
        List<String> newArr = recurMerge(arr);
        for (int i = 0; i < arr.size(); i++){
            arr.set(i, newArr.get(i));
        }
    }

	/**
     *	main recursion method of merge sort
     *	@param list arr of cities
     * @param choice, 1 for sort by population, 2 for by name
     * @return array of cities, sorted
     */
    private List<String> recurMerge(List<String> arr){
        if (arr.size() == 1)return arr;
        int midpoint = arr.size()/2;
        List<String> leftArr= new ArrayList<>();
        List<String> rightArr = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++){
            if (i < midpoint)leftArr.add(arr.get(i));
            else rightArr.add(arr.get(i));
        }
        return merge(recurMerge(leftArr), recurMerge(rightArr));
    }
    
	 /**
     *	merges by number
     *	@param first array, sorted
     * @param second array, sorted
     * @return merged array
     */
    private List<String> merge(List<String> arr1, List<String> arr2){
        List<String> ans = new ArrayList<>();
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
                ans.add(arr2.get(arrow2));
                arrow2++;
            }
        }
        return ans;
    }


}
