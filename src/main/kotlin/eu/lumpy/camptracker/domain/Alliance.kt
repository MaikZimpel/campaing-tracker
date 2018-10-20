package eu.lumpy.camptracker.domain

import com.mongodb.client.MongoCollection
import org.bson.conversions.Bson
import org.litote.kmongo.findOne

data class Alliance(val id: Int, val name: String, val ticker: String, val corporations: List<Corporation>): Dao<Alliance> {


    override fun insert(t: Alliance, collection: MongoCollection<Alliance>): Alliance {
        collection.insertOne(t);
        return t
    }

    override fun find(filter: Bson, collection: MongoCollection<Alliance>): Alliance? {
        return collection.findOne(filter)
    }
}