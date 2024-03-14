class Solution {
    public int romanToInt(String s) {
       HashMap <Character,Integer> romanMap = new HashMap<>();
       romanMap.put('I',1);
       romanMap.put('V',5);
       romanMap.put('X',10);
       romanMap.put('L',50);
       romanMap.put('C',100);
       romanMap.put('D',500);
       romanMap.put('M',1000);

       char[] symbols = s.toCharArray();
       int result =0;
       int i=0, j=1;

       while(i<symbols.length){
           if(j<symbols.length && romanMap.get(symbols[i])<romanMap.get(symbols[j])){
               result += romanMap.get(symbols[j])-romanMap.get(symbols[i]);
               i+=2;
               j+=2;
           }else{
               result+=romanMap.get(symbols[i]);
               i++;
               j++;
           }
       }
       return result;
  
 }

}
