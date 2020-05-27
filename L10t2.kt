package org.example
import java.util.concurrent.ArrayBlockingQueue
fun produs(vect: Array<Int>,const: Int): Array<Int>{
    return vect.map{it * const}.toTypedArray()
}
fun Ordonare(vect: Array<Int>): Array<Int>{
    return vect.sorted().toTypedArray()
}
fun Prnt(vect: Array<Int>){
    println("Array: ")
    vect.forEach{ print("${it} ")}
    println()
}
fun main(args: Array<String>) {
    val a = ArrayBlockingQueue<Array<Int>>(1)
    val b = ArrayBlockingQueue<Array<Int>>(1)
    val c = ArrayBlockingQueue<Array<Int>>(1)
    val executor = java.util.concurrent.Executors.newFixedThreadPool(3)
    try {
        executor.execute {
            try {
                while (true) {
                    b.put(produs(a.take(), 5))
                }
            } catch (ex: InterruptedException) {
            }
        }
        executor.execute {
            try {
                while (true) {
                    c.put(Ordonare(b.take()))
                }
            } catch (ex: InterruptedException) {
            }
        }
        executor.execute {
            try {
                while (true) {
                    Prnt(c.take())
                }
            } catch (ex: InterruptedException) {
            }
        }
        a.put(arrayOf(
            20, 30, 60, 32, 10
        ))
        a.put(arrayOf(
            74,555,1024,13,21
        ))
    } catch(ex:InterruptedException) {
        executor.shutdown()
    }
}