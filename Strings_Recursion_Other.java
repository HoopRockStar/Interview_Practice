/*
* We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' immediately to its left or right. 
* Return true if all the g's in the given string are happy.
*
* gHappy("xxggxx") → true
* gHappy("xxgxx") → false
* gHappy("xxggyygxx") → false
*/

public boolean gHappy(String str) {
  int counter = 0;
  char c = 0;
  
  while (counter < str.length()) {
     c = str.charAt(counter);
     if (c=='g') {
        if (counter > 0 && counter < str.length()-1) {
           if (!(str.charAt(counter-1)=='g' || str.charAt(counter+1)=='g'))
               return false;
        } else if (counter < 0 && counter < str.length()-1) {
           if (str.charAt(counter+1) != 'g')
              return false;
        } else if (counter > 0) {
           if (str.charAt(counter-1) != 'g') {
              return false;
           }
        } else if (str.length() == 1 && c =='g') {
           return false;
        }
     }
     counter++;
  
  }
  
  return true;
  
}

/*
* Given a non-negative int n, compute recursively (no loops) the count of the occurrences of 8 as a digit,
* except that an 8 with another 8 immediately to its left counts double, so 8818 yields 4.
*/

public int count8(int n) {
  return count8(n, false);
}

public int count8(int n, boolean last_digit) {
  if (n==0) 
     return 0;
  if (n % 10 == 8) {
    if (last_digit)
       return 2 + count8(n/10, true);
    else
       return 1 + count8(n/10, true);
  } else {
    return 0 + count8(n/10, false);
  }

}
