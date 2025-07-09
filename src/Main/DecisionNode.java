package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecisionNode extends Node
{
    List<ProbabilityNode> cut_possibilities =  new ArrayList<ProbabilityNode>();

    public DecisionNode() {super(); }
    public DecisionNode(byte chance, byte[] cut_status) { super(chance, cut_status); }

    // checks which cuts are possible and make sense
    @Override
    void evaluate()
    {

       //if(!is_outcome_possible())
            //return;

       for(int i = 0; i < 3; i++)
       {
           if(BytePacker.unpackSum(cut_status[i]) < MAX_ROW_SIZE)
           {
               cut_possibilities.add(new ProbabilityNode(chance, Arrays.copyOf(cut_status, cut_status.length), BytePacker.pack(0, i)));
           }
       }

    }

    // checks if the desired outcome is possible
    public boolean is_outcome_possible()
    {
        int min = Math.min(DESIRED_OUTCOME[0], DESIRED_OUTCOME[1]);
        int max = Math.max(DESIRED_OUTCOME[0], DESIRED_OUTCOME[1]);

        return BytePacker.unpackB(cut_status[0]) <= MAX_ROW_SIZE - min
                && BytePacker.unpackB(cut_status[1]) <= MAX_ROW_SIZE - min
                && (BytePacker.unpackB(cut_status[0]) <= MAX_ROW_SIZE - max || BytePacker.unpackB(cut_status[1]) <= MAX_ROW_SIZE - max);
    }

}
