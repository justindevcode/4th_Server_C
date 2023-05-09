#공 넣기
a, b = map(int, input().split())
c = [0 for i in range(a)]
for i in range(b):
    x, y, z = map(int, input().split())
    for j in range(x - 1, y):
        c[j] = z
for k in c:
    print(k, end=" ")