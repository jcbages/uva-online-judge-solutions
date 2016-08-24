import os
import random
PATH = os.getcwd()
with open(PATH+'/test2.in', 'w') as f:
	N = random.randint(1, 100)
	Q = random.randint(1, 100)
	f.write('%d %d\n' % (N, Q))
	vertices = [(x+1) for x in range(N)]
	random.shuffle(vertices)
	for i in range(N-1):
		u = vertices[random.randint(0, i)]
		v = vertices[random.randint(i+1, N-1)]
		f.write('%d %d\n' % (u, v))
	vertices = [(x+1) for x in range(Q)]
	random.shuffle(vertices)
	for i in range(Q-1):
		u = vertices[random.randint(0, i)]
		v = vertices[random.randint(i+1, Q-1)]
		f.write('%d %d\n' % (u, v))