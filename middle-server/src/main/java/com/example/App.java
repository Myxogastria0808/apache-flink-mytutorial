package com.example;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class App {

    public static void main(String[] args) throws Exception {
        // ストリーム実行環境の作成
        final StreamExecutionEnvironment env
                = StreamExecutionEnvironment.getExecutionEnvironment();

        // ソケットからのデータストリームを作成
        // ここでは、localhostの5000ポートからデータを受信する
        DataStream<String> text = env.socketTextStream("localhost", 5000);

        // ストリームデータをパース
        // DataStream<String> parsed = text.map(line -> {
        //     return line;
        // });
        // parsed.print();
        DataStream<String> parsed = text.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) {
                return value;
            }
        });

        // ストリームの出力を標準出力に出す
        parsed.print();
        // 実行開始
        env.execute("Start middle-server");

    }
}
