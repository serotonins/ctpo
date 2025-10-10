N = int(input())
M = int(input())

'''
cycle 판단 여부는 a,b의 루트가 같은지!
'''
root = [i for i in range(N+1)]
edge = []
for _ in range(M):
    a,b,c = map(int,input().split())
    edge.append((c,a,b))
edge.sort()

def findRoot(node, root):
    if node != root[node]:
        root[node] = findRoot(root[node], root)
    return root[node]

def updateRoot(a,b,root):
    root_a = findRoot(a,root)
    root_b = findRoot(b,root)
    if root_a < root_b:
        root[root_b] = root_a
    else:
        root[root_a] = root_b

res = 0
for c,a,b in edge:
    if findRoot(a,root) != findRoot(b,root):
        res += c
        updateRoot(a,b,root)
print(res)