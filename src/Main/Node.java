package Main;
public abstract class Node
{
    byte chance;
    byte[] cut_status = new byte[3];
    public static int MAX_ROW_SIZE = 5;
    public static int[] DESIRED_OUTCOME = {4, 4};

    public Node()
    {
        chance = BytePacker.pack(7, 5);
    }

    public Node(byte chance, byte[] cut_status)
    {
        this.chance = chance;
        this.cut_status = cut_status;
    }

    public boolean is_finished()
    {
        return BytePacker.unpackSum(cut_status[0]) == MAX_ROW_SIZE
                && BytePacker.unpackSum(cut_status[1]) == MAX_ROW_SIZE
                && BytePacker.unpackSum(cut_status[2]) == MAX_ROW_SIZE;
    }

    abstract void evaluate();
}

