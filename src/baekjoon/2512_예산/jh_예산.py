N = int(input())
arr = list(map(int, input().split()))
M = int(input())

s = 0
e = max(arr)
ans = 0
while s <= e:
    m = (s+e)//2
    temp = 0
    for i in arr:
        temp += min(i,m)
    
    if temp <= M:
        ans = m
        s = m+1
    else:
        e = m-1
    
print(ans)    