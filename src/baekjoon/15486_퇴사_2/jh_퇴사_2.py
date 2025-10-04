N = int(input())
dp = [0]*(N+1)

for i in range(N):
    dp[i+1] = max(dp[i+1], dp[i])
    t,p = map(int, input().split())

    if i+t <= N:
        dp[i+t] = max(dp[i+t], dp[i]+p)
print(dp[N])