package com.opencc.LambdaTest;

import java.util.*;
import java.util.function.Consumer;

import static java.lang.System.out;

public class OptionalTest {
    public static void main(String[] args) {
        String nickName = getNickName("Duke");
        if (nickName == null) {
            nickName = "Openhome Reader";
        }
        out.println(nickName);
        Optional<String> nickOptional = getNickName2("Justin");
        out.println(nickOptional.orElse("OpenHome Reader"));
    }

    static String getNickName(String name) {
        Map<String, String> nickNames = Map.of(
                "Justin", "caterpillar",
                "Monica", "momor",
                "Irene", "hamimi"
                );
        nickNames.entrySet().forEach(out::println);
        return nickNames.get(name);
    }

    static Optional<String> getNickName2(String name) {
        Map<String, String> nickNames = Map.of(
                "Justin", "caterpillar",
                "Monica", "momor",
                "Irene", "hamimi"
        );
//        String nickName = nickNames.get(name);
//        return nickName == null ? Optional.empty() : Optional.of(nickName);
        return Optional.ofNullable(nickNames.get(name));
    }
}
