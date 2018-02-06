package dev.fejnartal.type.either;

import dev.fejnartal.type.either.Either.L;
import dev.fejnartal.type.either.Either.R;

public final class EnclosedQuotePrinter {
	private Class<?> resultingClass;

	public EnclosedQuotePrinter(Either<String, Exception> someQuote) {
		someQuote.match(new HiddenQuotePrinter());
	}

	private final class HiddenQuotePrinter implements Either.Alternative<String, Exception> {
		public void when(String quote, L... _) {
			resultingClass = String.class;
		}

		public void when(Exception t, R... _) {
			resultingClass = Exception.class;
		}
	}

	public Class<?> getResultingClass() {
		return resultingClass;
	}
}