package eu.lumpy.camptracker.domain

import com.mongodb.client.MongoCollection
import org.bson.conversions.Bson


interface Dao<T> {


    fun insert(t:T, collection: MongoCollection<T>):T
    fun find(filter: Bson, collection: MongoCollection<T>): T?


}