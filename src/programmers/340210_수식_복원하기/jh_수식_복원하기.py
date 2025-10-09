from collections import deque

def calculate(x,y,oper,res):
    if oper=='-':
        for i in range(-1,-3,-1):
            if abs(i)>len(x) or abs(i)>len(y) or abs(i)>len(res) or int(x[i])-int(y[i]) == int(res[i]):
                continue
            return int(res[i])-int(x[i])+int(y[i])
        return 0
    else:
        for i in range(-1,-3,-1):
            if abs(i)>len(x) or abs(i)>len(y) or abs(i)>len(res) or int(x[i])+int(y[i]) == int(res[i]):
                continue
            return int(x[i])+int(y[i])-int(res[i])
        return 0
        
def baseCalculate(a,b,oper,base):
    temp = 0
    res = ''
    x = [0]*3
    y = [0]*3
    for i in range(len(a)):
        x[i] = int(a[-i-1])
    for i in range(len(b)):
        y[i] = int(b[-i-1])
    if oper=='-':
        for i in range(3):
            if x[i]-y[i]-temp >= base[0]:
                if len(base)>1:
                    return '?'
                res += str(x[i]-y[i]-temp-base[0])
                temp = 1
            elif x[i]-y[i]-temp < 0:
                if len(base)>1:
                    return '?'
                res += str(x[i]-y[i]-temp+base[0])
                temp = 1
            else:
                res += str(x[i]-y[i]-temp)
                temp = 0
        return str(int(res[::-1]))
    else:
        for i in range(3):
            if x[i]+y[i]+temp >= base[0]:
                if len(base)>1:
                    return '?'
                res += str(x[i]+y[i]+temp-base[0])
                temp = 1
            else:
                res += str(x[i]+y[i]+temp)
                temp = 0
        return str(int(res[::-1]))
    
def solution(expressions):
    answer = deque()
    complete = []
    maxNum = 1
    base = []
    for express in expressions:
        express_split = express.split()
        a,oper,b,equal,res = express_split
        for numbers in [a,b,res]:
            if numbers =='X':
                answer.append((a,oper,b,equal,res))
                continue
                
            for num in numbers:
                for n in num:
                    maxNum = max(int(n), maxNum)
                        
        if res!='X':
            cal_res = calculate(a,b,oper,res)
            if cal_res:
                base = [cal_res]
                
    if not base:
        base = [i for i in range(maxNum+1,10)]
    
    for i in range(len(answer)):
        a,oper,b,equal,res = answer.popleft()
        res = baseCalculate(a,b,oper,base)
        answer.append(' '.join([a,oper,b,equal,res]))        
    
    return list(answer)