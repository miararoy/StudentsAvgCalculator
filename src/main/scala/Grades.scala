import scala.collection.concurrent.TrieMap

class Grades (subject: String, mark: Int) {

  private val grades: TrieMap[String, Int] = TrieMap[String, Int](subject -> mark)

  def updateGrade(subject: String, mark: Int): Boolean = {
    try {
      grades.get(subject) match {
        case Some(mrk) => { // Problem, mark already exists
          false
        }
        case None => {
          grades.update(subject, mark)
          true
        }
      }
    } catch {
      case ex: Exception => {throw  ex}
    }
  }

  def getGradesAvg(): Double = {
    try{
      grades.readOnlySnapshot().map{case(_, m) => m}.sum / grades.size
    } catch {
      case ex: Exception => {throw ex}
    }
  }

  def getGrade(subject: String): Int = {
    grades.get(subject) match {
      case Some(mrk) => {mrk}
      case None => {-1}
    }
  }

  def getNumOfSubjects(): Int = {grades.size}
}
