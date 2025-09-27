n,k = map(int, input().split())
arr = list(set([int(input()) for _ in range(n)]))

'''
dp[i] = i원을 만들 수 있는 최소 동전 갯수
dp[k]를 만들어야 함
i 범위 = k 범위

현재 동전 s라 하면
dp[i] = min(dp[i], dp[i-s]+1)
'''

dp = [10001]*(k+1)
dp[0] = 0
for i in arr:
    for j in range(i,k+1):
        dp[j] = min(dp[j],dp[j-i]+1)
if dp[k]==10001:
    print(-1)
else:
    print(dp[k])
