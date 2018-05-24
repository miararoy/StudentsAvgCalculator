import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSpec}

abstract class UnitTestBaseClass extends FunSpec
  with BeforeAndAfterEach
  with BeforeAndAfterAll