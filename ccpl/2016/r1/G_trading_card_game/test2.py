from itertools import product
N = 3; m = 5; k = 4
nums = [x for x in range(1, N+1)]
allperms = [x for x in product(nums, repeat=m)]
valid = [x for x in allperms if len(set(x)) == k]
print len(valid)
print len(allperms)