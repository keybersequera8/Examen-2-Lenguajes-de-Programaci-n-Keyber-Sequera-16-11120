#sublistasCrecientes.py

# Ejecución:
#	python3 sublistasCrecientes.py

# Estudiante: Keyber Yosnar Sequera Avendaño.
# Carnet: 16-11120.

# Se realiza un programa que obtiene las sublistas crecientes
# de una lista.

def obtenerSublistasCreciente(lista):
	elementoActual = 0
	tamañoSublista = 1
	listaActual = lista
	sublista = []
	print(sublista)
	# Mientras que no estemos en el final de la lista
	# iteramos sobre la misma:
	while (elementoActual < len(lista)):
		maximo = lista[elementoActual]
		# La lista que evalúamos es un subconjunto de la lista
		# original, este subconjunto estará formado por todos
		# los elementos que siguen al elemento que estamos recorriendo:
		listaActual = lista[elementoActual + 1:len(lista)]
		# Mientras este subconjunto de la lista original no sea vacío:
		while(len(listaActual) != 0):
			maximo = lista[elementoActual]
			sublista = [maximo]
			# Solo consideramos aquellos subconjuntos cuyo primer elemento 
			# sea mayor al elemento que estamos considerando:
			if (listaActual[0] > maximo):
				print(sublista)
				for elemento in listaActual:
					if (maximo == None):
						maximo = elemento
						sublista += [maximo]
						print(sublista)
					else:
						if (maximo < elemento):
							maximo = elemento
							sublista += [maximo]
							print(sublista)
			else:
				pass
			# Recorremos el subconjunto de la lista original:
			listaActual = listaActual[1:]
		# Consideramos el siguiente elemento:
		elementoActual += 1

# Se prueba el programa:
lista = [1, 4, 3, 2, 5]
print(f"Sublista creciente de {lista}:\n")
obtenerSublistasCreciente(lista)
lista = [1,2,3,4,5]
print("-----------------------------------------------------")
print(f"Sublista creciente de {lista}:\n")
obtenerSublistasCreciente(lista)
print("-----------------------------------------------------")
print("Fin del programa.")