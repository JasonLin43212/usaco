"""
ID: jasonli7
LANG: PYTHON3
PROG: crypt1
"""
def valid_nums(num, avail):
    for i in str(num):
        if int(i) not in avail:
            return False
    return True

def is_sol(top, d, e, nums):
    partial_1 = top * e
    if partial_1 >= 1000 or not valid_nums(partial_1, nums):
        return False

    partial_2 = top * d
    if partial_2 >= 1000 or not valid_nums(partial_2, nums):
        return False

    product = partial_1 + (partial_2 * 10)

    if product >= 10000 or not valid_nums(product, nums):
        return False

    return True


with open('crypt1.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
avail_nums = {int(num) for num in data[1].split()}

num_sol = 0

for a in avail_nums:
    for b in avail_nums:
        for c in avail_nums:
            for d in avail_nums:
                for e in avail_nums:
                    if is_sol(a*100 + b * 10 + c, d, e, avail_nums):
                        num_sol += 1


output = str(num_sol)
with open('crypt1.out', 'w') as fout:
    fout.write(output + "\n")
