from collections import deque

N,L,R = map(int, input().split())
arr = [list(map(int,input().split())) for _ in range(N)]

def bfs(sx, sy, visited):
    dx = [1,-1,0,0]
    dy = [0,0,1,-1]

    visited[sx][sy] = 1
    q = deque([(sx,sy)])
    team = [(sx,sy)]
    total = arr[sx][sy]
    while q:
        x,y = q.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<N and not visited[nx][ny]:
                if L <= abs(arr[x][y] - arr[nx][ny]) <=R:
                    visited[nx][ny] = 1
                    q.append((nx,ny))
                    team.append((nx,ny))
                    total += arr[nx][ny]

    return team, total

res = 0
while 1:
    '''
    arr 탐색 (bfs)-> 연합 생성
    탐색 중 연합 생성 안되었으면 break
    '''
    visited = [[0]*N for _ in range(N)]
    isMoved = False

    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                team, total = bfs(i,j, visited)
                if len(team) > 1:
                    isMoved = True
                    for x,y in team:
                        arr[x][y] = total // len(team)

    if not isMoved:
        break
    res += 1

print(res)