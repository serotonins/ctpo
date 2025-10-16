from collections import defaultdict, deque

def bfs(a,b):
    q = deque([(1,a,a),(1,b,b)])

    parents_a = set({a})
    parents_b = set({b})
    
    while len(parents_a&parents_b)==0:
        depth, node, sep = q.popleft()
        if tree[node]:
            if sep==a:
                parents_a.add(tree[node])
                q.append((depth+1,tree[node],a))
            else:
                parents_b.add(tree[node])
                q.append((depth+1,tree[node],b))

        if len(parents_a&parents_b):
            for i in parents_a&parents_b:
                print(i)
                return

T = int(input())
for _ in range(T):
    tree = defaultdict(int)
    N = int(input())
    for _ in range(N-1):
        a,b = map(int,input().split())
        tree[b] = a
    ta,tb = map(int,input().split())
    bfs(ta,tb)
    
