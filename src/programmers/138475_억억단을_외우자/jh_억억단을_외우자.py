def solution(e, starts):
    answer = []
    
    div_cnt = [0]*(e+1)
    for i in range(1,e+1):
        for j in range(i,e+1,i):
            div_cnt[j] += 1
    
    temp = [0]*(e+1)
    max_cnt = 0
    max_value = 0
    for s in range(e,0,-1):
        if max_cnt <= div_cnt[s]:
            max_cnt = div_cnt[s]
            max_value = s
        temp[s] = max_value
    
    for s in starts:
        answer.append(temp[s])
    
    return answer