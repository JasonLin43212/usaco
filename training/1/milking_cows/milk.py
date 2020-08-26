"""
ID: jasonli7
LANG: PYTHON3
PROG: milk2
"""
class TimeBlock:

    def __init__(self, start, end):
        self.start = start
        self.end = end

    def merge(self, blocks):
        start = None
        end = None
        blocks = blocks[:]
        blocks.append(self)
        for block in blocks:
            if start == None:
                start = block.start
                end = block.end
            else:
                start = min(start, block.start)
                end = max(end, block.end)
        return TimeBlock(start, end)

    def overlap(self, other):
        if other.start < self.start:
            return other.end >= self.start
        return other.start <= self.end

    def __str__(self):
        return f'(Start: {self.start}, End: {self.end})'

class Schedule:

    def __init__(self):
        self.schedule = []

    def add_time(self, start, end):
        block = TimeBlock(start, end)
        if len(self.schedule) == 0:
            self.schedule.append(block)
        else:
            overlapped_blocks = list(filter(lambda x: block.overlap(x), self.schedule))
            new_block = block.merge(overlapped_blocks)
            for oldBlock in overlapped_blocks:
                self.schedule.remove(oldBlock)
            self.schedule.append(new_block)

    def __str__(self):
        output = "["
        for block in self.schedule:
            output += str(block)
        return output + "]"

with open('milk2.in', 'r') as fin:
    data = [line.strip() for line in fin.readlines()]

N = int(data[0])
sched = Schedule()

for times in data[1:]:
    start, end = [int(time) for time in times.split()]
    sched.add_time(start, end)

max_milk = 0
max_rest = 0

blocks = sched.schedule
blocks.sort(key=lambda x: x.start)

for block in blocks:
    max_milk = max(max_milk, block.end - block.start)

for block_1, block_2 in zip(blocks[:-1], blocks[1:]):
    max_rest = max(max_rest, block_2.start - block_1.end)

output = f'{max_milk} {max_rest}'
with open('milk2.out', 'w') as fout:
    fout.write (output + '\n')


# NOTE:
"""
Better Idea (Maybe): Create a list and add in TimeBlock objects
Sort it by start time
Go through it and merge one at a time
Find the max times
"""
