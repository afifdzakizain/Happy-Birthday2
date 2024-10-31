package BasicKotlin

fun main() {
    println("--- Notification Summary ---")
    notificationSummary()

    println("\n--- Foldable Phone ---")
    testFoldablePhone()

    println("\n--- Auction Price ---")
    testAuctionPrice()

    println("\n--- Informasi Lagu ---")
    testMusic()

    println("\n--- Person Profiles ---")
    testPerson()

    println("\n--- Temperature Conversion ---")
    testTemperatureConversion()

    println("\n--- Movie Ticket Prices ---")
    testTicketPrices()
}

// --- NOTIFICATION SUMMARY ---
fun notificationSummary() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages > 99) {
        println("Your phone is blowing up! You have 99+ notifications.")
    } else {
        println("You have $numberOfMessages notifications.")
    }
}

// --- FOLDABLE PHONE ---
fun testFoldablePhone() {
    val foldablePhone = FoldablePhone()
    foldablePhone.checkPhoneScreenLight()
    foldablePhone.unfold()
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()
    foldablePhone.fold()
    foldablePhone.switchOn()
}

open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = false) : Phone() {
    override fun switchOn() {
        if (!isFolded) {
            isScreenLightOn = true
            println("The foldable phone is unfolded and the screen's light is on.")
        } else {
            println("Can't switch on, the phone is folded.")
        }
    }

    fun fold() {
        isFolded = true
        println("The phone is now folded.")
    }

    fun unfold() {
        isFolded = false
        println("The phone is now unfolded.")
    }
}

// --- AUCTION PRICE ---
fun testAuctionPrice() {
    val winningBid = Bid(5000, "Private Collector")
    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    return bid?.amount ?: minimumPrice
}

// --- MUSIC INFORMATION ---
fun testMusic() {
    val lagu1 = Music("Baby Shark", "Denny Caknan", 1904, 1203)
    val lagu2 = Music("Untungnya Bumi Masih Diputar", "Bernad bear", 1946, 907)

    println("Informasi Lagu 1:")
    lagu1.deskripsiLagu()

    println("\nInformasi Lagu 2:")
    lagu2.deskripsiLagu()
}

class Music(
    val judul: String,
    val artis: String,
    val tahunPublikasi: Int,
    val jumlahPemutaran: Int
) {
    fun cekPopuler(): Boolean = jumlahPemutaran >= 1000

    fun deskripsiLagu() {
        println("Judul: $judul")
        println("Artis: $artis")
        println("Tahun Publikasi: $tahunPublikasi")
        println("Status: ${if (cekPopuler()) "Lagu sebelum kemerdekaan" else "Lagu setelah kemerdekaan"}")
    }
}

// --- PERSON PROFILES ---
fun testPerson() {
    val afif = Person("Afif", 20, "Golf", null)
    val salsa = Person("Salsa", 28, "Party", afif)

    afif.showProfile()
    salsa.showProfile()
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("Nama: $name")
        println("Age: $age")
        if (hobby != null) println("Hobby: $hobby")
        if (referrer != null) {
            println("Has a referrer named ${referrer.name}, who likes ${referrer.hobby}.")
        } else {
            println("Doesn't have a referrer.")
        }
    }
}

// --- TEMPERATURE CONVERSION ---
fun testTemperatureConversion() {
    convertTemperature(100.0, "Celsius", "Fahrenheit")
    convertTemperature(373.15, "Kelvin", "Celsius")
    convertTemperature(32.0, "Fahrenheit", "Kelvin")
}

fun convertTemperature(initialMeasurement: Double, initialUnit: String, finalUnit: String) {
    when {
        initialUnit == "Celsius" && finalUnit == "Fahrenheit" -> {
            printFinalTemperature(initialMeasurement, initialUnit, finalUnit) {
                (9.0 / 5.0) * it + 32
            }
        }
        initialUnit == "Kelvin" && finalUnit == "Celsius" -> {
            printFinalTemperature(initialMeasurement, initialUnit, finalUnit) {
                it - 273.15
            }
        }
        initialUnit == "Fahrenheit" && finalUnit == "Kelvin" -> {
            printFinalTemperature(initialMeasurement, initialUnit, finalUnit) {
                (5.0 / 9.0) * (it - 32) + 273.15
            }
        }
        else -> println("Conversion from $initialUnit to $finalUnit is not supported.")
    }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

// --- MOVIE TICKET PRICES ---
fun testTicketPrices() {
    val child = 5
    val adult = 28
    val senior = 87
    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when {
        age in 0..12 -> 100
        age in 13..60 -> if (isMonday) 115 else 125
        age in 61..100 -> 140
        else -> -1
    }
}