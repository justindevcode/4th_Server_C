#A+B-4
a,b = input().split()
a= int(a)
b=int(b)

while a >0 and b >0 and a<10 and b<10:
    try:
        print(a + b)
        a, b = input().split()
        a = int(a)
        b = int(b)
    except:
        break