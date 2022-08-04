package hellojpa

import javax.persistence.Persistence

object JpaMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val emf = Persistence.createEntityManagerFactory("hello")
        val em = emf.createEntityManager()
        val tx = em.transaction
        tx.begin()

        try {
//            val member = em.find(Member::class.java, 1L)
//            member.name = "노정민"
//            println("${member.id} / ${member.name}")

            val result = em.createQuery("select m from Member as m", Member::class.java)
                .resultList

            result.forEach { println(it.name) }

            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
        } finally {
            em.close()
        }
        emf.close()
    }
}
