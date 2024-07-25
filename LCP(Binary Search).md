# 14. Longest Common Prefix using Binary Search

```java
class Solution {
  public String longestCommonPrefix(String[] strs) {
     // Return an empty string if the input array is null or empty
    if (strs == null || strs.length == 0)
        return "";

     // Find the length of the shortest string in the array
    int minLen = Integer.MAX_VALUE;
    for (String str : strs)
        minLen = Math.min(minLen, str.length());

    // Set the initial range for binary search from 1 to minLen
    int low = 1;
    int high = minLen;

    // Binary search for the longest common prefix
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
            low = middle + 1;
        else
            high = middle - 1;
    }
    // Return the longest common prefix
    return strs[0].substring(0, (low + high) / 2);
 }

 // Helper function to check if a given length of prefix is common among all strings
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

### 1] Finding the Minimum Length String
This requires a single pass through the array of strings to find the length of the shortest string.
- **Time complexity**: O(N), where \(N\) is the number of strings.

### 2] Binary Search
We perform a binary search on the length of the prefix, which ranges from 1 to the length of the shortest string, let's denote it as \(L\).
- The number of iterations in the binary search is (O(log L)).

### 3] Checking the Common Prefix
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

## Example

Consider the strings ["flower", "flow", "flight"]:

- The shortest string is "flow" with a length of 4.

- Start binary search on length from 1 to 4.

- Check if the prefix of length 2 ("fl") is common among all strings.

- Since it is common, check for a longer prefix by setting low to 3.

- Check if the prefix of length 3 ("flo") is common among all strings.

- Since it is not common, check for a shorter prefix by setting high to 2.

- Continue until low exceeds high.
  

# Finding the Longest Common Prefix using Binary Search

This document explains how to find the longest common prefix among the strings "flower," "flow," and "flight" using a binary search approach.

## Steps

### 1. Initialization
- Shortest string: "flow" with length 4.
- `low` = 1, `high` = 4.

### 2. First Iteration
- Calculate `mid`: 
  \[
  \text{mid} = \left\lfloor \frac{\text{low} + \text{high}}{2} \right\rfloor = \left\lfloor \frac{1 + 4}{2} \right\rfloor = 2
  \]
- Check if the prefix of length `mid` (2) is common among all strings: "fl".

  Helper function checks:
  - "flower" starts with "fl" -> True
  - "flow" starts with "fl" -> True
  - "flight" starts with "fl" -> True

  Since "fl" is a common prefix, adjust `low` to `mid + 1`:
  - `low` = 2 + 1 = 3.

### 3. Second Iteration
- Calculate `mid`: 
  \[
  \text{mid} = \left\lfloor \frac{\text{low} + \text{high}}{2} \right\rfloor = \left\lfloor \frac{3 + 4}{2} \right\rfloor = 3
  \]
- Check if the prefix of length `mid` (3) is common among all strings: "flo".

  Helper function checks:
  - "flower" starts with "flo" -> True
  - "flow" starts with "flo" -> True
  - "flight" starts with "flo" -> False

  Since "flo" is not a common prefix, adjust `high` to `mid - 1`:
  - `high` = 3 - 1 = 2.

### 4. Termination
- Now, `low` (3) > `high` (2), so the search terminates.

The longest common prefix length we found was `high` = 2. Therefore, the longest common prefix is "fl".

## Summary of Iterations

### 1. First Iteration
- `low` = 1, `high` = 4, `mid` = 2
- Check prefix of length `mid` (2): "fl"
- "fl" is common among all strings -> `low` = 3

### 2. Second Iteration
- `low` = 3, `high` = 4, `mid` = 3
- Check prefix of length `mid` (3): "flo"
- "flo" is not common among all strings -> `high` = 2

### 3. Termination
- `low` = 3, `high` = 2
- Longest common prefix length is `high` = 2, so the prefix is "fl".

This binary search approach efficiently narrows down the longest common prefix by checking midpoints and adjusting the search space accordingly.
