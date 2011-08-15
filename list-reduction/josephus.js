function array_init(size) {
    people = []
    for (var i = 0; i < size; i++) {
        people[i] = i + 1;
    }

    return people
} 

function shout(people, nth, counter) {
    new_people = []
    if (people.length == 1) {
        return people[0]
    } else {
        for (var i = 0 ; i < people.length ; i++) {
            if ((i + counter) % nth != 0) {
                new_people.push(people[i])
            }
        }
        counter = (counter + people.length) % nth
        return shout(new_people, nth, counter)
    }
}

function josephus(size, nth) {
    people = array_init(size)
    return shout(people, nth, 0);
}
print(josephus(40,3))
ITERS = 1000000
for(var i = 0 ; i < ITERS ; i++)
    josephus(40,3)
var start = new Date()
for(var i = 0 ; i < ITERS ; i++)
    josephus(40,3)
var end = new Date()
var tmp = new Date()
print ("Time taken :" + (end.getTime() - start.getTime()) * 1000 / ITERS + " microseconds (list reduction)")
