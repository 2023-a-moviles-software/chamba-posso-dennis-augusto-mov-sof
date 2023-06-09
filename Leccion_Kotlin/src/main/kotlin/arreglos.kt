import java.util.ArrayList
import kotlin.String


fun main(arg: Array<String>){
    //arreglos
    //tipos de arreglos

    //arreglos estaticos
    val arreglosEstaticos: Array<Int> = arrayOf<Int>(1,2,3)
    println(arreglosEstaticos)
    //arreglos dinamicos

    val arreglosDinamicos: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arreglosDinamicos)

    //iteradores
    //FOR EACH
    //Los for each devuelven un unit (void), es decir no regresan nada
    val respuestaForEach: Unit = arreglosDinamicos.forEach{valorActual: Int ->
        println("valor actual: ${valorActual}")}

    //simplificar la impresion de un iterable
    arreglosEstaticos.forEach { println(it) } //a lo que resivas (it) imprimelo

    arreglosEstaticos.forEachIndexed { index:Int, valorActual: Int ->
        println("valor actual: ${valorActual} indice: ${index}") }

    //operador map
    //crear un nuevo arreglo mutando los elementos del arreglo original
    val respuestaMap: List<Double> = arreglosDinamicos.map {
            valorActual: Int -> return@map valorActual.toDouble() + 100.00 }
    println(respuestaMap)

    //filter
    //usa una expresion booleana y retorna un nuevo arreglo
    val resepuestaFilter: List<Int> = arreglosDinamicos.filter { valorActual: Int ->
        val mayoresACinco: Boolean = valorActual > 5 //expresion condicion
        return@filter mayoresACinco
    }
    val respuestaFilter2 = arreglosDinamicos.filter { it <= 5 }
    println(resepuestaFilter)
    println(respuestaFilter2)

    //OR AND
    // OR -> ANY (alguno cumple?)
    // AND -> ALL (todos cumplen?)

    val respuestaAny: Boolean = arreglosDinamicos.any { valorActual: Int -> return@any (valorActual > 5) }
    println(respuestaAny)

    val respuestaAll: Boolean = arreglosDinamicos.all { valorActual: Int -> return@all (valorActual > 5) }
    println(respuestaAll)  

    /*
    Reduce -> valor acumulado
    valor acumulado = 0
    [1, 2, 3, 4, 5]
    v1 = valorInicio + 1
    v2 = v1 + 2
    .
    .
    .
    */

    val respuestaReduce: Int = arreglosDinamicos.reduce{
        acumulado: Int, valorActual: Int -> return@reduce (acumulado + valorActual)
    }
    println(respuestaReduce)
}