import os

def read_input():
	file_name = "%s/large.in" % os.getcwd()
	return open(file_name, "r")

def get_max_nodes(s, graph, par):
	sub_ans = []
	if len(graph[s]) <= 1 and not par:
		return 1
	elif len(graph[s]) <= 2 and par:
		return 1
	else:
		for w in graph[s]:
			if w != par:
				sub_ans.append(get_max_nodes(w, graph, s))
	max_1 = max(sub_ans)
	sub_ans.remove(max_1)
	max_2 = max(sub_ans)
	return max_1 + max_2 + 1

def get_ans(graph, i):
	ans = -1
	for v in graph:
		sub_ans = get_max_nodes(v, graph, None)
		if ans == -1 or sub_ans > ans: ans = sub_ans
	return "Case #%d: %d" % (i, len(graph) - ans)

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
		graph = {}
		N = int(f.readline())
		for j in range(N-1):
			target, dest = (int(x) for x in f.readline().split())
			if target not in graph: graph[target] = {}
			if dest not in graph: graph[dest] = {}
			graph[target][dest] = True
			graph[dest][target] = True
		ans += get_ans(graph, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()

# graph = {
# 	1: { 2: True, 3: True },
# 	2: { 1: True },
# 	3: { 1: True }
# }
# print get_ans(graph, 1)
# graph = {
# 	4: { 5: True, 2: True, 6: True },
# 	5: { 4: True },
# 	2: { 4: True, 1: True },
# 	1: { 2: True, 3: True },
# 	3: { 1: True, 7: True },
# 	6: { 4: True },
# 	7: { 3: True }
# }
# print get_ans(graph, 2)
# graph = {
# 	1: { 2: True },
# 	2: { 1: True, 3: True },
# 	3: { 2: True, 4: True },
# 	4: { 3: True }
# }
# print get_ans(graph, 3)