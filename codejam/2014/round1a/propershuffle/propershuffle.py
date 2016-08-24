import os
import random

def read_input():
	file_name = "%s/small.in" % os.getcwd()
	return open(file_name, "r")

def good_algo(N):
	a = [0 for x in range(N)]
	for k in range(N):
		a[k] = k
	for k in range(N):
		p = random.randint(k, N-1)
		a[k], a[p] = a[p], a[k]
	return a

def bad_algo(N):
	a = [0 for x in range(N)]
	for k in range(N):
		a[k] = k
	for k in range(N):
		p = random.randint(0, N-1)
		a[k], a[p] = a[p], a[k]
	return a

def get_set(N, algo_type, top):
	ans = []
	for i in range(top):
		if algo_type == "good": ans.append(good_algo(N))
		elif algo_type == "bad": ans.append(bad_algo(N))
	return ans

def merge_sort_with_inv(array, pos):
	ans = []
	inv = 0
	if not array or len(array) <= 1:
		ans = array
		inv = 0
	else:
		part_1, inv_1 = merge_sort_with_inv(array[: len(array) / 2], pos)
		part_2, inv_2 = merge_sort_with_inv(array[len(array) / 2 :], pos)

		ans, inv = sort(part_1, part_2, pos)
		inv += inv_1 + inv_2
	return ans, inv

def sort(array1, array2, pos):
	ans = []
	inv = 0
	i, j = 0, 0
	while i < len(array1) or j < len(array2):
		if i < len(array1) and j < len(array2):
			if pos[array1[i]] <= pos[array2[j]]:
				ans.append(array1[i])
				i += 1
			else:
				ans.append(array2[j])
				j += 1
				inv += len(array1) - i
		elif i < len(array1):
			ans.append(array1[i])
			i += 1
		else:
			ans.append(array2[j])
			j += 1
	return ans, inv

def dist(x, y):
	ans = 0
	pos = {}
	for i in range(len(y)): pos[y[i]] = i
	arr, inv = merge_sort_with_inv(x, pos)
	return inv

def get_distances(a, goods, bads):
	ans = { "GOOD": [], "BAD": [] }
	n = len(goods)
	for i in range(n):
		ans["GOOD"].append(dist(a, goods[i]))
		ans["BAD"].append(dist(a, bads[i]))
	return ans

def get_score(ans):
	score_good = (sum(ans["GOOD"]) + 0.0) / (len(ans["GOOD"]) + 0.0)
	score_bad = (sum(ans["BAD"]) + 0.0) / (len(ans["BAD"]) + 0.0)
	return score_good, score_bad

def get_ans(N, a, goods, bads, i):
	ans = ""
	distances = get_distances(a, goods, bads)
	score_good, score_bad = get_score(distances)
	print score_good, score_bad
	if score_good > score_bad:
		ans = "GOOD"
	elif score_good < score_bad:
		ans = "BAD"
	else:
		print "RAND"
		choice = random.randint(0, 1)
		if choice == 0:
			ans = "GOOD"
		else:
			ans = "BAD"
	return "Case #%d: %s" % (i, ans)

def make_output(ans):
	file_name = "%s/small.out" % os.getcwd()
	f = open(file_name, "w")
	f.write(ans)
	f.close()

def main():
	ans = ""
	f = read_input()
	
	T = int(f.readline())
	goods, bads = None, None
	for i in range(T):
		print i
		N = int(f.readline())
		if goods is None:
			goods, bads = get_set(N, "good", 100), get_set(N, "bad", 100)
		a = [int(x) for x in f.readline().split()]
		ans += get_ans(N, a, goods, bads, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()

# N = 3
# goods, bads = get_set(N, "good", 1000), get_set(N, "bad", 1000)
# a = [0, 1, 2]
# print get_ans(N, a, goods, bads, 1)
# b = [2, 0, 1]
# print get_ans(N, b, goods, bads, 2)