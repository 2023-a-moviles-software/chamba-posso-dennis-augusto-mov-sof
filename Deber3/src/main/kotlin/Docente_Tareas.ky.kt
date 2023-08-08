import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class Docente1{
    private val Nombre: String;
    private val Cedula: String;
    private val Numero_Oficina: Int;
    private var Horario_Atencion: HashMap<String, String> = HashMap();
    private var Facultad: String;

    constructor(Nombre: String,
                Cedula: String,
                Numero_Oficina: Int,
                Horario_Atencion: HashMap<String, String>,
                Facultad: String
    ){
        this.Nombre = Nombre;
        this.Cedula = Cedula;
        this.Numero_Oficina = Numero_Oficina;
        this.Horario_Atencion = Horario_Atencion;
        this.Facultad = Facultad;
    }

    fun getNombre(): String {
        return Nombre
    }

    fun getCedula(): String {
        return Cedula
    }

    fun getNumeroOficina(): Int {
        return Numero_Oficina
    }

    fun getHorarioAtencion(): HashMap<String, String> {
        return Horario_Atencion
    }

    fun getFacultad(): String {
        return Facultad
    }

    fun horarioEnString(): String {
        val horarioAsString = StringBuilder()
        for ((dia, hora) in this.Horario_Atencion) {
            horarioAsString.append("$dia -> [$hora]; ")
        }
        if (horarioAsString.isNotEmpty()) {
            horarioAsString.delete(horarioAsString.length - 2, horarioAsString.length)
        }
        return horarioAsString.toString()
    }

    fun retornarInformacion(): String {
        return "\tNombre: ${this.getNombre()}\n" +
                "\tCedula: ${this.getCedula()}\n" +
                "\tOficina: ${this.getNumeroOficina()}\n" +
                "\tFacultad: ${this.getFacultad()}\n" +
                "\tHorario: ${this.horarioEnString()}\n"
    }
}



fun construirHashMapDesdeString(str: String): HashMap<String, String> {
    val hashMap = HashMap<String, String>()
    val regex = Regex("([^\\s]+)\\s->\\s\\[([^\\s]+)\\]")

    regex.findAll(str).forEach { matchResult ->
        val (dia, hora) = matchResult.destructured
        hashMap[dia] = hora
    }

    return hashMap
}



fun buscarDecenaSuperior(numero: Int): Int {
    var decena = 10
    while (decena < numero) {
        decena += 10
    }
    return decena
}


// Función para recuperar los docentes desde un archivo

fun recuperarNombresCedulasDocentes(nombreArchivo: kotlin.String): List<kotlin.String>{
    val NombresDocentes = mutableListOf<kotlin.String>()
    val lineas = File(nombreArchivo).readLines()

    for (linea in lineas) {
        val datos = linea.split(",")
        val nombre = datos[0]
        val cedula = datos[1]
        NombresDocentes.add("${nombre} -> ${cedula}\n")
    }
    return NombresDocentes
}
fun buscarCedulaEnDocentes(cedula: kotlin.String): Boolean{
    val CedulasDocentes = mutableListOf<kotlin.String>()
    val lineas = File("Docentes.txt").readLines()

    for (linea in lineas) {
        val datos = linea.split(",")
        val cedula = datos[1]
        CedulasDocentes.add(cedula)
    }
    return CedulasDocentes.contains(cedula)
}


// funciones para tareas

// Función para guardar un tareas en un archivo


// Función para recuperar los docentes desde un archivo


fun convertirFormatoFecha(fechaOriginal: Date): LocalDate {
    val formatoDeseado = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val fechaFormateada = formatoDeseado.format(fechaOriginal)
    val formatoLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    return LocalDate.parse(fechaFormateada, formatoLocalDate)
}

