import java.util.ArrayList


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
}