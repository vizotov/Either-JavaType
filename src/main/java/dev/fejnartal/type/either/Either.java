package dev.fejnartal.type.either;

public final class Either<T1,T2> {
	public enum L { Left }
	public enum R { Right }
	private enum LR { Left, Right }

	public static interface Alternative<T1, T2> {
		void when(T1 t, L... _);
		void when(T2 t, R... _);
	}

	private final T1 left;
	private final T2 right;
	private final LR discriminator;

	public Either(T1 left, L... _) {
		this.left = left;
		this.right = null;
		this.discriminator = LR.Left;
	}

	public Either(T2 right, R... _) {
		this.left = null;
		this.right = right;
		this.discriminator = LR.Right;
	}

	public void match(Alternative<T1, T2> matcher) {
		switch(discriminator) {
		case Left: matcher.when(left); break;
		case Right: matcher.when(right); break;
		}
	}
}