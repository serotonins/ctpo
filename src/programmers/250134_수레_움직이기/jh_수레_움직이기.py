from collections import deque
from itertools import product

'''
빨,파 동시 출발

상하좌우 탐색
- 빨간색 움직이는 조건
1. 빨간색이 방문하지 않음
    -> 빨,파 방문 각각 체크 
    -> (빨,파) 조합 체크 (과거부터 방문기록) : set으로
2. 벽이 아님
3. 파란색 도착지에 파란색이 도착하지 않음
4. 움직이는 자리에 파란색 돌이 없음
'''
def bfs(rx,ry,bx,by,maze):
    visited = set()
    r_visited = frozenset([(rx,ry)])
    b_visited = frozenset([(bx,by)])
    visited.add(((rx,ry), (bx,by) , r_visited, b_visited))
    
    q = deque([(rx,ry,bx,by,r_visited,b_visited,0)])
    dx = [-1,1,0,0]
    dy = [0,0,1,-1]
    while q:
        rx,ry,bx,by,r_visited,b_visited,time = q.popleft()
        
        if maze[rx][ry] == 3 and maze[bx][by]==4:
                return time
        
        temp_red = []
        if maze[rx][ry]==3:
            temp_red.append((rx,ry))
        else:
            for i in range(4):
                nrx = rx + dx[i]
                nry = ry + dy[i]

                if 0<=nrx<len(maze) and 0<=nry<len(maze[0]) and maze[nrx][nry]!=5 and (nrx,nry) not in r_visited:
                    temp_red.append((nrx,nry))
        
        temp_blue = []
        if maze[bx][by]==4:
            temp_blue.append((bx,by))
        else:
            for i in range(4):
                nbx = bx + dx[i]
                nby = by + dy[i]
                
                if 0<=nbx<len(maze) and 0<=nby<len(maze[0]) and maze[nbx][nby]!=5 and (nbx,nby) not in b_visited:
                    temp_blue.append((nbx,nby))

        temp = product(temp_red,temp_blue)            
        for r,b in temp:
            
            
            if r==b or (r==(bx,by) and b==(rx,ry)):
                continue
            
            nr_visited = r_visited | {r}
            nb_visited = b_visited | {b}
            if (r,b, nr_visited, nb_visited) in visited:
                continue
                
            visited.add((r,b, nr_visited, nb_visited))
            q.append((r[0],r[1],b[0],b[1],nr_visited,nb_visited,time+1))
        
    return 0
    

def solution(maze):
    rx,ry,bx,by = 0,0,0,0
    
    for i in range(len(maze)):
        for j in range(len(maze[0])):
            if maze[i][j]==1:
                rx,ry = i,j
            elif maze[i][j]==2:
                bx,by = i,j

    res = bfs(rx,ry,bx,by,maze)        
    return res