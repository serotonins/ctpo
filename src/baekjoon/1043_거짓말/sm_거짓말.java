import java.util.*;
import java.io.*;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사람의 수
        int m = Integer.parseInt(st.nextToken()); // 파티의 수

        st = new StringTokenizer(br.readLine());
        int trueKnowNum = Integer.parseInt(st.nextToken());

        //진실을 아는 사람이 없다면 모든 파티에 참여할 수 있음
        if (trueKnowNum == 0) {
            System.out.println(m);
            return;
        }

        //유니온 파인드를 위한 초기값 설정
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        //진실을 아는 사람을 구함
        boolean[] knowTrueArr = new boolean[n + 1];
        for (int i = 0; i < trueKnowNum; i++) {
            int people = Integer.parseInt(st.nextToken());
            knowTrueArr[people] = true;
        }

        //파티 참여자 목록 만들기
        List<Integer>[] parties = new List[m];
        for (int i = 0; i < m; i++) {
            parties[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int peopleNum = Integer.parseInt(st.nextToken()); // 해당 파티에 참여하는 사람 수
            for (int j = 0; j < peopleNum; j++) {
                //파티 참여자 추가
                parties[i].add(Integer.parseInt(st.nextToken()));

                //파티 참여자가 2명이상이라면 union진행
                if (j != 0) {
                    int now = parties[i].get(j);
                    int pre = parties[i].get(j - 1);

                    union(pre, now);
                }
            }
        }

        //유니온 한것을 돌면서 진실을 아는지 판단
        boolean[] check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            //아직 판단하지 않았고 진실을 아는 사람이라면
            if (!check[i] && knowTrueArr[i]) {
                //해당 사람과 연결된 사람을 찾음
                int parent = find(i);

                for (int j = 1; j <= n; j++) {
                    //부모가 같다면 같은 파티에 참여한 것이므로 진실을 아는 것으로 바꿔줌
                    if (find(j) == parent) {
                        knowTrueArr[j] = true;
                        check[j] = true;
                    }
                }
            }
        }

        //파티를 돌면서 참여한 사람이 진실을 아는지 모르는지 확인
        boolean isPossible = true;
        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                //진실을 아는 사람이 있다면 거짓말을 못하므로 패스
                if (parties[i].contains(j) && knowTrueArr[j]) {
                    isPossible = false;
                    break;
                }
            }

            //isPossible이 true라면 가능한 것이므로 파티 개수 ++
            if (isPossible) answer++;
            isPossible = true;
        }

        System.out.println(answer);

    }

    private static void union(int pre, int now) {
        parents[find(now)] = find(pre);
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }
}

