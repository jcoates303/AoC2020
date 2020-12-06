package day05

import common.InputData
import common.printAndMeasureDuration

const val seatsPerRow = 8
const val seatRows = 114 // Obtained after reading all data
const val seatOffset = 12

val data = InputData.readLines("2020-Day5.txt")
val emptySeat = Seat(0,0)

data class Seat(val row:Int, val col:Int) {
    val seatID:Int = row * seatsPerRow + col

    override fun toString():String {
        return "Seat row $row, column $col, seat ID $seatID"
    }
}

// ### Parsing functions (Part 1) ###

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

// ### Part 2 - Find the empty seat ###

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

// ### Alternate Part 2 - Finding all empty seats ###
fun planeMap(res:List<Seat>):IntArray {
    val plane = IntArray(seatRows-seatOffset)
    res.forEach{
        val row = it.row-seatOffset
        plane[row] = plane[row].xor(1 shl it.col)
    }
    return plane
}

fun emptySeatListForRow(row:Int, seats:Int):List<Seat> {
    if(seats == 255) return emptyList()
    val result = emptyList<Seat>().toMutableList()
    var emptySeats = seats.xor(255)
    var position = 0
    while( emptySeats != 0) {
        if(emptySeats.and(1) != 0) result.add(Seat(row + seatOffset, position))
        position++
        emptySeats = emptySeats ushr 1
    }
    return result
}

fun emptySeatsList(seats:IntArray):List<Seat> {
   return seats.flatMapIndexed { index, i -> emptySeatListForRow(index, i) }
}

// ### Main calls ###

fun solvePart1():String {
    return readReservations(data).maxByOrNull { it.seatID }.toString() // 28ms and second function takes 20ms, for a total of 48ms
    // return readReservations(data).sortedBy { it.seatID }.last().toString() // 52ms and second function takes 4ms, for a total of 56ms
}

fun solvePart2():String {
    return mySeatIs(seatGapsList(readReservations(data))).toString()
}

fun solveAltPart2():String{
    // Idea is to use a bitmap of the plane, occupy all the seats and find the empty ones
    return emptySeatsList(planeMap(readReservations(data))).first().toString()
}

fun main() {
    printAndMeasureDuration("Part One",::solvePart1)
    printAndMeasureDuration("Part Two",::solvePart2, 1000)
    printAndMeasureDuration("Part Two (Alternate)",::solveAltPart2, 1000)
}