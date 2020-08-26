"""
ID: jasonli7
LANG: PYTHON3
TASK: test
"""
with open('test.in', 'r') as fin:
    x,y = map(int, fin.readline().split())
    sum = x+y

with open('test.out', 'w') as fout:
    fout.write (str(sum) + '\n')
