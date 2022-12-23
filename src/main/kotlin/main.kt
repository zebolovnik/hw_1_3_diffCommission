package ru.netology

/*
fun main() {
    val aaa = какятофункция(параметр1, параметр2, сПараметромПоУмолчанию2 = "silver"
    println(aaa)
}

fun какятофункция(
    параметр1: Int,
    параметр2: String,
    сПараметромПоУмолчанию1: Int = 100,
    сПараметромПоУмолчанию2: String = "normal"
    ): Int {
        код-вычисления
        return ...
    }
==========КОМИССИЯ==========
Напишите алгоритм расчёта в виде функции, передавая в функцию:

    * тип карты/счёта (по умолчанию VK Pay);
    * сумму предыдущих переводов в этом месяце (по умолчанию 0 рублей);
    * сумму совершаемого перевода.
============================
MasterCard
Maestro
VK Pay

суммаПеревода
всяСуммаЗаМесяц(MasterCard, Maestro) // должны сохранять каждый перевод по выбранной карте
    // в МастерКард или Маэстро и когда привысится Лимит в календарный месяц 75тыс -> комиссия


 */


fun main() {
    commissions(100_000, TYPE_MASTERCARD, 0)
    commissions(76000)
    commissions(500_000, TYPE_MAESTRO)
}

const val LIMIT_CARD = 75_000 // лимит в календарный мес, после которого начисл комиссия
const val PERCENT = 0.6 // процентКомиссии 0,6%
const val TYPE_MASTERCARD = "MasterCard"
const val TYPE_MAESTRO = "Maestro"
const val TYPE_VK = "VK Pay"

fun commissions(
    transferAmount: Int, // сумма совершаемого перевода
    typeCard: String = "VK Pay", // тип карты/счёта
    amountPerMonth: Int = 0 // сумма всех переводов в этом месяце
) {
    val sum = amountPerMonth + transferAmount
    val calcCommission = transferAmount * (commissionByCardType(typeCard) / 100) // расчет суммы комиссии
    val resultCommission = if (sum > LIMIT_CARD && typeCard != TYPE_VK) calcCommission + 20 else 0.0
    return println("Сумма комиссии составит: ${resultCommission.toInt()} руб.")
//    val resultCommission = when {
////    val resultCommission = when (calcCommission > 0) {
//        (transferAmount + amountPerMonth > LIMIT_CARD) -> calcCommission + 20
//        else -> calcCommission
//    }
//    return println("Сумма комиссии составит: ${resultCommission.toInt()} руб.")
}

fun commissionByCardType(typeCard: String) = when (typeCard) {
    TYPE_VK -> 0.0
    TYPE_MASTERCARD, TYPE_MAESTRO -> PERCENT
    else -> 0.0
}