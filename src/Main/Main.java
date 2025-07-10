package Main;
public class  Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.generate(tree.root);
        System.out.println(tree.cache.size());
        tree.traverse(tree.root, 1);
        /*for(Map.Entry<Integer, NodeInformation> entry : tree.nodeInfoMap.entrySet())
        {
            System.out.println(entry.getValue().toString());
        }*/
        System.out.println(tree.floatToString(tree.getChance()));
    }

}

