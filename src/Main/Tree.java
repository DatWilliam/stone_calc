package Main;

import java.util.HashMap;
import java.util.Map;

public class Tree
{
    DecisionNode root = new DecisionNode();
    Map<Byte[], ProbabilityNode> map = new HashMap<Byte[], ProbabilityNode>();
    // creates the tree structure
    void generate(DecisionNode root)
    {
        if(root == null)
            return;

        root.evaluate();

        if(root.is_finished())
            return;

        for(ProbabilityNode node : root.cut_possibilities)
        {
            node.evaluate();
            generate(node.hit);
            generate(node.miss);
        }
    }

    int recurse_decision(DecisionNode node)
    {
        if(node.cut_possibilities.isEmpty())
        {
            return 1;
        }
        int sum = 0;
        for(int i = 0; i < node.cut_possibilities.size(); i++)
        {
            sum += recurse_probability(node.cut_possibilities.get(i));
        }
        return sum;
    }

    int recurse_probability(ProbabilityNode node)
    {
        return recurse_decision(node.hit) + recurse_decision(node.miss);
    }
}

