package com.parklap;

import com.parklap.dependencyinjectors.BeanGenerator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BeanGenerator.getInstance()
                .getParkApp().run();
    }
}
