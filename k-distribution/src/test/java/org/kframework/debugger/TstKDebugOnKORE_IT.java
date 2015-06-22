package org.kframework.debugger;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TestName;
import org.kframework.attributes.Source;
import org.kframework.kore.K;
import org.kframework.utils.KoreUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;


/**
 * Created by Manasvi on 6/19/15.
 * <p>
 * Test File for the Debugger Interface Implementation
 */
public class TstKDebugOnKORE_IT {


    @org.junit.Rule
    public TestName name = new TestName();
    private static KoreUtils utils;

    protected File testResource(String baseName) throws URISyntaxException {
        return new File(TstKDebugOnKORE_IT.class.getResource(baseName).toURI());
    }

    @BeforeClass
    public void setup() throws IOException, URISyntaxException {
        String filename = "/convertor-tests/" + name.getMethodName() + ".k";
        utils = new KoreUtils(filename);
    }

    @Test
    public void StepTest() throws IOException, URISyntaxException {
        String pgm = "int s, n; n = 10; while(0<=n) { s = s + n; n = n + -1; }";
        K parsed = utils.getParsed(pgm, Source.apply("generated by " + getClass().getSimpleName()));
        KDebug debuggerSession = new KoreKDebug(parsed, utils.getRewriter());
        K tenStepsResultDebug = debuggerSession.step(10).getCurrentK();
        K tenSetpsResultNormal = utils.stepRewrite(parsed, Optional.of(new Integer(10)));
        assertEquals("Taking Steps Failed", tenSetpsResultNormal, tenStepsResultDebug);
    }
}
