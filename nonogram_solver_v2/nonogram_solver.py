"""Nonogram solver"""

from importlib.abc import TraversableResources
import numpy as np
import time

def print_grid2(grid, h, w):
    """Imprime el tablero.
    """
    print("\n\t+", "---+"*w, sep='')
    for i in range(h):
        print("\t|", end='')
        for j in range(w):
            if grid[i][j] == True:
                print(" # |", end='')
            elif grid[i][j] == False:
                print(" · |", end='')
            else:
                print("   |", end='')
        print("\n\t+", "---+"*w, sep='')

def cabe2(arr, i, n):
    """ Retorna True si la secuencia de longitud n cabe a partir de la posicion i de arr,
    False en caso contrario.
    """
    if i > 0 and arr[i-1]: # Si tiene un True detras
        return False
    index = i
    left = n
    while left != 0 and index < len(arr): # Ver si cabe
        if arr[index] == False:
            return False
        left = left - 1
        index = index + 1
    if left > 0 or (index < len(arr) and arr[index]): # Si tiene un True delante
        return False
    return True

def fill(arr):
    """Rellena las posiciones vacías (None) con False.
    """
    for i in range(len(arr)):
        if arr[i] is None: # Cambiar None por False
            arr[i] = False

def combinations2(res, sol, sol_i, vals, vals_i):
    """Obtiene todas las combinaciones posibles de las secuencias de longitud indicadas
    en vals."""
    if len(vals) == 1 and vals[0] == 0: # si se buscan combinaciones de 0 conjuntos
        fill(sol)
        res.append(sol)
        return res
    if vals_i == len(vals): # Si ya se han insertado todas las secuencias
        index = sol_i
        while index < len(sol): # Buscar a ver si hay mas True
            if sol[index] == True:
                return res
            index = index + 1
        fill(sol)
        res.append(sol)
        return res
    one_true = False
    while sol_i < len(sol):
        copy = sol.copy()
        cabe = cabe2(copy, sol_i, vals[vals_i])
        if copy[sol_i]: # Si hay un True significa que vals[vals_i] tiene que estar aqui
            one_true = True
        if cabe: # Si cabe, rellenar y buscar con vals[vals_i+1]
            for x in range(vals[vals_i]):
                copy[sol_i+x] = True
            sol_i = sol_i + 1 # Espacio en blanco
            combinations2(res, copy, sol_i + vals[vals_i], vals, vals_i + 1)
        else: # Si no cabe y habia un True, entonces la secuencia no es valida
            if one_true:
                return res
            sol_i = sol_i + 1
    return res

def and_grid(grid): # Se supone que no contiene None y que len(grid) > 0
    """Calcula los valores comunes para todas las combinaciones posibles de una
    secuencia de valores."""
    col_i = 0
    row_i = 0
    rows = len(grid)
    cols = len(grid[0])
    res = [True for _ in range(cols)]
    for col_i in range(cols):
        one_true = False
        for row_i in range(rows):
            if grid[row_i][col_i]:
                one_true = True
            res[col_i] = res[col_i] and grid[row_i][col_i]
            if res[col_i] == False and one_true:
                res[col_i] = None
    return res

def vector_and(v1, v2): # Se supone que len(v1) = len(v2)
    """Actualiza los valores nulos de v1 con los valores de v2.
    """
    for i in range(len(v1)):
        if v1[i] is None:
            v1[i] = v2[i]

def solve2(row_vals, col_vals):
    """Busca el tablero solucion.
    """
    tablero = np.array([[None for _ in range(len(col_vals))]]*len(row_vals), dtype=object)
    turno = 0 # Turno de fila
    unchanged = 0 # Numero de iteraciones sin cambios en el tablero
    total_unchanged = tablero.shape[0] + tablero.shape[1] # rows + cols
    end = False
    n_iter = 0
    while not end:
        for row_col in range(tablero.shape[turno]): # Por cada fila/columna
            # Obtener combinaciones
            if turno == 0:
                new_combinations = combinations2([], np.copy(tablero[row_col]), 0, row_vals[row_col], 0)
            else:
                new_combinations = combinations2([], np.copy(tablero[:, row_col]), 0, col_vals[row_col], 0)
            if len(new_combinations) == 0: # Si no tiene solucion
                return (None, n_iter)
            # Calcular "and_grid"
            new_values = and_grid(new_combinations)
            # Guardar el resultado en el tablero
            if turno == 0:
                if np.sum(tablero[row_col] == new_values) == len(new_values):
                    unchanged = unchanged + 1
                else:
                    unchanged = 0
                vector_and(tablero[row_col], new_values)
            else:
                if np.sum(tablero[:, row_col] == new_values) == len(new_values):
                    unchanged = unchanged + 1
                else:
                    unchanged = 0
                vector_and(tablero[:, row_col], new_values)
            n_iter = n_iter + 1
            if unchanged >= total_unchanged: # Si no se pueden rellenar mas casillas
                end = True
                break
        turno = (turno + 1) % 2 # Cambio de turno
    return (tablero, n_iter)

