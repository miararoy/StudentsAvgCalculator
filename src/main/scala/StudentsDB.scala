import scala.collection.concurrent.TrieMap
import scala.collection.mutable.Map

object StudentsDB {

  private var students: TrieMap[Int, Grades] = TrieMap.empty[Int, Grades]
  private var studentsPersonalData: Map[Int, StudentNameStatus] = Map.empty[Int, StudentNameStatus]

  def update(row: (Int, String, String, Int)): Unit = {
    try{
      students.get(row._1) match {
        case Some(gr) => {  // Student already in the db
          if (gr.updateGrade(row._3, row._4)){} // Update Successful
          else {
            studentsPersonalData.update(row._1, StudentNameStatus(row._2, false))
          }
        }
        case None => { // Student not in db, create new entry
          students.update(
            row._1,
            new Grades(row._3, row._4)
          )
          studentsPersonalData.update(
            row._1,
            StudentNameStatus(row._2)
          )
        }
      }
    } catch {
      case ex: Exception => {throw ex}
    }
  }

  def getStudentAvg(studentId: Int): Double = {
    try {
      (students.readOnlySnapshot().get(studentId),
      studentsPersonalData.get(studentId)) match {
        case (Some(gr), Some(data)) => {
          if (data.status) {gr.getGradesAvg()}
          else {-1.0}
        }
        case _ => {
          throw new NoSuchElementException(s"Student $studentId does not exists in DataBase")
        }
      }
    } catch {
      case ex: Exception => {throw ex}
    }
  }

  def getAllStudentsAvg(): scala.collection.Map[Int, Double] = {
    students.readOnlySnapshot().map{
      case (id, _) => {
        id -> getStudentAvg(id)
      }
    }
  }

  def getStudentDetails(id: Int): (Grades, StudentNameStatus) ={
    if (students.isDefinedAt(id) && studentsPersonalData.isDefinedAt(id)){
      (students.get(id).get, studentsPersonalData.get(id).get)
    }
    else {
      throw new NoSuchElementException(s"Did not find student with id = $id")
    }
  }

  def getNumberOfStudents(): Int = {
    if (students.readOnlySnapshot().size == studentsPersonalData.size){
      students.readOnlySnapshot().size
    }
    else {
      throw new MatchError("students data and student's personal data num of entries does not match")
    }
  }

  def clearDB(): Unit = {
    this.students = TrieMap.empty[Int, Grades]
    this.studentsPersonalData = Map.empty[Int, StudentNameStatus]
  }

  case class StudentNameStatus(lastName: String, status: Boolean = true)
}
