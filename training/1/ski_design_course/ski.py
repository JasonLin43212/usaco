"""
ID: jasonli7
LANG: PYTHON3
PROG: skidesign
"""
def calc_price(hills, new_min, new_max):
    raise_hills = filter(lambda x: x < new_min, hills)
    lower_hills = filter(lambda x: x > new_max, hills)
    price = 0
    for hill in raise_hills:
        price += (new_min - hill) ** 2

    for hill in lower_hills:
        price += (hill - new_max) ** 2

    return price


with open('skidesign.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
hills = sorted([int(line) for line in data[1:]])

min = hills[0]
max = hills[-1]
change_need = max - min - 17

min_price = None

if change_need <= 0:
    min_price = 0
else:
    for i in range(change_need + 1):
        price = calc_price(hills, min + i, max - (change_need - i))
        if not min_price or price < min_price:
            min_price = price

output = str(min_price)
with open('skidesign.out', 'w') as fout:
    fout.write(output + "\n")
