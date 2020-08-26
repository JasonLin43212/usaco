"""
ID: jasonli7
LANG: PYTHON3
PROG: transform
"""
def is_grid_equal(grid_1, grid_2):
    for row_1, row_2 in zip(grid_1, grid_2):
        for col_1, col_2 in zip(row_1, row_2):
            if col_1 != col_2:
                return False
    return True

def rotate_90(grid):
    new_grid = []
    for row in range(len(grid)):
        new_row = []
        for col in range(len(grid)):
            new_row.append(grid[len(grid) - 1 - col][row])
        new_grid.append(new_row)
    return new_grid

def mirror(grid):
    new_grid = [row[::-1] for row in grid]
    return new_grid

with open('transform.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
grid_1 = [[cell for cell in line] for line in data[1:N+1]]
grid_2 = [[cell for cell in line] for line in data[N+1:2*N+1]]

rot_90 = rotate_90(grid_1)
rot_180 = rotate_90(rot_90)
rot_270 = rotate_90(rot_180)
refl = mirror(grid_1)
refl_90 = mirror(rot_90)
refl_180 = mirror(rot_180)
refl_270 = mirror(rot_270)

transforms = [rot_90, rot_180, rot_270, refl]
combs = [refl_90, refl_180, refl_270]

number = None

if not number:
    for i, transform in enumerate(transforms):
        if is_grid_equal(transform, grid_2):
            number = i + 1
            break

if not number:
    for comb in combs:
        if is_grid_equal(comb, grid_2):
            number = 5
            break

if not number:
    if is_grid_equal(grid_1, grid_2):
        number = 6

if not number:
    number = 7


output = f'{number}'
with open('transform.out', 'w') as fout:
    fout.write (output + '\n')
#NOTE:
"""
90 degree clockwise rotation new_grid[r][c] = grid[c][n-r]
"""
