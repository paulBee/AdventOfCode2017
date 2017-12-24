package day20

import java.lang.Math.abs

class Particle( val position: Vector,
                val velocity: Vector,
                val acceleration: Vector,
                val id:Int
                ) {

    fun tick(): Particle = Particle(position.add(velocity).add(acceleration), velocity.add(acceleration), acceleration, id)

    fun distanceToOrigin(): Long = abs(position.x) + abs(position.y) + abs(position.z)

    fun accMag(): Long = abs(acceleration.x) +  abs(acceleration.y) +  abs(acceleration.z)

}

fun clockAndInspect() {

}


data class Vector(val x: Long, val y: Long, val z: Long) {
    fun add(other: Vector): Vector = Vector(x + other.x, y + other.y, z + other.z)
    fun dotProd(other: Vector): Long = x * other.x + y * other.y + z * other.z
}

fun String.toVector() = this.split(",").map{ it.trim().toLong() }.let { (x,y,z) -> Vector(x,y,z) }

fun String.toParticle(id: Int): Particle {
    val (p, v, a) = Regex("""p=<(.*)>, v=<(.*)>, a=<(.*)>""").find(this.trim())!!.destructured
    return Particle(p.toVector(), v.toVector(), a.toVector(), id)
}