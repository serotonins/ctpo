def solution(alp, cop, problems):
    answer = 0
    
    maxAlp = 0
    maxCop = 0
    for p in problems:
        maxAlp = max(maxAlp, p[0])
        maxCop = max(maxCop, p[1])
    
    #dp[i][j] i알고력, j알고력 갖추는 최소 시간
    dp = [[300]*(maxCop+1) for _ in range(maxAlp+1)]
    alp = min(alp, maxAlp)
    cop = min(cop, maxCop)
    dp[alp][cop] = 0
    
    for a in range(alp,maxAlp+1):
        for c in range(cop, maxCop+1):
            if a < maxAlp:
                dp[a+1][c] = min(dp[a+1][c], dp[a][c]+1)
            if c < maxCop:
                dp[a][c+1] = min(dp[a][c+1], dp[a][c]+1)
            
            for p in problems:
                na,nc,aa,ac,cost = p
                if na <= a and nc <= c:
                    new_alp = min(a+aa, maxAlp)
                    new_cop = min(c+ac, maxCop)
                    
                    dp[new_alp][new_cop] = min(dp[new_alp][new_cop], dp[a][c]+cost)
    
    return dp[maxAlp][maxCop]