"""
ID: jasonli7
LANG: PYTHON3
PROG: combo
"""


with open('combo.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
farmer = tuple(int(num) for num in data[1].split())
master = tuple(int(num) for num in data[2].split())

def apply_delts(combo, delts):
    new_combo = []
    for c, d in zip(combo, delts):
        new_c = c + d
        if new_c < 1:
            new_c += N
        elif new_c > N:
            new_c -= N
        new_combo.append(new_c)
    return tuple(new_combo)

possible = set()

if N == 1:
    delts = [0]
elif N == 2:
    delts = [-1, 0, 1]
else:
    delts = [-2, -1, 0, 1, 2]

for d_1 in delts:
    for d_2 in delts:
        for d_3 in delts:
            cur_delts = (d_1, d_2, d_3)
            possible.add(apply_delts(farmer, cur_delts))
            possible.add(apply_delts(master, cur_delts))


output = str(len(possible))
with open('combo.out', 'w') as fout:
    fout.write(output + "\n")
#NOTE:
"""
I generated the combos. You can also filter it. Better because you can use a absolute value
so you don't have to deal with the delts
"""