def test_solve(row_vals, col_vals):
    """Prueba la funcion solve2.
    """
    print("Tablero %dx%d" % (len(row_vals), len(col_vals)))
    t0 = time.time()
    res = solve2(row_vals, col_vals)
    t = round((time.time() - t0) * 1000)
    if res[0] is None:
        print("\n\tNo hay solucion")
    else:
        print_grid2(res[0], res[0].shape[0], res[0].shape[1])
    print("\n\tFinalizado en %dms (%d iteraciones)\n\n" % (t, res[1]))

def main():
    # 5x5
    col_vals = [[3], [2], [3], [2], [3]]
    row_vals = [[2], [3], [3, 1], [3], [1]]
    #test_solve(row_vals, col_vals)
    
    # 10x10
    row_vals = [[2, 1, 3], [1, 1, 2], [6], [1, 1, 1, 2], [2, 3],
                [2, 1, 2], [3], [4, 2], [3, 2], [5]]
    col_vals = [[2, 3], [1, 2], [1, 1], [2], [2, 4],
                [3, 3], [1, 3], [1, 1, 1, 1], [6, 3], [6, 3]]
    #test_solve(row_vals, col_vals)
    
    # 10x10
    row_vals = [[2, 2], [6], [3, 1], [2, 4], [1, 1],
                [3], [3, 1], [1, 7], [2, 5], [2, 4]]
    col_vals = [[2, 2], [4, 3], [3], [3, 1], [2, 5],
                [1, 1, 5], [8], [1, 3], [3], [3]]
    #test_solve(row_vals, col_vals)
    
    # 10x10
    row_vals = [[1, 1], [2], [6], [2, 3], [3, 3],
                [6], [3, 1], [1, 4], [2, 4], [2, 6]]
    col_vals = [[4, 3], [3, 2], [1], [3, 1], [1, 4, 3],
                [8], [5], [6], [2, 1], [3]]
    #test_solve(row_vals, col_vals)
    
    # 10x20
    row_vals = [[2, 2, 1, 1], [6, 2], [3, 1, 6], [2, 4, 2, 3], [1, 1, 3, 3],
                [3, 6], [3, 1, 3, 1], [1, 8, 4], [2, 7, 4], [2, 4, 2, 6]]
    col_vals = [[2, 2], [4, 3], [3], [3, 1], [2, 5],
                [1, 1, 5], [8], [1, 3], [3], [3],
                [4, 3], [3, 2], [1], [3, 1], [1, 4, 3],
                [8], [5], [6], [2, 1], [3]]
    #test_solve(row_vals, col_vals)
    
    # 15x15
    row_vals = [[1, 1, 1, 1], [1], [3, 1, 2], [2, 6], [2, 7, 1, 2],
                [4, 3, 1], [2, 4, 2], [2, 5], [7], [1, 8],
                [4, 2, 5], [6, 4], [6], [6, 4], [1, 1, 1, 3]]
    col_vals = [[8, 5], [6, 4], [1, 1, 1, 6], [2, 4], [1, 4],
                [1, 8], [3, 5], [7], [7], [3, 3, 1],
                [2, 3, 2], [1, 3, 4, 2], [1, 3, 2], [1, 1, 1, 2], [2, 2]]
    #test_solve(row_vals, col_vals)
    
    # 15x15
    row_vals = [[5, 2], [4, 2], [3, 3], [4, 4], [2, 1, 6],
                [3, 6], [10, 1], [8], [4, 2], [1, 2, 3],
                [2, 2], [6], [2, 6], [10], [1, 7, 1]]
    col_vals = [[5], [5, 1], [4, 1], [2, 5], [1, 6],
                [4, 1, 3], [4, 3], [4, 2], [2, 2], [1, 5, 4],
                [8, 4], [1, 5, 4], [4, 1, 3], [2, 7], [3, 6]]
    #test_solve(row_vals, col_vals)
    
    # 15x15
    row_vals = [[1, 3, 5], [3, 1, 1, 2], [4, 1, 1], [4], [2, 1, 5],
                [1, 2, 3], [4, 4, 2], [3, 7], [4, 1, 4], [4, 2],
                [3, 3, 1], [1, 1, 5, 1], [1, 6], [3, 1, 5], [5, 2]]
    col_vals = [[3, 4], [1, 1, 5, 2], [6, 2], [6, 1], [1, 2, 2, 2],
                [2, 1], [7, 1], [6, 1], [7, 2], [4, 1, 4],
                [1, 1, 7], [3, 7], [1, 2, 1, 2], [2, 3, 2], [3, 2, 2]]
    #test_solve(row_vals, col_vals)
    
    # 20x20 sin solucion
    row_vals = [[6, 7], [7, 6], [6, 2, 2], [7, 1, 3], [3, 3, 1, 1, 2],
                [1, 3, 2], [7, 1, 2, 2], [11, 4], [4, 6, 4, 1], [2, 7, 2, 1],
                [2, 3, 4, 1, 2], [2, 3, 3], [2, 3, 3], [3, 1], [1, 1, 4, 1, 2],
                [5, 2], [2, 2], [1, 3, 5, 3], [3, 1, 7], [6, 3]]
    col_vals = [[7, 1], [5, 7, 1], [5, 3, 3, 1], [4, 3, 2], [9, 2],
                [8, 6, 2], [1, 11], [8], [2, 4, 2], [3, 4, 2],
                [3, 1, 7, 4, 1], [2, 8, 5], [6, 3, 2, 1, 1], [5, 3], [2, 1, 4, 3],
                [1, 4, 2], [3, 1], [1, 3], [1, 6], [3, 7]]
    #test_solve(row_vals, col_vals)
    
    # 20x20
    row_vals = [[6, 7], [7, 6], [6, 2, 2], [7, 1, 3], [3, 3, 1, 1, 2],
                [1, 3, 2], [7, 1, 2, 2], [11, 4], [4, 6, 3, 1], [2, 7, 2, 1],
                [2, 3, 4, 1, 2], [2, 3, 3], [2, 3, 3], [3, 1], [1, 1, 4, 1, 2],
                [5, 2], [2, 2], [1, 3, 5, 3], [3, 1, 7], [6, 3]]
    col_vals = [[7, 1], [5, 7, 1], [5, 3, 3, 1], [4, 3, 2], [9, 2],
                [8, 6, 2], [1, 11], [8], [2, 4, 2], [3, 4, 2],
                [3, 1, 7, 4, 1], [2, 8, 5], [6, 3, 2, 1, 1], [5, 3], [2, 1, 4, 3],
                [1, 4, 2], [3, 1], [1, 3], [1, 6], [3, 7]]
    #test_solve(row_vals, col_vals)

    # 20x20
    row_vals = [[1, 8], [10], [2, 7], [2, 6, 1, 3], [9, 4],
                [9, 2, 1], [4, 2, 2, 2], [3, 1, 4, 1], [3, 1, 1, 1], [4, 3, 1, 2, 1],
                [9, 5, 2], [4, 4, 5, 2], [4, 3, 4], [2, 1, 1, 3, 2], [1, 3, 2],
                [1, 2, 5, 5], [1, 3, 1, 1, 2], [2, 3, 3, 3], [1, 1, 1, 1], [1, 1, 1, 3]]
    col_vals = [[3, 10], [11, 1], [10], [10, 1], [1, 3, 2, 2],
                [3, 3, 5], [5, 4, 2], [12], [3, 3, 1], [1, 3, 1],
                [1, 1, 1], [1, 3, 5], [2, 1, 6], [8, 5, 1], [3, 4, 4],
                [5, 4, 1, 1], [5, 2, 3], [4, 5], [3, 1, 2, 3, 1], [3, 7, 3, 1]]
    #test_solve(row_vals, col_vals)

    # 20x20
    row_vals = [[1, 2, 1, 1, 1], [1, 3, 3, 2, 1], [2, 2, 5, 2], [1, 1, 9, 1], [3, 1, 1],
                [3, 7, 1, 1], [2, 4, 2], [3, 4, 1], [1, 3, 1, 1], [8, 2],
                [2, 1, 7, 2], [4, 2, 6, 2], [5, 1, 1, 3], [3, 4, 1, 2], [9, 2, 2],
                [1, 1, 1, 7], [3, 6, 3], [3, 1, 1, 1, 5], [2, 3, 6, 1], [2, 1, 5]]
    col_vals = [[1, 4, 2], [4, 2, 4, 2], [3, 7], [3, 3, 1, 3], [1, 1, 1, 1, 5],
                [4, 1, 1], [3, 2, 3, 1, 1], [3, 1, 2], [7, 2, 1, 1], [1, 5, 3, 2],
                [6, 4, 1, 2], [5, 3, 4], [5, 4, 1, 1, 2], [2, 3, 3, 1], [6, 5, 2, 1],
                [1, 1, 3, 3], [1, 1, 3, 1, 1], [1, 3], [1, 6, 5], [7, 3, 6]]
    #test_solve(row_vals, col_vals)

    # 20x20
    row_vals = [[1, 5, 3, 2], [10, 2], [12, 1, 1], [12, 2], [4, 1, 1, 1, 3],
                [5, 2, 3], [3, 1, 2], [4, 5], [3, 4], [1, 4],
                [2, 3, 1, 3], [3, 4], [2, 4], [1, 3, 1], [1, 1, 4],
                [2, 8, 1, 1], [1, 3, 6, 5], [1, 3, 6, 1, 4], [1, 3, 3, 7], [1, 1, 6, 4, 1]]
    col_vals = [[4, 7], [5, 1], [9, 1, 4], [10, 3], [4, 4, 4],
                [6, 1, 1, 1], [4, 1, 1], [4, 6], [4, 3, 5], [4, 5, 1, 5],
                [2, 7, 3], [3, 9, 3], [2, 1, 1, 3], [1, 3, 2], [1, 2, 2, 3],
                [1, 1, 5, 1, 2], [1, 3, 6], [1, 3, 1, 1, 3], [4, 4], [4, 3]]
    #test_solve(row_vals, col_vals)

    # 20x20
    row_vals = [[1, 6, 1], [1, 3, 1, 2], [15], [14], [1, 1, 2, 8],
                [2, 1, 1], [4, 1], [2, 5, 1], [3, 3, 3, 2], [14],
                [10, 1, 2], [9, 2], [9, 1, 1], [8, 2], [1, 1, 4, 2],
                [3, 5, 2], [1, 2, 6], [2, 1, 6, 1], [2, 5, 1], [1, 4, 4, 3]]
    col_vals = [[1, 2, 2, 1], [3, 4, 1, 1], [7, 3], [1, 8, 2], [1, 7],
                [3, 1, 5, 1], [1, 3, 7, 2], [4, 8, 4], [4, 7, 2, 1], [5, 1, 10],
                [3, 3, 7], [4, 3, 6], [3, 3, 6], [3, 1, 3, 3], [3, 1, 1, 1, 1],
                [5, 2], [3, 1, 1], [2, 1], [3, 1, 2, 1], [4, 1, 2, 3]]
    test_solve(row_vals, col_vals)
    
    # 25x25
    row_vals = [[7, 2], [7, 2, 4, 2], [3, 1, 1, 9, 1], [3, 1, 5, 3, 3], [4, 3, 12, 1, 1],
                [8, 2, 4], [9, 3, 2, 1], [3, 5, 1, 2], [2, 7, 4, 1], [1, 5, 7, 1],
                [1, 5, 1, 9], [5, 2, 1, 4], [4, 3, 5], [3, 1, 7], [1, 1, 4, 1],
                [3, 3, 5], [1, 3, 2, 3], [2, 9, 2], [3, 5, 1, 1], [2, 3, 4, 2],
                [8, 4, 5], [6, 2, 1, 3, 4], [2, 1, 8], [2, 1, 4], [2, 1, 4]]
    col_vals = [[10, 1, 2], [9, 4, 2], [8, 1, 5], [2, 3, 1, 4], [2, 2, 1, 3],
                [3, 4, 3, 2], [2, 5, 5, 1], [12, 3], [7, 5], [2, 4, 6],
                [3, 3, 1, 1, 3, 1], [4, 2, 3, 2], [4, 1, 3, 1, 2], [3, 2, 1, 1, 2], [1, 3, 1, 1, 1],
                [1, 3, 3, 3], [4, 2, 6], [5, 3, 5], [6, 7, 3], [2, 7, 3, 2],
                [14, 1, 1], [1, 8, 5], [1, 2, 5, 2, 5], [1, 2, 5, 6], [1, 1, 3, 1, 1, 8]]
    #test_solve(row_vals, col_vals)
    
    # 25x25
    row_vals = [[4, 7, 2, 1], [3, 6, 3, 1], [4, 1, 2, 2, 1], [3, 3, 3, 1], [3, 4, 4, 2],
                [4, 9, 2, 2], [5, 7, 4, 1], [7, 2, 1, 1, 1, 1, 2], [4, 1, 1, 1, 5, 1], [3, 3, 7, 1],
                [3, 7], [2, 3, 7], [3, 2, 2, 4], [1, 7, 4], [2, 3, 2, 5],
                [1, 1, 3, 7], [1, 2, 1], [1, 4, 3, 2, 2], [2, 3, 3, 6], [5, 1, 9],
                [2, 1, 1, 3, 4], [4, 2, 2, 5], [3, 2, 2, 1, 3], [4, 11, 1], [6, 6]]
    col_vals = [[3, 3, 3, 3], [3, 4, 2, 6], [3, 5, 4, 6], [1, 7, 2, 1, 1, 1, 2], [2, 3, 1, 1, 2, 1],
                [4, 1, 1, 4, 2], [2, 3, 3, 8], [2, 1, 3, 1, 4], [2, 3, 1, 1, 1], [6, 1, 1],
                [8, 1, 1, 3], [1, 12, 3], [2, 7, 2, 2], [5, 3, 2, 3], [1, 4, 2, 2],
                [10, 1, 2], [3, 3, 1, 1, 4], [4, 1, 3], [1, 4, 6], [12, 3],
                [3, 8, 5], [10, 7], [8, 4], [6, 2, 3], [4, 1, 1, 1, 1, 1]]
    #test_solve(row_vals, col_vals)
    
    # 25x50
    row_vals = [[7, 2, 4, 7, 2, 1], [7, 2, 4, 2, 3, 6, 3, 1], [3, 1, 1, 9, 1, 4, 1, 2, 2, 1], [3, 1, 5, 3, 3, 3, 3, 3, 1], [4, 3, 12, 1, 4, 4, 4, 2],
                [8, 2, 4, 4, 9, 2, 2], [9, 3, 2, 6, 7, 4, 1], [3, 5, 1, 2, 7, 2, 1, 1, 1, 1, 2], [2, 7, 4, 5, 1, 1, 1, 5, 1], [1, 5, 7, 4, 3, 7, 1],
                [1, 5, 1, 12, 7], [5, 2, 1, 4, 2, 3, 7], [4, 3, 5, 3, 2, 2, 4], [3, 1, 8, 7, 4], [1, 1, 4, 1, 2, 3, 2, 5],
                [3, 3, 6, 1, 3, 7], [1, 3, 2, 3, 1, 2, 1], [2, 9, 3, 4, 3, 2, 2], [3, 5, 1, 3, 3, 3, 6], [2, 3, 4, 7, 1, 9],
                [8, 4, 7, 1, 1, 3, 4], [6, 2, 1, 3, 8, 2, 2, 5], [2, 1, 11, 2, 2, 1, 3], [2, 1, 8, 11, 1], [2, 1, 10, 6]]
    col_vals = [[10, 1, 2], [9, 4, 2], [8, 1, 5], [2, 3, 1, 4], [2, 2, 1, 3],
                [3, 4, 3, 2], [2, 5, 5, 1], [12, 3], [7, 5], [2, 4, 6],
                [3, 3, 1, 1, 3, 1], [4, 2, 3, 2], [4, 1, 3, 1, 2], [3, 2, 1, 1, 2], [1, 3, 1, 1, 1],
                [1, 3, 3, 3], [4, 2, 6], [5, 3, 5], [6, 7, 3], [2, 7, 3, 2],
                [14, 1, 1], [1, 8, 5], [1, 2, 5, 2, 5], [1, 2, 5, 6], [1, 1, 3, 1, 1, 8],
                [3, 3, 3, 3], [3, 4, 2, 6], [3, 5, 4, 6], [1, 7, 2, 1, 1, 1, 2], [2, 3, 1, 1, 2, 1],
                [4, 1, 1, 4, 2], [2, 3, 3, 8], [2, 1, 3, 1, 4], [2, 3, 1, 1, 1], [6, 1, 1],
                [8, 1, 1, 3], [1, 12, 3], [2, 7, 2, 2], [5, 3, 2, 3], [1, 4, 2, 2],
                [10, 1, 2], [3, 3, 1, 1, 4], [4, 1, 3], [1, 4, 6], [12, 3],
                [3, 8, 5], [10, 7], [8, 4], [6, 2, 3], [4, 1, 1, 1, 1, 1]]
    #test_solve(row_vals, col_vals)

    # 30x25
    row_vals = [[4, 3, 1], [5, 3, 3, 1], [4, 3, 3, 7], [7, 1, 4], [7, 1, 2, 3, 2],
                [1, 4, 1, 2, 3, 1], [4, 3, 1], [4, 3, 2], [4, 1, 4, 1, 1], [3, 3, 10, 1],
                [3, 4, 10, 1], [2, 2, 4, 8, 1], [3, 2, 2, 10], [4, 1, 8, 3], [4, 4, 2, 3, 3],
                [2, 4, 2, 3], [1, 3, 1, 2, 2], [1, 2, 3, 2], [2, 6], [1, 6],
                [1, 7], [1, 6, 3], [4, 7, 1], [3, 3, 4, 2], [9, 7],
                [1, 3, 7], [10, 4, 2], [1, 10, 6, 1], [2, 1, 1, 4, 2, 1, 3], [2, 2, 4, 2]]
    col_vals = [[9, 2, 3, 3], [2, 14, 5, 2], [3, 5, 3, 3], [9, 2, 1, 2], [7, 2, 1],
                [5, 3, 2, 3, 4], [3, 3, 4, 8, 1], [4, 1, 1, 3, 9], [4, 1, 3, 4, 4], [5, 1, 7, 4],
                [3, 13], [1, 4, 12], [1, 4, 1, 4, 7], [11, 3, 4], [11, 1, 3, 4],
                [3, 1, 6, 3, 1], [3, 6, 3, 3], [6, 1, 1, 1], [1, 1, 10, 1, 2], [1, 2, 1, 7, 2, 2],
                [1, 2, 3, 3], [4, 2, 2], [3, 5, 1], [3, 1, 5, 3], [15, 1, 1]]
    #test_solve(row_vals, col_vals)
    
    # 50x50
    row_vals = [[2, 1, 2, 10, 1], [3, 2, 4, 3, 1, 1], [5, 4, 4, 3, 1, 1, 1], [1, 7, 4, 1, 2, 1], [2, 12, 7, 1, 1, 1, 1],
                [2, 3, 7, 1, 2, 3, 1, 1, 2], [5, 1, 3, 2, 1, 5], [3, 3, 7, 2, 8, 2], [3, 3, 1, 5, 1, 2, 7, 2, 1], [1, 2, 3, 2, 6, 3],
                [3, 1, 1, 4, 2, 3, 1, 1], [2, 9, 1, 3, 1, 3], [3, 2, 1, 4, 3, 4, 4, 3], [3, 6, 5, 3, 4, 3, 2], [4, 6, 1, 3, 9, 2],
                [3, 8, 1, 4, 2, 4, 1, 1, 7, 2], [1, 1, 14, 4, 3, 5, 2], [2, 3, 10, 3, 2, 4], [3, 1, 5, 4, 1, 2, 2], [1, 5, 8, 1, 3],
                [3, 8, 1, 6, 5, 2], [7, 21, 1, 6], [6, 3, 3, 7, 13], [4, 3, 1, 7, 4, 8], [5, 1, 3, 4, 5],
                [5, 1, 6, 3, 3, 2], [8, 1, 4, 1, 4, 4, 7], [1, 1, 3, 1, 7, 6, 6, 6], [3, 2, 6, 1, 1, 1, 6, 5], [3, 1, 8, 1, 1, 1, 4, 1, 1, 1],
                [1, 1, 1, 3, 8, 3, 3, 2, 1, 7], [3, 2, 8, 3, 5, 5], [3, 8, 2, 4, 4, 3, 6], [3, 11, 1, 4, 2, 5], [9, 4, 3, 4, 5, 4],
                [4, 6, 8, 3, 4, 7, 3], [3, 4, 3, 9, 6, 3], [3, 3, 1, 3, 1, 3], [1, 4, 1, 5, 6, 1, 1], [1, 2, 1, 4, 4, 1],
                [1, 3, 1, 6, 4, 4, 3, 5, 2], [1, 3, 4, 1, 2, 1, 2, 2, 3, 1, 2], [4, 7, 6, 2, 3, 3, 1, 1], [11, 2, 10, 3, 3, 3], [5, 4, 4, 6, 3, 8],
                [2, 1, 1, 4, 5, 5, 8, 4, 3], [2, 1, 12, 3, 5, 15], [4, 2, 2, 5, 11, 6, 9], [5, 2, 8, 9, 1, 4], [5, 2, 9, 7, 1, 4]]
    col_vals = [[7, 6, 10, 3, 7], [5, 4, 1, 6, 2, 2, 7], [3, 4, 13, 2, 2, 3], [5, 1, 1, 1, 7, 3, 3, 3], [9, 1, 1, 2, 3, 3, 6, 7, 2],
                [5, 6, 2, 2, 2, 4, 4], [4, 5, 1, 1, 3, 5, 4, 1], [3, 1, 3, 3, 1, 2], [3, 3, 3, 1, 1, 1, 4], [3, 3, 2, 3, 1, 3],
                [4, 3, 1, 1, 7, 4], [2, 2, 3, 2, 3, 6, 3], [1, 4, 5, 3, 2, 3, 2], [5, 7, 3, 5, 1, 1, 1, 1, 4], [5, 7, 1, 10, 3, 4],
                [3, 6, 14, 1, 2], [2, 3, 9, 18, 5], [1, 4, 1, 8, 9, 3, 6], [3, 3, 3, 5, 6, 1, 1, 1, 8], [5, 4, 3, 1, 9, 1, 8],
                [5, 3, 2, 1, 13, 1, 2, 1, 1], [4, 4, 2, 2, 4, 2, 2], [1, 2, 2, 1, 1, 1, 1, 1, 1, 3, 3], [1, 3, 2, 4, 1, 3, 3, 3], [3, 2, 3, 2, 4, 3, 4, 4, 4],
                [3, 3, 2, 1, 1, 6, 6, 2, 3, 3], [9, 6, 1, 4, 8], [3, 1, 11, 6, 8], [1, 13, 14], [3, 5, 1, 10, 1, 1, 3, 7],
                [5, 3, 7, 1, 1, 1, 3, 3, 1, 5], [4, 3, 3, 1, 1, 3, 3, 3, 1], [3, 1, 1, 5, 3, 7], [2, 7, 3, 4], [4, 3, 8, 1, 4],
                [7, 2, 2, 7, 1, 3], [11, 2, 7, 1, 8], [2, 9, 1, 1, 1, 3, 4, 8], [4, 4, 4, 9, 8], [6, 3, 3, 1, 1, 2, 6, 4],
                [1, 2, 3, 6, 2, 1, 3, 3, 1, 1], [10, 3, 9, 3, 2], [6, 1, 3, 3, 5, 9], [3, 6, 4, 5, 1, 11], [3, 7, 8, 1, 1, 1, 2, 4],
                [1, 1, 1, 7, 8, 5], [1, 1, 3, 8, 3, 4], [2, 2, 10, 1, 1, 1, 1], [6, 4, 2, 4, 1, 1, 1], [1, 1, 7, 5, 2, 1, 1]]
    #test_solve(row_vals, col_vals)

if __name__ == '__main__':
    main()
