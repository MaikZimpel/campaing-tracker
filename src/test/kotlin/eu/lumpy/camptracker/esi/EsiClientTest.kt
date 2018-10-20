package eu.lumpy.camptracker.esi


import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it


class EsiClientTest: Spek({


    val client = EsiClient("https://esi.evetech.net/latest")

    describe("Test EsiClient functionality") {
        it("should print a list of corporation ids") {
            println(client.corporations(99005843))
        }
    }

    describe("Test allianceInfo") {
        it("should print alliance info") {
            println(client.allianceInfo(99005843))
        }
    }
})