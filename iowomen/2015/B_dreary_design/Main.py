T = int(raw_input())
for t in range(T):
	vals = raw_input().split()
	K = int(vals[0])
	V = int(vals[1])

	ans = 1
	for i in range(1, V+1):
		ans += 