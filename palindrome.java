class Solution {
    public boolean isPalindrome(int x) {
        // base case 
        if (x < 0) {
            return false;
        }
        if (x % 10 == 0 && x != 0) {
            return false;
        }

        // Approach 1 ( using 2 pointers)

        /*String strX = Integer.toString(x);
        int left = 0;
        int right = strX.length() - 1;

        while (left < right) {

            if (strX.charAt(left) != strX.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;*/


      

        /* Approach 2 ( Using StringBuilder's inbuilt Reverse API)
        String original = String.valueOf(x);
        String reversed = new StringBuilder(original).reverse().toString();

        return original.equals(reversed); */

        /* Approach 3 ( Reverse second half of the string and compare with first half) */

        int reverse =0;

        while(x>reverse){
            int lastDigit = x%10;

            reverse = reverse*10 + lastDigit;
            x= x/10;
        }
        return (x==reverse ||  x== reverse/10);
    }
}
/* APPROACH 1 (USING 2 Pointers)
 Time Complexity:
  
 1] Converting Integer to String: O(log10 n), where n is the value of the integer.
 
  Example : for 1000 number
  log10(1000) ≈ 3
  Converting 1000 to a string involves three digits, and the logarithm base 10 correctly captures this information.
 
 2] Character Comparison with Pointers:As we traverse half of the string. Time complexity should be n/2
  Since we have integer here we can use O(log10 n)/2
  The overall time complexity is O(log10 n + (log10 n)/2), but in big-O notation, we drop constants and simplify to O(log10 n)
  ======================
 Space Complexity:
 
  1] String Conversion: O(log10 n) for storing the string representation of the integer.
  2] String Reversal: O(1) - No additional space is used for storing the  reversed string explicitly.
  
 The overall space complexity is O(log10 n).
 
 */

/*
 Example walk through
  Input: 121
 
 1] 121 is not negative and nor it ends with 0 so we can proceed
 2] String Conversion: Convert 121 to a string, resulting in strX = "121". 
 3] Pointers Initialization: Set left to 0 (start of the string) and right to  2 (end of the string).
  4] While Loop:
  Iteration 1:
  left =0 and right = 2
  We first check the loop condition which is left < right ( 0<2 )
  Then we check character matching
  Characters at left (1st position i.e 0) and right (last position i.e 2) match, so we move the pointers (left++, right--).
 
  Iteration 2: 
  left =1 and right = 1
  We first check the loop condition which is left < right ( 1<1 ) which is false
  Loop completes, and we return true since all character comparisons were successful.
  */

/*
APRROACH 2 ( Using StringBuilder Class)
Time Complexity:
1] Converting Integer to String: O(log10 n), where n is the value of the integer. 
This is because the number of digits in the integer is proportional to log n.
2] String Reversal: O(log10 n) - Reversing a string involves iterating through its characters, and 
the length of the string is proportional to log10 n the number of digits in the integer.

The overall time complexity is O(log10 n).

Space Complexity:
1] String Conversion: O(log10 n) for storing the string representation of the integer.
2] String Reversal: O(log10 n) for storing the reversed string.

The overall space complexity is O(log10 n).

*/

/* Approach 3 example walkthrough

For example, with the input 12321, we start with 
x = 12321. 
reverse =0 
A] First iteration, 
check the while loop condition ( x> reverse)  12321>0    ---> yes 
we get the last digit = 1 ( 12321 % 10)   ( x %10)
add it to the reversed number (reverse * 10 + last digit = 0*10 + 1 = 1)
Then, we update x to be 1232  (x = 12321/10 = 1232)   and rev =1 

B]  Second iteration, 
check the while loop condition ( x> reverse)  1232>1   ---> yes 
we get the last digit = 2 ( 1232 % 10)   ( x %10)
add it to the reversed number (reverse * 10 + last digit = 1*10 + 2 = 12)
Then, we update x to be 123.  (x = 1232/10 = 123) and   rev =12 

B]  Third iteration, 
check the while loop condition ( x> reverse)  123>12    ---> yes 
we get the last digit = 3( 123 % 10)  ( x %10)
add it to the reversed number (reverse * 10 + last digit = 12*10 + 3 = 123)
Then, we update x to be 12.  (x = 123/10 = 12)  and  rev =123  

B]  Fourth iteration, 
check the while loop condition ( x> reverse)  12>123    ---> no
break the loop

For odd-length numbers, the reversed second half has an extra digit. So, we need to remove the last digit from the reversed second half before comparison.
reverse = reverse/ 10 which 123/10 = 12

x == reverse --- return true 

For even-length numbers, the comparison is straightforward.  Example : input integer =1221.
If the first half is 12 and the reversed second half is also 12, it's a palindrome.

*/



/* Apprach 3
Time Complexity : 0(log10 n)

Space Complexity : 0(1) OR O(log10n)/2
*/
