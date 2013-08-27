package com.fujitsu.ca.fic.caissepop.evaluation.kdd1999;

import java.io.IOException;

import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import static org.hamcrest.MatcherAssert.assertThat;

public class VectorizeFlagTest {
    private final VectorizeFlag vectorizer = new VectorizeFlag();

    @Test
    public void givenTokenSFShouldReturnZero() throws IOException {
        Tuple input = TupleFactory.getInstance().newTuple();
        input.append("SF");

        Integer value = vectorizer.exec(input);
        assertThat(value, equalTo(0));
    }

    @Test
    public void givenNullTokenShouldReturnNull() throws IOException {
        Integer value = vectorizer.exec(null);
        assertThat(value, is(nullValue()));
    }

    @Test
    public void givenTupleWithIncorrectSizeShouldReturnNull() throws IOException {
        Tuple input = TupleFactory.getInstance().newTuple();
        input.append("1");
        input.append("2");
        Integer value = vectorizer.exec(input);
        assertThat(value, is(nullValue()));
    }

    @Test
    public void givenTupleWithNullContentShouldReturnNull() throws IOException {
        Tuple input = TupleFactory.getInstance().newTuple();
        input.append(null);
        Integer value = vectorizer.exec(input);
        assertThat(value, is(nullValue()));
    }
}
