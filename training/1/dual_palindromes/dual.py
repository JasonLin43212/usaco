"""
ID: jasonli7
LANG: PYTHON3
PROG: dualpal
"""

# 2 <= base <= 10
def convert_bases(num, base):
    converted_num = ''
    while num != 0:
        converted_num = str(num % base) + converted_num
        num = num // base
    return converted_num


with open('dualpal.in', 'r') as fin:
    data = fin.readline()

first_line = data.split()
N, S = int(first_line[0]), int(first_line[1])

dual_pal_nums = []

i = S + 1

while len(dual_pal_nums) < N:
    num_pal = 0
    for base in range(2, 12):
        if num_pal >= 2:
            dual_pal_nums.append(str(i))
            break
        converted = convert_bases(i, base)
        if converted == converted[::-1]:
            num_pal += 1
    i += 1

output = '\n'.join(dual_pal_nums)
with open('dualpal.out', 'w') as fout:
    fout.write(output + "\n")
