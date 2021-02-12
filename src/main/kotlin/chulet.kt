const val AUTOR = "Pedro Sánchez" //Constante (define)

fun main(args: Array<String>) {

    val pi: Float = 3.14F //Sos inimputable
    var nombre: String? = null //? = anulable

    var numero: Int? = readLine()?.toInt() //? porque podria devolver null
    println("Numero vale $numero")
    if (numero!=null && numero%2==0) {
        println("$numero es par")
    } else if (numero!=null) {
        println("$numero es impar")
    }

    var num: Int = readLine()?.toIntOrNull() ?: 1 //si null, elvis derecho
    println("$num")

    print("Como te llamas bro?: ")
    val bro = readLine()
    println("Hola, $bro")
    print("Teclea tu edad en años: ")
    val edad = readLine()!!.toInt()
    println("$bro, llevas durmiendo aprox ${edad/3} años") //${edad/3} es una string template

    //8
    print("Por favor, teclea la base del triángulo: ")
    var b = readLine()?.toDoubleOrNull()
    print("Por favor, teclea la altura del triángulo: ")
    var h = readLine()?.toDoubleOrNull()
    if (b==null || h==null)
        println("Por favor, vuelve a intentarlo")
    else
        println("El área del triángulo es ${b*h/2}")

    // ?.
    var name: String? = null
    // si name==null, ni llama a capitalize y devuelve null directamente
    println("Bienvenido, ${name?.capitalize()}")

    // Let devuelve valor de la ultima linea
    val hername = "  elena"
    val longitud = hername.let {
        // prints elena
        println(it.trim())
        it.length
    }
    // prints 7
    println(longitud)

    // prints Elena, que es la ultima linea del let
    println("Elena".let {
        it.length
        it
    })

    //Nullable into let
    print("Por favor, introduce tu nombre de usuario: ")
    var texto: String? = readLine()?.let {
        if (it.isNotBlank())
            "Bienvenido, $it"
        else
            "Por favor, vuelve a intentarlo"
    }
    println("$texto")

    // ? vs !!
    var mensaje: String? = null
    println("${mensaje?.capitalize()}")  //safe call. si es null no llames
    println("${mensaje!!.capitalize()}") //not null assertion. Que no es null!! Creeme!!

    // === OJO!
    var uno: String = "Ana"
    var dos: String = "Ana"
    println(uno === dos)
    println(uno == dos)
    // true true, porque en String, Int, Double === es lo mismo que ==

    //contains
    if (readLine()?.contains('@') != true)
        println("[ERROR] El correo debe contener una '@'")

    //lambdas y "variables funcion"
    var funcion: (a: Int) -> Boolean
    fun media(a: Int, b: Int): Double {
        return (a + b) / 2.0
    }
    funcion = { it: Int -> it % 2 == 0 }
    funcion = { it % 2 == 0 } //lo mismo
    println("Es par: ${funcion(2)}")
    println("La media es ${media(1, 2)}")

    //La ultima linea de la lambda es lo que se devuelve
    var resultado = { a: Int, b: Int ->
        println("Vale, voy a calcular la media de $a y $b...")
        a+b
    }
    println("La media es: ${resultado(1,2)}")

    //Preferencia al llamar: nombre de funcion
    fun calculo(a: Int, b: Int): Double {
        return (a+b)/2.0
    }
    var calculo = { a: Int, b: Int -> (a+b)/1.0 }
    println("La media es: ${calculo(1,2)}") //1.5


    //argumento funcion en una funcion. OJO! Unit es como si fuera VOID
    var una: ((a: Int, b: Int) -> Double) -> Unit
    una = { println("La respuesta es ${it(1,2)}")}
    una({ a: Int, b: Int -> (a+b)/2.0 })
    una { a: Int, b: Int -> (a+b)/1.0 } //lo mismo que arriba

    //tambien se puede juntar asignacion
    var otra: ((a: Int, b: Int) -> Double) -> Unit = { println("La respuesta es ${it(1,2)}")}

    //mas complicado, argumento funcion y parametro normal
    var esvoid: ((a: Int, b: Int) -> Double, parametro2: Int) -> Unit
    esvoid = { funcion, parametro2 -> println("${funcion(1,2)}, $parametro2") }
    esvoid ( { a: Int, b: Int -> (a + b) / 2.0}, 9)

    //str format en template. Fit 10 caracteres, 1 decimal maximo
    val peso = 63
    val altura = 172
    val imc = 10000.0 * peso / (altura * altura)
    println("El índice de masa corporal es ${"%10.1f".format(imc)}")

    //if en cascada horrible
    if (imc < 16)
        println("Delgadez severa")
    else if (imc < 17)
        println("Delgadez moderada")
    else if (imc < 18.5)
        println("Delgadez leve")
    else if (imc < 25)
        println("Peso normal")
    else if (imc < 30)
        println("Preobesidad")
    else if (imc < 35)
        println("Obesidad leve")
    else if (imc < 40)
        println("Obesidad media")
    else
        println("Obesidad mórbida")

    //when significando lo mismo, precioso
    when {
        imc < 16 -> println("Delgadez severa")
        imc < 17 -> println("Delgadez moderada")
        imc < 18.5 -> println("Delgadez leve")
        imc < 25 -> println("Peso normal")
        imc < 30 -> println("Preobesidad")
        imc < 35 -> println("Obesidad leve")
        imc < 40 -> println("Obesidad media")
        else -> println("Obesidad mórbida")
    }

    //asignar a variable de one
    val estado = when {
        imc < 16 -> "Delgadez severa"
        imc < 17 -> "Delgadez moderada"
        imc < 18.5 -> "Delgadez leve"
        imc < 25 -> "Peso normal"
        imc < 30 -> "Preobesidad"
        imc < 35 -> "Obesidad leve"
        imc < 40 -> "Obesidad media"
        else -> "Obesidad mórbida"
    }
    println(estado)

    //full pograma imcs
    print("Introduce tu peso en kg: ")
    val pes = readLine()?.toIntOrNull()
    print("Introduce tu altura en cm: ")
    val alt = readLine()?.toIntOrNull()

    if (pes == null || alt == null)
        println("Por favor, vuelva a intentarlo")
    else {
        var imc: Double = 10000.0 * pes / (alt * alt)
        val mensaje = when {
            imc < 16 -> "Delgadez severa"
            imc < 17 -> "Delgadez moderada"
            imc < 18.5 -> "Delgadez leve"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Preobesidad"
            imc < 35 -> "Obesidad leve"
            imc < 40 -> "Obesidad media"
            else -> "Obesidad mórbida"
        }
        println(mensaje)
    }


}