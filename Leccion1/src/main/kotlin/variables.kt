import java.util.Date

fun main(arg: Array<String>) {
    //inmutables: no se reasignan "="
    val inmutable: String = "Dennis";

    //mutables (re asiganar)
    var mutable: String = "Dennis";
    mutable = "chamba";

    //usar val lo mas posible

    //duck typing
    var ejemplo = "sol";
    val edad = 12;

    //tipos de variables primitivas
    val nombre:String = "String";
    val sueldo: Double = 123.3;
    val estadoCivil: Char = 'c';
    val mayorEdad: Boolean = true;

    //clase java
    val fecha: Date = Date();

}