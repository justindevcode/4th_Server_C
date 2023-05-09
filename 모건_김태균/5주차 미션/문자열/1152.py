#단어의 개수
a = input()
a = a.strip()
a += " ";
count = 0
for i in a:
    if i == " ":
        count += 1
if a == " ":
    print(0)
else :
    print(count)