from collections import defaultdict

def solution(info, n, m):
    answer = n

    record = {(0,0)}
    for i,v in enumerate(info):
        idx = i+1

        temp = set()
        for a,b in record:
            if a+v[0] < n:
                temp.add((a+v[0],b))
            if b+v[1] < m:
                temp.add((a,b+v[1]))

        if len(temp)==0:
            return -1
        record = temp

    for a,b in record:
        answer = min(answer,a)

    return answer