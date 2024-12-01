import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("input/$name.txt").readText().trim().lines()

//md5 hash
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun Any?.println() = println(this)
