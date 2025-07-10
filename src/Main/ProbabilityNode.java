package Main;

import java.util.Arrays;
import static Main.BytePacker.*;
/*
    this object covers the nodes for both the hit and the miss scenario
    further it has the information in which row the cut was made
 */
public class ProbabilityNode extends Node
{
    protected DecisionNode hit;
    protected DecisionNode miss;
    protected byte rowId; // store id 0-2 in b

    public ProbabilityNode(byte[] cut_status, byte rowId) { super(cut_status); this.rowId = rowId; }

    @Override
    void evaluate() {
        hit = new DecisionNode(calcCutStatus(0));
        miss = new DecisionNode(calcCutStatus(1));
    }

    byte[] calcCutStatus(int i) // 0 -> hit & 1 -> miss
    {
        byte[] copy = Arrays.copyOf(cutStatus, cutStatus.length);
        if(i == 0) {
            copy[getRowId()] = incrementA(cutStatus[getRowId()]);
            copy[3] = decreasePercent(copy[3]);
        }
        else {
            copy[getRowId()] = incrementB(cutStatus[getRowId()]);
            copy[3] = increasePercent(copy[3]);
        }
        return copy;
    }

    private int getRowId() {
        return unpackB(rowId);
    }

}
