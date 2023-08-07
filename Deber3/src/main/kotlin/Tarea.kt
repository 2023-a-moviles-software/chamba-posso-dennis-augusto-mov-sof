import java.util.*

class Tarea{
    private val Descripcion: String;
    private val Fecha_Entrega: Date;
    private val Materia: String;
    private val CedulaDocente: String;
    private var Entregado: Boolean;
    private var Calificacion: Double;

    constructor(Descripcion: String,
                Fecha: Date,
                Materia: String,
                Docente: String,
                Entregado: Boolean,
                Calificacion: Double
    ){
        this.Descripcion = Descripcion;
        this.Fecha_Entrega = Fecha;
        this.Materia = Materia;
        this.CedulaDocente = Docente;
        this.Entregado = Entregado;
        this.Calificacion = Calificacion;
    }

}