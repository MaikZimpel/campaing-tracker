package eu.lumpy.camptracker.domain

import com.mongodb.client.MongoCollection
import org.bson.conversions.Bson
import org.litote.kmongo.findOne

data class Corporation(val id: Int, val name: String, val ticker: String): Dao<Corporation> {

    override fun insert(t: Corporation, collection: MongoCollection<Corporation>): Corporation {
        collection.insertOne(t)
        return t
    }

    override fun find(filter: Bson, collection: MongoCollection<Corporation>): Corporation? {
        return collection.findOne(filter)
    }
}