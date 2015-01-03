def charMapping(words1, words2):
    m,n=len(words1),len(words2)
    if m!=n or m==0:
        return {}
    map={}
    for i in range(n):
        w1, w2=words1[i], words2[i]
        for j in range(len(w1)):
            if w1[j] in map:
                if map[w1[j]]!=w2[j]:
                    print w1[j], w2[j], map
                    return {}
            if w2[j] in map:
                if map[w2[j]]!=w1[j]:
                    print w1[j], w2[j], map
                    return {}
            map[w1[j]]=w2[j]
            map[w2[j]]=w1[j]
    return map




if __name__=="__main__":
    w1=["cat", "coffee", "common"]
    w2=["dkp","dbhhzz","dbllbq"]
    print charMapping(w1,w2)
