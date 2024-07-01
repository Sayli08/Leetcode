# 14. Longest Common Prefix using Binary Search

```java
class Solution {
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0)
        return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs)
        minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return strs[0].substring(0, (low + high) / 2);
 }

 private boolean isCommonPrefix(String[] strs, int len){
    String str1 = strs[0].substring(0,len);
    for (int i = 1; i < strs.length; i++)
        if (!strs[i].startsWith(str1))
            return false;
    return true;
  }
}
```
## Time Complexity


### Steps of the Algorithm

### Finding the Minimum Length String
This requires a single pass through the array of strings to find the length of the shortest string.
- **Time complexity**: O(N), where \(N\) is the number of strings.

### Binary Search
We perform a binary search on the length of the prefix, which ranges from 1 to the length of the shortest string, let's denote it as \(L\).
- The number of iterations in the binary search is (O(log L)).

### Checking the Common Prefix
For each midpoint in the binary search, we need to check if the prefix of that length is common among all strings.
- In the worst case, this involves comparing the prefix with each string, which is (O(N) comparisons, and each comparison can be up to L characters long.
- Thus, the time complexity for checking the common prefix is O(N * L).

## Overall Time Complexity
Combining these steps, the overall time complexity can be calculated as:
- The binary search runs in O(log L) iterations.
- For each iteration, we perform an O(N * L) check.

Therefore, the total time complexity is:
O(N) + O(log L) * O(N * L) = O(N + N *L * log L)

For practical purposes, the O(N + N * L *log L) can be simplified to:
O(N * L * log L)


## Space Complexity

The space complexity of this approach is (O(1) extra space, as we are only using a few additional variables to store indices and lengths. 
The space used for the input strings themselves does not count towards extra space.

## Summary

- **Time Complexity**: O(N * L * log L), where N is the number of strings and L is the length of the shortest string.
- **Space Complexity**: O(1) extra space.

  

