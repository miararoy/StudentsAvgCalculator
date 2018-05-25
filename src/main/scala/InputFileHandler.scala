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

  def getLinesIterator(): Iterator[StudentRow] = {
    bufferedSource.getLines().map(l => DataOperations.strToRow(separator)(l))
  }

  def strToRow(str: String): StudentRow = {
    DataOperations.strToRow(separator)(str: String)
  }

  def closeBuffer(): Unit = {
    bufferedSource.close()
  }
}
