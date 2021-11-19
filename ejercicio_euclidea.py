# Para confirmar que todo está listo y que has comprendido alguno de los conceptos vistos
# en clase, te propongo que escribas un programa Python que, partiendo de dos matrices X y M,
# siendo X.shape = (fa,c) y M.shape = (fm, c), calcule la distancia euclídea de cada fila
# de X a cada fila de M, y las almacene en una matriz R.shape = (fa, fm). Para ello debes
# utilizar las facilidades que te proporcina NumPy intentando no emplear ningún bucle y
# minimizar el número de líneas de código.
#
# Para resolver esta tarea te puede ser útil:
# - repasar los conceptos de broadcasting vistos en clase
#     https://numpy.org/doc/stable/user/basics.broadcasting.html
# - usar algun procedimiento para incluir nuevas dimensiones en un array NumPy
#     np.newaxis
# - usar alguna función que sume filas o columnas de matrices
#     np.sum
#   o que calcule un potencia de sus elementos
#     np.power
#
# Este trabajo es individual y voluntario. El primer estudiante que lo resuelva en
# una línea de código y desee presentarlo en clase debe enviarme un email con la solución.
# Le contará como nota extra de teoría.

import numpy as n

A = n.array([[1, 1, 1], [2, 2, 2], [3, 3, 3], [4, 4, 4]])
B = n.array([[1, 1, 1], [2, 2, 2]])

R =  n.sqrt(n.sum(n.power(n.expand_dims(A, axis=1) - n.expand_dims(B, axis=0), 2), axis=2))
R2 = n.sqrt(n.sum(n.power(A[:,n.newaxis]-B, 2), axis=2))
print(10*'-', "A:", A.shape, 10*'-', '\n', A, '\n')
print(10*'-', "B:", B.shape, 10*'-', '\n', B, '\n')
print(10*'-', "R:", R.shape, 10*'-', '\n', R, '\n')
print(10*'-', "R2:", R2.shape, 10*'-', '\n', R2, '\n')
