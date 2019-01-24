package eu.lumpy.camptracker.esi

import eu.lumpy.camptracker.domain.Alliance
import io.vertx.ext.mongo.MongoClient
import khttp.get
import khttp.responses.Response
import org.json.JSONArray

class EsiClient(val baseUrl: String) {

    val authUrl = "https://login.eveonline.com/v2/oauth/authorize"

    fun corporations(allianceId: Int): JSONArray {
        val url = "$baseUrl/alliances/$allianceId/corporations/?datasource=tranquility"
        return get(url).jsonArray
    }

    fun allianceInfo(allianceId: Int): Alliance {
        val url = "$baseUrl/alliances/$allianceId/?datasource=tranquility"
        val responseJson = get(url).jsonObject
        val name = responseJson.getString("name")
        val ticker = responseJson.getString("ticker")
        //responseJson = get("$baseUrl/alliances/$allianceId/corporations/?datasource=tranquility").jsonObject

        return Alliance(allianceId, name, ticker)
    }

    fun allianceInfo(): List<Int> {
        val url = "$baseUrl/alliances/?datasource=tranquility"
        val responseJson = get(url).jsonArray
        return responseJson.map { it as Int }
    }

    fun killmails(corporationsId: Int): Response {
        return get("https://" + baseUrl + "/corporations/" + corporationsId + "/killmails/recent/?datasource=tranquility")
    }

}

