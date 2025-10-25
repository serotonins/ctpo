from collections import deque

N,K = map(int, input().split())
arr = [[i,0] for i in map(int,input().split())]
arr[len(arr)//2:] = arr[len(arr)//2:][::-1]
robot = deque()

def moveBelt(arr,robot):
    last = arr[len(arr)//2-1]
    for i in range(len(arr)//2-1,0,-1):
        arr[i] = arr[i-1]
        if i==len(arr)//2-1:
            arr[i][1] = 0
    arr[0] = arr[len(arr)//2]
    for i in range(len(arr)//2,len(arr)-1):
        arr[i] = arr[i+1]
    arr[-1] = last

    for _ in range(len(robot)):
        idx = robot.popleft()
        n_idx = idx+1 if idx!=len(arr)-1 else 0

        if n_idx==N-1:
            arr[n_idx][1] = 0
            continue
        robot.append(n_idx)

def moveRobot(arr,robot):
    for _ in range(len(robot)):
        idx = robot.popleft()
        n_idx = idx+1 if idx!=len(arr)-1 else 0

        flag = False
        if not arr[n_idx][1] and arr[n_idx][0]>=1 :
            arr[idx][1] = 0
            arr[n_idx][1] = 1
            arr[n_idx][0] -= 1
            flag = True

        if n_idx==N-1:
            arr[n_idx][1] = 0
            continue
        
        if flag:
            robot.append(n_idx)
        else:
            robot.append(idx)

def putRobot(arr,robot):
    if arr[0][0]!=0 and not arr[0][1]:
        arr[0][0] -= 1
        arr[0][1] = 1
        robot.append(0)

def check(arr):
    cnt = 0
    for a,b in arr:
        if a==0:
            cnt+=1
    return cnt

res = 0
z_cnt = 0
while z_cnt<K:
    res += 1
    moveBelt(arr,robot)
    moveRobot(arr,robot)
    putRobot(arr,robot)
    z_cnt = check(arr)
print(res)
