
# Divide and Conquer Approach for Longest Common Prefix

**Step 1: Establish the Base Case**
- If the array of strings is empty, return an empty string.
- If the array contains only one string, return that string as the prefix.

#### Example:
- Consider the array `["flower", "flow", "flight"]`. 

**Step 2: Divide the Problem**
- We first divide this array into two halves: `["flower", "flow"]` and `["flight"]`.

**Step 3: Conquer the Problem**
- We further divide each half. The left half `["flower", "flow"]` is divided into `["flower"]` and `["flow"]`.
- We keep doing this recursively until we reach the base case where each segment contains only one string:
  - `["flower"]`
  - `["flow"]`
  - `["flight"]`
- We then identify the longest common prefix for each segment independently:
  - For `["flower"]`, the longest common prefix is "flower".
  - For `["flow"]`, the longest common prefix is "flow".
  - For `["flight"]`, the longest common prefix is "flight".

**Step 4: Combine the Results**
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

Code
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

### Recurrence Relation
The recurrence relation for the problem is:

```
T(n) = 2T(n/2) + O(m)
```

where:
- `n` is the number of strings.
- `m` is the length of the strings (assuming all strings have the same length in the worst case).

### Step 1: Compare with the General Form
The general form of the recurrence relation in the Master Theorem is:

```
T(n) = aT(n/b) + f(n)
```

Here:
- `a = 2`
- `b = 2`
- `f(n) = O(m)`, which can be interpreted as `O(n^0 * m)`, where `k = 0` and `p = 0`.

### Step 2: Calculate the Two Key Values
1. **Compute `log_b a`:**

   ```
   log_b a = log_2 2 = 1
   ```

2. **Determine `k`:**

   - Here, `f(n) = O(m)`, which can be treated as `f(n) = O(n^0 * m)`.
   - So, `k = 0` (since `n^0 = 1`).

### Step 3: Apply the Master Theorem Cases
Now, let’s compare `log_b a` with `k`:

- **Case 1: `log_b a > k`**

  Since `log_2 2 = 1 > 0 = k`, this situation corresponds to **Case 1** of the Master Theorem.

  - According to **Case 1**, the time complexity is dominated by the `T(n/b)` part of the recurrence:

    ```
    T(n) = Theta(n^log_b a) = Theta(n^1) = Theta(n)
    ```

  - However, since `f(n) = O(m)` contributes additional complexity, the overall time complexity is:

    ```
    T(n) = O(n * m)
    ```

Here's the explanation in Markdown format without LaTeX:

---

### Understanding the Contribution of `f(n) = O(m)`

When we use the Master Theorem to solve recurrence relations like:

```
T(n) = 2T(n/2) + f(n)
```

the result depends on how the function `f(n)` compares to the other parts of the recurrence. Here, `f(n) = O(m)` is the cost of the "combine" step in the divide and conquer algorithm, where you merge the results of the two recursive calls.

### How the Master Theorem Applies Here

We computed two key values:
- `log_b a = 1`, where `b = 2` and `a = 2`.
- `k = 0`, because `f(n) = O(n^0 * m)`.

These values tell us we're in **Case 1** of the Master Theorem, where `log_b a > k`. In this case, the time complexity is dominated by the recursive calls, giving us:

```
T(n) = Theta(n^log_b a) = Theta(n)
```

### Considering the Function `f(n) = O(m)`

The Master Theorem helps us determine the complexity based on the comparison between `f(n)` and the recursive structure. Here, `f(n) = O(m)` is the additional work done at each level of recursion, which is not simply absorbed by the recursive calls.

This is because the total work at each level is not just a constant (which would be the case if `m` were a constant) but instead grows with `m`.

In the context of the `longestCommonPrefix` problem:
- `m` represents the length of the strings being compared.
- Each recursive step involves comparing up to `m` characters.

Since `f(n) = O(m)` scales with `m`, this additional complexity needs to be included in the overall time complexity.

### Why `T(n) = O(n * m)`?

1. **Recursive Calls**: The recursive part of the relation `2T(n/2)` simplifies to `O(n)`, which represents the number of levels in the recursion tree.

2. **Merge Step at Each Level**: 
- Each level of recursion involves a merge step that takes `O(m)` time. 
- Since there are `O(log n)` levels in the recursion tree, and each level potentially requires `O(m)` time for the merge step, the overall complexity involves combining both these factors.

3. **Total Complexity**: 
   - At each level of recursion, `O(m)` work is done.
   - There are `O(log n)` levels of recursion, but since `m` does not change across levels, the total time across all levels is `O(n * m)`.

Thus, even though the Master Theorem initially suggests `O(n)`, the actual time complexity becomes `O(n * m)` because the function `f(n) = O(m)` adds significant work at each level of recursion.

This is why the overall time complexity is:

```
T(n) = O(n * m)
```

Where `n` is the number of strings and `m` is the length of the strings. This product represents the total number of character comparisons needed to find the longest common prefix across all strings.

--- 

### Step 4: Consider the Entire Problem
Given that `S = m * n` represents the total number of characters in the array, and the Master Theorem has given us `T(n) = O(n * m)`, the time complexity can also be expressed as:

```
T(n) = O(S)
```

where `S` is the total number of characters in the array.

### Conclusion
The time complexity for the `longestCommonPrefix` algorithm, considering the worst-case scenario where `n` strings each have a length `m`, is:

```
O(S) = O(m * n)
```

This matches the worst-case complexity derived earlier, confirming that the total time required is proportional to the total number of characters in the input.

--- 
# DETAILS

#### A] Prepare Recurrence Relation :

Algorithm longestCommonPrefix(strs, l , r); ----------- T(n)
 lcpLeft = longestCommonPrefix(strs, l , mid); -------------- T(n/2)
 lcpRight = longestCommonPrefix(strs, mid + 1,r); ------------ T(n/2)
 commonPrefix(l,r) ------------- m}
-----------------------------------------------------------------------------------
 T(n) = 2 T(n/2) + m    Master Theorem, 

### B] Write a Recurrence Relation 

T(n) = O(m)                for n = 1
T(n) = 2T(n/2) + O(m)      for n > 1


C] 
### Conceptual Explanation of the Time Complexity:

1. **Problem Splitting**:
   - Each split divides the problem size in half, leading to a logarithmic depth of recursion (`log n`).

2. **Work Performed at Each Level**:
   - At each level, strings are compared character by character, potentially involving all characters in the shortest string (`m`).

3. **Total Work**:
   - The total work combines the number of comparisons at each level with the number of levels in the recursion. Therefore, the overall time complexity is proportional to `O(n \cdot m \cdot log n)`.

### Simplified Explanation:

- **Analogy**: 
- Imagine organizing a large group of people to find a common trait. 
- By repeatedly splitting the group in half and identifying common traits within smaller groups, you simplify the task. Finally, you combine the results from each smaller group to find the common trait for the entire group.

- **High-Level Overview**: 
- Each split simplifies the problem until you're working with individual strings. 
- Then, building back up, you use the results from these smaller tasks to efficiently solve the larger problem.

By explaining the recurrence relation alongside the divide and conquer approach, you demonstrate the efficiency of this method and 
how it effectively handles the problem by breaking it down into smaller, more manageable tasks.
