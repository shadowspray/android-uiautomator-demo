package com.example.shadowspray.uitestdemo.runners;

import com.example.shadowspray.uitestdemo.CalculatorUiTestCase;
import com.example.shadowspray.uitestdemo.SampleApiTestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Run all tests in suite classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SampleApiTestCase.class, CalculatorUiTestCase.class})
public class SampleJunit4TestRunner {
    // the class remains empty,
    // used only as a holder for the above annotations
}