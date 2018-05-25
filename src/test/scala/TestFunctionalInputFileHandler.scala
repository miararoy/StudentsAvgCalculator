import java.io.FileNotFoundException

class TestFunctionalInputFileHandler extends UnitTestBaseClass {
  describe("Reading file in a more functional manner"){
    describe("Should result in proper file reading"){
      it("should return Iterator[(Int, String, String, Int)]"){
        var row = FunctionalInputFileHandler
          .readFileAndMapToIterator(
            getClass.getResource("sanity_reading_test_file.csv").getPath
          ).next()
        assert(
          row.equals(
            StudentRow(113, "Cohen", "Math", 80)
          )
        )
      }
      it("should throw FileNotFoundException when file not found"){
        assertThrows[FileNotFoundException](
          FunctionalInputFileHandler
            .readFileAndMapToIterator(
              "./students/src/main/resources/sanity_reading_test_filecccc.csv"
            )
        )
      }
    }
  }
}
