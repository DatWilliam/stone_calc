package Main;

import static Main.BytePacker.*;

public abstract class Node
{
    protected byte[] cutStatus = new byte[4];
    public static int MAX_ROW_SIZE = 10;
    public static int[] DESIRED_OUTCOME = {9, 7};

    public Node()
    {
        cutStatus[3] = BytePacker.pack(7, 5);
    }

    public Node(byte[] cutStatus)
    {
        this.cutStatus = cutStatus;
    }

    public boolean isGoalHit()
    {
        return (getRowHits(cutStatus[0]) == DESIRED_OUTCOME[0]
                && getRowHits(cutStatus[1]) == DESIRED_OUTCOME[1])
                || (getRowHits(cutStatus[0])== DESIRED_OUTCOME[1]
                && getRowHits(cutStatus[1]) == DESIRED_OUTCOME[0]);

    }

    public boolean isGoalHitOrBetter()
    {
        return (getRowHits(cutStatus[0]) >= DESIRED_OUTCOME[0]
                && getRowHits(cutStatus[1]) >= DESIRED_OUTCOME[1])
                || (getRowHits(cutStatus[0])>= DESIRED_OUTCOME[1]
                && getRowHits(cutStatus[1]) >= DESIRED_OUTCOME[0]);

    }

    public boolean isFullCut()
    {
        return unpackSum(cutStatus[0]) == MAX_ROW_SIZE
                && unpackSum(cutStatus[1]) == MAX_ROW_SIZE
                && unpackSum(cutStatus[2]) == MAX_ROW_SIZE;
    }

    public int getHash() {
        return ((this.cutStatus[0] & 0xFF) << 24) |
                ((this.cutStatus[1] & 0xFF) << 16) |
                ((this.cutStatus[2] & 0xFF) << 8)  |
                (this.cutStatus[3] & 0xFF);
    }

    public int getRowHits(Byte row) {
        return unpackA(row);
    }

    public int getRowMisses(Byte row) {
        return unpackB(row);
    }

    abstract void evaluate();
}

