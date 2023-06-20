import kotlin.String

fun main(arg: Array<String>){
    //switch
    val estadoCivilWhen = 'C'
    when (estadoCivilWhen){
        'C' -> {
            println("casado")
        }
        'S'->{
            println("soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == 'S')
    val coqueteo = if(esSoltero) "si" else "no"
}