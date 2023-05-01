#문자열 반복
a = int(input())
for k in range(0,a):
    b, c = input().split()
    d = []
    for i in c:
        d.append(i)
    e = ""
    for j in d:
        e += j * int(b)
    print(e)
