// TWO POINTER APPROACH

class Solution {
    public int romanToInt(String s) {
       // Step 1: Initialize a hashmap to store Roman numeral symbols and their integer values
       HashMap <Character,Integer> romanMap = new HashMap<>();
       romanMap.put('I',1);
       romanMap.put('V',5);
       romanMap.put('X',10);
       romanMap.put('L',50);
       romanMap.put('C',100);
       romanMap.put('D',500);
       romanMap.put('M',1000);
       
       // Step 2: Convert the input string into a character array
       char[] symbols = s.toCharArray();

       // Step 3: Initialize variables to store the final integer value and pointers i and j
       int result =0;
       int i=0, j=1;

       // Step 4: Iterate through the symbols array
       while(i<symbols.length){
             // Step 5: Check for subtraction case
           if(j<symbols.length && romanMap.get(symbols[i])<romanMap.get(symbols[j])){
               result += romanMap.get(symbols[j])-romanMap.get(symbols[i]);
               i+=2;
               j+=2;
           }else{
              // Step 6: Addition case
               result+=romanMap.get(symbols[i]);
               i++;
               j++;
           }
       }
        // Step 7: Return the final integer value
       return result;
  
 }

}

// Time Complexity : O(n)
// Space Complexity : O(n)
