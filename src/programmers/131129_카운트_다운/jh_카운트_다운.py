def solution(target):
    score = {}
    visited = set()
    for i in range(1,4):
        for j in range(1,21):
            if i*j not in visited:
                score[i*j] = i
                visited.add(i*j)
    score[50] = 1
    
    # i점수의 [최소 다트수, 최대 싱글 수]
    dp = [[float('inf'),0] for _ in range(target+1)]
    dp[0] = [0,0]
    
    for i in range(1,target+1):
        for s, region in score.items():
            if i<s:
                continue
            
            prev = dp[i-s]
            cur = dp[i]
            if prev[0] != float('inf'):
                n_cnt = prev[0]+1
                n_single = prev[1]+1 if region==1 else prev[1]
                
                if n_cnt<cur[0] or (n_cnt==cur[0] and n_single>cur[1]):
                    dp[i] = [n_cnt, n_single]
    return dp[target]    