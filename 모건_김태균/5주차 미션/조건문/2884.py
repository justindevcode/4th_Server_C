#알람 시계
a, b = input("").split()
a = int(a)
b = int(b)

if b >= 45:
    print(a, b - 45)
elif a == 0:
    print(23, (15 + b))
else:
    print((a - 1), (15 + b))