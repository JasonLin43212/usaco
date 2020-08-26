"""
ID: jasonli7
LANG: PYTHON3
PROG: palsquare
"""
num_to_string = '0123456789ABCDEFGHIJ'

def convert_bases(num, base):
    converted_num = ''
    while num != 0:
        converted_num = num_to_string[num % base] + converted_num
        num = num // base
    return converted_num


with open('palsquare.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

B = int(data[0])
output = ''

for i in range(1, 301):
    converted = convert_bases(i**2, B)
    if converted == converted[::-1]:
        output += f'{convert_bases(i, B)} {converted}\n'

with open('palsquare.out', 'w') as fout:
    fout.write(output)
