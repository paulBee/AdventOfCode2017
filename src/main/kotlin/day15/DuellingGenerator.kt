package day15


object GeneratorBattle {
    fun letBattleCommence(aStartingVal: Long,
                          bStartingVal: Long,
                          take: Int = 40_000_000,
                          aPredicate: (Long) -> Boolean = { true },
                          bPredicate: (Long) -> Boolean = { true }): Int {

        val seqA = generateSequence(aStartingVal) { (it * 16807) % 2_147_483_647 }.filter(aPredicate)
        val seqB = generateSequence(bStartingVal) { (it * 48271) % 2_147_483_647 }.filter(bPredicate)

        return seqA.zip(seqB).take(take)
                .filter { (val1, val2) -> val1 % 65_536 == val2 % 65_536 }.count()
    }

    fun letBattleCommence2(aStartingVal: Long, bStartingVal: Long) =
            letBattleCommence(aStartingVal, bStartingVal, 5_000_000, { it % 4 == 0L}, {it % 8 == 0L})
}