class TestStudentsDB extends UnitTestBaseClass {

  describe("Test Student's DataBase"){
    describe("Good DataBase update"){
      it("Should update the database"){
        StudentsDB.update(1, "miara", "math", 90)
        assert(StudentsDB.getNumberOfStudents() == 1)
      }
      it("should initialize student in active mode = true"){
        assert(StudentsDB.getStudentDetails(1)._2.status)
      }
      it("should enable updating of mark only"){
        StudentsDB.update(1, "miara", "history", 70)
        assert(StudentsDB.getNumberOfStudents() == 1)
        assert(StudentsDB.getStudentDetails(1)._1.getGrade("math") == 90)
        assert(StudentsDB.getStudentDetails(1)._1.getGrade("history") == 70)
      }
      it("should enable updating students"){
        StudentsDB.update(2, "cohen", "math", 60)
        assert(StudentsDB.getNumberOfStudents() == 2)
      }
      it("should enbale re-updating of mark, but will make student as active = false"){
        StudentsDB.update(2, "cohen", "math", 70)
        assert(!StudentsDB.getStudentDetails(2)._2.status)
      }
      it("should return the average of existing, valid student"){
        assert(
          StudentsDB.getStudentAvg(1) == 80.0
        )
      }
      it("Should return -1.0 if student is invalid"){
        assert(
          StudentsDB.getStudentAvg(2) == -1.0
        )
      }
      describe("Get All Students Avg"){
        it("should return map of id->avg"){
          assert(
            StudentsDB.getAllStudentsAvg().equals(
              Map(1 -> 80.0, 2 -> -1.0)
            )
          )
        }
      }
    }
    describe("Trying to retrieve non-existing student"){
      it("Should throw NoSuchElementException"){
        assertThrows[NoSuchElementException](
          StudentsDB.getStudentAvg(3)
        )
      }
    }
  }
}
