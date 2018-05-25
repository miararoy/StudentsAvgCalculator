import java.io.FileNotFoundException

import scala.io.BufferedSource

object FunctionalInputFileHandler {

  def readFileAndMapToIterator(filePath: String, sep: Char = '|'): Iterator[StudentRow] = {
    try {
      getLinesIterator(
        io.Source.fromFile(filePath),
        DataOperations.strToRow(sep)
      )
    } catch {
      case ex: Exception => {
        throw new FileNotFoundException(
          s"${filePath.split("/").last} was not found at ${filePath.split("/").dropRight(1).mkString("/")}"
        )
      }
    }
  }

  private def getLinesIterator(
                        bufferedSource: BufferedSource,
                        mappingFunction: (String) => StudentRow
                      ): Iterator[StudentRow] =
  {
    bufferedSource.getLines().map(l => mappingFunction(l))
  }
}
