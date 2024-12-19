package com.example.interviewprepsample.data.network

data class JokeResponse(
    val category: String,
    val type: String,
    val joke: String?,
    val setup: String?,
    val delivery: String?,
    val id: Long,
    val error: Boolean
) {
    fun getCombinedJoke(): String? {
        return if (joke.isNullOrEmpty()) {
            "$setup $delivery"
        } else joke
    }
}

/**
 *
 * {
 *     "error": false,
 *     "category": "Programming",
 *     "type": "single",
 *     "joke": "The six stages of debugging:\n1. That can't happen.\n2. That doesn't happen on my machine.\n3. That shouldn't happen.\n4. Why does that happen?\n5. Oh, I see.\n6. How did that ever work?",
 *     "flags": {
 *         "nsfw": false,
 *         "religious": false,
 *         "political": false,
 *         "racist": false,
 *         "sexist": false,
 *         "explicit": false
 *     },
 *     "id": 123,
 *     "safe": true,
 *     "lang": "en"
 * }
 */

/**
 * {
 *     "error": false,
 *     "category": "Misc",
 *     "type": "twopart",
 *     "setup": "I'm sure good looking lesbians look at fat lesbians and give them no chance.",
 *     "delivery": "Until they see their fingers.",
 *     "flags": {
 *         "nsfw": true,
 *         "religious": false,
 *         "political": false,
 *         "racist": false,
 *         "sexist": false,
 *         "explicit": true
 *     },
 *     "id": 164,
 *     "safe": false,
 *     "lang": "en"
 * }
 */
