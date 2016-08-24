import os

def read_input():
	file_name = "%s/large.in" % os.getcwd()
	return open(file_name, "r")

def get_val_for(bricks, winner, looser):
	ans = 0
	while bricks > 0:
		if winner[0] < looser[0]:
			del winner[0]
			del looser[bricks-1]
		else:
			del winner[0]
			del looser[0]
			ans += 1
		bricks -= 1
	return ans

def get_ans(bricks, naomi, ken, i):
	naomi, ken = sorted(naomi), sorted(ken)
	deceitful = get_val_for(bricks, naomi[:], ken[:])
	war = bricks - get_val_for(bricks, ken[:], naomi[:])
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