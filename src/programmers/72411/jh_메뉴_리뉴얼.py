from itertools import combinations
from collections import Counter
'''
손님들 주문의 총 교집합
해당 교집합에서 주문 수만큼 조합
조합이 손님 주문에 들어가있는 경우가 제일 큰 경우
'''
def solution(orders, course):
    answer = []
    
    for i in course:
        combi_list = []
        for order in orders:
            combi = list(combinations(sorted(order),i))
            combi_list+=combi
        
        if combi_list:
            counter = Counter(combi_list)
            
            maxCnt = 0
            temp = []
            for comb,cnt in counter.items():
                if cnt < maxCnt or cnt < 2:
                    continue
                elif cnt == maxCnt:
                    temp.append(''.join(comb))
                else:
                    temp = [''.join(comb)]
                    maxCnt = cnt
            
            answer += temp
    answer.sort()
    
    return answer