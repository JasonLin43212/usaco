"""
ID: jasonli7
LANG: PYTHON3
PROG: barn1
"""
def get_big_gap(boards, cows):
    biggest_gap = (0,0)
    big_gap_board = (0,0)
    for board in boards:
        start, end = board
        gap_start = None
        for cow_index in range(start, end):
            if cows[cow_index] == 0:
                if gap_start == None:
                    gap_start = cow_index
            else:
                if gap_start:
                    if biggest_gap[1] - biggest_gap[0] < cow_index - gap_start:
                        biggest_gap = (gap_start, cow_index)
                        big_gap_board = board
                    gap_start = None
    return big_gap_board, biggest_gap

def split_board(board, gap):
    return {(board[0], gap[0]), (gap[1], board[1])}

with open('barn1.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

M, S, C = [int(num) for num in data[0].split()]
cows = [0] * S
min_num = S
max_num = 1

for num in data[1:]:
    cow_index = int(num)
    cows[cow_index - 1] = 1
    min_num = min(min_num, cow_index-1)
    max_num = max(max_num, cow_index)

boards = {(min_num, max_num)}


while len(boards) < M:
    board, big_gap = get_big_gap(boards, cows)
    if big_gap == (0,0):
        break
    new_boards = split_board(board, big_gap)
    boards.discard(board)
    boards |= new_boards

total_covered = 0
for board in boards:
    total_covered += board[1] - board[0]


output = str(total_covered)
with open('barn1.out', 'w') as fout:
    fout.write(output + "\n")
#NOTE:

"""
Instead of trying to find what boards to use to cover, you find the M-1 empty boards
"""
