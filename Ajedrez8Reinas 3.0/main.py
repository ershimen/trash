import time

n_sol = 0

def print_tablero(tablero):
    global n_sol
    print("\nsol ", n_sol, ":\n\t+", "---+"*8, sep='')
    for i in range(8):
        print("\t|", end='')
        for j in range(8):
            if j == tablero[i]:
                print(" # |", end='')
            else:
                print("   |", end='')
        print("\n\t+", "---+"*8, sep='')

# Probar si se puede colocar la reina en la columna n, fila j
def check(tablero, n, j):
    for i in range(n):              # probar cada una de las otras reinas
        if tablero[i] != -1 and (   # celdas con ficha
            tablero[i] == j or      # misma fila
            tablero[i]-j == n-i or  # misma diagonal ascendente
            j-tablero[i] == n-i):   # misma diagonal descendente
            return False
    return True

# Intentar colocar reinas en las columnas >= n
def solve(tablero, n):
    global n_sol
    if n == 8:
        n_sol = n_sol + 1
        print_tablero(tablero)
    else:
        for i in range(8): # probar a colocar cada reina
            if check(tablero, n, i):
                tablero[n] = i
                solve(tablero, n + 1)

def main():
    tablero = [-1]*8
    t0 = time.time()
    solve(tablero, 0)
    print("\nFinalizado en %dms" % round((time.time() - t0) * 1000))

if __name__ == '__main__':
    main()
