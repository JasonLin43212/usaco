"""
ID: jasonli7
LANG: PYTHON3
PROG: beads
"""
def get_length(left, right):
    seen = set()
    length = 0
    for beads in [left, right]:
        bead_color = None
        for bead in beads:
            id, color = bead

            if id in seen or (bead_color and color != 'w' and color != bead_color):
                break

            if not bead_color:
                if color != 'w':
                    bead_color = color

            if color == 'w' or bead_color:
                length += 1
                seen.add(id)

    return length




with open('beads.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

num_beads = int(data[0])
beads = [bead for bead in enumerate(data[1])] * 2


max_length = 0
for i in range(1, len(beads) - 1):
    total = get_length(beads[:i][::-1], beads[i:])
    max_length = max(total, max_length)



output = f'{max_length}'
with open('beads.out', 'w') as fout:
    fout.write (output + '\n')
