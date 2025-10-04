def solution(n, bans):
    bans.sort(key=lambda x: (len(x),x))
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    temp = 0
    idx = 0
    for i in range(11):
        num = pow(26,i+1)
        if temp+num < n:
            temp += num
        else:
            idx = i+1
            break

    n_idx = 0
    for i in range(idx,0,-1):
        n_idx += pow(26,i-1)
    n_idx = n-n_idx

    temp_string = ''
    ans_idx = [0]*idx
    while idx:
        temp_string += alphabet[n_idx%26]
        ans_idx[idx-1] = n_idx%26
        n_idx //=26
        idx -= 1
    ans = temp_string[::-1]
    temp = ans_idx[len(ans_idx)-1]
    for i in bans:
        if len(i)<len(ans) or (len(i)==len(ans) and i <= ans):
            for j in range(len(ans_idx)-1,0,-1):
                if ans_idx[j]+1 == 26:
                    ans_idx[j] = 0
                else:
                    ans_idx[j]+=1
                    break

            if(ans_idx[0]==26):
                ans_idx[0]=0
                ans_idx.append(0)

            ans = ''
            for i in ans_idx:
                ans += alphabet[i]
        else:
            break
    return ans