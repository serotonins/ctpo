from collections import defaultdict
N = int(input())
tree = defaultdict(list)

for _ in range(N):
    root, left, right = input().split()
    tree[root].append(left)
    tree[root].append(right)
'''
# 처음 코드
def printPreOrder(node):
    print(node, end="")
    if tree[node][0]!='.':
        printPreOrder(tree[node][0])
    if tree[node][1]!='.':
        printPreOrder(tree[node][1])

def printInOrder(node):
    if tree[node][0]!='.':
        printInOrder(tree[node][0])
    print(node, end="")
    if tree[node][1]!='.':
        printInOrder(tree[node][1])

def printPostOrder(node):
    if tree[node][0]!='.':
        printPostOrder(tree[node][0])
    if tree[node][1]!='.':
        printPostOrder(tree[node][1])
    print(node, end="")

printPreOrder('A')
print()
printInOrder('A')
print()
printPostOrder('A')
'''
# 문자열 재귀 반환
def preorder(node):
    if node=='.':
        return ""
    return node + preorder(tree[node][0]) + preorder(tree[node][1])

def inorder(node):
    if node=='.':
        return ""
    return inorder(tree[node][0]) + node + inorder(tree[node][1])

def postorder(node):
    if node=='.':
        return ""
    return postorder(tree[node][0]) + postorder(tree[node][1]) + node

print(preorder('A'))
print(inorder('A'))
print(postorder('A'))