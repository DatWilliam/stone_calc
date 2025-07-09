package Main;

import java.util.Arrays;

public class ProbabilityNode extends Node
{
    DecisionNode hit;
    DecisionNode miss;
    byte row_id; // store id 0-2 in b

    public ProbabilityNode(byte chance, byte[] rows, byte row_id) { super(chance, rows); this.row_id = row_id; }

    @Override
    void evaluate() {
        hit = new DecisionNode(BytePacker.decreasePercent(chance), calc_cut_status(0));
        miss = new DecisionNode(BytePacker.increasePercent(chance), calc_cut_status(1));
    }

    // i = 0 -> increment hit, i = 1 -> increment miss
    byte[] calc_cut_status(int i)
    {
        byte[] tmp = Arrays.copyOf(cut_status, cut_status.length);
        if(i == 0)
            tmp[BytePacker.unpackB(row_id)] = BytePacker.incrementA(cut_status[BytePacker.unpackB(row_id)]);
        else
            tmp[BytePacker.unpackB(row_id)] = BytePacker.incrementB(cut_status[BytePacker.unpackB(row_id)]);
        return tmp;
    }

}
