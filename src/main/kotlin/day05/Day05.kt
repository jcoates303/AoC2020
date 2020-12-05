package day05

import common.InputData
import common.printAndMeasureDuration

const val seatsPerRow = 8
val data = InputData.readLines("2020-Day5.txt")
val emptySeat = Seat(0,0)

data class Seat(val row:Int, val col:Int) {
    val seatID:Int = row * seatsPerRow + col

    override fun toString():String {
        return "Seat row $row, column $col, seat ID $seatID"
    }
}

fun readReservations(reservations:List<String>):List<Seat> {
    return reservations.map(::readSeat)
}

fun readSeat(str:String):Seat {
    return if(str.length == 10) {
        val rowVal = Integer.parseInt(str.take(7).replace('F', '0').replace('B','1'),2)
        val colVal = Integer.parseInt(str.takeLast(3).replace('R', '1').replace('L','0'),2)
        Seat(rowVal, colVal)
    } else
        emptySeat
}

fun seatGapsList(reservations: List<Seat>): List<Seat> {
    val orderedSeats = reservations.sortedBy { it.seatID }
    return orderedSeats.filterIndexed { index, seat -> (seat.seatID - orderedSeats[Integer.max(index-1,0)].seatID) > 1}
}

fun mySeatIs(seats:List<Seat>):Seat {
    val first = seats.first()
    val col = first.col.minus(1).rem(seatsPerRow)
    return if(col == 7)
        Seat(first.row-1, col)
    else
        Seat(first.row, col)
}

fun solvePart1():String {
    return readReservations(data).maxByOrNull { it.seatID }.toString() // 28ms and second function takes 19ms, for a total of 47ms
    // return readReservations(data).sortedBy { it.seatID }.last().toString() // 42ms and second function takes 4ms, for a total of 46ms
}

fun solvePart2():String {
    return mySeatIs(seatGapsList(readReservations(data))).toString()
}

fun main() {
    printAndMeasureDuration("Part One",::solvePart1)
    printAndMeasureDuration("Part Two",::solvePart2)
}