// Ejecución:
//		kotlinc -include-runtime maxHeapSimetrico.kt -d maxHeapSimetrico.jar
// 		java -jar maxHeapSimetrico.jar

// Estudiante: Keyber Yosnar Sequera Avendaño
// Carnet: 16-11120

// Problema:
// 	Se determina si un objeto de tipo Árbol binario es max
// heap simétrico.

class Hoja {
	var valor : Int?
	var padre : Hoja?
	var hijoIzquierdo : Hoja?
	var hijoDerecho : Hoja?
	// Creamos un constructor donde no se le asigna un valor
	// a la hoja:
	constructor() {
		this.valor = null
		this.padre = null
		this.hijoIzquierdo = null
		this.hijoDerecho = null
	}
	// Creamos un constructor donde se le asigna un valor
	// a la hoja:
	constructor(valor : Int) {
		this.valor = valor
		this.padre = null
		this.hijoIzquierdo = null
		this.hijoDerecho = null
	}
	// Representación como String de la clase:
	override fun toString(): String {
		if (this.padre == null) {
			return "(${this.padre?.valor} -> ${this.valor})"
		} else {
			return "(${this.padre?.valor} -> ${this.valor})"
		}
	}
}

class ArbolBinario {
	var raiz : Hoja?
	var tamanioArbol : Int // Número de niveles que tiene el árbol
	var auxiliar : Boolean = false
	var elementosDelArbol : MutableList<MutableList<Hoja>> = mutableListOf()
	var arbolPreOrden : MutableList<Int?> = mutableListOf()
	var arbolPostOrden : MutableList<Int?> = mutableListOf()

	// Definimos un constructor donde no se le asigna un valor
	// a la raiz del árbol:
	constructor() {
		this.raiz = null
		this.tamanioArbol = 0
	}
	// Definimos un constructor donde se le asigna un valor a la
	// raiz del árbol:
	constructor(valor : Int){
		this.raiz = Hoja(valor)
		this.elementosDelArbol.add(mutableListOf())
		this.elementosDelArbol[0].add(Hoja(valor))
		this.tamanioArbol = 1
	}
	// Retorna true si el árbol es max heap, o false en caso contrario:
	fun esMaxHeap() : Boolean {
		// Se usará la variable global auxiliar para rastrear si a cada
		// nivel del árbol se cumple que el árbol es max heap:
		this.auxiliar = true // Será max heap hasta demostrarse lo contrario
		// Se comprueba si la raiz es distinta de null, si la raiz es null
		// entonces el árbol está vacío por lo tanto es un max heap:
		if (this.raiz != null) {
			this.comprobarHojaMaxima(this.raiz)
		}
		// Si el árbol es max heap retorno true:
		if (this.auxiliar == false) {
			return this.auxiliar
		}
		this.auxiliar = false
		return true

		// Si no es max heap retorno false:
	}
	// Comprueba si los valores de los hijos de la hoja actual son menores
	// o iguales a él:
	fun comprobarHojaMaxima(hoja : Hoja?) {
		// Compruebo que el árbol sea max heap:
		if (this.auxiliar == true) {
			// Compruebo que el hijo izquierdo sea no nulo y que tenga
			// un valor menor que el padre:
			if (hoja!!.hijoIzquierdo != null) {
				if (hoja.valor!! >= hoja.hijoIzquierdo!!.valor!!) {
					this.comprobarHojaMaxima(hoja.hijoIzquierdo)
				}
				else  {
					this.auxiliar = false
				}
			}
			// Compruebo que el hijo derecho sea no nulo y que tenga
			// un valor menor que el padre y que el árbol sea max heap:
			if (this.auxiliar == true && hoja.hijoDerecho != null) {
				if (hoja.valor!! >= hoja.hijoDerecho!!.valor!!) {
					this.comprobarHojaMaxima(hoja.hijoDerecho)
				}
				else  {
					this.auxiliar = false
				}
			}
		}
	}
	// Se obtiene una lista con la salida del árbol en pre orden:
	fun obtenerArbolEnPreOrden(): MutableList<Int?> {
		this.arbolPreOrden = mutableListOf()
		if (this.raiz != null) {
			this.arbolPreOrden.add(this.raiz!!.valor)
			if (this.raiz!!.hijoIzquierdo != null) {
				this.agregarHojaPreOrden(this.raiz!!.hijoIzquierdo)
			}
			if (this.raiz!!.hijoDerecho != null) {
				this.agregarHojaPreOrden(this.raiz!!.hijoDerecho)
			}
		}
		return this.arbolPreOrden
	}
	// Se agrega la hoja al pre orden:
	fun agregarHojaPreOrden(hoja : Hoja?) {
		this.arbolPreOrden.add(hoja!!.valor)
		if (hoja.hijoIzquierdo != null) {
			this.agregarHojaPreOrden(hoja.hijoIzquierdo)
		}
		if (hoja.hijoDerecho != null) {
			this.agregarHojaPreOrden(hoja.hijoDerecho)
		}
	}
	// Se obtiene una lista con la salida del árbol en post orden:
	fun obtenerArbolEnPostOrden(): MutableList<Int?> {
		this.arbolPostOrden = mutableListOf()
		if (this.raiz != null) {
			if (this.raiz!!.hijoIzquierdo != null) {
				this.agregarHojaPostOrden(this.raiz!!.hijoIzquierdo)
			}
			if (this.raiz!!.hijoDerecho != null) {
				this.agregarHojaPostOrden(this.raiz!!.hijoDerecho)
			}
			this.arbolPostOrden.add(this.raiz!!.valor)
		}
		return this.arbolPostOrden
	}
	// Se agrega la hoja al post orden:
	fun agregarHojaPostOrden(hoja : Hoja?) {
		if (hoja!!.hijoIzquierdo != null) {
			this.agregarHojaPostOrden(hoja.hijoIzquierdo)
		}
		if (hoja.hijoDerecho != null) {
			this.agregarHojaPostOrden(hoja.hijoDerecho)
		}
		this.arbolPostOrden.add(hoja.valor)
	}
	// Retorna true si el árbol es simétrico, retorna false en caso contrario:
	fun esSimetrico() : Boolean {
		if (this.obtenerArbolEnPreOrden() == this.obtenerArbolEnPostOrden()) {
			return true
		}
		return false
	}

