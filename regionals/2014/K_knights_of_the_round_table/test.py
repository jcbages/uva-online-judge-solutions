import random
import os
f = open(os.getcwd() + "/test.in", "w")
K = 1000000;
D = 100000
for j in range(2):
	f.write(str(K) + " " + str(D) + "\n")
	for i in range(D):
		a = random.randint(1, K)
		b = random.randint(1, K)
		f.write(str(a) + " " + str(b) + "\n")
f.close()