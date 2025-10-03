import heapq

N = int(input())
heap = []
for _ in range(N):
    heapq.heappush(heap,int(input()))

res = 0
while heap:
    if len(heap)==1:
        break
    
    a = heapq.heappop(heap)
    b = heapq.heappop(heap)
    temp = a+b
    res += temp
    
    heapq.heappush(heap,temp)
print(res)