fun convertirStringAFecha(fechaString: String): Date {
    val formato = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return formato.parse(fechaString)

}
/*
fun main(arg: Array<kotlin.String>){
    while (true){

        println("--- Sistema Gestor de Tareas ---");
        println("1. Mostrar tareas");
        println("2. Mostrar docentes")
        println("3. Ingresar tareas")
        println("4. Ingresar docentes")
        println("5. Salir")
        print("Eleccion: ")
        var eleccion: kotlin.String? = readLine();

        when (eleccion){
            "1" -> {
                println("------------------------------------------------------")
                println("                Historial de tareas                   ")
                println("------------------------------------------------------")

                var tareasRecuperadas = recuperarTareasDesdeArchivo("Tareas.txt").toMutableList()
                var contador = 1
                for (tarea in tareasRecuperadas){
                    println("Tarea ${contador}): ")
                    println(tarea.retornarInformacion())
                    contador++;
                }
                print("¿Deseas modificar alguna entrega? (si/no): ")
                var modificar = readln()

                if (modificar == "si"){
                    print("Ingrese el numero de la tarea: ")
                    var numeroTarea: Int = readln().toInt()
                    println(tareasRecuperadas[numeroTarea-1].retornarInformacion())

                    println("----- Ingreso de cambios ----")

                    print("Descripcion: ")
                    var descripcion: String = readln()

                    print("Fecha (yyyy-mm-dd): ")
                    var fechaSinFormato = readln()
                    val formato = SimpleDateFormat("yyyy-MM-dd")
                    var fecha = formato.parse(fechaSinFormato)

                    print("Materia: ")
                    var materia:String = readln()

                    print("Docentes disponibles: ")
                    print(recuperarNombresCedulasDocentes("Docentes.txt"))
                    print("Numero de cedula del docente: ")

                    var numeroCedula: String = ""
                    do{
                        numeroCedula= readln()
                        if(!numeroCedula.matches(Regex("\\d+"))){ //validar que solo tenga numeros
                            println("La cedula solo debe contener numeros")
                            continue
                        }else if(numeroCedula.length != 10){ //validar que tenga 10 caracteres
                            println("La cedula debe estar compuesta por 10 numeros")
                            continue
                        }else if(!validarCedula(numeroCedula)){ //validad que la cedula sea valida
                            println("Ingrese una cedula valida")
                            continue
                        }
                    }while(!buscarCedulaEnDocentes(numeroCedula))

                    print("¿Entregado?: ")
                    var estado: String = readln()
                    var isEntregado: Boolean = true
                    if (estado == "no"){
                        isEntregado = false
                    }

                    print("Califiacion: ")
                    var nota: Double = readln().toDouble()

                    var tarea1 = Tarea(descripcion, fecha, materia, numeroCedula, isEntregado, nota )
                    tareasRecuperadas[numeroTarea-1] = tarea1

                    File("Tareas.txt").writeText("")
                    for (tarea in tareasRecuperadas){
                        guardarTareasEnArchivo(tarea, "Tareas.txt")
                    }
                    println("Modificacion completa")
                }

                print("¿Deseas eliminar alguna entrega? (si/no): ")
                var eliminar = readln()

                if (eliminar == "si"){
                    print("Ingrese el numero de la tarea: ")
                    var numeroTarea: Int = readln().toInt()
                    println(tareasRecuperadas[numeroTarea-1].retornarInformacion())
                    tareasRecuperadas.removeAt(numeroTarea-1)

                    File("Tareas.txt").writeText("")
                    for (tarea in tareasRecuperadas){
                        guardarTareasEnArchivo(tarea, "Tareas.txt")
                    }

                }

            }
            "2" -> {
                println("------------------------------------------------------")
                println("                Docentes Ingresados                   ")
                println("------------------------------------------------------")
                var docentesRecuperados = recuperarDocentesDesdeArchivo("Docentes.txt")

                for (docente in docentesRecuperados){
                    println(docente.retornarInformacion())
                }
            }
            "3" -> {
                print("Descripcion: ")
                var descripcion: String = readln()

                print("Fecha (yyyy-mm-dd): ")
                var fechaSinFormato = readln()
                val formato = SimpleDateFormat("yyyy-MM-dd")
                var fecha = formato.parse(fechaSinFormato)

                print("Materia: ")
                var materia:String = readln()

                print("Docentes disponibles:\n ")
                print(recuperarNombresCedulasDocentes("Docentes.txt"))
                println("")
                var numeroCedula: String = ""
                var validarCedula: Boolean
                do{
                    validarCedula = true
                    print("Numero de cedula del docente: ")
                    numeroCedula= readln()
                    if(!numeroCedula.matches(Regex("\\d+"))){ //validar que solo tenga numeros
                        println("La cedula solo debe contener numeros")
                        validarCedula = false

                    }else if(numeroCedula.length != 10){ //validar que tenga 10 caracteres
                        println("La cedula debe estar compuesta por 10 numeros")
                        validarCedula = false

                    }else if(!validarCedula(numeroCedula)){ //validad que la cedula sea valida
                        println("Ingrese una cedula valida")
                        validarCedula = false
                    }else if(!buscarCedulaEnDocentes(numeroCedula)){
                        println("El numero de cedula debe pertenecer a algun docente")
                    }
                }while(!buscarCedulaEnDocentes(numeroCedula) or !validarCedula)

                print("¿Entregado?: ")
                var estado: String = readln()
                var isEntregado: Boolean = true
                if (estado == "no"){
                    isEntregado = false
                }

                print("Califiacion: ")
                var nota: Double = readln().toDouble()

                var tarea1 = Tarea(descripcion, fecha, materia, numeroCedula, isEntregado, nota )
                guardarTareasEnArchivo(tarea1, "Tareas.txt")

            }
            "4" -> {
                print("Nombre del docente: ")
                var nombre_Docente: String = readln();

                var cedula_Docente: String = "";
                do{
                    var cedulaValida: Boolean = true;
                    print("Cedula: ");
                    cedula_Docente = readln();

                    if(!cedula_Docente.matches(Regex("\\d+"))){ //validar que solo tenga numeros
                        cedulaValida = false
                        println("La cedula solo debe contener numeros")

                    }else if(cedula_Docente.length != 10){ //validar que tenga 10 caracteres
                        cedulaValida = false;
                        println("La cedula debe estar compuesta por 10 numeros")

                    }else if(!validarCedula(cedula_Docente)){ //validad que la cedula sea valida
                        cedulaValida = false;
                        println("Ingrese una cedula valida")

                    }else if(buscarCedulaEnDocentes(cedula_Docente)){ //validar que la cedula no este en el sistema
                        cedulaValida = false;
                        println("La cedula ya ha sido ingresada en el sistema");
                    }


                }while(!cedulaValida)

                print("Facultad: ")
                var facultad: kotlin.String = readln()

                print("Numero de oficina: ")
                var oficina: Int = readln().toInt();

                val horarioAtencion = HashMap<kotlin.String, kotlin.String>()
                println("Dias de consulta")
                do{
                    print("\tDia: ")
                    var dia: kotlin.String = readln();
                    print("\tHora (HH:mm): ");
                    horarioAtencion[dia] = readln();

                    print("Agregar dia extra (s/n): ")
                    var continuar: kotlin.String = readln();
                }while(continuar == "s")

                var d1 = Docente(nombre_Docente, cedula_Docente, oficina, horarioAtencion, facultad);
                guardarDocenteEnArchivo(d1, "Docentes.txt")
                println("Docente guardado con exito")

            }
            "5" -> {
                println("adios")
                break;
            }
            else ->{
                println("Seleccione una opcion valida")
            }
        }
    }
}
*/


