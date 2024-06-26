Question 14: Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string ""

/*
So what is a prefix ?
-  A prefix : is a sequence of characters at the beginning of a string.
-  Prefix  can be a single letter or a group of letters 
*/
/*
Clarifying Questions:
1] Are all the strings lowercase, or should the function be case-insensitive
2] Are the strings limited to ASCII characters, or should the function support Unicode characters as well (e.g., emojis, accented characters)?
3] What is the maximum number of strings that can be included in the array?
*/
/*
Callout assumptions
- If the array is empty then  the api should return empty string 
- There are no leading or trailing spaces we need to handle
*/
/*
L
N-1
TC: L*(N-1)= O(N*L)
SC: O(L)
*/
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length==0) {
          return "";
        }
        if(strs.length ==1){
          return strs[0];
        }
        String prefix = strs[0];
        StringBuilder result = new StringBuilder();
        for(int i=0; i<prefix.length();i++){
            char c = prefix.charAt(i);
            for(int j=1; j<strs.length;j++){
                if(i==strs[j].length() ||strs[j].charAt(i)!=c){
                    return result.toString();
                }
            }
            result.append(c);
        }
       return result.toString();
    }
}
/*
Limitations and Optimizations

A] Limitations:
i] Dependence on the First String:
The current approach assumes the first string in the array is the potential longest common prefix. If the first string is significantly longer or different from the others, it might lead to unnecessary comparisons.

ii]Character-by-Character Comparison:
The approach performs a character-by-character comparison for each string, which might be inefficient if the common prefix is very short compared to the length of the strings.

iii]Handling Edge Cases:
The current implementation does not handle cases where the array is empty or where the first string is empty, which can lead to potential runtime errors.

B] Optimizations:
i] Early Termination:
We can optimize the solution by checking for the minimum length string first, and limiting our comparison to that length. This ensures we do not compare unnecessary characters beyond the shortest string length.

ii]Handling Edge Cases:
Improve handling of edge cases such as empty arrays or empty strings within the array.

iii]Binary Search Approach:
Use a binary search approach to find the longest common prefix. This can reduce the number of comparisons by checking the middle point and adjusting the search range based on the result.

iv]Divide and Conquer:
Use a divide and conquer approach to split the array into two halves, find the longest common prefix for each half, and then combine the results. This can help to optimize comparisons.
*/











  
