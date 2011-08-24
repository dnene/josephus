final class Shoutout {

   private int k = 1, kth;

   //Shoutout(int n) { kth = n; }   // standard Josephus problem
   Shoutout(int n) { kth = n; k = n; }  

   boolean wasKth(){ 
      boolean wasKth = k == kth;
      if (wasKth) k = 1; else k++;
      return wasKth;
   }
}


