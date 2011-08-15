class Josephus
    def initialize(count,nth)
        @count = count
        @nth = nth
    end
    def shout()
        shout_recursive((1..@count).to_a, @nth, 0)
    end
    def shout_recursive(people,nth,counter)
        if people.size > 1
            if counter == 0
                people.shift
                counter = counter + 1
            else
                if counter == nth - 1
                    counter = 0
                else
                    counter = counter + 1
                end
                people.rotate!
            end
            return shout_recursive(people,nth,counter)
        else
            return people[0]
        end
    end
end

j = Josephus.new(40,3)
puts j.shout()
ITER=100000
ITER.times { |i|
    j.shout()
}
start = Time.now
ITER.times { |i|
    j.shout()
}
ends = Time.now
puts 'Time per iteration = ' + ((ends - start) * 1000000 / ITER).to_s() + " microseconds"
