class Node
{
    int data;
    Node left, right;
    int balance;
}

class Tree
{
    private Node createNewNode(int k)
    {
        Node a = new Node();
        a.data=k;
        a.left=a.right=null;
        a.balance=0;
        return a;
    }

    protected int getHeight(Node node)
    {
        if (node == null) {return 0;}

        return Math.max(getHeight(node.right), getHeight(node.left))+1;
    }

    private void updateBalance(Node node)
    {
        node.balance = getHeight(node.right) - getHeight(node.left);
    }

    private Node balanceLeft(Node node)
    {
        if (node == null) {return node;}

        Node nRoot = node.right;
        node.right = nRoot.left;
        nRoot.left = node;

        return nRoot;
    }

    private Node balanceRight(Node node)
    {
        if (node == null) {return node;}

        Node nRoot = node.left;
        node.left = nRoot.right;
        nRoot.right = node;

        return nRoot;
    }


    private Node balanceTree(Node node) {
        if (node == null) {return node;}

        updateBalance(node);

        if (node.balance > 1)
        {
            if (node.right != null && node.right.balance < 0) {node.right = balanceRight(node.right);}
            return balanceLeft(node);
        }

        if (node.balance < -1)
        {
            if (node.left != null && node.left.balance > 0) {node.left = balanceLeft(node.left);}
            return balanceRight(node);
        }

        return node;
    }

    public Node insert(Node node, int v)
    {
        if (node==null) {return createNewNode(v);}

        if(v<node.data){node.left=insert(node.left, v);}
        if(v>node.data){node.right=insert(node.right, v);}

        return balanceTree(node); //balanced tree
        //return node;// unbalanced tree
    }

    protected boolean ifBalanced(Node node)
    {
        if (node == null) {return true;}

        int leftH=getHeight(node.left);
        int rightH=getHeight(node.right);

        return (Math.abs(rightH-leftH) <= 1) && ifBalanced(node.left) && ifBalanced(node.right);
    }

    public void postOrder(Node node)
    {
        if (node == null) {return;}

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
        try {Thread.sleep(500);}
        catch (InterruptedException e) {e.printStackTrace();}
    }
}


public class Main
{
    public static void main(String[] args)
    {
       Tree a = new Tree();
       Node root = null;

       root = a.insert(root, 45);
       a.insert(root, 27);
       a.insert(root, 67);
       a.insert(root, 36);
       a.insert(root, 56);
       a.insert(root, 15);
       a.insert(root, 75);
       a.insert(root, 31);
       a.insert(root, 53);
       a.insert(root, 39);
       a.insert(root, 64);
       a.insert(root, 32);
       a.insert(root, 42);
       a.insert(root, 52);
       a.insert(root, 62);
       //a.insert(root, 100); //adding element which will unbalance tree
        
       System.out.println("height of tree: " + a.getHeight(root));
       System.out.println("balanced?: "+a.ifBalanced(root));
       System.out.print("PostOrder: "); //printing in postorder
       a.postOrder(root);
    }
}