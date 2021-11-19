# Collatz Conjecture
# Even -> n = n/2
# Odd -> n = n*3 + 1

n = 1000


def check(last3, index):
    if last3[index] == 4 and last3[index-1] == 1 and last3[index-2] == 2:
        return True
    return False

for i in range (1, n):
    last3 = [None, None, None]
    index = 0
    aux = i
    while True:
        if aux == 1:
            print(i, "->", check(last3, index))
            break
        if aux % 2 == 0:
            aux = aux // 2
        else:
            aux = aux * 3 + 1
        last3[index] = aux
        index = index + 1
        index = index % 3

exit()

for i in range (1, n):
    aux = i
    print(i, "->", end=' ')
    while True:
        print(aux, end=' ')
        if aux == 1:
            break
        if aux % 2 == 0:
            aux = aux // 2
        else:
            aux = aux * 3 + 1
    print('\n\n')
