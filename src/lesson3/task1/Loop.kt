@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.sqrt
import kotlin.math.PI

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int =
    when {
        n < 10 -> 1
        else -> 1 + digitNumber(n / 10)
    }

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int =
    when {
        n < 3 -> 1
        else -> fib(n - 1) + fib(n - 2)
    }

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = abs(m * n) / gcd(m, n)

fun gcd(m: Int, n: Int): Int =
    when {
        n == 0 -> m
        else -> gcd(n, m % n)
    }

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int =
    (2..n).fold(n, {acc, elem -> if(n % elem == 0) min(elem, acc) else acc})

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int =
    (2..n - 1).fold(1, {acc, elem -> if(n % elem == 0) max(elem, acc) else acc})

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean =
    (floor(sqrt(m.toDouble())).toInt()..ceil(sqrt(n.toDouble())).toInt()).map({a -> a * a}).any({elem -> elem >= m && elem <= n})

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int =
    when {
        x == 1 -> 0
        x % 2 == 0 -> 1 + collatzSteps(x / 2)
        else -> 1 + collatzSteps(3 * x + 1)
    }

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val rx = x % (2 * PI)
    return sin_m(rx, eps, rx, 2, rx)
}

fun sin_m(x: Double, eps: Double, mp: Double, n: Int, acc: Double): Double {
    val m = mp * (-1.0 * x * x) / ((n.toDouble() * 2 - 1) * (n.toDouble() * 2 - 2))
    if(abs(m) < eps) {
        return m + acc
    } else {
        return sin_m(x, eps, m, n + 1, acc + m)
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val rx = x % (2 * PI)
    return cos_m(rx, eps, 1.0, 1, 1.0)
}

fun cos_m(x: Double, eps: Double, mp: Double, n: Int, acc: Double): Double {
    val m = mp * (-1.0 * x * x) / ((n.toDouble() * 2) * (n.toDouble() * 2 - 1))
    if(abs(m) < eps) {
        return m + acc
    } else {
        return cos_m(x, eps, m, n + 1, acc + m)
    }
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int = revert_m(1, n).second

fun revert_m(m: Int, n: Int): Pair<Int, Int> {
    if(n > 10) {
        val (mm, nn) = revert_m(m, n / 10)
        return Pair(mm * 10, (n % 10) * mm + nn)
    } else {
        return Pair(m * 10, n)
    }
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean =
    when {
        n < 10 -> false
        (n / 10) % 10 == n % 10 -> hasDifferentDigits(n / 10)
        else -> true
    }

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = squareSequenceDigit_m(n, 1)

fun squareSequenceDigit_m(n: Int, number: Int): Int {
    val digits_count = digitNumber(number * number)
    if(digits_count < n) {
        return squareSequenceDigit_m(n - digits_count, number + 1)
    } else {
     	return getDigitFromNumber(number * number, digits_count - n + 1)
    }
}

fun getDigitFromNumber(n: Int, m: Int): Int =
    when {
        m == 1 -> n % 10
        else -> getDigitFromNumber(n / 10, m - 1)
    }

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = fibSequenceDigit_m(n, 0, 1)

fun fibSequenceDigit_m(n: Int, fibp: Int, fib: Int): Int {
    val digits_count = digitNumber(fib)
    if(digits_count < n) {
        return fibSequenceDigit_m(n - digits_count, fib, fibp + fib)
    } else {
     	return getDigitFromNumber(fib, digits_count - n + 1)
    }
}
