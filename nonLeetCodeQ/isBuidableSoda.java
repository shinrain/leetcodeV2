/*
A soda water machine,press button A can generate 300-310ml, button B can generate 400-420ml and button C can generate 
500-515ml, then given a number range [min, max], tell if all the numers of water in the range can be generated. 

range DP.??
*/

public static boolean isBuidable(int low, int high, int min, int max) {
    
    if(low <= min && max <= high)
      return true;
    
    if(low > min) 
      return false;
    
    return isBuidable(low+300, high+310, min, max) ||
         isBuidable(low+400, high+420, min, max) ||
         isBuidable(low+500, high+515, min, max);
  }