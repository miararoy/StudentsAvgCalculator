import java.io.FileNotFoundException

object Students {

  val basePath = "/test_file.csv"

  def main(args: Array[String]): Unit = {
    var path = getClass.getResource(basePath).getPath
    if (args.size > 1) {path = args(1)}

    if (args(0) == "OO") {
      digestFileOO(path)
      pprintStudents()
    }
    else if (args(0) == "fun_db") {
      StudentsDB.update(
        FunctionalInputFileHandler.readFileAndMapToIterator(
          path
        )
      )
      pprintStudents()
    }
    else if (args(0) == "fun_imm") {
      pprintStudents(
        DataOperations.studentIteratorToMapOfAvg(
          FunctionalInputFileHandler.readFileAndMapToIterator(
            path
          )
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

  def pprintStudents(map: Map[Int, Double] = null): Unit = {
    var printOutMap: Map[Int, Double] = Map.empty[Int, Double]
    if (map != null) {
      printOutMap = map
    }
    else {
      printOutMap = StudentsDB.getAllStudentsAvg().toMap
    }
    printOutMap.foreach{
      case (id, avg) =>{
        println(s"$id, $avg")
      }
    }
  }

}