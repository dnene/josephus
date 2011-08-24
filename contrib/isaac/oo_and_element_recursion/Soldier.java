final class Soldier {

   private int number = 0;
   private Soldier previous, next;

   Soldier(Soldier previousSoldier, int number) 
   {
      this.number = number;

      if (previousSoldier != null){

         next = previousSoldier.next;
         previousSoldier.next = this;

         previous = previousSoldier;
         next.previous = this;

      } else {
         next = this;
         previous = this;
      }
   }


   int countoff(Shoutout shout)
   {
      if (next != this){

         if (shout.wasKth()) {
            previous.next = next;
            next.previous = previous;
         } 
         return next.countoff(shout);

      } else {
         return number;
      }
   } 


   static int countoffSoldiers(int n, int kth){
      Soldier soldier = null;
      for (int i = 1; i <= n; i++) soldier = new Soldier(soldier,i); 

      soldier = soldier.next; // set to the first Soldier constructed

      return soldier.countoff( new Shoutout(kth) );
   }
}

