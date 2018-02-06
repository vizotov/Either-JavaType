package dev.fejnartal.type.either;

import dev.fejnartal.type.either.Either.L;
import dev.fejnartal.type.either.Either.R;

public final class QuotePrinter implements Either.Alternative<String,Exception> {
	private Class<?> resultingClass;

	public void when(String quote, L... _) {
		resultingClass = String.class;
	}

	public void when(Exception t, R... _) {
		resultingClass = Exception.class;
	}

	public Class<?> getResultingClass() {
		return resultingClass;
	}
}