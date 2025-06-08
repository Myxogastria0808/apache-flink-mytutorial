package com.example

import org.apache.flinkx.api.*
import org.apache.flinkx.api.serializers.*
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction

object App {
  def main(args: Array[String]): Unit = {
    // flink stream exec environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // connect socket
    val socketStream = env.socketTextStream("localhost", 5000)

    // logging
    // Record sample: StockA,309.65,768.04,190.54,415.46,2025-04-25 06:42:20.905995563
    socketStream
      .map(line => {
        val parts = line.split(",")
        if (parts.length == 6) {
          (
            parts(0),
            parts(1).toDouble,
            parts(2).toDouble,
            parts(3).toDouble,
            parts(4).toDouble,
            parts(5)
          )
        } else {
          throw new IllegalArgumentException(s"Invalid input: $line")
        }
      })
      .print()

    env.execute("Start Socket Stream")
  }
}
