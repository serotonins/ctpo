import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Word {
        int bm;
        Map<Character, Integer> cntMap = new TreeMap<>();
        String word;

        public Word(String word) {
            this.bm = bit(word);
            this.word = word;
            for (char c : word.toCharArray()) {
                this.cntMap.put(c, this.cntMap.getOrDefault(c, 0) + 1);
            }
        }
        public int alphaCount(char alpha) {
            if (!cntMap.containsKey(alpha)) return -1;
            return cntMap.get(alpha);
        }
        public boolean ok (Word puzzle) {
            if ((this.bm | puzzle.bm) != puzzle.bm) return false;
            for (char c : this.cntMap.keySet()) {
                if (puzzle.alphaCount(c) < this.alphaCount(c)) return false;
            }
            return true;
        }
        public String toString() {
            return "@ " + word + " - " + cntMap;
        }
    }

    static int bit(String str) {
        int num = 0;
        for (char a : str.toCharArray()) {
            num |= (1 << (a - 'A'));
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word;
        int[] count = new int[26];
        List<Word> list = new ArrayList<>();
        while (!(word = br.readLine()).equals("-")) {
            list.add(new Word(word));
        }

        while (!(word = br.readLine()).equals("#")) {
            Word now = new Word(word);

            count = new int[26];
            for (Word w : list) {
                if (w.ok(now)) {
                    for (char c : w.cntMap.keySet()) {
                        count[c-'A']++;
                    }
                }

            }

            int min = 20_0001;
            int max = -1;
            List<Character> minList = new ArrayList<>();
            List<Character> maxList = new ArrayList<>();
            for (char c : now.cntMap.keySet()) {
                int i = c - 'A';
                if (count[i] <= min) {
                    if (count[i] < min) minList.clear();
                    min = count[i];
                    minList.add((char) (i+'A'));
                }
                if (count[i] >= max) {
                    if (count[i] > max) maxList.clear();
                    max = count[i];
                    maxList.add((char) (i+'A'));
                }
            }

            for (char c : minList) { sb.append(c); }
            sb.append(" ").append(min).append(" ");
            for (char c : maxList) { sb.append(c); }
            sb.append(" ").append(max).append("\n");
        }

        System.out.println(sb.toString());
    }
}