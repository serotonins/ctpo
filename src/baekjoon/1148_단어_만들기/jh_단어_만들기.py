from collections import Counter

wordCounterList = []
while 1:
    word = input()
    if word == '-':
        break
    wordCounterList.append(Counter(word))

while 1:
    board = input()
    if board=='#':
        break
    
    boardCounter = Counter(board)
    temp = []
    for wordCounter in wordCounterList:
        for k,v in wordCounter.items():
            if k not in boardCounter or boardCounter[k] < v:
                break
        else:
            temp.append(wordCounter)
    
    ans = []
    for essential in boardCounter.keys():
        cnt = 0
        for wordCounter in temp:
            if essential in wordCounter:
                cnt += 1
        ans.append((cnt,essential))
    ans.sort()
    
    minWord = ''
    minCnt = ans[0][0]
    maxWord = ''
    maxCnt = ans[-1][0]
    for cnt,word in ans:
        if cnt == minCnt:
            minWord+=word
        if cnt == maxCnt:
            maxWord+=word
    print(minWord, minCnt, maxWord, maxCnt)