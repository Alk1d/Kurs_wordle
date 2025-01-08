package com.example.kurs_wordle.ui.game
import kotlin.random.Random

class GameCore(
    private var rowCount: Int = 7
) {
    val IN_WORD = 0
    val IN_PLACE = 1
    val NOT_IN = 2

    private var pause = false
    private var curRow: Int = 0;
    private var curCol: Int = 0;
    private var difficulty = 1
    private var rows = mutableListOf<MutableList<Char>>();
    private lateinit var word: String;

    init {
        for (i in 0 until rowCount) {
            val row = MutableList(5) { ' ' }
            rows.add(row)
        }
    }

    fun getFinalWord(): String {
        return word
    }

    fun getResult(): Boolean {
        for (row in 0 until rowCount) {
            if (rows[row].joinToString(separator="") == word) {
                pause = true
                return true
            }
        }
        return false
    }

    fun isPause(): Boolean {
        return pause
    }

    fun startOver() {
        curCol = 0
        curRow = 0
        pause = false
        for (row in 0 until rowCount) {
            for (col in 0 until 5) {
                rows[row][col] = ' '
            }
        }
        when(difficulty) {
            0 -> setWordTest()
            1 -> setWordNormal()
            2 -> setWordHard()
            else -> setWordNormal()
        }
    }


    fun setNextChar(c: Char): Boolean {
        when {
            curCol == 5 -> return false
            rows[curRow][curCol] == ' ' -> {
                rows[curRow][curCol] = c
                if (curCol < 5) curCol++
                return true
            }

            else -> return false
        }
    }

    fun erase() {
        if (curCol > 0 && rows[curRow][curCol] == ' ') {
            curCol--
        }
        rows[curRow][curCol] = ' '
    }

    fun enter(): Boolean {
        if (curCol == 5 && curRow <= rowCount) {
            curCol = 0
            curRow++
            if (curRow == rowCount) {
                pause = true
            }
            return true
        }
        return false
    }

    fun validateChar(row: Int, col: Int): Int {
        if (rows[row][col] == word[col]) {
            return IN_PLACE
        } else if (rows[row][col] in word) {
            return IN_WORD
        }
        return NOT_IN
    }

    fun getCurRow(): Int {
        return curRow
    }

    fun getCurCol(): Int {
        return curCol
    }

    fun setWordNormal() {
        var words = listOf<String>(
            "APPLE",
            "TIGER",
            "OCEAN",
            "ROBOT",
            "SNAIL",
            "PANDA",
            "IGLOO",
            "LEMON",
            "MOUSE",
            "PIZZA",
            "CHAIR",
            "EARTH",
            "PIANO",
            "RIVER",
            "EAGLE",
            "ZEBRA",
            "CLOWN",
            "CLOUD",
            "SPOON",
            "TRAIN",
            "CLOCK",
            "SHOES",
            "SOCKS",
            "MAGIC",
            "COMET",
            "WHALE",
            "JELLY",
            "SHIRT",
            "LEMON",
            "SMILE",
            "MOUSE",
            "ANGEL",
            "OCEAN",
            "ROBOT",
            "SWORD",
            "SUSHI",
            "HEART",
            "GHOST",
            "GRAPE",
            "HONEY",
            "MANGO",
            "PEACH",
            "SNACK",
            "JELLY",
            "PIZZA",
            "EAGLE",
            "ALARM",
            "FAIRY",
            "CLOUD",
            "PHONE",
            "PLANE",
            "WATCH",
            "CHAIR",
            "GRASS",
            "HOTEL",
            "LEMON",
            "TIGER",
            "WATER",
            "PAPER",
            "FRUIT",
            "SWING",
        )
        word = words[Random.nextInt(words.size)]
    }
    fun setWordTest() {
        var words = listOf<String>(
            "QWERT",
        )
        word = words[Random.nextInt(words.size)]
    }
    fun setWordHard() {
        var words = listOf<String>(
            "SPATE",
            "ODIUM",
            "VAPID",
            "STINT",
            "AUGUR",
            "BANAL",
            "ASSAY",
            "PIQUE",
            "CANON",
            "TAPER",
            "BLASE",
            "ANTIC",
            "CLOUT",
            "TACIT",
            "INTER",
            "DROLL",
            "TROPE",
            "SMITE",
            "TRICE",
            "MELEE",
            "MIMIC",
            "PZAZZ",
            "FLUFF",
            "CIVIC",
            "NYMPH",
            "PYGMY",
            "EPOXY",
            "GECKO",
            "PUPPY",
            "CACAO",
            "VIVID",
            "SAVVY",
        )
        word = words[Random.nextInt(words.size)]
    }
}