	// Representación como String de la clase:
	override fun toString(): String {
		var cadenaRetorno : String = ""
		for (i in 0..this.tamanioArbol - 1) {
			cadenaRetorno += "[ "
			for (j in 0..this.elementosDelArbol[i].size - 2) {
				cadenaRetorno += "${this.elementosDelArbol[i][j]}, "
			}
			cadenaRetorno += "${this.elementosDelArbol[i][this.elementosDelArbol[i].size - 1]} ]\n"
		}
		cadenaRetorno = cadenaRetorno.substring(0, cadenaRetorno.length - 1)
		return cadenaRetorno
	}

	// Ajustar el árbol binario para que permita hojas con valor null
	// pero que no las tome en cuenta para el max heap, el pre y post orden,
	// la altura del árbol y esos espacios los pueda ocupar luego al ingresar hojas.
}

// 

fun main () {
	var arbol : ArbolBinario
	var hojaActual : Hoja?
	var hojaAInsertar : Hoja?
	// Se crea un árbol binario de prueba que sea simétrico y max heap, de la forma:
	//                  10 
	//                 /  \
	//               10     10
	//                \
	//                 10
	println("------------------------------------------------------------------")
	println("Se crea un árbol binario que es simétrico y max heap:")
	arbol = ArbolBinario(10)
	hojaActual = arbol.raiz
	// Agrego un hijo izquierdo a la hoja actual:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = arbol.raiz
	arbol.tamanioArbol += 1
	hojaActual!!.hijoIzquierdo = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = hojaActual
	hojaActual.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Me desplazo a la hoja izquierda que acabo de crear:
	hojaActual = hojaActual.hijoIzquierdo
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = hojaActual
	arbol.tamanioArbol += 1
	hojaActual!!.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[2].add(hojaAInsertar)
	// Imprimo si el árbol es max heap o no:
	if (arbol.esMaxHeap()) {
		println("El árbol es max heap")
	} else {
		println("El árbol no es max heap")
	}
	// Imprimo el pre orden del árbol creado:
	println("Pre orden del árbol:")
	println(arbol.obtenerArbolEnPreOrden())
	// Imprimo el post orden del árbol creado:
	println("Post orden del árbol:")
	println(arbol.obtenerArbolEnPostOrden())
	// Imprimo si el árbol es o no simétrico:
	if (arbol.esSimetrico()) {
		println("El árbol es simétrico")
	} else {
		println("El árbol no es simétrico")
	}
	// Se crea un árbol binario de prueba que sea simétrico pero no max heap, 
	// de la forma:
	//                  10 
	//                 /  \
	//               9     10
	//                \
	//                 10
	println("------------------------------------------------------------------")
	println("Se crea un árbol binario que es simétrico pero no max heap:")
	arbol = ArbolBinario(10)
	hojaActual = arbol.raiz
	// Agrego un hijo izquierdo a la hoja actual:
	hojaAInsertar = Hoja(9)
	hojaAInsertar.padre = arbol.raiz
	arbol.tamanioArbol += 1
	hojaActual!!.hijoIzquierdo = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = hojaActual
	hojaActual.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Me desplazo a la hoja izquierda que acabo de crear:
	hojaActual = hojaActual.hijoIzquierdo
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = hojaActual
	arbol.tamanioArbol += 1
	hojaActual!!.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[2].add(hojaAInsertar)
	// Imprimo si el árbol es max heap o no:
	if (arbol.esMaxHeap()) {
		println("El árbol es max heap")
	} else {
		println("El árbol no es max heap")
	}
	// Imprimo el pre orden del árbol creado:
	println("Pre orden del árbol:")
	println(arbol.obtenerArbolEnPreOrden())
	// Imprimo el post orden del árbol creado:
	println("Post orden del árbol:")
	println(arbol.obtenerArbolEnPostOrden())
	// Imprimo si el árbol es o no simétrico:
	if (arbol.esSimetrico()) {
		println("El árbol es simétrico")
	} else {
		println("El árbol no es simétrico")
	}
	// Se crea un árbol binario de prueba que sea max heap pero no simétrico, 
	// de la forma:
	//                  10 
	//                 /  \
	//               10     10
	//                \
	//                 9
	println("------------------------------------------------------------------")
	println("Se crea un árbol binario que es max heap pero no simétrico:")
	arbol = ArbolBinario(10)
	hojaActual = arbol.raiz
	// Agrego un hijo izquierdo a la hoja actual:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = arbol.raiz
	arbol.tamanioArbol += 1
	hojaActual!!.hijoIzquierdo = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(10)
	hojaAInsertar.padre = hojaActual
	hojaActual.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol[1].add(hojaAInsertar)
	// Me desplazo a la hoja izquierda que acabo de crear:
	hojaActual = hojaActual.hijoIzquierdo
	// Agrego un hijo derecho a la hoja:
	hojaAInsertar = Hoja(9)
	hojaAInsertar.padre = hojaActual
	arbol.tamanioArbol += 1
	hojaActual!!.hijoDerecho = hojaAInsertar
	arbol.elementosDelArbol.add(mutableListOf())
	arbol.elementosDelArbol[2].add(hojaAInsertar)
	// Imprimo si el árbol es max heap o no:
	if (arbol.esMaxHeap()) {
		println("El árbol es max heap")
	} else {
		println("El árbol no es max heap")
	}
	// Imprimo el pre orden del árbol creado:
	println("Pre orden del árbol:")
	println(arbol.obtenerArbolEnPreOrden())
	// Imprimo el post orden del árbol creado:
	println("Post orden del árbol:")
	println(arbol.obtenerArbolEnPostOrden())
	// Imprimo si el árbol es o no simétrico:
	if (arbol.esSimetrico()) {
		println("El árbol es simétrico")
	} else {
		println("El árbol no es simétrico")
	}
	println("------------------------------------------------------------------")
	println("Fin del programa")
}