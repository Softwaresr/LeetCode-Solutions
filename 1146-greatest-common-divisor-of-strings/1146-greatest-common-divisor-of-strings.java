// class Solution {
//     public String gcdOfStrings(String str1, String str2) {
//         if(str1 + str2).equals(str2 + str1){
//             int ans = gcd(str1.length() , str2.length());
            
//         }return str1.substring(0,ans);
//         else{
//             return "";


//         }

        
//     }
//     int gcd(int a, intb){
//         while(a%b!=0){
//             int r=a/b;
//             b=a;
//             a=r;
//          }
//          return b;
//     }
// }

class Solution {
  public String gcdOfStrings(String str1, String str2) {
    if (str1.length() < str2.length())
      return gcdOfStrings(str2, str1);
    if (!str1.startsWith(str2))
      return "";
    if (str2.isEmpty())
      return str1;
    return gcdOfStrings(str2, mod(str1, str2));
  }

  private String mod(String s1, final String s2) {
    while (s1.startsWith(s2))
      s1 = s1.substring(s2.length());
    return s1;
  }
}