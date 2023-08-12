import java.io.File
import java.text.SimpleDateFormat

fun buscarDecenaSuperior(numero: Int): Int {
    var decena = 10
    while (decena < numero) {
        decena += 10
    }
    return decena
}

fun mostrarInfoTarea(t:Tarea, base: SQLite){
    println("${t.idTarea}) ${t.descripcion}\n" +
            "Docente: ${base.consultarDocentePorCedula(t.ciDocente)?.nombre} - ${t.ciDocente}\n" +
            "Fecha: ${t.fechaEntrega}\n" +
            "Materia: ${t.materia}\n" +
            "Entregado: ${if(t.entregado) "si" else "no"}\n" +
            "Nota: ${t.nota}")
}
fun mostrarInfoDocente(d:Docente){
    println("${d.nombre})\n" +
            "Cedula: ${d.ciDocente}\n" +
            "N° Oficina: ${d.numeroOficina}\n" +
            "Facultad: ${d.facultad}\n" +
            "Tutorias: ${d.horarioAtencion}\n")
}
fun validarCedula(cedula: String): Boolean {
    var total = 0
    for (x in 0 until 9) {
        if ((x + 1) % 2 == 0) {
            total += cedula[x].toString().toInt()
        } else {
            var producto = cedula[x].toString().toInt() * 2
            if (producto >= 10) {
                producto -= 9
            }
            total += producto
        }
    }
    val digitoVerificador = buscarDecenaSuperior(total) - total
    return digitoVerificador == cedula[9].toString().toInt()
}
fun main(args: Array<String>) {
    val BaseDatos = SQLite()
    BaseDatos.createTable()
    while (true){

        println("--- Sistema Gestor de Tareas ---");
        println("1. Mostrar tareas");
        println("2. Mostrar docentes")
        println("3. Ingresar tareas")
        println("4. Ingresar docentes")
        println("5. Salir")
        print("Eleccion: ")
        var eleccion: String? = readLine();

        when (eleccion){
            "1" -> {
                println("------------------------------------------------------")
                println("                Historial de tareas                   ")
                println("------------------------------------------------------")

                var tareasRecuperadas = BaseDatos.consultarTodosLasTareas()
                for (tarea in tareasRecuperadas){
                    mostrarInfoTarea(tarea, BaseDatos)
                }
                print("¿Deseas modificar alguna entrega? (si/no): ")
                var modificar = readln()

                if (modificar == "si"){
                    print("Ingrese el numero de la tarea: ")
                    var numeroTarea: Int = readln().toInt()

                    BaseDatos.consultarTareaPorId(numeroTarea)?.let { mostrarInfoTarea(it, BaseDatos) }

                    println("----- Ingreso de cambios ----")

                    print("Descripcion: ")
                    var descripcion: String = readln()

                    print("Fecha (yyyy-mm-dd): ")
                    var fecha = readln()

                    print("Materia: ")
                    var materia:String = readln()

                    println("Docentes disponibles: ")

                    val docentes = BaseDatos.consultarTodosLosDocentes()
                    for (docente in docentes){
                        println("------------------------------------")
                        mostrarInfoDocente(docente)
                    }

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
                    }while(BaseDatos.consultarDocentePorCedula(numeroCedula) == null)

                    print("¿Entregado?: ")
                    var estado: String = readln()
                    var isEntregado: Int = 1
                    if (estado == "no"){
                        isEntregado = 0
                    }

                    print("Califiacion: ")
                    var nota: Double = readln().toDouble()

                    val actualizacion = BaseDatos.actualizarTarea(
                        numeroTarea,
                        numeroCedula,
                        fecha,
                        materia,
                        isEntregado,
                        nota,
                        descripcion
                    )

                    if(actualizacion){
                        println("Modificacion completa")
                    }else{
                        println("Ocurrio Algo")
                    }
                }

                print("¿Deseas eliminar alguna entrega? (si/no): ")
                var eliminar = readln()

                if (eliminar == "si"){
                    print("Ingrese el numero de la tarea: ")
                    var numeroTarea: Int = readln().toInt()

                    BaseDatos.consultarTareaPorId(numeroTarea)?.let { mostrarInfoTarea(it, BaseDatos) }

                    val resultadoEliminacion = BaseDatos.eliminarTareaPorId(numeroTarea)
                    if (resultadoEliminacion){
                        println("Eliminado con exito")
                    }else{
                        println("Ocurrio Algo")
                    }
                }

            }
            "2" -> {
                println("------------------------------------------------------")
                println("                Docentes Ingresados                   ")
                println("------------------------------------------------------")
                var docentesRecuperados = BaseDatos.consultarTodosLosDocentes()

                for (docente in docentesRecuperados){
                    mostrarInfoDocente(docente)
                }
            }
            "3" -> {
                print("Descripcion: ")
                var descripcion: String = readln()

                print("Fecha (yyyy-mm-dd): ")
                var fecha = readln()

                print("Materia: ")
                var materia:String = readln()

                print("Docentes disponibles: ")

                val docentes = BaseDatos.consultarTodosLosDocentes()
                for (docente in docentes){
                    println("------------------------------------")
                    mostrarInfoDocente(docente)
                }

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
                }while(BaseDatos.consultarDocentePorCedula(numeroCedula) == null)

                print("¿Entregado?: ")
                var estado: String = readln()
                var isEntregado: Int = 1
                if (estado == "no"){
                    isEntregado = 0
                }

                print("Califiacion: ")
                var nota: Double = readln().toDouble()

                val creacion = BaseDatos.crearTarea(
                    numeroCedula,
                    fecha,
                    materia,
                    isEntregado,
                    nota,
                    descripcion
                )

                if(creacion){
                    println("Tarea creada")
                }else{
                    println("Ocurrio Algo")
                }

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

                    }else if(BaseDatos.consultarDocentePorCedula(cedula_Docente) != null){ //validar que la cedula no este en el sistema
                        cedulaValida = false;
                        println("La cedula ya ha sido ingresada en el sistema");
                    }


                }while(!cedulaValida)

                print("Facultad: ")
                var facultad: String = readln()

                print("Numero de oficina: ")
                var oficina: Int = readln().toInt();

                var horarioAtencion = ""
                println("Dias de consulta")
                do{
                    print("\tDia: ")
                    var dia: String = readln();
                    print("\tHora (HH:mm): ");
                    var hora: String = readln()
                    horarioAtencion += "${dia} -> ${hora}; "

                    print("Agregar dia extra (s/n): ")
                    var continuar: String = readln();
                }while(continuar == "s")

                var resultadoCreacion = BaseDatos.crearDocente(cedula_Docente, nombre_Docente, oficina, facultad, horarioAtencion);
                if (resultadoCreacion){
                    println("Docente guardado con exito")
                }else{
                    println("Ocurrio algo")
                }

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