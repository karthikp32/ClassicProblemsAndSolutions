import java.util.Arrays;

public class BinPacking {

//used https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/description/ as a starting point

    //Implementation of variant of first-fit decreasing heuristic
    //Sort tasks in descending order  - time: O(nlogn)
    //iterate through sortedTasks
    //if currTaskHours + sortedTasks[i] <= sessionTime
    //currTaskHours += sortedTasks[i]
    //else 
    //sessions++
    //currTaskHours = sortedTasks[i]
    //i++
    //return currTaskHours > 0 ? sessions++; sessions
    //time: O(nlogn)
    //space: O(n)
    public static int getMinimumWorkSessions(int[] tasks, int sessionTime) {
        int[] sortedTasks = getArrayInDescendingOrder(tasks);
        int currTaskHours = 0, sessions = 0;
        for (int i=0; i < sortedTasks.length; i++) {
            if (currTaskHours + sortedTasks[i] <= sessionTime) {
                currTaskHours += sortedTasks[i];
            } else {
                sessions++;
                currTaskHours = sortedTasks[i];
            }
        }
        return currTaskHours > 0 ? sessions + 1 : sessions;
    }

    public static int[] getArrayInDescendingOrder(int[] tasks) {
      int[] sortedTasks = Arrays.copyOf(tasks, tasks.length);
      Arrays.sort(sortedTasks);
      int i=0, j=sortedTasks.length - 1, temp=0;
      while (i <= j) {
        temp = sortedTasks[i];
        sortedTasks[i] = sortedTasks[j];
        sortedTasks[j] = temp;
        i++;
        j--;
      }
      
      return sortedTasks;
    }

    public static void main(String args[]) {

      int[] tasks1 = {1, 2, 3};
      int sessionTime1 = 3;
      int expectedMinWorkSessions1 = 2;
      int actualMinWorkSessions1 = getMinimumWorkSessions(tasks1, sessionTime1);
      System.out.println("Expected minimum work sessions are " + expectedMinWorkSessions1 
      + " while actual minimum work sessions are " + actualMinWorkSessions1);


      int[] tasks2 = {3, 1, 3, 1, 1};
      int sessionTime2 = 8;
      int expectedMinWorkSessions2 = 2;
      int actualMinWorkSessions2 = getMinimumWorkSessions(tasks2, sessionTime2);
      System.out.println("Expected minimum work sessions are " + expectedMinWorkSessions2 
      + " while actual minimum work sessions are " + actualMinWorkSessions2);
      
      int[] tasks3 = {10,10,7,8,1};
      int sessionTime3 = 15;
      int expectedMinWorkSessions3 = 3;
      int actualMinWorkSessions3 = getMinimumWorkSessions(tasks3, sessionTime3);
      System.out.println("Expected minimum work sessions are " + expectedMinWorkSessions3 
      + " while actual minimum work sessions are " + actualMinWorkSessions3 + ". Since we used a heuristic, we got a less than "
      + "optimal but good enough solution. ");

      int[] tasks4 = {1,2,3,4,5};
      int sessionTime4 = 15;
      int expectedMinWorkSessions4 = 1;
      int actualMinWorkSessions4 = getMinimumWorkSessions(tasks4, sessionTime4);
      System.out.println("Expected minimum work sessions are " + expectedMinWorkSessions4 
      + " while actual minimum work sessions are " + actualMinWorkSessions4);

    }
}