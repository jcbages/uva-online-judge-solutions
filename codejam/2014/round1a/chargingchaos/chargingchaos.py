import os

def read_input():
	file_name = "%s/small.in" % os.getcwd()
	return open(file_name, "r")

def swap(i, outlets):
	ans = []
	for outlet in outlets:
		list_outlet = list(outlet)
		if list_outlet[i] == "1":
			list_outlet[i] = "0"
		else:
			list_outlet[i] = "1"
		ans.append("".join(list_outlet))
	return ans

def get_ans(N, L, devices, outlets, num):
	ans = 0
	for i in range(L-1, -1, -1):
		sub_devices = sorted([x[i:] for x in devices])
		sub_outlets = sorted([x[i:] for x in outlets])

		print sub_devices
		print sub_outlets

		if sub_devices != sub_outlets:
			ans += 1
			outlets = swap(i, outlets)
			sub_outlets = sorted([x[i:] for x in outlets])

			print "Swap..."
			print sub_devices
			print sub_outlets

			if sub_devices != sub_outlets:
				return "Case #%d: NOT POSSIBLE" % num	
	return "Case #%d: %d" % (num, ans)

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
		N, L = (int(x) for x in f.readline().split())
		devices = f.readline().split()
		outlets = f.readline().split()
		ans += get_ans(N, L, devices, outlets, i+1)
		if i < T-1: ans += "\n"

	f.close()
	make_output(ans)

# main()

# N, L = [3, 2]
# devices = ["01", "11", "10"]
# outlets = ["11", "00", "10"]
# print get_ans(N, L, devices, outlets, 1)
# N, L = [2, 3]
# devices = ["101", "111"]
# outlets = ["010", "001"]
# print get_ans(N, L, devices, outlets, 2)
# N, L = [2, 2]
# devices = ["01", "10"]
# outlets = ["10", "01"]
# print get_ans(N, L, devices, outlets, 3)
N, L = [10, 10]
devices = ["0000101111", "1010001100", "1101011001", "1111110010", "0100111011", "1110000101", "0110000100", "0001110100", "1001101011", "0011010010"]
outlets = ["0000000100", "1010111101", "1001000000", "0110011101", "0100110110", "0011100011", "1101010100", "1111101011", "1000011011", "0111101010"]
print get_ans(N, L, devices, outlets, 3)