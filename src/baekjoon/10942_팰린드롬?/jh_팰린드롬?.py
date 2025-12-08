import sys
input = sys.stdin.readline

N = int(input())
arr = [0]+list(map(int,input().split()))
dp = [[0]*(N+1) for _ in range(N+1)]

for i in range(1,N+1):
    dp[i][i] = 1

for i in range(1,N):
    if arr[i] == arr[i+1]:
        dp[i][i+1] = 1

for length in range(3,N+1):
    for start in range(1,N-length+2):
        end = start + length -1
        if arr[start]==arr[end] and dp[start+1][end-1]:
            dp[start][end] = 1

M = int(input())
for _ in range(M):
    s,e = map(int, input().split())
    print(dp[s][e])
