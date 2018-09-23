package eu.lumpy.camptracker.esi

import khttp.get
import khttp.responses.Response

class EsiClient(val baseUrl: String) {

    fun requestCorporationsForAlliance(allianceId: Int): Response {
        return get("https://" + baseUrl + "/alliances/" + allianceId + "/corporations/?datasource=tranquility")
    }

    fun requestKillmailsforCorporation(corporationsId: Int): Response {
        return get("https://" + baseUrl + "/corporations/" + corporationsId + "/killmails/recent/?datasource=tranquility")
    }

}

