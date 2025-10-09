def solution(n, s):
    answer = []
    
    if s<n:
        answer.append(-1)
        return answer
    
    base = s//n
    plus = s%n
    
    answer = [base]*n
    for i in range(plus):
        answer[-1-i]+=1
        
    return answer