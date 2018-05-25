import scala.collection.concurrent.TrieMap
import scala.collection.mutable.ListBuffer

object DataOperations {

  def strToRow(separator: Char)(str: String): StudentRow =
  {
    val row: Array[String] = str.split(separator)
    StudentRow(row(0).toInt, row(1), row(2), row(3).toInt)
  }

  def studentIteratorToMapOfAvg(rows: Iterator[StudentRow]): Map[Int, Double] = {
    val students: TrieMap[Int, (Double, Int)] = TrieMap.empty[Int, (Double, Int)]
    val subjects: TrieMap[String, ListBuffer[Int]] = TrieMap.empty[String, ListBuffer[Int]]
    rows.foreach(
      r => {
        students.get(r.id) match {
          case Some(gr) => { // student already in db
            subjects.get(r.subject) match {
              case Some(l) => { // subject already has students
                if (l.contains(r.id)) { // double inserting for subject
                  students.update(r.id, (-1.0, -1))
                }
                else {
                  if (gr._2 != -1) { // if student not already invalid
                    students.update(r.id, ((gr._1 * gr._2 + r.grade) / (gr._2 + 1), gr._2 + 1))
                    subjects.update(r.subject, l += r.id)
                  }
                }
              }
              case None => { // no students assigned for subject
                students.update(r.id, ((gr._1 * gr._2 + r.grade) / (gr._2 + 1), gr._2 + 1))
                subjects.update(r.subject, ListBuffer(r.id))
              }
            }
          }
          case None => {
            students.update(r.id, (r.grade, 1))
            subjects.update(r.subject, ListBuffer(r.id))
          }
        }
      }
    )
    students.map{
      case (id, gr) => {
        id -> gr._1
      }
    }.readOnlySnapshot().toMap
  }
}
