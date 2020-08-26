"""
ID: jasonli7
LANG: PYTHON3
PROG: milk
"""

with open('milk.in', 'r') as fin:
    data = [tuple(int(num) for num in line.strip().split()) for line in fin.readlines()]

N, M = data[0]
farmers = sorted(data[1:])

total_milk = 0
total_cost = 0

for farmer in farmers:
    cost, units = farmer
    if units + total_milk >= N:
        total_cost += (N - total_milk) * cost
        total_milk += N
        break
    else:
        total_cost += units * cost
        total_milk += units


output = str(total_cost)
with open('milk.out', 'w') as fout:
    fout.write(output + "\n")
#note
"""
Count sort is a thing
"""
