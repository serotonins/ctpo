import heapq,sys

N = int(sys.stdin.readline())
max_heap = [] #중간값 이하
min_heap = [] #중간값 초과
res = []
for _ in range(N):
    num = int(sys.stdin.readline())
    if len(max_heap) == len(min_heap):
        heapq.heappush(max_heap, -num)
    else:
        heapq.heappush(min_heap, num)
    # print(max_heap, min_heap)
    if min_heap and -max_heap[0] > min_heap[0]:
        max_v = heapq.heapop(max_heap)
        min_v = heapq.heapop(min_heap)
        heapq.heappush(max_heap, -min_v)
        heapq.heappush(min_heap, -max_v)
        # print("changed : ",max_heap, min_heap)
    res.append(-max_heap[0])

for i in res:
    print(i)