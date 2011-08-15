public class Person
{
    int count;
    private Person prev = null;
    private Person next = null;

    public Person(int count)
    {
        this.count = count + 1;
    }

    public int shout(int shout, int nth)
    {
        if (shout == 1) {
            this.getPrev().setNext(this.getNext());
            this.getNext().setPrev(this.getPrev());
            return 2;
        }
        else if (shout == nth) {
            return 1;
        } else {
            return shout + 1;
        }
    }

    public int getCount()
    {
        return this.count;
    }

    public Person getPrev()
    {
        return prev;
    }

    public void setPrev(Person prev)
    {
        this.prev = prev;
    }

    public Person getNext()
    {
        return next;
    }

    public void setNext(Person next)

    {
        this.next = next;
    }
}
