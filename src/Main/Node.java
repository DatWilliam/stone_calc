package Main;

import java.util.Arrays;

public abstract class Node
{
    byte[] cut_status = new byte[4];
    public static int MAX_ROW_SIZE = 10;
    public static int[] DESIRED_OUTCOME = {7, 9};

    public Node()
    {
        cut_status[3] = BytePacker.pack(7, 5);
    }

    public Node(byte[] cut_status)
    {
        this.cut_status = cut_status;
    }

    public boolean is_finished()
    {
        return BytePacker.unpackSum(cut_status[0]) == MAX_ROW_SIZE
                && BytePacker.unpackSum(cut_status[1]) == MAX_ROW_SIZE
                && BytePacker.unpackSum(cut_status[2]) == MAX_ROW_SIZE;
    }

    public int get_hash() {
        return ((this.cut_status[0] & 0xFF) << 24) |
                ((this.cut_status[1] & 0xFF) << 16) |
                ((this.cut_status[2] & 0xFF) << 8)  |
                (this.cut_status[3] & 0xFF);
    }


    abstract void evaluate();
}

