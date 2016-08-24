import os

def read_input():
	file_name = "%s/large.in" % os.getcwd()
	return open(file_name, "r")

def get_valX(X, f, F):
	return X * (1.0 / (2.0 + f * F))

def get_valC(C, f, F):
	return C * (1.0 / (2.0 + (f - 1) * F))

def get_ans(C, F, X, i):
	ans = -1
	found = False
	last_valC = 0
	f = 0
	while not found:
		if f == 0:
			actual = get_valX(X, f, F)
		else:
			last_valC += get_valC(C, f, F)
			actual = last_valC + get_valX(X, f, F)

		if ans == -1 or actual < ans:
			ans = actual
		else:
			found = True

		f += 1

	return "Case #%d: %.7f" % (i, ans)

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
		C, F, X = (float(x) for x in f.readline().split())
		ans += get_ans(C, F, X, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

main()