"""
ID: jasonli7
LANG: PYTHON3
PROG: ride
"""
alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
def calc_name_num(name):
    nums = [alphabet.index(letter) + 1 for letter in name]
    product = 1
    for num in nums:
        product *= num
    return product % 47

with open('ride.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]
    comet, group = data

comet_num = calc_name_num(comet)
group_num = calc_name_num(group)

if  comet_num == group_num:
    output = "GO"
else:
    output = "STAY"


with open('ride.out', 'w') as fout:
    fout.write(output + '\n')

# NOTES:
"""
I can use ord to get the integer value of a character
use .strip() for file reading
i can use map() function
use with open to open files
"""
