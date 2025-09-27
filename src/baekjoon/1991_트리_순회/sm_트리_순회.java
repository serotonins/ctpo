import java.util.*;
import java.lang.*;
import java.io.*;

public class sm_트리_순회 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String value = st.nextToken();
            String leftChild = st.nextToken();
            String rightChild = st.nextToken();

            tree.makeTree(value, leftChild, rightChild);
        }

        tree.preOrder(tree.root);
        sb.append("\n");
        tree.inOrder(tree.root);
        sb.append("\n");
        tree.postOrder(tree.root);

        System.out.println(sb);
    }

    static class Tree {
        Node root;

        public void makeTree(String value, String leftChild, String rightChild) {
            if (root == null) {
                if (!value.equals("."))
                    root = new Node(value);

                if (!leftChild.equals("."))
                    root.left = new Node(leftChild);

                if (!rightChild.equals("."))
                    root.right = new Node(rightChild);
            } else {
                findRoot(root, value, leftChild, rightChild);
            }

        }

        public void findRoot(Node node, String value, String leftChild, String rightChild) {
            if (node == null) {
                return;
            }

            if (node.value.equals(value)) {
                if (!leftChild.equals("."))
                    node.left = new Node(leftChild);

                if (!rightChild.equals("."))
                    node.right = new Node(rightChild);
            } else {
                findRoot(node.left, value, leftChild, rightChild);
                findRoot(node.right, value, leftChild, rightChild);
            }
        }

        //전위 순회
        public void preOrder(Node node) {
            sb.append(node.value);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }

        //중위 순회
        public void inOrder(Node node) {
            if (node.left != null) {
                inOrder(node.left);
            }
            sb.append(node.value);
            if (node.right != null) {
                inOrder(node.right);
            }
        }

        //후위 순회
        public void postOrder(Node node) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            sb.append(node.value);
        }
    }

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }
}