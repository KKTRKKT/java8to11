package me.kktrkkt.java8to11.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class AgentMasulsa {

    public static void main(String[] args) throws IOException {
        System.out.println(new Moja().pullOut());
    }
}
