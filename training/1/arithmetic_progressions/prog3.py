"""
ID: jasonli7
LANG: PYTHON3
PROG: ariprog
"""
import time
start_time = time.time()
with open('ariprog.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N, M = int(data[0]), int(data[1])

bisquares_set = {p*p + q*q for p in range(M + 1) for q in range(M + 1)}
bisquares = list(bisquares_set)
bisquares.sort()

print(len(bisquares_set))
sequences = []
num_calc = 0

print("time 1:", time.time() - start_time)

for i, start in enumerate(bisquares[:-(N-1)]):
    for next_num in bisquares[i+1:-(N-2)]:
        b = next_num - start
        is_valid = True
        if start + (N-1) * b > bisquares[-1]:
            is_valid = False
            num_calc += 1
        else:
            for n in range(2, N):
                num_calc += 1
                if start + n * b not in bisquares_set:
                    is_valid = False
                    break
        # for n in range(2, N):
        #     num_calc += 1
        #     if start + n * b not in bisquares_set:
        #         is_valid = False
        #         break

        if is_valid:
            sequences.append((start, b))
print(num_calc)
sequences.sort(key=lambda x: (x[1], x[0]))

if len(sequences) == 0:
    output = 'NONE\n'
else:
    sequences.sort(key=lambda x: (x[1], x[0]))

    output = ''

    for seq in sequences:
        output += f'{seq[0]} {seq[1]}\n'

print("time 2:", time.time() - start_time)

with open('ariprog.out', 'w') as fout:
    fout.write(output)
