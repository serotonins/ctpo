from collections import defaultdict, deque

def solution(a, edges):
    if sum(a)!=0:
        return -1
    
    for i in a:
        if i!=0:
            break
    else:
        return 0
    
    tree = defaultdict(list)
    for u,v in edges:
        tree[u].append(v)
        tree[v].append(u)
    
    temp = defaultdict(int)
    root= set()
    for k,v in tree.items():
        if len(v)==1:
            if temp[v[0]] and temp[v[0]] in root:
                root.remove(temp[v[0]])
            else:
                root.add(k)
                temp[v[0]] = k
    
    level_tree = defaultdict(list)
    q = deque()
    visited = set()
    for r in root:
        q.append((0,r))
        level_tree[0].append(r)
        visited.add(r)
        break
    
    parents = defaultdict(int)
    while q:
        level,n = q.popleft()
        for node in tree[n]:
            if node not in visited:
                visited.add(node)
                level_tree[level+1].append(node)
                parents[node] = n
                q.append((level+1,node))

    cnt = 0
    idx = len(level_tree)
    for level in range(idx-1,0,-1):
        for node in level_tree[level]:
            p = parents[node]
            a[p] += a[node]
            cnt += abs(a[node])
            a[node] = 0
    return cnt