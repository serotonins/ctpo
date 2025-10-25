import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n =  Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            String[] words = new String[k];
            for (int j = 0; j < k; j++) {
                words[j] = st.nextToken();
            }

            trie.insert(words);
        }

        trie.search(trie.root, 0);
    }

    static class Node{
        Map<String, Node> child;
        boolean isEnd;

        public Node(){
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class Trie{
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String[] words){
            Node node  = this.root;

            for (String word : words) {
                node.child.putIfAbsent(word, new Node());
                node = node.child.get(word);
            }
            node.isEnd = true;
        }

        public void search(Node cur, int depth){
            if (cur.child != null) {
                List<String> childLinst = new ArrayList<>(cur.child.keySet());
                Collections.sort(childLinst);

                for (String word : childLinst) {
                    for (int i = 0; i < depth; i++) {
                        System.out.print("--");
                    }

                    System.out.println(word);
                    search(cur.child.get(word), depth + 1);
                }
            }
        }
    }
}