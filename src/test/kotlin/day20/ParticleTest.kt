package day20

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import utils.FileOpener


class ParticleTest {

    @Test
    fun actual1NoSimulation() {
        val particles = FileOpener().getFile("/day20.txt").mapIndexed { index, it -> it.toParticle(index) }

         val theGuy = particles.groupBy { it.accMag() }
                .let{ it[it.keys.min()] }!!
                .minBy { it.velocity.dotProd(it.acceleration) }!!

        println(theGuy.id)

    }

    @Test
    fun actual1WithSimulationUnboundedSolution() {
        val particles = FileOpener().getFile("/day20.txt").mapIndexed { index, it -> it.toParticle(index) }

        (0..1000000).fold(particles, { particles, _ -> particles.map { it.tick() }})
                .sortedBy { it.distanceToOrigin() }
                .forEach { println("${it.id} md: ${it.distanceToOrigin()}    p: ${it.position}  v:${it.velocity}  a: ${it.acceleration}") }
    }

    @Test
    fun actual2UnboundedSolution() {
        val particles = FileOpener().getFile("/day20.txt").mapIndexed { index, it -> it.toParticle(index) }
        val survivors = (0..38)
                .fold(particles, { particles, _ ->
                    particles.map { it.tick() }
                    .groupBy { it.position }
                    .filter { it.value.size == 1 }
                    .map { it.value[0] }
        }).size
        println(survivors)
    }

    @Test
    fun equalityTest() {
        val p1 = Particle(Vector(1,1,1), Vector(0,0,0), Vector(0,0,0), 0)
        val p2 = Particle(Vector(1,1,1), Vector(0,0,0), Vector(0,0,0), 0)
        assertThat(p1.position).isEqualTo(p2.position)
    }

    @Test
    fun tickerTest1() {
        val particleTime0 = "p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>".toParticle(0)
        val particleTime1 = particleTime0.tick()
        val particleTime2 = particleTime1.tick()
        val particleTime3 = particleTime2.tick()
        val particleTime4 = particleTime3.tick()
        val particleTime5 = particleTime4.tick()
        val particleTime6 = particleTime5.tick()

        println("p: ${particleTime0.position} v: ${particleTime0.velocity} a: ${particleTime0.acceleration}")
        println("p: ${particleTime1.position} v: ${particleTime1.velocity} a: ${particleTime1.acceleration}")
        println("p: ${particleTime2.position} v: ${particleTime2.velocity} a: ${particleTime2.acceleration}")
        println("p: ${particleTime3.position} v: ${particleTime3.velocity} a: ${particleTime3.acceleration}")
        println("p: ${particleTime4.position} v: ${particleTime4.velocity} a: ${particleTime4.acceleration}")
        println("p: ${particleTime5.position} v: ${particleTime5.velocity} a: ${particleTime5.acceleration}")
        println("p: ${particleTime6.position} v: ${particleTime6.velocity} a: ${particleTime6.acceleration}")

    }
}