class DietService {
    /**
     * Fetches diet information for the user
     *
     * @param userId id of the user
     * @return Diet object
     */
    suspend fun getDietInfo(var userId: Int): Diet {
        return Diet('Keto Diet', 2000);
    }

    /**
     * Creates a new Diet object and inserts it into the database
     *
     * @param userId id of the user
     * @param name name of the diet
     * @param caloriesPerDay The number of calories per day set for the diet
     * @return newly created Diet object
     */
    suspend fun createDiet(var userId: Int, var name: String, var caloriesPerDay: Int): Diet {

    }

    /**
     * Updates an already existing Diet record in the database
     *
     * @param userId id of the user
     * @param name name of the diet
     * @param caloriesPerDay The number of calories per day set for the diet
     * @return newly updated Diet object
     */
    suspend fun updateDiet(var userId: Int, var name: String, var caloriesPerDay: Int): Diet {

    }

    /**
     * Deletes an existing Diet record from the database
     *
     * @param userId id of the user
     * @param dietId id of the diet record
     */
    suspend fun deleteDiet() {

    }
}