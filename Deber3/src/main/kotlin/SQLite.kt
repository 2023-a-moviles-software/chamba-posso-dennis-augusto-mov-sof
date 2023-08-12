import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.util.Date

data class Docente(
    val ciDocente: String,
    val nombre: String?,
    val numeroOficina: Int?,
    val facultad: String?,
    val horarioAtencion: String?
)
data class Tarea(
    val idTarea: Int,
    val ciDocente: String,
    val fechaEntrega: String,
    val materia: String?,
    val entregado: Boolean,
    val nota: Double,
    val descripcion: String
)

class SQLite(){
    private val connection: Connection = DriverManager.getConnection("jdbc:sqlite:Deber3")
    private val statement: Statement = connection.createStatement()

    fun createTable() {
        val checkDocenteTable = "SELECT name FROM sqlite_master WHERE type='table' AND name='DOCENTE';"
        val checkTareaTable = "SELECT name FROM sqlite_master WHERE type='table' AND name='TAREA';"

        val docenteTableExists = statement.executeQuery(checkDocenteTable).next()
        val tareaTableExists = statement.executeQuery(checkTareaTable).next()

        if (!docenteTableExists) {
            val createDocenteTable = """
            CREATE TABLE DOCENTE (
                CI_DOCENTE       CHAR(10)     PRIMARY KEY NOT NULL,
                NOMBRE           CHAR(50),
                NUMERO_OFICINA   INTEGER,
                FACULTAD         CHAR(50),
                HORARIOATENCION  TEXT
            );
        """.trimIndent()
            statement.execute(createDocenteTable)
        }

        if (!tareaTableExists) {
            val createTareaTable = """
            CREATE TABLE TAREA (
                ID_TAREA         INTEGER      PRIMARY KEY AUTOINCREMENT,
                CI_DOCENTE       CHAR(10)     NOT NULL,
                FECHA_ENTREGA    CHAR(15),
                MATERIA          CHAR(20),
                ENTREGADO        INTEGER,
                CALIFICACION     REAL,
                DESCRIPCION      TEXT,
                FOREIGN KEY (CI_DOCENTE) REFERENCES DOCENTE (CI_DOCENTE)
            );
        """.trimIndent()
            statement.execute(createTareaTable)
        }
    }

    fun close(){
        statement.close()
        connection.close()
    }

    // funciones para docentes
    fun crearDocente(ciDocente: String,
                     nombre: String,
                     numeroOficina: Int?,
                     facultad: String?,
                     horarioAtencion: String?): Boolean{
        val insertQuery =
            """
        INSERT INTO DOCENTE (CI_DOCENTE, NOMBRE, NUMERO_OFICINA, FACULTAD, HORARIOATENCION)
        VALUES ('$ciDocente', '$nombre', $numeroOficina, '$facultad', '$horarioAtencion')
         """.trimIndent()
        try{
            statement.executeUpdate(insertQuery)
            return true
        }catch(e: Exception){
            println("A ocurrido un error al insertar un registro en la tabla docente: ${e.toString()}")
        }
        return false
    }

    fun consultarDocentePorCedula(ciDocente:String): Docente?{
        val query = """
            SELECT * FROM DOCENTE 
            WHERE CI_DOCENTE = '$ciDocente'
        """.trimIndent()

        val resultado = statement.executeQuery(query)
        try{
            if(resultado.next()){
                val nombre = resultado.getString("NOMBRE")
                val oficina = resultado.getInt("NUMERO_OFICINA")
                val facultad = resultado.getString("FACULTAD")
                val horarioAtencion = resultado.getString("HORARIOATENCION")
                return Docente(ciDocente,nombre, oficina, facultad, horarioAtencion)
            }
        } catch(e: Exception){
            println("A ocurrido un error al consultar la tabla docente: ${e.toString()}")
        }finally {
            resultado.close()
        }
        return null
    }

    fun eliminarDocentePorCedula(ciDocente:String): Boolean{
        val query = """
            DELETE FROM DOCENTE 
            WHERE CI_DOCENTE = '$ciDocente'
        """.trimIndent()

        try{
            statement.executeUpdate(query)
            return true
        }catch(e:Exception){
            println("A ocurrido un error al intentar eliminar el registro en la tabla docente: ${e.toString()}")
        }
        return false
    }

