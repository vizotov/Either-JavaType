# Implementing Either<Left,Right> in Java
(Current Approaches)

## Instantiation
- Static Methods
```java
public final class Either<Left,Right> {
    private Either(Left left,Right right) {...}

    public static <Left,Right> Either<Left,Right> left(Left left) {
      return new Either<Left,Right>(a,null);
    }
    public static <Left,Right> Either<Left,Right> right(Right right) {
      return new Either<Left,Right>(null,b);
    }
    ...
}
////////////// In use //////////////
Either<Integer,String>.left(0);
Either<Integer,String>.right("Right");
```
- Subclassing
```java
public abstract class Either<L,R> {
    private Either() {}

    public static final class Left<L,R> extends Either<L,R> {
        public Left(L left) {...}
        ...
    }
    public static final class Right<L,R> extends Either<L,R> {
        public Right(R right) {...}
        ...
    }
}
////////////// In use //////////////
new Either.Left<Integer,String>(0);
new Either.Right<Integer,String>("Right");
```
- Multiple constructors (Won't compile because of Type Erasure)
```java
public final class Either<Left,Right> {
    public Either(Left left) {...}
    public Either(Right right) {...}
//// Won't compile because of Type Erasure ////
}
```
- Multiple constructors (Workaround Type Erasure)
```java
public final class Either<Left,Right> {
    public Either(Left left, Void _) {...}
    public Either(Void _, Right right) {...}
}
////////////// In use //////////////
new Either<Integer,String>(0,null);
new Either<Integer,String>(null,"Right");
```

## Deconstruction
- instanceof + casting (subclassing)
```java
if (either instanceof Left) {
  ... = (Left<Integer,String>)either;
} else if (either instanceof Right) {
  ... = (Right<Integer,String>)either;
}
```
- Method isLeft
```java
if (either.isLeft()) {
  ... = either.getLeft();
}else{
  ... = either.getRight();
}
```
- Improving Isolation & Type Safety (Higher Order Functions)
```java
public interface Function<T> {
  public void apply(T x);
}

public final class Either<Left,Right> {
  private final LeftOrRight leftOrRight;
  private final Left left;
  private final Right right;

  public void perform(Function<Left> whenLeft, Function<Right> whenRight) {
    switch(leftOrRight){
    case LeftOrRight.Left:  whenLeft.apply(left); break;
    case LeftOrRight.Right: whenRight.apply(right); break;
    }
  }
}
////////////// In use //////////////
public final class CaseInteger implements Function<Integer> {
  public void apply(Integer x) {
    System.out.println("Either<Integer>");
  }
}
public final class CaseString implements Function<String> {
  public void apply(String x) {
    System.out.println("Either<String>");
  }
}
either.perform(new CaseInteger(),new CaseString());
```
