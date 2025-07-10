package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    this object contains all possible decisions that are legal for the turn
    and therefore excludes rows that are fully cut
 */
public class DecisionNode extends Node
{
    protected List<ProbabilityNode> cutPaths =  new ArrayList<ProbabilityNode>();

    public DecisionNode() {super(); }
    public DecisionNode(byte[] cut_status) { super(cut_status); }

    @Override
    void evaluate()
    {
       if(!isOutcomePossible())
            return;

       for(int i = 0; i < 3; i++)
       {
           if(BytePacker.unpackSum(cutStatus[i]) < MAX_ROW_SIZE)
               cutPaths.add(new ProbabilityNode(Arrays.copyOf(cutStatus, cutStatus.length), BytePacker.pack(0, i)));
       }

    }

    public boolean isOutcomePossible()
    {
        int low = Math.min(DESIRED_OUTCOME[0], DESIRED_OUTCOME[1]);
        int high = Math.max(DESIRED_OUTCOME[0], DESIRED_OUTCOME[1]);

        return getRowMisses(cutStatus[0]) <= MAX_ROW_SIZE - low
                && getRowMisses(cutStatus[1]) <= MAX_ROW_SIZE - low
                && (getRowMisses(cutStatus[0]) <= MAX_ROW_SIZE - high || getRowMisses(cutStatus[1]) <= MAX_ROW_SIZE - high);
    }



}
