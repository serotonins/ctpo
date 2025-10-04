from collections import deque

R,C = map(int, input().split())
arr = [list(input()) for _ in range(R)]

j_visited = [[-1]*C for _ in range(R)]
f_visited = [[-1]*C for _ in range(R)]
q = deque()

j_idx = [0,0]
for i in range(R):
    for j in range(C):
        if arr[i][j] == 'J':
            j_idx = [i,j]
        elif arr[i][j] == 'F':
            q.append((False,i,j))
            f_visited[i][j] = 0

q.append((True,j_idx[0],j_idx[1]))
j_visited[j_idx[0]][j_idx[1]] = 0

dx = [-1,1,0,0]
dy = [0,0,-1,1]
while q:
    isJihun,x,y = q.popleft()

    if isJihun:
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if nx<0 or nx>=R or ny<0 or ny>=C:
                print(j_visited[x][y]+1)
                exit(0)

            elif 0<=nx<R and 0<=ny<C and arr[nx][ny]!='#' and j_visited[nx][ny]==-1:
                if f_visited[nx][ny] == -1 or f_visited[nx][ny] > j_visited[x][y]+1:
                    j_visited[nx][ny] = j_visited[x][y]+1
                    q.append((True,nx,ny))
    else:
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<R and 0<=ny<C and arr[nx][ny]!='#' and f_visited[nx][ny]==-1:
                f_visited[nx][ny] = f_visited[x][y]+1
                q.append((False,nx,ny))
print("IMPOSSIBLE")