import os
import random
PATH = os.getcwd()
with open(PATH+'/test2.in', 'w') as f:
	for i in range(100):
		N = random.randint(1, 100)
		m = random.randint(0, 100)
		k = random.randint(0, 100)
		f.write('%d %d %d\n' % (N, m, k))
	f.write('0 0 0\n')