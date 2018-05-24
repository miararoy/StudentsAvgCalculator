import java.io.FileNotFoundException

import scala.io.BufferedSource

class InputFileHandler(filePath: String, sep: Char = '|') {

  private val separator: Char = sep
  private var bufferedSource: BufferedSource = _
  try {
    bufferedSource = io.Source.fromFile(filePath)
  } catch {
    case ex: Exception => {
      throw new FileNotFoundException()
    }
  }

  def getLinesIterator(): Iterator[(Int, String, String, Int)] = {
    bufferedSource.getLines().map(l => strToRow(l))
  }

  def strToRow(str: String): (Int, String, String, Int) = {
    val row: Array[String] = str.split(separator)
    (row(0).toInt, row(1), row(2), row(3).toInt)
  }

  def closeBuffer(): Unit = {
    bufferedSource.close()
  }
}
