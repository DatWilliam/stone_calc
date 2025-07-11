package Main;
import java.util.HashMap;
import java.util.Map;

public class Tree
{
    DecisionNode root = new DecisionNode();
    Map<Integer, DecisionNode> cache = new HashMap<Integer, DecisionNode>();

    public Tree()
    {
        generate(root);
    }

    private void generate(DecisionNode root)
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

    Map<Integer, Float> chanceCache = new HashMap<>();
    float traverse(DecisionNode node)
    {
        if(chanceCache.containsKey(node.getHash())) return chanceCache.get(node.getHash());
        if(node.isGoalHitOrBetter()) return 1;

        float maxChance = 0;

        for(ProbabilityNode curr : node.cutPaths)
        {
            float chanceAfterHit = traverse(curr.hit);
            float chanceAfterMiss = traverse(curr.miss);

            float cutProbability = BytePacker.toFloat(node.cutStatus[3]);
            float weightedHit = chanceAfterHit * cutProbability;
            float weightedMiss = chanceAfterMiss * (1-cutProbability);

            maxChance = Math.max(maxChance, weightedHit+weightedMiss);
        }
        chanceCache.put(node.getHash(), maxChance);
        return maxChance;
    }

    public String floatToString(float value) {
        float precent = value * 100;
        return String.format("%." + 4 + "f%%", precent);
    }
}

