//person DAO fill out more with when connection to data becomes a thing

interface IPersonDao {

    //@GET("/person/info")
    suspend fun getPersonInfo(): Person

    //@POST("/person/newperson")
    suspend fun createPerson(){

    }

    suspend fun updatePerson(){

    }

    suspend fun updateWeight(){

    }

    suspend fun deletePerson(){

    }
}