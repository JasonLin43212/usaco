"""
ID: jasonli7
LANG: PYTHON3
PROG: friday
"""
with open('friday.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

n = int(data[0])

def is_leap(year):
    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    return year % 4 == 0

def compute_friday(first_date, month, year):
    leap = is_leap(year)
    if month == 2:
        num_days = 29 if leap else 28
    elif month in {4, 6, 9, 11}:
        num_days = 30
    else:
        num_days = 31
    return (first_date + 12) % 7, (first_date + num_days) % 7

def get_friday_frequency(n):
    friday_freq = [0, 0, 0, 0, 0, 0, 0]
    first_date = 2
    month = 1
    year = 1900

    while year < 1900 + n:
        fri_index, first_date = compute_friday(first_date, month, year)
        friday_freq[fri_index] += 1
        month += 1
        if month == 13:
            month = 1
            year += 1

    return friday_freq



freq = get_friday_frequency(n)
output = ''

for num in freq:
    output += f'{num} '
output = output[:-1]

with open('friday.out', 'w') as fout:
    fout.write (output + '\n')
