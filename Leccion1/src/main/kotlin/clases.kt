fun main(arg: Array<String>){
    abstract class NumerosJava{
        protected val numeroUno: Int
        private val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ){
            //bloque de codigo constructor
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializado")
        }
    }

    abstract class Numeros(//constructor primario
        //ejemplo
        //uno: Int parametro sin modificador de accesp
        //private var uno:Int Proiedad publica clase numeros.uno
        //var uno. Int propiedad de la clase por defecto es public
        //public var uno: Int
        protected val numeroUno: Int, // propiedad de la clase protected numeros.numeroUno
        protected val numeroDos: Int, // Propiedad de la clase protected numero.numeroDos
    ){
        // var cedula: String = "" //public es por defecto
        // private valorCalculado: Int = 0 //private
        init {
            this.numeroUno; this.numeroDos;
            numeroUno; numeroDos;
            println("Inicializado")
        }
    }
}