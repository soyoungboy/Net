/*
 * Copyright © 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.kalle


object Canceler {

    private val map = mutableMapOf<Any, Canceller>()

    /**
     * Add a task to cancel.
     *
     * @param uid   target request.
     * @param canceller canceller.
     */
    fun addCancel(uid: Any?, canceller: Canceller) {
        uid ?: return
        map[uid] = canceller
    }

    /**
     * Remove a task.
     *
     * @param uid target request.
     */
    fun removeCancel(uid: Any?) {
        uid ?: return
        map.remove(uid)
    }

    /**
     * According to the tag to cancel a task.
     *
     */
    fun cancel(uid: Any?) {
        uid ?: return
        val iterator = map.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (uid == next.key) {
                iterator.remove()
                next.value.cancel()
            }
        }
    }
}