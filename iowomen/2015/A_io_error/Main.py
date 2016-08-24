T = int(raw_input())
for t in range(T):
	B = raw_input()
	string = raw_input()
	exp = 7
	num = 0
	ans = 'Case #%d: ' % (t+1)
	for c in string:
		if exp == -1:
			exp = 7
			ans += chr(num)
			num = 0
		num += 2 ** exp if c == 'I' else 0
		exp -= 1
	ans += chr(num)
	print ans