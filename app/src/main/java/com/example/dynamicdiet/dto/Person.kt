//person class with a constructor

data class Person(var name: String, var currentWeight: Int, var past: List<Int>, var diet: String){
    override fun toString(): String {
        return ("$name $currentWeight $diet")
    }
}