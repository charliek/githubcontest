package charliek.githubcontest

import scala.collection.mutable.HashMap
import scala.io.Source
import java.io.File
import java.io.FileWriter

object Main {

  def main(args: Array[String]) :Unit = {
    val watch = new File("data", "data.txt")
    val guess = new File("data", "test.txt")
    val out = new File("results.txt")
    var pop = new ZeroDict[String]()
    Source.fromFile(watch).getLines.foreach(line => {
      val cols = line.trim.split(":")
      val (user, watched) = ( cols(0), cols(1) )
      pop(watched) = pop(watched) + 1

    })
    val top10 = pop.toList.sort{(a,b) => a._2 > b._2}.slice(0, 10).map(_._1)
    pop = null
    val w = new FileWriter(out)
    Source.fromFile(guess).getLines.foreach(line => {
      w.write(line.trim + ":" + top10.mkString(",") + "\n")
    })
  }
}

class ZeroDict[K]() extends HashMap[K, Int] {
    override def default(key: K) : Int = return 0
}