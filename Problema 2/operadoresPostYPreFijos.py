# operadoresPostYPreFijos.py

# Ejecución:
#	python3 operadoresPostYPreFijos.py

# Estudiante: Keyber Yosnar Sequera Avendaño.
# Carnet: 16-11120.

# Se realiza un programa que procesa expresiones en notación
# pre-fija y post-fija.

operadores = {"+" : 1, "-" : 1, "*" : 2, "/" : 2}

# Se obtienen los elementos de la operación prefija:
def obtenerOperacionPrefija(operacion, elementoActual, operadorAnterior):
	try:
		# Si el elemento actual es un número devuelvo dicho número,
		# la posición en la que me quedo, el elemento como string y
		# el operador de dicho número que en este caso es ninguno (None):
		if (operadores.get(operacion[elementoActual], None) == None):
			return [int(operacion[elementoActual]), elementoActual + 1, operacion[elementoActual], None]
		# Si no lo es, debo formar la operación:
		else:
			# Obtengo el operador de la operación:
			operador = operacion[elementoActual]
			# Obtengo el primer operando:
			primerOperando = obtenerOperacionPrefija(operacion, elementoActual + 1, operador)
			operador1 = primerOperando[0]       # Operando como número
			elementoActual = primerOperando[1]  # Posición que estoy considerando
			stringOperador1 = primerOperando[2] # Operando como string
			# Obtengo el segundo operando:
			segundoOperando = obtenerOperacionPrefija(operacion, elementoActual, operador)
			operador2 = segundoOperando[0]      # Operando como número
			elementoActual = segundoOperando[1] # Posición que estoy considerando
			stringOperador2 = segundoOperando[2]# Operando como string
			# Obtengo las prioridades de las operaciones:
			prioridadOperacion = operadores.get(operador)
			prioridadOperacionAnterior = operadores.get(operadorAnterior)
			if (operador == "*"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 * operador2, elementoActual, f"{stringOperador1} * {stringOperador2}", "*"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 * operador2, elementoActual, f"({stringOperador1} * {stringOperador2})", "*"]
					else:
						return [operador1 * operador2, elementoActual, f"{stringOperador1} * {stringOperador2}", "*"]
			elif (operador == "/"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 / operador2, elementoActual, f"{stringOperador1} / {stringOperador2}", "/"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 / operador2, elementoActual, f"({stringOperador1} / {stringOperador2})", "/"]
					else:
						return [operador1 / operador2, elementoActual, f"{stringOperador1} / {stringOperador2}", "/"]
			elif (operador == "+"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 + operador2, elementoActual, f"{stringOperador1} + {stringOperador2}", "+"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 + operador2, elementoActual, f"({stringOperador1} + {stringOperador2})", "+"]
					else:
						return [operador1 + operador2, elementoActual, f"{stringOperador1} + {stringOperador2}", "+"]
			else:
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 - operador2, elementoActual, f"{stringOperador1} - {stringOperador2}", "*"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 - operador2, elementoActual, f"({stringOperador1} - {stringOperador2})", "*"]
					else:
						return [operador1 - operador2, elementoActual, f"{stringOperador1} - {stringOperador2}", "*"]
	except:
		return None

# Se obtiene el resultado de la operacion prefija:
def operarPreFijo(operacion):
	resultado = [0, 0, ""]
	if (len(operacion) == 0):
		return ""
	else:
		resultado = obtenerOperacionPrefija(operacion, 0, None)
		if (resultado == None):
			resultado = "Operacion no valida"
	return resultado

