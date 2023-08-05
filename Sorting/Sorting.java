package Sorting;

import java.util.List;

//Sorting Problem:
//Input: A set of n items
//Problem description: Arrange the items in increasing order

//Notes:
//O(n^2) sorting algorithms like insertion sort
//can be sufficient if there are <= 100 items

//it becomes more important to use a O(nlogn) sorting
//algorithm like quicksort and mergesort if 
//there > 100 items to sort

//another idea is to use quicksort to sort 900 of the items
//and then use insertion once the subarrays have <= 100 items
//suppose there was 1000 items to sort


public class Sorting {


    //QuickSort Idea:
    //pick a random element as the pivot
    //put all of the elements from the rest
    //of the elements that are <= pivot 
    //in the left subarray
    //put all of the elements from the rest
    //of the elements that are > pivot 
    //in the right subarray
    //repeat this process on the left and right subarray
    //once the left and subarrays of a pivot
    //have only 1 or 0 elements
    //merge the pivot and these left and right subarrays
    //repeat this process with their parent subarrays and so on

    //QuickSort Algorithm:
    //Sort()
    //Pick a pivot
    //use the median of three heuristic
    //to pick the median of left, right, middle elements
    //Separate items into left and right sublists
    //create the leftSubList and rightSubList
    //iterate through the list
    //Put nums <= pivot in left sublist
    //Put nums > pivot in right sublist
    //call sort() again on left and right sublist
    //Merge
    //if left/right sublist have size <=1
    //return left/right sublist     


    // input : List<Integer> unsortedArr1 = {1};
    // output: [1] 
    // List<Integer> unsortedArr3 = {4,6,3,10,1};
    // output: [1,3,4,6,10]
    // List<Integer> unsortedArr4 = {1,2,3,4,5};
    // output: [1,2,3,4,5]

    // 2nd test case : List<Integer> unsortedArr2 = {4,3,1};
    // output: [1,3,4]
    public void quickSort(List<Integer> nums) {
        if (nums.size() > 1) {
            int pivot = getPivot(nums);
            List<Integer> leftSubList = new ArrayList<>();
            List<Integer> rightSubList = new ArrayList<>();
            partitionIntoLeftAndRightSublists(nums, leftSubList, rightSubList, pivot); 
            //tested 2nd test case up to after this partitioning
            if (leftSubList.size() > 1) {
                quickSort(leftSubList);
            }
            if (rightSubList.size() > 1) {
                quickSort(rightSubList);
            }
            merge(nums, leftSubList, rightSubList, pivot);
        }
    }

    public int getPivot(List<Integer> nums) {
        int len = nums.size();
        int first = nums.get(0);
        int middle = nums.get(len / 2);
        int last = nums.get(len - 1);
        return getMedian(first, last, middle);
    }

    //first = 4, last=1, median = 3
    public int getMedian(int first, int last, int middle) {
        if (last >= first && last <= middle || last >= middle && last <= first) {
            return last;
        }
        if (first >= last && first <= middle || first >= middle && first <= last) {
            return first;
        }
        return middle;
    }
    
    public void partitionIntoLeftAndRightSublists(List<Integer> nums, List<Integer> leftSubList, List<Integer> rightSubList, int pivot) {
        int len = nums.size();
        for (int i=0; i < len; i++) {
            if (nums.get(i) <= pivot) {
                leftSubList.add(nums.get(i)); //[3,1]
            } else {
                rightSubList.add(nums.get(i)); //[4]
            }
        }
    }

    public void merge(List<Integer> nums, List<Integer> leftSubList, List<Integer> rightSubList, int pivot) {
        nums.clear();
        nums.addAll(leftSubList);
        nums.addAll(pivot);
        nums.addAll(rightSubList);

    }

    public void mergeSort(List<Integer> nums) {

    }

    public static void main(String[] args) {
        List<Integer> unsortedArr1 = {1};

        List<Integer> unsortedArr2 = {4,3,1};

        List<Integer> unsortedArr3 = {4,6,3,10,1};

        List<Integer> unsortedArr4 = {1,2,3,4,5};
    }

}