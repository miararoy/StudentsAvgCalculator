# StudentsAvgCalculator



## how to compile, test and run 

`NOTE:` PATH_TO_FILE is optional, in that case the prog' will run with the example file from the resources

### object-oriented Flow

using sbt:

```
sbt clean compile coverage test coverageReport "run OO <PATH_TO_FILE>"
```


### functional/object db Flow

this implementation use hybrid approach of functional and OO programming

using sbt:

```
sbt clean compile coverage test coverageReport "run fun_db <PATH_TO_FILE>"
```


### functional/ no db Flow

this implementation use hybrid approach of functional and OO programming

using sbt:

```
sbt clean compile coverage test coverageReport "run fun_imm <PATH_TO_FILE>"
```
