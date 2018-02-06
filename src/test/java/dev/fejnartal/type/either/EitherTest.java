package dev.fejnartal.type.either;

import junit.framework.Assert;

import org.junit.Test;

public final class EitherTest {
	@Test
	public void quoteIsPresentQuotePrinter() {
		final Either<String,Exception> someQuote = someQuote("Shakespeare");
		final QuotePrinter qp = new QuotePrinter();
		someQuote.match(qp);
		Assert.assertEquals(String.class, qp.getResultingClass());
	}

	@Test
	public void quoteIsPresentEnclosedQuotePrinter() {
		final Either<String,Exception> someQuote = someQuote("Shakespeare");
		final EnclosedQuotePrinter qp = new EnclosedQuotePrinter(someQuote);
		Assert.assertEquals(String.class, qp.getResultingClass());
	}

	@Test
	public void quoteIsAbsentQuotePrinter() {
		final Either<String,Exception> someQuote = someQuote("Hackespeare");
		final QuotePrinter qp = new QuotePrinter();
		someQuote.match(qp);
		Assert.assertEquals(Exception.class, qp.getResultingClass());
	}

	@Test
	public void quoteIsAbsentEnclosedQuotePrinter() {
		final Either<String,Exception> someQuote = someQuote("Hackespeare");
		final EnclosedQuotePrinter qp = new EnclosedQuotePrinter(someQuote);
		Assert.assertEquals(Exception.class, qp.getResultingClass());
	}

	private final Either<String,Exception> someQuote(String author) {
		if(author.equals("Shakespeare")) {
			return new Either<String,Exception>("There is nothing either good or bad, but thinking makes it so.");
		} else {
			return new Either<String,Exception>(new Exception("Our quotes library sucks. We are sorry :("));
		}
	}
}