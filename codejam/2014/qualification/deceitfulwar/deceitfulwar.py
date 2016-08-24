import os

def read_input():
	file_name = "%s/large.in" % os.getcwd()
	return open(file_name, "r")

def init_matrix(n, naomi, ken):
	M = []
	for i in range(n):
		M.append([0 for x in range(n)])
	return M

def get_val_for(winner, looser, bricks):
	M = init_matrix(bricks+1, winner, looser)
	for i in range(1, len(M)):
		for j in range(1, len(M)):
			if winner[i-1] > looser[j-1]:
				M[i][j] = 1 + M[i-1][j-1]
			else:
				M[i][j] = max(M[i-1][j], M[i][j-1])
	return M[bricks][bricks]

def get_ans(bricks, naomi, ken, i):
	naomi, ken = sorted(naomi), sorted(ken)
	deceitful = get_val_for(naomi, ken, bricks)
	war = bricks - get_val_for(ken, naomi, bricks)
	return "Case #%d: %d %d" % (i, deceitful, war)

def make_output(ans):
	file_name = "%s/large.out" % os.getcwd()
	f = open(file_name, "w")
	f.write(ans)
	f.close()

def main():
	ans = ""
	f = read_input()
	
	T = int(f.readline())
	for i in range(T):
		bricks = int(f.readline())
		naomi = [float(x) for x in f.readline().split()]
		ken = [float(x) for x in f.readline().split()]
		ans += get_ans(bricks, naomi, ken, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()

# b = 1
# n = [0.5]
# k = [0.6]
# print get_ans(b, n, k, 1)
# b = 2
# n = [0.7, 0.2]
# k = [0.8, 0.3]
# print get_ans(b, n, k, 2)
# b = 3
# n = [0.5, 0.1, 0.9]
# k = [0.6, 0.4, 0.3]
# print get_ans(b, n, k, 3)
# b = 9
# n = [0.186, 0.389, 0.907, 0.832, 0.959, 0.557, 0.300, 0.992, 0.899]
# k = [0.916, 0.728, 0.271, 0.520, 0.700, 0.521, 0.215, 0.341, 0.458]
# print get_ans(b, n, k, 4)