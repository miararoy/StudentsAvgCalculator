import java.io.FileNotFoundException

import scala.io.BufferedSource

object FunctionalInputFileHandler {

  def readFileAndMapToIterator(filePath: String, sep: Char = '|'): Iterator[(Int, String, String, Int)] = {
    try {
      getLinesIterator(
        io.Source.fromFile(filePath),
        sep,
        strToRow(sep)
      )
    } catch {
      case ex: Exception => {
        throw new FileNotFoundException()
      }
    }
  }

  def getLinesIterator(
                        bufferedSource: BufferedSource,
                        sep: Char,
                        mappingFunction: (String) => (Int, String, String, Int)
                      ): Iterator[(Int, String, String, Int)] =
  {
    bufferedSource.getLines().map(l => strToRow(separator = sep)(l))
  }

  def strToRow(separator: Char)(str: String): (Int, String, String, Int) =
  {
    val row: Array[String] = str.split(separator)
    (row(0).toInt, row(1), row(2), row(3).toInt)
  }
}
