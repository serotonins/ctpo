N = int(input())
prev = list(input())
next = list(input())

def change(idx,arr):
    arr[idx]= '1' if arr[idx]=='0' else '0'

def simulate(arr):
    cnt = 0
    if arr == next:
        return cnt
    for i in range(1,N):
        if arr[i-1] != next[i-1]:
            cnt += 1
            change(i-1,arr)
            change(i,arr)
            if i<N-1:
                change(i+1,arr)
    if arr == next:
        return cnt
    else:
        return -1

prev2 = []
for i in prev:
    prev2.append(i)
change(0,prev2)
change(1,prev2)
res1 = simulate(prev)
res2 = simulate(prev2)
res2 += 1 if res2!=-1 else 0
if res1==-1 and res2==-1:
    print(-1)
elif res1==-1:
    print(res2)
elif res2==-1:
    print(res1)
else:
    print(min(res1,res2))