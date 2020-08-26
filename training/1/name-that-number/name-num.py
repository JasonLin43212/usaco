"""
ID: jasonli7
LANG: PYTHON3
PROG: namenum
"""
with open('dict.txt', 'r') as name_file:
    names = {line.strip() for line in name_file.readlines()}

with open('namenum.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = data[0]

num_to_letter = {
    '2': ['A', 'B', 'C'],
    '3': ['D', 'E', 'F'],
    '4': ['G', 'H', 'I'],
    '5': ['J', 'K', 'L'],
    '6': ['M', 'N', 'O'],
    '7': ['P', 'R', 'S'],
    '8': ['T', 'U', 'V'],
    '9': ['W', 'X', 'Y'],
}

generated_names = [letter for letter in num_to_letter[N[0]]]
for number in N[1:]:
    generated_names = [base+next_letters for base in generated_names for next_letters in num_to_letter[number]]

valid_names = list(filter(lambda x: x in names, generated_names))

output = ''

for name in valid_names:
    output += name + '\n'

if output == '':
    output = 'NONE\n'

with open('namenum.out', 'w') as fout:
    fout.write (output)
# NOTE:
"""
Instead of looping through all possible generated names and comparing it to the dict.txt
we can do it the other way around which is faster.
"""
