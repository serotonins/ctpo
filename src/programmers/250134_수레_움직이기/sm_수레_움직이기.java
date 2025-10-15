import java.lang.*;
class Solution {
    static int MAX = 987654321;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean isRedEnd, isBlueEnd;
    static boolean[][][] visited; // [][][0] : 빨간색, [][][1]: 파란색
    public int solution(int[][] maze) {
        visited = new boolean[maze.length][maze[0].length][2];
        Locate redStart = null;
        Locate blueStart = null;
        for(int i=0; i<maze.length ;i++){
            for(int j=0; j<maze[i].length ; j++){
                if(maze[i][j] == 1) redStart = new Locate(i, j);
                else if(maze[i][j] == 2) blueStart = new Locate(i, j);
            }
        }

        visited[redStart.row][redStart.col][0] = true;
        visited[blueStart.row][blueStart.col][1] = true;
        int answer = dfs(redStart, blueStart, 0, maze);
        return answer == MAX ? 0 : answer;
    }

    private static int dfs(Locate redLocate, Locate blueLocate, int moveCnt, int[][] maze){
        if(isRedEnd && isBlueEnd) return moveCnt;

        int cntTmp = MAX;

        for(int i=0; i<4 ; i++){
            Locate nxtRed =
                    (isRedEnd ? redLocate : new Locate(redLocate.row + dr[i], redLocate.col + dc[i]));
            for(int j=0; j<4 ; j++){


                Locate nxtBlue =
                        (isBlueEnd ? blueLocate : new Locate(blueLocate.row + dr[j], blueLocate.col+dc[j]));

                //범위 벗어나면 패스
                if(nxtRed.row < 0 || nxtRed.col <0 || nxtRed.row >= maze.length || nxtRed.col >= maze[0].length) continue;
                if(nxtBlue.row< 0 || nxtBlue.col <0 || nxtBlue.row >= maze.length  || nxtBlue.col >= maze[0].length) continue;

                //벽이라면 패스
                if(maze[nxtRed.row][nxtRed.col] == 5 || maze[nxtBlue.row][nxtBlue.col] == 5) continue;

                //방문한 곳이라면 패스
                if((!isRedEnd && visited[nxtRed.row][nxtRed.col][0])
                        || (!isBlueEnd && visited[nxtBlue.row][nxtBlue.col][1])) continue;

                //동일 위치라면 패스
                if(nxtRed.row == nxtBlue.row && nxtRed.col == nxtBlue.col) continue;

                //서로 자리를 바꾸는 위치라면 패스
                if(nxtRed.row == blueLocate.row && nxtRed.col == blueLocate.col
                        && nxtBlue.row == redLocate.row && nxtBlue.col == redLocate.col) continue;

                visited[nxtRed.row][nxtRed.col][0] = true;
                visited[nxtBlue.row][nxtBlue.col][1] = true;

                if(maze[nxtRed.row][nxtRed.col] == 3) isRedEnd = true;
                if(maze[nxtBlue.row][nxtBlue.col] == 4) isBlueEnd = true;

                cntTmp = Math.min(cntTmp, dfs(nxtRed, nxtBlue, moveCnt+1, maze));

                visited[nxtRed.row][nxtRed.col][0] = false;
                visited[nxtBlue.row][nxtBlue.col][1] = false;
                isRedEnd = false;
                isBlueEnd = false;
            }
        }

        return cntTmp;
    }

    static class Locate{
        int row, col;

        public Locate(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}