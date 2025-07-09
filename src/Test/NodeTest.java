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
        DecisionNode node = new DecisionNode(BytePacker.pack(7,5), cut_status);
        assertTrue(node.is_finished());
    }

    @Test
    void test_is_cutting_not_finished()
    {
        if(Node.MAX_ROW_SIZE < 2) return;
        byte[] cut_status = {1, (byte)Node.MAX_ROW_SIZE, (byte)Node.MAX_ROW_SIZE};
        DecisionNode node = new DecisionNode(BytePacker.pack(7,5), cut_status);
        assertFalse(node.is_finished());
    }

    @Test
    void test_is_outcome_possible()
    {
        byte[] cut_status = {
                BytePacker.pack(Node.DESIRED_OUTCOME[0], 0),
                BytePacker.pack(Node.DESIRED_OUTCOME[1], 0),
                0};
        DecisionNode node = new DecisionNode(BytePacker.pack(7,5), cut_status);
        assertTrue(node.is_outcome_possible());
    }

    @Test
    void test_not_is_outcome_possible()
    {
        byte[] cut_status = {
                BytePacker.pack(0, Node.MAX_ROW_SIZE),
                BytePacker.pack(0, 0),
                0};
        DecisionNode node = new DecisionNode(BytePacker.pack(7,5), cut_status);
        assertFalse(node.is_outcome_possible());
    }

}
