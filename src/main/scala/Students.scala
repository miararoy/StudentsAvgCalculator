import java.io.FileNotFoundException

object Students {

  val basePath = "/test_file.csv"

  def main(args: Array[String]): Unit = {
    if (args(0) == "OO") {
      digestFileOO(getClass.getResource(basePath).getPath)
      pprintStudents()
    }
    else if (args(0) == "func") {
      StudentsDB.update(
        FunctionalInputFileHandler.readFileAndMapToIterator(
          getClass.getResource(basePath).getPath
        )
      )
    }
    else {
      throw new IllegalArgumentException(s"${args(0)} is not yet Supported")
    }
  }

  def digestFileOO(filePath: String): Unit = {
    try {
      val it = new InputFileHandler(filePath)
      it.getLinesIterator().foreach(
        line => StudentsDB.update(line)
      )
      it.closeBuffer()
    } catch {
      case e: FileNotFoundException => {
        throw new FileNotFoundException(s"Couldn't find file at path: $filePath")
      }
      case e: Throwable => {
        throw new RuntimeException("Caught unexpected exception")
      }
    }
  }

  def pprintStudents(): Unit = {
    StudentsDB.getAllStudentsAvg().foreach{
      case (id, avg) =>{
        println(s"$id, $avg")
      }
    }
  }

}