#색종이
n = int(input())
a = [[0 for j in range(101)] for i in range(101)]
for i in range(n):
    x, y = map(int, input().split())
    for j in range(10):
        for k in range(10):
            a[x + j][y + k] = 1
result = 0
for i in a:
    result += sum(i)
print(result)