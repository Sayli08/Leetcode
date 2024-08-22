# Divide and Conquer Approach for Longest Common Prefix

### Index
1. [Introduction](#introduction)
2. [Step 1: Establish the Base Case](#step-1-establish-the-base-case)
3. [Step 2: Divide the Problem](#step-2-divide-the-problem)
4. [Step 3: Conquer the Problem](#step-3-conquer-the-problem)
5. [Step 4: Combine the Results](#step-4-combine-the-results)
6. [Code Implementation](#code-implementation)
7. [Time Complexity](#time-complexity)
8. [Space Complexity](#space-complexity)
9. [Conceptual Explanation of Time Complexity](#conceptual-explanation-of-time-complexity)
10. [Simplified Explanation](#simplified-explanation)

---

## Introduction

This document explains the divide and conquer approach for finding the longest common prefix among an array of strings. We'll walk through each step, analyze the time and space complexity, and provide a clear and simple explanation of how the algorithm works.

[Link for Divide and Conquer Algorithm](https://github.com/Sayli08/Algorithms/blob/main/DivideAndConquerStrategy.md)

## Step 1: Establish the Base Case
- If the array of strings is empty, return an empty string.
- If the array contains only one string, return that string as the prefix.

#### Example:
- Consider the array `["flower", "flow", "flight"]`. 

## Step 2: Divide the Problem
- We first divide this array into two halves: `["flower", "flow"]` and `["flight"]`.

## Step 3: Conquer the Problem
- We further divide each half. The left half `["flower", "flow"]` is divided into `["flower"]` and `["flow"]`.
- We keep doing this recursively until we reach the base case where each segment contains only one string:
  - `["flower"]`
  - `["flow"]`
  - `["flight"]`
- We then identify the longest common prefix for each segment independently:
  - For `["flower"]`, the longest common prefix is "flower".
  - For `["flow"]`, the longest common prefix is "flow".
  - For `["flight"]`, the longest common prefix is "flight".

## Step 4: Combine the Results
- For the left half `["flower", "flow"]`, we compare "flower" and "flow" to find the common prefix, which is "fl".
- The right half contains only one string "flight", so its prefix remains "flight".
- Now, we combine the results from both halves. Comparing "fl" with "flight" character by character, the longest common prefix for the entire array is "fl".

Here's how the process is visualized:

```
                       ["flower", "flow", "flight"]
                                /          \
                  ["flower", "flow"]     ["flight"]
                            /       \            \
                   ["flower"]   ["flow"]   ["flight"]
                       |            |             |
                    {flower}      {flow}       {flight}
                               \            /
                                    {fl}
```

---

## Code Implementation

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";    
        return longestCommonPrefix(strs, 0 , strs.length - 1);
}

private String longestCommonPrefix(String[] strs, int l, int r) {
    if (l == r) {
        return strs[l];
    }
    else {
        int mid = (l + r)/2;
        String lcpLeft =   longestCommonPrefix(strs, l , mid);
        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
        return commonPrefix(lcpLeft, lcpRight);
   }
}

String commonPrefix(String left,String right) {
    int min = Math.min(left.length(), right.length());      
    for (int i = 0; i < min; i++) {
        if ( left.charAt(i) != right.charAt(i) )
            return left.substring(0, i);
    }
    return left.substring(0, min);
} 
```

---

## Time Complexity

### Recurrence Relation
The recurrence relation for the problem is:

$$
T(n) = 2T(n/2) + O(m)
$$

where:
- \( n \) is the number of strings.
- \( m \) is the length of the strings (assuming all strings have the same length in the worst case).

### Step 1: Compare with the General Form
The general form of the recurrence relation in the Master Theorem is:

$$
T(n) = aT(n/b) + f(n)
$$

Here:
- \( a = 2 \)
- \( b = 2 \)

$$
f(n) = O(m)
$$

which can be interpreted as 

$$
O(n^0 \cdot m)
$$

where \( k = 0 \) and \( p = 0 \).

### Step 2: Calculate the Two Key Values

$$
\text{Compute } \log_b(a) = \log_2(2) = 1
$$

$$
\text{Here, } f(n) = O(m), \text{ which can be treated as } O(n^0 \cdot m)
$$

$$
\text{So, } k = 0 \text{ (since } n^0 = 1)
$$

### Step 3: Apply the Master Theorem Cases

$$
\text{Now, let’s compare } \log_b(a) \text{ with } k:
$$

$$
\text{Case 1: } \log_b a > k
$$

$$
\text{Since } \log_2 2 = 1 > 0 = k, \text{ this situation corresponds to Case 1 of the Master Theorem.}
$$

According to **Case 1**, the time complexity is dominated by the \( T(n/b) \) part of the recurrence:

$$
T(n) = \Theta(n^{\log_b a}) = \Theta(n^1) = \Theta(n)
$$

However, since \( f(n) = O(m) \) contributes additional complexity, the overall time complexity is:

$$
T(n) = O(n \cdot m)
$$

### Understanding the Contribution of \( f(n) = O(m) \)

When we use the Master Theorem to solve recurrence relations like:

$$
T(n) = 2T(n/2) + f(n)
$$

the result depends on how the function \( f(n) \) compares to the other parts of the recurrence.
Here, \( f(n) = O(m) \) is the cost of the "combine" step in the divide and conquer algorithm, where you merge the results of the two recursive calls.

### How the Master Theorem Applies Here

$$
\text{We computed two key values:}
$$

$$
\log_b a = 1, \text{ where } b = 2 \text{ and } a = 2.
$$

$$
k = 0, \text{ because } f(n) = O(n^0 \cdot m).
$$

These values tell us we're in **Case 1** of the Master Theorem, where \( \log_b a > k \). In this case, the time complexity is dominated by the recursive calls, giving us:

$$
T(n) = \Theta(n^{\log_b a}) = \Theta(n)
$$

### Considering the Function \( f(n) = O(m) \)

The Master Theorem helps us determine the complexity based on the comparison between \( f(n) \) and the recursive structure. Here, \( f(n) = O(m) \) is the additional work done at each level of recursion, which is not simply absorbed by the recursive calls.

This is because the total work at each level is not just a constant (which would be the case if \( m \) were a constant) but instead grows with \( m \).

In the context of the `longestCommonPrefix` problem:
- \( m \) represents the length of the strings being compared.
- Each recursive step involves comparing up to \( m \) characters.

Since \( f(n) = O(m) \) scales with \( m \), this additional complexity needs to be included in the overall time complexity.

----- 
$$
\text{**Why } T(n) = O(n \cdot m) \text**{?}
$$

$$
\text{1. Recursive Calls: The recursive part of the relation } 2T(n/2) \text{ simplifies to } O(n), \text{ which represents the number of levels in the recursion tree.}
$$

$$
\text{2. Merge Step at Each Level:}
$$

$$
\text{Each level of recursion involves a merge step that takes } O(m) \text{ time.}
$$

$$
\text{Since there are } O(\log n) \text{ levels in the recursion tree, and each level potentially requires } O(m) \text{ time for the merge step, the overall complexity involves combining both these factors.}
$$

$$
\text{3. Total Complexity:}
$$

$$
\text{At each level of recursion, } O(m) \text{ work is done.}
$$

$$
\text{There are } O(\log n) \text{ levels of recursion, but since }
m \text{ does not change across levels, the total time across all levels is } O(n \cdot m).
$$

$$
\text{Thus, even though the Master Theorem initially suggests } O(n),
$$

$$
\text{ the actual time complexity becomes } O(n \cdot m)
$$

$$
\text{ because the function } f(n) = O(m) \text{ adds significant work at each level of recursion.}
$$

$$
\text{This is why the overall time complexity is:}
$$

$$
T(n) = O(n \cdot m)
$$

$$
\text{where } n \text{ is the number of strings and } m \text{ is the length of the strings. This product represents the total number of character comparisons needed to find the longest common prefix across all strings.}
$$

---

## Space Complexity

### Recurrence Relation

$$
T(n) = O(m) \quad \text{for} \ n = 1 \\
$$

$$
T(n) = 2T(n/2) + O(m) \quad \text{for} \ n > 1
$$

---



---

$$
\text{Conceptual Explanation of the Time Complexity:}
$$

$$
\text{1. Problem Splitting:}
$$

$$
\text{Each split divides the problem size in half, leading to a logarithmic depth of recursion } (\log n).
$$

$$
\text{2. Work Performed at Each Level:}
$$

$$
\text{At each level, strings are compared character by character, potentially involving all characters in the shortest string } (m).
$$

$$
\text{3. Total Work:}
$$

$$
\text{The total work combines the number of comparisons at each level with the number of levels in the recursion. Therefore, the overall time complexity is proportional to } O(n \cdot m \cdot \log n).
$$

----

### Space Complexity : 

$$
\text{Space Complexity: } O(m \cdot \log n)
$$

$$
\text{There is a memory overhead since we store recursive calls in the execution stack. 
There are } \log n \text{ recursive calls, each requiring } m \text{ space to store the result, so space complexity is }
 O(m \cdot \log n).
$$

-----

## Simplified Explanation

 **Analogy**: 
- Imagine organizing a large group of people to find a common trait. 
- By repeatedly splitting the group in half and identifying common traits within smaller groups, you simplify the task. Finally, you combine the results from each smaller group to find the common trait for the entire group.

 **High-Level Overview**: 
- Each split simplifies the problem until you're working with individual strings. 
- Then, building back up, you use the results from these smaller tasks to efficiently solve the larger problem.

---

