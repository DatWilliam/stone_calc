package Main;

public class  Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.generate(tree.root);
        System.out.println(tree.map.size());
        tree.recurse_count(tree.root);
        int sum = 0;
        for (int value : tree.count_map.values()) {
            sum += value;
        }
        System.out.println(sum);
    }

}

