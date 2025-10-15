n = int(input())
m = int(input())
dist = [[0]*(n+1) for _ in range(n+1)]

for _ in range(m):
    a,b,c = map(int,input().split())
    dist[a][b] = min(dist[a][b], c) if dist[a][b] else c

for k in range(1,n+1):
    for i in range(1,n+1):
        for j in range(1,n+1):
            if i==j or not dist[i][k] or not dist[k][j]:
                continue
            dist[i][j] = min(dist[i][j], dist[i][k]+dist[k][j]) if dist[i][j] else dist[i][k]+dist[k][j]

for i in range(1,n+1):
    print(*dist[i][1:])