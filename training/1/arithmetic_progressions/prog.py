"""
ID: jasonli7
LANG: PYTHON3
PROG: ariprog
"""
with open('ariprog.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N, M = int(data[0]), int(data[1])

max_num = 2 * M**2

bisquares = {p**2 + q**2 for p in range(M + 1) for q in range(M + 1) }

def valid_sequence(a, b):
    for n in range(1, N):
        if a + b*n not in bisquares:
            return False
    return True

sequences = []

for a in range(max_num):
    if a in bisquares:
        for b in range(1, max_num // (N-1) + 1):
            if valid_sequence(a, b):
                sequences.append((a, b))

if len(sequences) == 0:
    output = 'NONE\n'
else:
    sequences.sort(key=lambda x: (x[1], x[0]))

    output = ''

    for seq in sequences:
        output += f'{seq[0]} {seq[1]}\n'

with open('ariprog.out', 'w') as fout:
    fout.write(output)
