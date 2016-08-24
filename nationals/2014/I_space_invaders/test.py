import os
import random
PATH = os.getcwd()
SHIELD = ['#', '.']
with open(PATH+'/test3.in', 'w') as f:
	for i in range(10):
		r = random.randint(1, 10)
		c = random.randint(1, 10)
		s = random.randint(0, 100)
		f.write('%d %d %d\n' % (r, c, s))
		for j in range(r):
			line = ''.join([SHIELD[random.randint(0, 1)] for k in range(c)])
			f.write('%s\n' % line)
		for j in range(s):
			shoot = random.randint(1, c)
			f.write('%d\n' % shoot)