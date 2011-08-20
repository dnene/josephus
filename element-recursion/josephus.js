function array_init(size) {
    people = []
    for (var i = 0; i < size; i++) {
        people[i] = i + 1;
    }

    return people
} 

function shout(people, nth, counter) {
    if (people.length == 1) {
        return people[0]
    } else {
        if (counter == 0) {
            people.shift()
            counter = counter + 1
        } else {
            if (counter == nth - 1) {
                counter = 0
            } else {
                counter = counter + 1
            }
            val = people.shift()
            people.push(val)
        }
        return shout(people,nth,counter)
    }

}

function josephus(size, nth) {
    people = array_init(size)
    return shout(people, nth, 0);
}

function run_iterations(iterations, times) {
    for (var t = 0 ; t < times ; t++) {
        var start = new Date()
        for(var i = 0 ; i < iterations ; i++) {
            josephus(40,3)
        }
        var end = new Date()
        print ((end.getTime() - start.getTime()) * 1000 / ITERS)
    }
}

print(josephus(40,3))
ITERS = 1000000
print ("element recursion")
run_iterations(ITERS,10)
