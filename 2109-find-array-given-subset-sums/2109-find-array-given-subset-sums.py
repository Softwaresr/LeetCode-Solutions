def split(sums, d):
    possible = True
    d_in = {}
    d_out = {}
    it = iter(sums)
    a = next(it)
    d_in[a] = sums[a]
    for b in it:
        if b+d in d_in:
            d_out[b] = d_in[b + d]
            if sums[b] > d_in[b+d]:
                d_in[b] = sums[b] - d_in[b+d]
            elif sums[b] < d_in[b+d]:
                return None
        else:
            d_in[b] = sums[b]
    return (-d, d_in), (d, d_out)

def try_to_solve(n, sums):
    if n==0:
        return []
    keys = iter(sums)
    d = next(keys) - next(keys)
    options = split(sums, d)
    if not options:
        return None
    for v, option in options:
        if 0 not in option:
            continue
        ans = try_to_solve(n-1, option)
        if ans is not None:
            return ans + [v]
    return None


def div_with_check(a, b):
    frac, r = divmod(a, b)
    # assert r == 0
    return frac

class Solution:
    def recoverArray(self, n: int, sums: List[int]) -> List[int]:
        sums = sorted(collections.Counter(sums).items(), reverse=True)
        multiplicity = sums[0][1]
        sums = {k: div_with_check(cnt, multiplicity) for k, cnt in sums}
        ## multiplicity is a power of two!
        num_zeros = 0
        while multiplicity > 1:
            num_zeros += 1
            multiplicity >>= 1

        return [0]*num_zeros + try_to_solve(n-num_zeros, sums)