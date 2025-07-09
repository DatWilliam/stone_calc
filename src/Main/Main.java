package Main;

public class  Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.generate(tree.root);
        System.out.println(tree.recurse_decision(tree.root));
    }

}

