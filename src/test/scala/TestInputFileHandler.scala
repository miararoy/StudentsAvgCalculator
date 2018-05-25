import java.io.FileNotFoundException

class TestInputFileHandler extends UnitTestBaseClass {
  describe("When InputFileHandler is initialized"){
    it("should read file if file exists"){
      assert(
        (new InputFileHandler("/Users/roymiara/students/src/main/resources/test_file.csv"))
          .getLinesIterator().size == 9
      )
    }
    it("should throw FileNotFoundException o.w."){
      assertThrows[FileNotFoundException](
        new InputFileHandler("/tmp/wooooohoooo.csv")
      )
    }
  }

  describe("str to row"){
    it("should parse | seperated value string to (Int, String, String, Int)"){
      assert(
        (new InputFileHandler("/Users/roymiara/students/src/main/resources/test_file.csv"))
          .strToRow("1|a|b|2")
          .equals(StudentRow(1,"a","b",2))
      )
    }
  }


}
