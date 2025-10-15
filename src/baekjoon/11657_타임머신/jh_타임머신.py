N,M = map(int, input().split())
arr = []
for _ in range(M):
    a,b,c = map(int,input().split())
    arr.append((a,b,c))

INF = float('inf')
def calDist():
    dist = [INF] * (N+1)
    dist[1] = 0

    for i in range(M-1):
        for a,b,c in arr:
            if dist[a] != INF and dist[a]+c < dist[b]:
                dist[b] = dist[a]+c

    for a,b,c in arr:
        if dist[a] != INF and dist[a]+c < dist[b]:
            return [-1]

    return dist

res = calDist()
if len(res)==1:
    print(-1)
else:
    for i in res[2:]:
        print(i) if i!=INF else print(-1)

