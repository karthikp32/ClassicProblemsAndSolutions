package ShortestCommonSuperstring;

//What is the set cover problem?
//Given a collection of sets S={S1, ..., Sm} and a universal set U = {1,...,n} that contains all of the elements
//find the minimum number of sets that will include all of the elements 
//in the universal set

//Used https://leetcode.com/problems/smallest-sufficient-team/description/
//as a starting point
//This is an instance of the set cover problem since given a list of required skills ( universal set U = {1,...,n})
//and people with some of those skills (set of some or all of those skills S={S1, ..., Sm}) , you want to figure out the 
//smallest number of people you have to hire to have all of skills.

public class ShortestCommonSuperstring {

    //Implementation of greedy heuristic to find shortest common superstring

    private class SuffixTreeNode {
        String branch = "";
        List<SuffixTreeNode> children = new ArrayList<>();
    }
    
    //iterate through each
    public void buildSuffixTrees(List<String> words1) {
        
    }

        //
    public void findWhichStringsHaveMaxOverlap() {

    }

        //
    public void getMergedStringOfOverlappingStrings() {

    }


    public static void main(String[] args) {
        List<String> words1 = {"cat","bat","rat","tat","at"};
        String scs1 = "catbatrattat";

        List<String> words2 = {"catg","ctaagt","gcta","ttca","atgcatc"};
        String scs2 = "gctaagttcatgcatc";

    }
}
