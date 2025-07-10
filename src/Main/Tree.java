package Main;

import java.util.HashMap;
import java.util.Map;

public class Tree
{
    DecisionNode root = new DecisionNode();
    Map<Integer, DecisionNode> cache = new HashMap<Integer, DecisionNode>();
    Map<Integer, NodeInformation> nodeInfoMap = new HashMap<Integer, NodeInformation>(); //tuple 0->count, 1->chance, 2->is_desired

    void generate(DecisionNode root)
    {
        if(root == null)
            return;

        root.evaluate();

        if(root.isFullCut())
            return;

        for(ProbabilityNode node : root.cutPaths)
        {
            node.evaluate();

            // if the path after hit doesn't exist create it and explore it
            if (cache.get(node.hit.getHash()) == null) {
                cache.put(node.hit.getHash(), node.hit);
                generate(node.hit);
            }
            else // if it exists reference it
                node.hit = cache.get(node.hit.getHash());

            // same for miss
            if (cache.get(node.miss.getHash()) == null) {
                cache.put(node.miss.getHash(), node.miss);
                generate(node.miss);
            }
            else node.miss =  cache.get(node.miss.getHash());
        }
    }

    void traverse(DecisionNode node, float cumulative_chance)
    {
        NodeInformation info = new NodeInformation(cumulative_chance, node.isGoalHit());
        nodeInfoMap.put(node.getHash(), info);

        if(node.cutPaths.isEmpty())
            return;

        for(ProbabilityNode curr :  node.cutPaths)
        {
            if(nodeInfoMap.get(curr.hit.getHash()) == null) // pattern does not exist until now
                traverse(curr.hit, cumulative_chance * BytePacker.toFloat(curr.cutStatus[3]));
            else // pattern does exist -> increase counter
            {
                NodeInformation updated = nodeInfoMap.get(curr.hit.getHash());
                updated.incrementCount();
                nodeInfoMap.put(curr.hit.getHash(), updated);
            }

            if(nodeInfoMap.get(curr.miss.getHash()) == null)
                traverse(curr.miss, cumulative_chance * (1-BytePacker.toFloat(curr.cutStatus[3])));
            else
            {
                NodeInformation updated = nodeInfoMap.get(curr.miss.getHash());
                updated.incrementCount();
                nodeInfoMap.put(curr.miss.getHash(), updated);
            }
        }
    }

    // sum up all the chances of the nodes that reached the goal
    float getChance()
    {
        float sum = 0;
        for (NodeInformation info : this.nodeInfoMap.values()) {
            if(info.isGoal())
            {
                for(int i = 0; i < info.getCount(); i++)
                    sum += info.getChance();
            }
        }
        return sum;
    }

    public String floatToString(float value) {
        float precent = value * 100;
        return String.format("%." + 4 + "f%%", precent);
    }
}

