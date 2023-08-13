package Sorting;

import java.util.List;
import java.util.ArrayList;
//Sorting Problem:
//Input: A set of n items
//Problem description: Arrange the items in increasing order
import java.util.Arrays;

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
    //time: O(nlogn) average case, O(n^2) worst case

    // input : List<Integer> unsortedArr1 = {1};
    // output: [1] 
    // List<Integer> unsortedArr3 = {4,6,3,10,1};
    // output: [1,3,4,6,10]
    // List<Integer> unsortedArr4 = {1,2,3,4,5};
    // output: [1,2,3,4,5]

    // 2nd test case : List<Integer> unsortedArr2 = {4,3,1};
    // output: [1,3,4]
    public static void quickSort(List<Integer> nums) {
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

    public static int getPivot(List<Integer> nums) {
        int len = nums.size();
        int first = nums.get(0);
        int middle = nums.get(len / 2);
        int last = nums.get(len - 1);
        return getMedian(first, last, middle);
    }

    //first = 4, last=1, median = 3
    public static int getMedian(int first, int last, int middle) {
        if (last >= first && last <= middle || last >= middle && last <= first) {
            return last;
        }
        if (first >= last && first <= middle || first >= middle && first <= last) {
            return first;
        }
        return middle;
    }
    
    public static void partitionIntoLeftAndRightSublists(List<Integer> nums, List<Integer> leftSubList, List<Integer> rightSubList, int pivot) {
        int len = nums.size();
        for (int i=0; i < len; i++) {
            if (nums.get(i) <= pivot) {
                leftSubList.add(nums.get(i)); //[3,1]
            } else {
                rightSubList.add(nums.get(i)); //[4]
            }
        }
    }

    public static void merge(List<Integer> nums, List<Integer> leftSubList, List<Integer> rightSubList, int pivot) {
        nums.clear();
        nums.addAll(leftSubList);
        nums.add(pivot);
        nums.addAll(rightSubList);

    }


    //MergeSort Algorithm:
    //divide and conquer algorithm
    //keep splitting the list into halves
    //until you are left with sublists of size 1
    //merge the pairs of sublists of size 1
    //in sorted order
    //repeats wtih the pairs of sublists of size 2,4,8..
    //until you have sorted the entire list
    //time: O(nlogn)
    //input:  4,3,1
    //output: 1, 3, 4

    //call stack:
    //mergeSort([3,1])
    //mergeSort([4,3,1])

    public static List<Integer> mergeSort(List<Integer> nums) {
        if (nums.size() == 2) {
            swap(nums);
        } else if (nums.size() > 2) {
            //split into halves
            int size = nums.size();
            List<Integer> leftSubList = new ArrayList<>();
            List<Integer> rightSubList = new ArrayList<>();
            int mid = size / 2;
            leftSubList = sublist(nums,0, mid); //[4]
            rightSubList = sublist(nums, mid, size); //[3,1]
            //call mergesort on each half
            leftSubList = mergeSort(leftSubList); //[4]
            rightSubList = mergeSort(rightSubList); //[3,1]
            //merge each half back together
            List<Integer> merged = mergeForMergeSort(leftSubList, rightSubList); //[1,3,4,6, 10]
            return merged;
        }
        return nums;
       
    }

    public static void swap(List<Integer> nums) {
        if (nums.get(0) > nums.get(1)) {
            int second = nums.get(1);
            int first = nums.get(0);
            nums.clear();
            nums.add(second);
            nums.add(first);
        }
    }

    //merge smaller arrays into larger array
    //for ex. 2 arrays of length 2 into a larger array of length 4
    //insert the element of the smaller into the larger array in the correct order
    //                                                          //[4,6]                  //[1,3,10] 
    public static List<Integer> mergeForMergeSort(List<Integer> smallerSub1, List<Integer> smallerSub2) {
        List<Integer> merged = new ArrayList<>();
        int ptr1 = 0, ptr2 = 0; 
        int size1 = smallerSub1.size();
        int size2 = smallerSub2.size();
        while (ptr1 < size1 && ptr2 < size2) {
            if (smallerSub1.get(ptr1) <= smallerSub2.get(ptr2)) {
                merged.add(smallerSub1.get(ptr1)); //[1,3,4,6]
                ptr1++;
            } else {
                merged.add(smallerSub2.get(ptr2)); 
                ptr2++;
            }
        }
        if (ptr1 < size1) {
            merged.addAll(sublist(smallerSub1, ptr1, size1));
        }
        if (ptr2 < size2) {
            merged.addAll(sublist(smallerSub2, ptr2, size2)); //[1,3,4,6, 10]
        }
        return merged;
    }

    public static List<Integer> sublist(List<Integer> list, int start, int end) {
        int sz = list.size();
        List<Integer> result = new ArrayList<>();
        for (int i=start; i < end; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public static void printList(List<Integer> nums) {
        for (int i=0; i < nums.size(); i++) {
            System.out.print(nums.get(i) + ",");
        }
    }

    public static void main(String[] args) {

        List<Integer> unsortedList1 = Arrays.asList(1);
        unsortedList1 = mergeSort(unsortedList1);
        printList(unsortedList1);
        System.out.println();

        List<Integer> unsortedList2 = new ArrayList<>();
        unsortedList2.add(4);
        unsortedList2.add(3);
        unsortedList2.add(1);
        unsortedList2 = mergeSort(unsortedList2);
        printList(unsortedList2);
        System.out.println();

        List<Integer> unsortedList3 = Arrays.asList(4,6,3,10,1);
        unsortedList3 = mergeSort(unsortedList3);
        printList(unsortedList3);
        System.out.println();

        List<Integer> unsortedList4 = Arrays.asList(5,1,1,2,0,0);
        unsortedList4 = mergeSort(unsortedList4);
        printList(unsortedList4);
    }

}