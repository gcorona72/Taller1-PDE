import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUser(id: Int): Flow<User>  // Si usas corrutinas y Flow para consultas asíncronas
}


