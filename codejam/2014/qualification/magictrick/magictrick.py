import os

def read_input():
	file_name = "%s/small.in" % os.getcwd()
	return open(file_name, "r")

def readrow(f):
	ans = None
	selection = int(f.readline())
	for i in range(4):
		if i+1 == selection:
			ans = [int(x) for x in f.readline().split()]
		else:
			f.readline()
	return ans

def get_ans(r1, r2, i):
	choices = []
	for c1 in r1:
		for c2 in r2:
			if c1 == c2: choices.append(c1)

	if len(choices) > 1:
		return "Case #%d: Bad magician!" % i
	elif len(choices) == 0:
		return "Case #%d: Volunteer cheated!" % i
	else:
		return "Case #%d: %d" % (i, choices[0])

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
		r1 = readrow(f)
		r2 = readrow(f)
		ans += get_ans(r1, r2, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()