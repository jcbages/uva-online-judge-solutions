import os

def read_input():
	file_name = "%s/small.in" % os.getcwd()
	return open(file_name, "r")

def init_confs(R, C):
	k = "0" * (R * C)
	return { 0: { k: True } }

def is_valid_conf(new_conf):
	for c in new_conf:
		if c == "0": return True
	return False

def get_new_conf(conf, i):
	list_conf = list(conf)
	list_conf[i] = "X"
	for j in range(-4, 5):
		if (j < 0 and i+j >= 0) or (j > 0 and i+j < len(conf)):
			if list_conf[i+j] != "X":
				list_conf[i+j] = str(int(list_conf[i+j])+1)
	return "".join(list_conf)

def add_games(conf, m, confs):
	for i in range(len(conf)):
		if conf[i] != "X":
			new_conf = get_new_conf(conf, i)
			if new_conf not in confs[m]:
				confs[m][new_conf] = is_valid_conf(new_conf)

def add_confs(m, confs):
	confs[m] = {}
	prevs = confs[m-1]
	for conf in prevs:
		if prevs[conf]:
			add_games(conf, m, confs)

def numofones(configs):
	acc = -1
	res = ""
	for c in configs:
		ans = 0
		for char in c:
			if char == "X":
				ans += 1
		if acc == -1:
			acc = ans
		if ans != acc:
			res = str(ans) + "-" + str(False)
	if len(res) > 0:
		pass
		#print res + " -> len is: " + str(len(configs))
	else:
		pass
		#print str(acc) + "-" + str(True) + " -> len is: " + str(len(configs))

def get_ans(R, C, M, i):
	confs = init_confs(R, C)
	for m in range(1, M+1):
		add_confs(m, confs)
		numofones(confs[m])

	# final = confs[M]
	# for conf in final:
	# 	if final[conf]:
	# 		return "Case #%d:\n%s" % draw_conf(conf, R, C)
	return "Case #%d:\n%s" % (i, "Impossible")

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
		print i
		R, C, M = (int(x) for x in f.readline().split())
		ans += get_ans(R, C, M, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()
# get_ans(5, 5, 25, 1)