# Se obtienen los elementos de la operación postfija:
def obtenerOperacionPostfija(operacion, elementoActual, operadorAnterior):
	try:
		# Si el elemento actual es un número devuelvo dicho número,
		# la posición en la que me quedo, el elemento como string y
		# el operador de dicho número que en este caso es ninguno (None):
		if (operadores.get(operacion[elementoActual], None) == None):
			return [int(operacion[elementoActual]), elementoActual - 1, operacion[elementoActual], None]
		# Si no lo es, debo formar la operación:
		else:
			# Obtengo el operador de la operación:
			operador = operacion[elementoActual]
			# Obtengo el primer operando:
			primerOperando = obtenerOperacionPostfija(operacion, elementoActual - 1, operador)
			operador2 = primerOperando[0]       # Operando como número
			elementoActual = primerOperando[1]  # Posición que estoy considerando
			stringOperador2 = primerOperando[2] # Operando como string
			# Obtengo el segundo operando:
			segundoOperando = obtenerOperacionPostfija(operacion, elementoActual, operador)
			operador1 = segundoOperando[0]      # Operando como número
			elementoActual = segundoOperando[1] # Posición que estoy considerando
			stringOperador1 = segundoOperando[2]# Operando como string
			# Obtengo las prioridades de las operaciones:
			prioridadOperacion = operadores.get(operador)
			prioridadOperacionAnterior = operadores.get(operadorAnterior)
			if (operador == "*"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 * operador2, elementoActual, f"{stringOperador1} * {stringOperador2}", "*"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 * operador2, elementoActual, f"({stringOperador1} * {stringOperador2})", "*"]
					else:
						return [operador1 * operador2, elementoActual, f"{stringOperador1} * {stringOperador2}", "*"]
			elif (operador == "/"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 / operador2, elementoActual, f"{stringOperador1} / {stringOperador2}", "/"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 / operador2, elementoActual, f"({stringOperador1} / {stringOperador2})", "/"]
					else:
						return [operador1 / operador2, elementoActual, f"{stringOperador1} / {stringOperador2}", "/"]
			elif (operador == "+"):
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 + operador2, elementoActual, f"{stringOperador1} + {stringOperador2}", "+"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 + operador2, elementoActual, f"({stringOperador1} + {stringOperador2})", "+"]
					else:
						return [operador1 + operador2, elementoActual, f"{stringOperador1} + {stringOperador2}", "+"]
			else:
				# Si la prioridad de la operación anterior es None, es decir,
				# si leo un número, entonces no debo añadir paréntesis:
				if (prioridadOperacionAnterior == None):
					return [operador1 - operador2, elementoActual, f"{stringOperador1} - {stringOperador2}", "*"]
				else:
					# Si la prioridad de la operación de la operación actual
					# es menor que la de la operación anterior añado paréntesis:
					if (prioridadOperacion < prioridadOperacionAnterior):
						return [operador1 - operador2, elementoActual, f"({stringOperador1} - {stringOperador2})", "*"]
					else:
						return [operador1 - operador2, elementoActual, f"{stringOperador1} - {stringOperador2}", "*"]
	except:
		return None

# Se obtiene el resultado de la operacion prefija:
def operarPostFijo(operacion):
	resultado = [0, 0, ""]
	if (len(operacion) == 0):
		return ""
	else:
		resultado = obtenerOperacionPostfija(operacion, len(operacion) - 1, None)
		if (resultado == None):
			resultado = "Operacion no valida"
	return resultado

# Programa de prueba:
# Se debe tener en cuenta que todos los datos de entrada deben estar
# separados por un solo espacio.
entrada = ""

while (entrada.upper() != "SALIR"):
	print("-------------------------------------------------------------------------")
	print("Introduzca la accion que desea realizar:\n")
	print("EVAL <orden> <expr> (Evalua la expresion <expr> escrita en orde <orden> (POST o PRE))" )
	print("MOSTRAR <orden> <expr> (Representa una impresión en orden infijo de la expresión <expr> de orden <orden> (POST o PRE))")
	print("SALIR (Para salir del programa)\n")
	entrada = str(input("Su acción: "))
	try:
		entrada = entrada.split(" ")
		if (entrada[0].upper() == "EVAL"):
			if (entrada[1].upper() == "PRE"):
				resultado = operarPreFijo(entrada[2:len(entrada)])
				if (resultado == "Operacion no valida"):
					print(f"\n{resultado}")
				else:
					print(f"\nResultado de la operacion: {resultado[0]}")
			elif (entrada[1].upper() == "POST"):
				resultado = operarPostFijo(entrada[2:len(entrada)])
				if (resultado == "Operacion no valida"):
					print(f"\n{resultado}")
				else:
					print(f"\nResultado de la operacion: {resultado[0]}")
			else:
				print("\nOrden no valido, recuerde que el orden solo puede ser PRE o POST")
		elif (entrada[0].upper() == "MOSTRAR"):
			if (entrada[1].upper() == "PRE"):
				resultado = operarPreFijo(entrada[2:len(entrada)])
				if (resultado == "Operacion no valida"):
					print(f"\n{resultado}")
				else:
					print(f"\nOperacion en forma in-fija: {resultado[2]}")
			elif (entrada[1].upper() == "POST"):
				resultado = operarPostFijo(entrada[2:len(entrada)])
				if (resultado == "Operacion no valida"):
					print(f"\n{resultado}")
				else:
					print(f"\nOperacion en forma in-fija: {resultado[2]}")
			else:
				print("\nOrden no valido, recuerde que el orden solo puede ser PRE o POST")
		elif (entrada[0].upper() == "SALIR"):
			print("\nFin del programa.")
		else:
			print("\nAcción no válida.")
		entrada = entrada[0]
	except:	
		print("\nAcción no válida.")
		entrada = ""