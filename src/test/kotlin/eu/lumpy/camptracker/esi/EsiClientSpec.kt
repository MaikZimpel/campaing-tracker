package eu.lumpy.camptracker.esi


import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.on

object EsiClientSpec: Spek({
    given("An Esi Client") {
        val client = EsiClient("esi.evetech.net/latest")
        on ("call corporations on alliance 99005843") {
            val response = client.requestCorporationsForAlliance(99005843)
            assertThat("[98323502,98371266,98419470,98568293]".toByteArray(Charsets.UTF_8)).isEqualTo(response.content)
        }
    }
})