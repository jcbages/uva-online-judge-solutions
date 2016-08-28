import os

completed = []
with open('./completed.txt') as f:
	for line in f:
		completed.append(line.strip())

missing = []
with open('./missing.txt') as f:
	for line in f:
		missing.append(line.strip())

problems = []
for line in os.listdir('./../'):
	if line.endswith('.java') or line.endswith('.c') or line.endswith('.cpp'):
		problems.append(line.split('-')[0].strip())

for prob in problems:
	if prob not in completed:
		print '%-5s is done but not uploaded.' % prob
	if prob in missing:
		print '%-5s is in missing list, thats not right.' % prob

for prob in completed:
	if prob not in problems:
		print '%-5s is missing implementation.' % prob