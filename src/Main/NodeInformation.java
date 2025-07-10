package Main;

public class NodeInformation {
    private int count;
    private float chance;
    private boolean goalHit;

    public NodeInformation(float chance, boolean goalHit) {
        this.chance = chance;
        this.goalHit = goalHit;
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count += 1;
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    public boolean isGoal() {
        return goalHit;
    }

    public void setGoalHit(boolean goalHit) {
        this.goalHit = goalHit;
    }

    public String toString() {
        return "Count: " + this.count + ", Chance: " + this.chance + ", Goal Reached: " + this.goalHit;
    }

}
