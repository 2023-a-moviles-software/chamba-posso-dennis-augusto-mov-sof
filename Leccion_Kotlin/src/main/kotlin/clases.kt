
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
            this.numeroUno;
            this.numeroDos;
            numeroUno;
            numeroDos;
            println("Inicializado")
        }
    }
    class suma( //Constructor primario Suma
        unoParametro: Int, //Parametro
        dosParametros: Int, //Parametro
        ):Numeros(unoParametro, dosParametros){ //extendiendo y mandando los parametros (super)
        init {
             this.numeroUno
             this.numeroDos
        }

        constructor( //Segundo constructor
        uno: Int?,//parametros
        dos:Int//Parametros
        ):this(if(uno == null) 0 else uno,
        dos
        )

        constructor( //Tercero constructor
        uno: Int,//parametros
        dos:Int?//Parametros
        ):this(
        uno,
        if(dos == null)0 else dos,
        )

        constructor( //Segundo constructor
        uno: Int?,//parametros
        dos:Int?//Parametros
        ):this(
        if(uno == null)0 else uno,
        if(dos == null)0 else dos,
        )
        public fun sumar(): Int{
            val total = numeroUno + numeroDos
            agregarHistorial(total) //this.agregarHistorial(total)
            return total
        }
        companion object{//atributos y mentodos compartidos singletons o static de esta clase
        // todas las instancias de esta clase comparten estos atributos y metodos
        // dentro del companion object
        val pi = 3.14
            fun elevarAlCuadrado(num: Int): Int{
                return num * num
            }
            val historialSumas = ArrayList<Int>()
            fun agregarHistorial(valorNuevaSuma: Int){
                historialSumas.add(valorNuevaSuma)
            }
        }
    }

    fun main(arg: Array<String>){
        val sumaUno = suma(1,1)
        val sumaDos = suma(null, 1)
        val sumaTres = suma(1, null)
        val sumacuatro = suma(null, null)
        sumaUno.sumar()
        sumaDos.sumar()
        sumaTres.sumar()
        sumacuatro.sumar()

        println(suma.pi)
        println(suma.elevarAlCuadrado(2))
        println(suma.historialSumas)
    }
