import heapq

N = int(input())
arr = []
for _ in range(N):
    s,t = map(int, input().split())
    arr.append((s,t))
arr.sort()

hq = []
heapq.heappush(hq,arr[0][1])
for i in range(1,len(arr)):
    start,end = arr[i]
    if hq[0] <= start:
        heapq.heappop(hq)
        heapq.heappush(hq,end)
    else:
        heapq.heappush(hq,end)
print(len(hq))