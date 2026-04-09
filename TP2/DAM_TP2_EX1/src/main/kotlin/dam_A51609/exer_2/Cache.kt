package org.example.dam_A51609.exer_2

class Cache<K : Any, V : Any> {

    private val entries = mutableMapOf<K,V>()


    fun put(key: K, value: V){

        entries[key] = value

    }

    fun get(key: K): V?{
        return entries[key]
    }

    fun evict(key: K){
        entries.remove(key)
    }

    fun size(): Int{
        return entries.size
    }

    fun getOrPut(key: K, default: ()-> V): V{

        if(entries.containsKey(key)){
            return entries[key]!!
        } else{
            val value = default()
            put(key, value)
//            get(key)!!
            //entries[key] = value
            return value

        }


    }

    fun transform(key: K, action: (V)-> V): Boolean{

        if(entries.containsKey(key)) {

            val value = entries[key]
            if (value != null) {
                val newVal = action(value)
                put(key, newVal)
                return true
            }
        }
        return false
    }

    fun snapshot(): Map<K, V>{
        return entries.toMap()
    }

    fun filterValues(predicate: (V)-> Boolean): Map<K, V>{

        return entries.filterValues(predicate)

    }
}