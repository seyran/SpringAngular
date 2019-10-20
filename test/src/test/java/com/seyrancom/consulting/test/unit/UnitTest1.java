package com.seyrancom.consulting.test.unit;

import com.seyrancom.consulting.test.core.AbstractTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class UnitTest1 extends AbstractTestRunner {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void example(){
        assert true;
    }

    @Test
    public void testTreeSet(){
        Set<String> ts = new TreeSet<>();
        ts.add("t");
        ts.add("c");
        ts.add("a");
        logger.debug("testTreeSet={}", ts);
    }

    @Test
    public void testTreeMap(){
        Map<Integer, String> tm = new TreeMap<>();
        tm.put(3, "33");
        tm.put(2, "22");
        tm.put(1, "11");
        logger.debug("testTreeMap={}", tm);
    }

    @Test
    public void testConfig(){

       //ConfigUtils.loadProperties("/hikari.properties");
    }

    @Test
    public void testStream(){

     /*   Stream<Integer> stream = Stream.of(1, 2, null, 4);
        long one = stream.ofNullable("42").count();
        long zero = stream.ofNullable(null).count();*/
    }
}
