package Main;

import java.util.HashMap;
import java.util.Map;

public class Tree
{
    DecisionNode root = new DecisionNode();
    Map<Integer, DecisionNode> map = new HashMap<Integer, DecisionNode>();
    Map<Integer, Integer> count_map = new HashMap<Integer, Integer>();
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

            if (map.get(node.hit.get_hash()) == null) {
                map.put(node.hit.get_hash(), node.hit);
                generate(node.hit);
            }
            else node.hit = map.get(node.hit.get_hash());

            if (map.get(node.miss.get_hash()) == null) {
                map.put(node.miss.get_hash(), node.miss);
                generate(node.miss);
            }
            else node.miss =  map.get(node.miss.get_hash());
        }
    }

    void recurse_count(DecisionNode node)
    {
        count_map.put(node.get_hash(), 1);
        if(node.cut_possibilities.isEmpty())
        {
            return;
        }

        for(int i = 0; i < node.cut_possibilities.size(); i++)
        {
            if(count_map.get(node.cut_possibilities.get(i).hit.get_hash()) == null)
            {
                recurse_count(node.cut_possibilities.get(i).hit);
            }
            else
            {
                count_map.put(node.cut_possibilities.get(i).hit.get_hash(), count_map.get(node.cut_possibilities.get(i).hit.get_hash())+1);
            }

            if(count_map.get(node.cut_possibilities.get(i).miss.get_hash()) == null)
            {
                recurse_count(node.cut_possibilities.get(i).miss);
            }
            else
            {
                count_map.put(node.cut_possibilities.get(i).miss.get_hash(), count_map.get(node.cut_possibilities.get(i).miss.get_hash())+1);
            }
        }
    }
}