    fun actualizarDocente(ciDocente: String, nuevoNombre: String?, nuevoNumeroOficina: Int?, nuevaFacultad: String?, nuevoHorario: String?): Boolean {
        val query = """
        UPDATE DOCENTE 
        SET NOMBRE = ?, NUMERO_OFICINA = ?, FACULTAD = ?, HORARIOATENCION = ?
        WHERE CI_DOCENTE = ?
    """.trimIndent()

        try {
            val preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, nuevoNombre)
            preparedStatement.setInt(2, nuevoNumeroOficina ?: 0)
            preparedStatement.setString(3, nuevaFacultad)
            preparedStatement.setString(4, nuevoHorario)
            preparedStatement.setString(5, ciDocente)

            preparedStatement.executeUpdate()
            preparedStatement.close()

            return true
        } catch (e: Exception) {
            println("A ocurrido un error al intentar actualizar el registro en la tabla docente: ${e.toString()}")
        }
        return false
    }

    fun consultarTodosLosDocentes(): List<Docente> {
        val selectQuery = "SELECT * FROM DOCENTE"
        val resultSet = statement.executeQuery(selectQuery)

        val docentes = mutableListOf<Docente>()

        try {
            while (resultSet.next()) {
                val ciDocente = resultSet.getString("CI_DOCENTE")
                val nombre = resultSet.getString("NOMBRE")
                val numeroOficina = resultSet.getInt("NUMERO_OFICINA")
                val facultad = resultSet.getString("FACULTAD")
                val horarioAtencion = resultSet.getString("HORARIOATENCION")

                docentes.add(Docente(ciDocente, nombre, numeroOficina, facultad, horarioAtencion))
            }
        } catch (e: Exception) {
            println("A ocurrido un error al consultar la tabla docente: ${e.toString()}")
        } finally {
            resultSet.close()
        }

        return docentes
    }

    // funciones para tareas
    fun crearTarea(ciDocente: String,
                   fechaEntrega: String,
                   materia: String,
                   entregado: Int,
                   nota: Double,
                   descripcion: String): Boolean{
        val insertQuery = """
        INSERT INTO TAREA (CI_DOCENTE, FECHA_ENTREGA, MATERIA, ENTREGADO, CALIFICACION, DESCRIPCION)
        VALUES ('$ciDocente', '$fechaEntrega', '$materia', '$entregado', '$nota', '$descripcion')
        """.trimIndent()
        try{
            statement.executeUpdate(insertQuery)
            return true
        }catch(e: Exception){
            println("A ocurrido un error al insertar un registro en la tabla tarea: ${e.toString()}")
        }
        return false
    }

    fun consultarTareaPorId(id: Int): Tarea?{
        val query = """
            SELECT * FROM TAREA
            WHERE ID_TAREA = '$id'
        """.trimIndent()

        val resultado = statement.executeQuery(query)
        try{
            if(resultado.next()){
                val docente = resultado.getString("CI_DOCENTE")
                val fecha = resultado.getString("FECHA_ENTREGA")
                val materia = resultado.getString("MATERIA")
                val entregado = resultado.getInt("ENTREGADO")
                val calificacion = resultado.getDouble("CALIFICACION")
                val descripcion = resultado.getString("DESCRIPCION")
                return Tarea(id, docente, fecha, materia, entregado == 1, calificacion, descripcion)
            }
        }catch(e: Exception){
            println("A ocurrido un error al consultar la tabla tarea: ${e.toString()}")
        }finally {
            resultado.close()
        }
        return null
    }

    fun eliminarTareaPorId(id: Int):Boolean{
        val query = """
            DELETE FROM TAREA
            WHERE ID_TAREA = '$id'
        """.trimIndent()
        try{
            statement.executeUpdate(query)
            return true
        }catch(e:Exception){
            println("A ocurrido un error al intentar eliminar el registro en la tabla tarea: ${e.toString()}")
        }
        return false
    }

    fun consultarTodosLasTareas(): List<Tarea> {
        val selectQuery = "SELECT * FROM TAREA"
        val resultSet = statement.executeQuery(selectQuery)

        val tareas = mutableListOf<Tarea>()

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("ID_TAREA")
                val docente = resultSet.getString("CI_DOCENTE")
                val fecha = resultSet.getString("FECHA_ENTREGA")
                val materia = resultSet.getString("MATERIA")
                val entregado = resultSet.getInt("ENTREGADO")
                val calificacion = resultSet.getDouble("CALIFICACION")
                val descripcion = resultSet.getString("DESCRIPCION")

                tareas.add(Tarea(id, docente, fecha, materia, entregado == 1, calificacion, descripcion))
            }
        } catch (e: Exception) {
            println("A ocurrido un error al consultar la tabla tarea: ${e.toString()}")
        } finally {
            resultSet.close()
        }

        return tareas
    }

    fun actualizarTarea(idTarea:Int,
                          ciDocente: String,
                          fechaEntrega: String,
                          materia: String,
                          entregado: Int,
                          nota: Double,
                          descripcion: String): Boolean{
        val updateQuery = """
        UPDATE TAREA 
        SET CI_DOCENTE = ?, FECHA_ENTREGA = ?, MATERIA = ?, ENTREGADO = ?, CALIFICACION = ?, DESCRIPCION = ?
        WHERE ID_TAREA = ?
    """.trimIndent()

        try {
            val preparedStatement = connection.prepareStatement(updateQuery)
            preparedStatement.setString(1, ciDocente)
            preparedStatement.setString(2, fechaEntrega.toString())
            preparedStatement.setString(3, materia)
            preparedStatement.setInt(4, entregado)
            preparedStatement.setDouble(5, nota)
            preparedStatement.setString(6, descripcion)
            preparedStatement.setInt(7, idTarea)

            preparedStatement.executeUpdate()
            preparedStatement.close()

            return true
        } catch (e: Exception) {
            // Manejar excepciones, si es necesario
            println("A ocurrido un error al intentar actualizar el registro en la tabla tarea: ${e.toString()}")
        }

        return false
    }

    fun consultarTareasPorCedulaDocente(cedula: String): List<Tarea> {
        val selectQuery = """
            SELECT * FROM TAREA
            WHERE CI_DOCENTE = '$cedula'
        """.trimIndent()
        val resultSet = statement.executeQuery(selectQuery)

        val tareas = mutableListOf<Tarea>()

        try {
            while (resultSet.next()) {
                val id = resultSet.getInt("ID_TAREA")
                val docente = resultSet.getString("CI_DOCENTE")
                val fecha = resultSet.getString("FECHA_ENTREGA")
                val materia = resultSet.getString("MATERIA")
                val entregado = resultSet.getInt("ENTREGADO")
                val calificacion = resultSet.getDouble("CALIFICACION")
                val descripcion = resultSet.getString("DESCRIPCION")

                tareas.add(Tarea(id, docente, fecha, materia, entregado == 1, calificacion, descripcion))
            }
        } catch (e: Exception) {
            println("A ocurrido un error al consultar la tabla tarea: ${e.toString()}")
        } finally {
            resultSet.close()
        }

        return tareas
    }



}


