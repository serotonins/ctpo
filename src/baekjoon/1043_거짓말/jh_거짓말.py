'''
거짓말
1. 진실을 아는 사람이 없는 파티
2. 진실을 말했던 파티에 없었던 사람들만 있는 파티
'''
from collections import deque,defaultdict

N,M = map(int, input().split())
true = list(map(int, input().split()))
visited = [0]*(N+1)
q = deque()
if true[0]:
    for i in true[1:]:
        visited[i] = 1
        q.append(i)

party_idx = defaultdict(list) # 누가 무슨 파티에 참가했는지
people_idx = defaultdict(list) # 파티에 참가한 사람이 누군지
for i in range(M):
    people = list(map(int, input().split()))
    for j in people[1:]:
        party_idx[j].append(i)
        people_idx[i].append(j)

visited_party = [0]*(M)
while q:
    who = q.popleft()
    for party in party_idx[who]:
        if visited_party[party]:
            continue

        for person in people_idx[party]:
            if visited[person]:
                continue
            visited[person] = 1
            q.append(person)
        
        visited_party[party] = 1
print(len(visited_party)-sum(visited_party))