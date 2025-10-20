from collections import defaultdict
N = int(input())

root = set()
tree = defaultdict(set)
for _ in range(N):
    arr = list(input().split())
    root.add(arr[1])
    prev = arr[1]
    for cur in arr[2:]:
        tree[prev].add(cur)
        prev = prev+" "+cur
for k in tree:
    tree[k] = sorted(tree[k])

def dfs(prev,sep,cur):
    print(sep+cur)
    prev += " "+cur if prev else cur
    if prev not in tree:
        return

    for node in tree[prev]:
        dfs(prev,sep+"--",node)

for r in sorted(root):
    dfs('',"",r)