
func minWindow(s string, t string) string {
    answer := ""
    if len(t)>len(s) {
        return answer
    }
    n := len(t)
    m := len(s)
    targetMap := make(map[rune]int,n)
    tRune := []rune(t)
    sRune := []rune(s)
    for _,r := range tRune {
        count,_ := targetMap[r]
        targetMap[r] = count + 1
    }
    matchCount := 0
    start := 0
    minLength := m +2
    min_start := 0 
    for i, r := range sRune {
        if count, ok := targetMap[r]; ok {
            if count > 0 {
                matchCount++
            }
            targetMap[r] = count - 1
        }
        if matchCount == n {
            tempCount,ok := targetMap[sRune[start]]
            for  (start < i && !ok) || (ok&&tempCount<0){
                if ok {
                    targetMap[sRune[start]] = tempCount + 1
                }
                start++
                tempCount,ok = targetMap[sRune[start]]
            }
            if minLength > i - start +1 {
                answer = string(sRune[start:i+1])
                min_start = start
                minLength = i - start +1
            }
        }
    }
    if minLength == m + 2 {
        return ""
    }
    answer = string(sRune[min_start:min_start + minLength])
    return answer
}