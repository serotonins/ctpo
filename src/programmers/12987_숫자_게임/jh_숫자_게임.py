from bisect import bisect_right
def solution(A, B):
    answer = 0
    B.sort()
    idx_bigger_than_a = set()
    for a in A:
        idx = bisect_right(B,a)
        while idx in idx_bigger_than_a:
            idx += 1
        if idx < len(B):
            idx_bigger_than_a.add(idx)
            answer += 1
    return answer