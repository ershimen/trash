# nonogram solver
import numpy as np
import time

def print_grid(grid, h, w):
    print("\n\t+", "---+"*w, sep='')
    for i in range(h):
        print("\t|", end='')
        for j in range(w):
            if grid[i][j]:
                print(" # |", end='')
            else:
                print("   |", end='')
        print("\n\t+", "---+"*w, sep='')


# Generar todas las combinaciones posibles en res de las secuencias en vals, en un array de longitud n
# Ejemplo: n=8, vals=[5]
#       # # # # # - - -
#       - # # # # # - -
#       - - # # # # # -
#       - - - # # # # #
# Ejemplo: n=10, vals=[1, 4, 1]
#       - - - - - - - - - -
#       # - # # # # - # - -
#       # - # # # # - - # -
#       # - # # # # - - - #
#       # - - # # # # - # -
#       # - - # # # # - - #
#       # - - - # # # # - #
#       - # - # # # # - # -
#       - # - # # # # - - #
#       - # - - # # # # - #
#       - - # - # # # # - #
def combinations(res, sol, sol_i, vals, vals_i, n):
    if len(vals) == 1 and vals[0] == 0: # si se buscan combinaciones de 0 conjuntos
        res.append(sol)
        return res
    if vals_i == len(vals): # Si se han insertado todas las secuencias, añadir al resultado
        res.append(sol)
        return res
    copy = sol.copy()
    while n-sol_i >= vals[vals_i]: # ver si cabe la secuencia
        for x in range(vals[vals_i]): # Añadir vals[vals_i] casillas en negro
            copy[sol_i+x] = True
        sol_i = sol_i + 1 # dejar un espacio en blanco
        # probar a insertar la siguiente secuenia o retornar
        combinations(res, copy, sol_i + vals[vals_i], vals, vals_i+1, n)
        copy = sol.copy() # resetear el array cuando se prueba otra combinacion
    return res


def get_n_combinations(vals):
    res = 1
    for e in vals:
        res = res * len(combinations([], [False for _ in range(len(vals))], 0, e, 0, len(vals)))
    return res


def solve(tablero, tablero_i, r_vals, r_vals_i, c_vals, rows, cols):
    if tablero_i == len(tablero): # combprobar si el tablero es valido
        for c in range(len(c_vals)): # por cada array de valores
            index = 0
            for c_i in c_vals[c]: # comprobar si estan todos los conjuntos
                # buscar el primero
                while index < rows and not tablero[:,c][index]:
                    index = index + 1
                if index == rows: # si no hay mas casillas negras, pero deberia
                    if c_i == 0: # si se buscan 0 casillas, ignorar
                        continue
                    return False
                # index es el indice con el primer cuadrado negro
                # comprobar que estan todos los cuadrados negros
                if rows-index < c_i: # si no hay mas espacio
                    return False
                for _ in range(c_i):
                    if not tablero[:,c][index]:
                        return False
                    index = index + 1
                # comprobar si hay un espacio en blanco
                if index < rows and tablero[:,c][index]:
                    return False
            # comprobar que no hay cuadrados negros de mas
            while index < rows:
                if tablero[:,c][index]:
                    return False
                index = index + 1
        print_grid(tablero, rows, cols)
        return True
    # construir el tablero
    for r in range(r_vals_i, len(r_vals)):
        res = combinations([], [False for _ in range(cols)], 0, r_vals[r], 0, cols)
        for res_i in res:
            tablero[tablero_i] = res_i
            solve(tablero, tablero_i+1, r_vals, r+1, c_vals, rows, cols)


def main():

    #col_vals = [[2, 3], [1, 2], [1, 1], [2], [2, 4], [3, 3], [1, 3], [1, 1, 1, 1], [6, 3], [6, 3]]
    #row_vals = [[2, 1, 3], [1, 1, 2], [6], [1, 1, 1, 2], [2, 3], [2, 1, 2], [3], [4, 2], [3, 2], [5]]
    col_vals = [[3], [2], [3], [2], [3]]
    row_vals = [[2], [3], [3, 1], [3], [1]]
    cols = len(col_vals)
    rows = len(row_vals)
    tablero = np.empty(shape=(rows, cols), dtype=object)

    yes_no = input("Se probarán " + str(get_n_combinations(row_vals)) + " combinaciones, ¿seguro que quieres continuar? [y/N] ")
    if yes_no == "N":
        return
    t0 = time.time()
    solve(tablero, 0, row_vals, 0, col_vals, rows, cols)
    print("\nFinalizado en %dms" % round((time.time() - t0) * 1000))


if __name__ == '__main__':
    main()
