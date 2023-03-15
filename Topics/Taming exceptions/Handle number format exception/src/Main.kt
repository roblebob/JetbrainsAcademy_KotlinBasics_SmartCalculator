fun parseCardNumber(cardNumber: String): Long {
    // TODO
    if (cardNumber[4] != ' ' || cardNumber[9] != ' ' || cardNumber[14] != ' ' || cardNumber.length != 19) {
        throw Exception("Not a valid card number.")
    }
    return cardNumber.replace(" ", "").toLong()
}