with open('triangles.in', 'r') as fin:
    data = [tuple(int(num) for num in line.strip().split()) for line in fin.readlines()]

N = data[0][0]
posts = data[1:]
print(posts)
