const val TYPE_1 = "Maestro"
const val TYPE_2 = "MasterCard"
const val TYPE_3 = "VISA"
const val TYPE_4 = "Мир"
const val TYPE_5 = "VK Pay"
const val MAXIMUM_TYPE_1 = 75000_00
const val COMMISSION_OTHER_TYPE_1 = 20_00
const val COMMISSION_TYPE_1 = 0.06
const val COMMISSION_OTHER_TYPE_3 = 35_00
const val COMMISSION_TYPE_3 = 0.0075
const val COMMISSION_TYPE_5 = 0


fun main() {
    processingCalc(type = TYPE_3, amountTransfer = 9_100_00)   //вводится номер платёжной системы и сумма
    processingCalc(type = TYPE_5, amountTransfer = 8_500_00)
    processingCalc(type = TYPE_1, amountTransfer = 75_500_00)
}

fun processingCalc(type : String = TYPE_5,
                  amountPurchases : Int = 0,
                  amountTransfer : Int){
    println()
    println("Сумма перевода: " + convertRub(amountTransfer));
    println("Тип карты: $type");
    println("Комиссия: " + convertRub(calcCommission(type, amountPurchases, amountTransfer)));
}

fun convertRub(amount : Int) : String{
    return (amount / 100).toInt().toString() + " руб "
}
fun calcCommission(type : String,
                   amountOfPreviousPurchases : Int,
                   amountTransfer : Int) : Int{
    when(type){
        TYPE_1, TYPE_2 -> {
            if ((amountOfPreviousPurchases + amountTransfer) in (1..MAXIMUM_TYPE_1)) return 0
            else return ( COMMISSION_OTHER_TYPE_1 + COMMISSION_TYPE_1 * amountTransfer).toInt()
        }
        TYPE_3, TYPE_4 -> {
            if (COMMISSION_TYPE_3 * amountTransfer > COMMISSION_OTHER_TYPE_1)
                return (COMMISSION_TYPE_3 * amountTransfer).toInt()
            else return  COMMISSION_OTHER_TYPE_3
        }
        TYPE_5 -> {
            return (COMMISSION_TYPE_5 * amountTransfer).toInt()
        }
    }
    return 0
}