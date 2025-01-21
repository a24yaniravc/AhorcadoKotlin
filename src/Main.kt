var palabraAdivinada = ""
var guardado = StringBuilder("")
var fallos = 0

fun jugar(palabrasPosibles: List<String>, intentosMax: Int) {
    // Elegimos una palabra aleatoria
    val elegido = palabrasPosibles.random()
    val adivinado = StringBuilder("")

    // Inicializamos la palabra adivinada con guiones bajos
    for (letra in elegido.indices) {
        adivinado.append("_")
    }

    guardado = adivinado
    palabraAdivinada = adivinado.toString()

    // Bucle principal
    while (fallos < intentosMax && guardado.toString()!=elegido) {
        println("-------------------------------")
        println("Palabra: $guardado")
        print("Introduce una letra o palabra: ")

        val letra = readln().lowercase()

        // Comprobamos si la entrada es lógica + si es palabra o letra
        if (letra != "" && letra[0] in 'a'..'z' && letra.length <= elegido.length) {
            if (letra.length == 1) {
                intento(elegido, letra[0]) // Comprueba la letra
            } else if (letra == elegido) {
                println("¡Buen trabajo! Has adivinado la palabra: $elegido")
                break
            } else {
                println("¡Incorrecto!")
                fallos++
                DibujoAhorcado.dibujar(fallos)
            }
        } else {
            println("Ingrese una letra o palabra válida.")
        }

        // Comprobamos si la palabra fue adivinada
        if (guardado.toString() == elegido) {
            println("¡Buen trabajo! Has adivinado la palabra: $elegido")
            break
        }

        // Comprobamos si el jugador ha agotado los intentos
        if (fallos == intentosMax) {
            println("Has gastado todos tus intentos. La palabra era: $elegido")
        }
    }
}

fun intento(elegido: String, letra: Char) {
    // Comprobamos
    if (elegido.contains(letra)) {
        println("¡Correcto!")
        val nuevaPalabra = palabraAdivinada.toCharArray()

        // Actualizamos la palabra adivinada con la letra correcta
        for (i in elegido.indices) {
            if (elegido[i] == letra) {
                nuevaPalabra[i] = letra
            }
        }

        // Asignamos la nueva palabra adivinada (String)
        palabraAdivinada = String(nuevaPalabra)

        // Actualizamos el StringBuilder guardado (StringBuilder)
        guardado = StringBuilder(palabraAdivinada)
    } else {
        println("¡Incorrecto!")
        fallos++
        DibujoAhorcado.dibujar(fallos)
    }
}

fun main() {
    // Java //
    val rm = ReproductorMidi("pugnodollari.mid")
    println("Cargando juego...")
    Thread.sleep(20000)

    // Kotlin //
    val palabrasPosibles = listOf("platano", "manzana", "pera", "mandarina", "kiwi", "naranja")
    val intentosMax = 7

    jugar(palabrasPosibles, intentosMax)
    Thread.sleep(1000)
    rm.cerrar()
}
