import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Chain {
	private int count;
	private int nth;
	public Chain(int count, int nth) {
		this.count = count;
		this.nth = nth;
	}
	
	public int shout() {
		List<Integer> people = new ArrayList<Integer>();
		for (int i = 0 ; i < this.count ; i++) {
			people.add(i+1);
		}
		int counter = 0;
		while(people.size() > 1) {
			List<Integer> newPeople = new ArrayList<Integer>();
			for(int i = 0 ; i < people.size() ; i++) {
				if ((counter + i) % this.nth != 0) {
					newPeople.add(people.get(i));
				}
			}
			counter = ((counter + people.size()) % this.nth);
			people = newPeople;
		}
		return people.get(0);
	}
	
	public int shoutRecursive() {
		List<Integer> people = new LinkedList<Integer>();
		for (int i = 0 ; i < this.count ; i++) {
			people.add(i+1);
		}
		return shoutRecursive(people, new LinkedList<Integer>(), this.nth, 0);
	}
	
	public int shoutRecursive(List<Integer> people, List<Integer> survivors, int nth, int counter) {
		if ((survivors.size() == 0) && (people.size() == 1)) {
			return people.get(0);
		} else if (people.size() == 0){
			return shoutRecursive(survivors, new LinkedList<Integer>(), nth, counter);
		} else {
			int v = people.remove(0);
			if (counter != 0) {
				survivors.add(v);
			}
			if (counter == nth - 1) counter = 0;
			else counter = counter + 1;
			return shoutRecursive(people, survivors, nth, counter);
		}
	}
	
	public static void main(String[] args) {
		Chain chain = new Chain(40,3);
        System.out.println(chain.shoutRecursive());
        int ITER = 1000000;
        for (int i = 0 ; i < ITER ; i++)
        {
            chain.shoutRecursive();
        }
        long start = System.nanoTime();
        for (int i = 0 ; i < ITER ; i++)
        {
            chain.shoutRecursive();
        }
        long end = System.nanoTime();
        System.out.println("Time per iteration (element recursive) = " + ((end - start) / (ITER )) + " nanoseconds.");

        System.out.println(chain.shout());
		
        for (int i = 0 ; i < ITER ; i++)
        {
            chain.shout();
        }
        start = System.nanoTime();
        for (int i = 0 ; i < ITER ; i++)
        {
            chain.shout();
        }
        end = System.nanoTime();
        System.out.println("Time per iteration (list reduction) = " + ((end - start) / (ITER )) + " nanoseconds.");
		
	}
}
