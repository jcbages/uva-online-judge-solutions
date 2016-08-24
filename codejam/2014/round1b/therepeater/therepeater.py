import os

def read_input():
	file_name = "%s/small.in" % os.getcwd()
	return open(file_name, "r")

def init_matrix(n, m):
	E = []
	for x in range(n):
		if x == 0:
			E.append([y for y in range(m)])
		else:
			E.append([x] + [0 for y in range(m-1)])
	return E

def diff(s1, s2, i, j):
	return 0 if s1[i-1] == s2[j-1] else 1

def get_path(E, s1, s2):
	ans1, ans2  = "", ""
	n, m = len(E) - 1, len(E[0]) - 1
	while n > 0 and m > 0:
		ans1, ans2 = s1[n-1] + ans1, s2[m-1] + ans2
		a, b, c = E[n-1][m], E[n][m-1], E[n-1][m-1]
		if   min(a, b, c) == a: n -= 1
		elif min(a, b, c) == b: m -= 1
		elif min(a, b, c) == c: n -= 1; m -= 1
	for i in range(len(ans1)):
		if ans1[i] != ans2[i]:
			if len(s1) < len(s2):
				ans1 = ans1[:i] + "-" + ans2[i+1:]
			else:
				ans2 = ans2[:i] + "-" + ans2[i+1:]
	return ans1, ans2

def min_cost(s1, s2):
	new_s1, new_s2 = "", ""
	n, m = len(s1), len(s2)
	E = init_matrix(n+1, m+1)
	for i in range(1, n+1):
		for j in range(1, m+1):
			a = 1 + E[i-1][j]
			b = 1 + E[i][j-1]
			c = diff(s1, s2, i, j) + E[i-1][j-1]
			E[i][j] = min(a, b, c)
	ans1, ans2 = get_path(E, s1, s2)
	print ans1 == ans2
	print ans1, "\n", ans2
	return str(E[n][m]) if ans1 == ans2 else "Fegla Won"

def remove_newline(strings):
	ans = []
	for s in strings:
		if s[-1] == "\n":
			ans.append(s[:-1])
		else:
			ans.append(s)
	return ans

def get_ans(N, strings, num):
	strings = remove_newline(strings)
	ans = min_cost(strings[0], strings[1])
	return "Case #%d: %s" % (num, ans)

def make_output(ans):
	file_name = "%s/small.out" % os.getcwd()
	f = open(file_name, "w")
	f.write(ans)
	f.close()

def main():
	ans = ""
	f = read_input()
	
	T = int(f.readline())
	for i in range(T):
		N = int(f.readline())
		strings = []
		for j in range(N):
			strings.append(f.readline())
		ans += get_ans(N, strings, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

# main()
print get_ans(2, ["mmaw", "maw"], 1)
print get_ans(2, ["exponential", "polynomial"], 2)