from bisect import bisect_right

def getOrder(k):
    length = 1
    temp = 1
    while k > temp*26:
        k -= temp*26
        length += 1
        temp *= 26
    
    order = ""
    k-=1
    for _ in range(length):
        order += chr(ord('a')+k%26)
        k//=26
    return order[::-1]


def solution(n, bans):
    answer = ''
    
    bans.sort(key=lambda x : (len(x),x))
    bans_with_length = [(len(i),i) for i in bans]
    
    s,e = n,n+len(bans)
    res = n
    while s<=e:
        mid = (s+e)//2
        
        order = getOrder(mid)
        idx = bisect_right(bans_with_length, (len(order),order))
        if mid-idx < n:
            s = mid+1
        else:
            res = mid
            e = mid-1
    
    return getOrder(res)
