#평균
n = int(input())
a = list(map(int,input().split()))
a.sort(reverse=True)
bigOn = a[0]
s=0

for i in range(0,n):
    a[i] = a[i] / bigOn * 100
    s += a[i]
print(s/n)