/*
$ /usr/local/src/v8/d8 josephus.js
26
list reduction array
1.232
1.217
1.217
1.224
1.22
1.227
1.224
1.226
1.219
1.217
*/

function countoffSoldiers(n,kth) {
   var soldiers = []
   for (var i = 0; i < n; i++) soldiers[i] = i+1

   //var k = kth-1, survivedLastRound = n; // standard Josephus problem
   var k = 0, survivedLastRound = n

   while (survivedLastRound > 1) {
      var survived = 0

      for (var i = 0 ; i < survivedLastRound; i++)
         if (i != k) 
            soldiers[survived++] = soldiers[i]
         else
            k += kth

         k -= survivedLastRound // wrap around
         survivedLastRound = survived
      }

      return soldiers[0] 
} 


//-----------------------------------------------------------------------------

function run_iterations(iterations, times) {
    for (var t = 0 ; t < times ; t++) {
        var start = new Date()
        for(var i = 0 ; i < iterations ; i++) {
            countoffSoldiers(40,3)
        }
        var end = new Date()
        print ((end.getTime() - start.getTime()) * 1000 / ITERS)
    }
}

print(countoffSoldiers(40,3))
ITERS = 1000000
print ("list reduction array")
run_iterations(ITERS,10)
