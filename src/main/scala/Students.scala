import java.io.FileNotFoundException

object Students {

  def main(args: Array[String]): Unit = {
    digestFileOO("/Users/roymiara/students/src/main/resources/test_file.csv")
    pprintStudents()
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