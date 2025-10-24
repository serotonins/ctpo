from collections import defaultdict,deque

def solution(nodes, edges):
    answer = [0,0]
    tree = defaultdict(set)
    for i in edges:
        a,b = i
        tree[a].add(b)
        tree[b].add(a)
    
    check = [[False,False] for _ in range(max(nodes)+1)]
    for root in nodes:
        q = deque([root])
        visited = set({root})
        root_hj = (root%2==len(tree[root])%2)
        if root_hj and check[root][0]:
            continue
        if not root_hj and check[root][1]:
            continue
        
        while q:
            n = q.popleft()
            children = tree[n]-visited
            
            #역홀짝 구분
            node_hj = (n%2 == len(children)%2)
            if root_hj != node_hj:
                break
            visited |= children
            q += list(children)
        else:
            if root_hj:
                answer[0]+=1
                for i in visited:
                    check[i][0] = True
            else:
                answer[1]+=1
                for i in visited:
                    check[i][1] = True
    return answer