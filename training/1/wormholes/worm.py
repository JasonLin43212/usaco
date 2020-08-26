"""
ID: jasonli7
LANG: PYTHON3
PROG: wormhole
"""
def has_cycle(holes, pairs, y_connect):
    for hole in holes:
        # Travel across
        cur_hole = y_connect.get(hole, None)
        while cur_hole:
            # Teleport
            next_hole = pairs[cur_hole]

            if next_hole == hole:
                return True
            # Travel across again
            cur_hole = y_connect.get(next_hole, None)

    return False

with open('wormhole.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
holes = []
for line in data[1:]:
    x, y = line.split()
    holes.append((int(x), int(y)))

ys = {}
for hole in holes:
    y_list = ys.get(hole[1], [])
    y_list.append(hole)
    ys[hole[1]] = y_list

y_connect = {}

for y_list in ys.values():
    sorted_y_list = sorted(y_list, key=lambda x: x[0])
    for hole_1, hole_2 in zip(sorted_y_list[:-1], sorted_y_list[1:]):
        y_connect[hole_1] = hole_2


def pair_compute(remaining_holes, cur_pairs):
    if len(remaining_holes) == 0:
        yield cur_pairs

    else:
        hole_1 = remaining_holes[0]
        for hole_2 in remaining_holes[1:]:
            new_remain_holes = remaining_holes[:]
            new_pairs = cur_pairs.copy()
            new_pairs[hole_1] = hole_2
            new_pairs[hole_2] = hole_1
            new_remain_holes.remove(hole_1)
            new_remain_holes.remove(hole_2)
            yield from pair_compute(new_remain_holes, new_pairs)


num_cycles = 0
for pairings in pair_compute(holes,{}):
    if has_cycle(holes, pairings, y_connect):
        num_cycles += 1

output = str(num_cycles)
with open('wormhole.out', 'w') as fout:
    fout.write(output + "\n")

#NOTE:
"""
Instead of having a dict that pairs the worm holes, I can use the indicies of a list of size 12 to represent
each wormhole and then another list where the indicies indicate which wormhole it is paired to

When doing recursion, you can reset the mutable thing back to its original state instead of creating a copy
"""
