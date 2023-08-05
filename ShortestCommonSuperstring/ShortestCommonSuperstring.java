package ShortestCommonSuperstring;

import java.util.*;
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
    String alphabet = "abcdefghijklmnopqrstuvwxyz";


    private class SuffixTreeNode {
        String edge = "";
        List<SuffixTreeNode> children = new ArrayList<>();
        String alphabet = "";

        public SuffixTreeNode() {
        }

        public SuffixTreeNode(String alphabet) {
            this.alphabet = alphabet;
        }

   
    }
    
    //alphabet is lowercase english letters
    //suffix tree: compressed trie where a non-branching nodes, node with only child, are compressed
    //input: "cat","bat","rat","tat","at"
    //                  *
    // at    bat  ...     cat ...  rat ..    t    ...     z
    //$   *    $       *   $    *   $      $  at            null
    //     
    //algorithm: come up with the suffixes of string n like "cat"
    //which would be "cat", "at", "t" 
    //if a suffix already exists in suffix tree
    //don't add it
    //otherwise add each character of the suffix as an edge to a new child node
    //iterate through each node in the tree from bottom to top
    //if a node has only child, compress these two nodes by deleting the child node
    //and combining the parent node's edge's string + child node's edge's string
    public void buildSuffixTrees(List<String> words1) {
        SuffixTreeNode suffixTreeRoot = new SuffixTreeNode(alphabet);
        buildRootChildren(suffixTreeRoot);
    }

     public void buildRootChildren(SuffixTreeNode suffixTreeRoot) {
            int len = this.alphabet.length();
            suffixTreeRoot.children = new ArrayList<>();
            for (int i=0; i < len; i++) {
                SuffixTreeNode child = new SuffixTreeNode();
                child.edge = "" + this.alphabet.charAt(i);
                suffixTreeRoot.children.add(child);
            }
    }

    public void buildTrieOfSuffixes() {

    } 

//  input: cat
//  output: "t". "at",  "cat"
    public static List<String> findSuffixesOfString(String word) {
        List<String> suffixes = new ArrayList();
        int len = word.length();
        for (int i=len - 1; i >= 0; i--) {
            suffixes.add(word.substring(i));
        }
        return suffixes;
    }

    public void compressTrie() {

    }

    public void findWhichStringsHaveMaxOverlap() {

    }

        //
    public void getMergedStringOfOverlappingStrings() {

    }

    public static void printStringsInList(List<String> strList) {
        int sz = strList.size();
        for (int i=0; i < sz; i++) {
            System.out.println(strList.get(i));
        }
    }

    public static void main(String[] args) {
        List<String> words1 = Arrays.asList("cat","bat","rat","tat","at");
        String scs1 = "catbatrattat";

        List<String> words2 = Arrays.asList("catg","ctaagt","gcta","ttca","atgcatc");
        String scs2 = "gctaagttcatgcatc";

        String word = "cat";
        List<String> suffixesTest = findSuffixesOfString(word);
        printStringsInList(suffixesTest);
    }
}
