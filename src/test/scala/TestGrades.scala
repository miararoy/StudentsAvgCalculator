class TestGrades extends UnitTestBaseClass {

  describe("test Grades class"){
    val grades: Grades = new Grades("math", 90)
    describe("When class is initiated with subject and mark"){
      it("Should initialize successfully"){
        assert(grades.getNumOfSubjects() == 1)
      }
      it("Should be retrievable"){
        assert(grades.getGrade("math") == 90)
      }
    }
    describe("When Updating a class"){
      it("Should return true if subject does not already exists"){
        assert(grades.updateGrade("history", 70))
        assert(grades.getNumOfSubjects() == 2)
      }
      it("should return false if trying to update an existing grage"){
        assert(!grades.updateGrade("math", 80))
      }
    }
    describe("When retrieving average"){
      it("Should return the average as Double"){
        assert(grades.getGradesAvg() == 80.0)
      }
    }
  }
}
