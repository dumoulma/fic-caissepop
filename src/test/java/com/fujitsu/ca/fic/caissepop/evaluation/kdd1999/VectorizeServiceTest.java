package com.fujitsu.ca.fic.caissepop.evaluation.kdd1999;

import java.io.IOException;

import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class VectorizeServiceTest {
    private final VectorizeService vectorizer = new VectorizeService();

    @Test
    public void givenTokenSFShouldReturnZero() throws IOException {
	Tuple input = TupleFactory.getInstance().newTuple();
	input.append("http");

	Integer value = vectorizer.exec(input);
	assertThat(value, equalTo(12));
    }

    @Test
    public void givenNullTokenShouldReturnNull() throws IOException {
	Integer value = vectorizer.exec(null);
	assertThat(value, is(nullValue()));
    }

    @Test(expected = IOException.class)
    public void givenTupleWithIncorrectSizeShouldThrowException() throws IOException {
	Tuple input = TupleFactory.getInstance().newTuple();
	input.append("1");
	input.append("2");
	vectorizer.exec(input);
    }

    @Test
    public void givenTupleWithNullContentShouldReturnUnknownValue() throws IOException {
	Tuple input = TupleFactory.getInstance().newTuple();
	input.append(null);
	Integer value = vectorizer.exec(input);
	assertThat(value, equalTo(-1));
    }
}
