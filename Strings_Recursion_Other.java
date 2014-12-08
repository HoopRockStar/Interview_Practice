/*
* We'll say that a lowercase 'g' in a string is "happy" if there is another 'g' immediately to its left or right. 
* Return true if all the g's in the given string are happy.
*
* gHappy("xxggxx") → true
* gHappy("xxgxx") → false
* gHappy("xxggyygxx") → false
*/

public class Strings_Recursion_Other {

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

public int longestSubsequence(int[] arr) {
   int[] subsequences = new int[arr.length];
   
   for (int i =0; i < subsequences.length; i++) {
   		subsequences[i] = 1;
   }
   int curr_value;
   
   for (int i =0; i < arr.length-1; i++) {
   		curr_value = arr[i];
   		for (int j=i+1; j< arr.length; j++) {
   			if (curr_value < arr[j]) {
   			   subsequences[i] = subsequences[i]+1;
   			   curr_value =  arr[j];
   			}
   		}
   }
   
   int max = 0;
   for (int i = 0; i < subsequences.length; i++) {
   		if (max < subsequences[i])
   		    max = subsequences[i];
   }
   
   return max;

}

public static void main(String[] args) {
	Strings_Recursion_Other s = new Strings_Recursion_Other();
	int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
	System.out.println(s.longestSubsequence(arr));

}

}
