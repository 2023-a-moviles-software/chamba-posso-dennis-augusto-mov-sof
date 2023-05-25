fun main(arg: Array<String>){
    //void -> unit
    fun imprimirNombre(nombre: String): Unit{
        println("nombre: ${nombre}") //template string
    }

    fun calcularSueldo(
        sueldo: Double, //requerido
        tasa: Double = 12.00, //por defecto, moificable

        //el kotlin las variables no pueden tomar el valor de null,
        //pero se puede agregar el signo ? para que puedan aceptar
        bonoEspecial: Double? = null //opcion null -> nulleable
    ): Double{
        if (bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }
    }

    calcularSueldo(10.0)
    calcularSueldo(10.0, 12.0, 20.0)
    //parametros nombrados
    calcularSueldo(sueldo = 10.0, bonoEspecial = 20.0)
    calcularSueldo(10.0, bonoEspecial = 20.0)
}