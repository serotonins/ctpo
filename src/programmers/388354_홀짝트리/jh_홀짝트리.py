from collections import defaultdict

def union(a,b,parent):
    root_a = find(a,parent)
    root_b = find(b,parent)
    if root_a < root_b:
        parent[root_b] = root_a
    else:
        parent[root_a] = root_b
    return parent

def find(n,parent):
    if parent[n]!=n:
        parent[n] = find(parent[n], parent)
    return parent[n]
    
def solution(nodes, edges):
    answer = [0,0]
    
    parents = {i:i for i in nodes}
    degree = {i:0 for i in nodes}
    for a,b in edges:
        union(a,b,parents)
        degree[a]+=1
        degree[b]+=1
    
    #홀짝, 역홀짝
    hol_jjag = defaultdict(lambda : [0,0])
    for n in nodes:
        parent = find(n,parents)
        if n%2 == (degree[n]-1)%2:
            hol_jjag[parent][0]+=1
        else:
            hol_jjag[parent][1]+=1
    
    for a,b in hol_jjag.values():
        if a==1:
            answer[1]+=1
        if b==1:
            answer[0]+=1
    
    return answer