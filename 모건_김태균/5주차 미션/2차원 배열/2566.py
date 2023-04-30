#최댓값
a = [[0 for col in range(9)] for row in range(9)]
result = 0
r = 1
c = 1

for i in range(9):
    b = list(map(int, input().split()))
    for j in range(9):
        a[i][j] = b[j]
        if result < a[i][j]:
            result = a[i][j]
            r = i + 1
            c = j + 1
print(result)
print(r, c)
