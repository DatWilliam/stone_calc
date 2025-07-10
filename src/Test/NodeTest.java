package Test;
import Main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    void test_is_cutting_finished()
    {
        assertTrue(Node.MAX_ROW_SIZE <= 10);
        byte[] cut_status = {(byte)Node.MAX_ROW_SIZE, (byte)Node.MAX_ROW_SIZE, (byte)Node.MAX_ROW_SIZE};
        DecisionNode node = new DecisionNode(cut_status);
        assertTrue(node.isFullCut());
    }

    @Test
    void test_is_cutting_not_finished()
    {
        if(Node.MAX_ROW_SIZE < 2) return;
        byte[] cut_status = {1, (byte)Node.MAX_ROW_SIZE, (byte)Node.MAX_ROW_SIZE};
        DecisionNode node = new DecisionNode(cut_status);
        assertFalse(node.isFullCut());
    }

    @Test
    void test_is_outcome_possible()
    {
        byte[] cut_status = {
                BytePacker.pack(Node.DESIRED_OUTCOME[0], 0),
                BytePacker.pack(Node.DESIRED_OUTCOME[1], 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertTrue(node.isOutcomePossible());
    }

    @Test
    void test_not_is_outcome_possible()
    {
        byte[] cut_status = {
                BytePacker.pack(0, Node.MAX_ROW_SIZE),
                BytePacker.pack(0, 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertFalse(node.isOutcomePossible());
    }

    @Test
    void test_is_goalHit_1()
    {
        byte[] cut_status = {
                BytePacker.pack(Node.MAX_ROW_SIZE, 0),
                BytePacker.pack(Node.MAX_ROW_SIZE, 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertTrue(node.isGoalHit());
    }

    @Test
    void test_is_goalHit_2()
    {
        int min = Math.min(Node.DESIRED_OUTCOME[0], Node.DESIRED_OUTCOME[1]);
        int max = Math.max(Node.DESIRED_OUTCOME[0], Node.DESIRED_OUTCOME[1]);

        byte[] cut_status = {
                BytePacker.pack(max, 0),
                BytePacker.pack(min, 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertTrue(node.isGoalHit());
    }

    @Test
    void test_is_not_desired_outcome_1()
    {
        byte[] cut_status = {
                BytePacker.pack(0, 0),
                BytePacker.pack(0, 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertFalse(node.isGoalHit());
    }

    @Test
    void test_is_not_desired_outcome_2()
    {
        int min = Math.min(Node.DESIRED_OUTCOME[0], Node.DESIRED_OUTCOME[1]);

        byte[] cut_status = {
                BytePacker.pack(min-1, 0),
                BytePacker.pack(min-1, 0),
                0};
        DecisionNode node = new DecisionNode(cut_status);
        assertFalse(node.isGoalHit());
    }

}
