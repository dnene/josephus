def find_last(count=40, nth=3) {
    def people = 1 .. count
    def counter = 0
    while (people.size() > 1) {
        def new_people = []
        for(i in 0 .. people.size() - 1) {
            if ((i + counter) % nth != 0) {
                new_people.add(people.get(i))
            }
        }
        counter = (counter + people.size()) % nth
        people = new_people
    }
    return people.get(0)
}

println find_last()
def ITER = 100000
for(def i = 0 ; i < ITER ; i++)
    find_last()
def start = System.nanoTime()
for(def i = 0 ; i < ITER ; i++)
    find_last()
def end = System.nanoTime()
println "Time per iteration = " + ((end - start)/(ITER * 1000)) + " microseconds"
