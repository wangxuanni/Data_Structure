package MyTree;

public class BTNode {
    //二叉决策树
    private String data;
    private BTNode left;
    private BTNode right;

    public BTNode(String data, BTNode left, BTNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) {
        BTNode root;
        BTNode child;

        root = new BTNode("生物", null, null);
        child = new BTNode("动物", null, null);
        child.setRight(new BTNode("哺乳动物", null, null));
        child.setLeft(new BTNode("冷血动物", null, null));
        root.setLeft(child);

        child = new BTNode("植物", null, null);
        child.setRight(new BTNode("花", null, null));
        child.setLeft(new BTNode("树", null, null));
        root.setRight(child);


        System.out.println(root.getLeftmostData());
        root.removeLeftmost();
        System.out.println(root.getLeftmostData());

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BTNode getLeft() {
        return left;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }

    public String getLeftmostData() {
        if (left == null)
            return data;
        else
            return left.getLeftmostData();
    }

    public String getRightmostData() {
        if (right == null)
            return data;
        else
            return right.getRightmostData();
    }

    public boolean isLeaf() {
        if (left == null && right == null) {
            return true;
        } else {
            return false;
        }
    }

    public BTNode removeLeftmost() {
        if (left == null)
            return right;
        else
            left = left.removeLeftmost();
        return this;
    }
}
