@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if(age % 100 == 0) return "$age лет"
    if(age % 100  == 1) return "$age год"
    if(age % 100 > 1 && age % 100 <= 4) return "$age года"
    if(age % 100 > 4 && age % 100 <= 20) return "$age лет"

    if(age % 10 == 0) return "$age лет"
    if(age % 10 == 1) return "$age год"
    if(age % 10 > 1 && age % 10 <= 4) return "$age года"
    if(age % 10 > 4 && age % 10 <= 9) return "$age лет"

    return TODO()
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val way = t1 * v1 + t2 * v2 + t3 * v3

    if(t1 * v1 >= way / 2) return way / 2 / v1
    if(t1 * v1 < way / 2 && t1 * v1 + t2 * v2 >= way / 2) return t1 + (way / 2 - t1 * v1) / v2
    if(t1 * v1 + t2 * v2 < way / 2 && t1 * v1 + t2 * v2 + t3 * v3 >= way / 2) return t1 + t2 + (way / 2 - t1 * v1 - t2 * v2) / v3

    return TODO()
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val rook1Attacks = rookX1 - kingX == 0 || rookY1 - kingY == 0
    val rook2Attacks = rookX2 - kingX == 0 || rookY2 - kingY == 0

    if(rook1Attacks && rook2Attacks) return 3
    if(rook1Attacks) return 1
    if(rook2Attacks) return 2
    return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val rookAttacks = rookX - kingX == 0 || rookY - kingY == 0
    val bishopAttacks = abs(bishopX - kingX) == abs(bishopY - kingY)

    if(rookAttacks && bishopAttacks) return 3
    if(rookAttacks) return 1
    if(bishopAttacks) return 2
    return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val triangle = arrayOf(a, b, c)
    triangle.sort()

    if(triangle[0] + triangle[1] <= triangle[2]) return -1

    if(triangle[0].pow(2.0) + triangle[1].pow(2.0) > triangle[2].pow(2.0)) return 0
    if(triangle[0].pow(2.0) + triangle[1].pow(2.0) < triangle[2].pow(2.0)) return 2

    return 1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a <= c && b >= c) {
        return min(b, d) - c
    } else if(a <= d && b >= d) {
        return d - max(a, c)
    } else if(c <= a && d >= a) {
        return min(b, d) - a
    } else if(c <= b && d >= b) {
        return b - max(a, c)
    } else {
        return -1
    }

}
