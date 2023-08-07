import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.sql.ResultSet

data class Docente(
    val ciDocente: String,
    val nombre: String?,
    val numeroOficina: Int?,
    val facultad: String?,
    val horarioAtencion: String?
)

class SQLite(){
    private val connection: Connection = DriverManager.getConnection("jdbc:sqlite:Deber3")
    private val statement: Statement = connection.createStatement()

    fun createTable(){
        val SQLCode = """
            CREATE TABLE DOCENTE (
                CI_DOCENTE       CHAR(10)     PRIMARY KEY NOT NULL,
                NOMBRE           CHAR(50),
                NUMERO_OFICINA   INTEGER,
                FACULTAD         CHAR(50),
                HORARIOATENCION  TEXT
            );

            CREATE TABLE TAREA (
                ID_TAREA         INTEGER      PRIMARY KEY AUTOINCREMENT,
                CI_DOCENTE       CHAR(10)     NOT NULL,
                FECHA_ENTREGA    DATE,
                MATERIA          CHAR(20),
                ENTREGADO        INTEGER,
                CALIFICACION     REAL,
                DESCRIPCION      TEXT,
                FOREIGN KEY (CI_DOCENTE) REFERENCES DOCENTE (CI_DOCENTE)
            );
        """.trimIndent()
        statement.execute(SQLCode)
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

    fun consultarDocentePorCedula(ciDocente:String): List<Any>?{
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
                return listOf(nombre, oficina, facultad, horarioAtencion)
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

}


