# Implementing Either<Left,Right> in Java
## [(View current approaches)](CURRENT-APPROACHES.md)
## [(New Approach - Discovery Process)](DiscoveryProcess.odp)
## (New Approach - Implemented in this library)
### Instantiation
- Multiples constructors + Parameter Inference + var-args
```java
public final class Either<T1,T2> {
    public enum L { Left }
    public enum R { Right }

    public Either(T1 left , L... _) {...}
    public Either(T2 right, R... _) {...}
    ...
}
////////////// In use //////////////
new Either<Integer,String>(2);
new Either<Integer,String>("Text");
```
### Deconstruction
- Matching classes
```java
public final class IntStringMatcher implements Either.Alternative<Integer,String> {
  @Override public void when(Integer value, L... _) {...}
  @Override public void when(String  value, R... _) {...}
}
////////////// In use //////////////
Either<Integer,String> e = new Either<Integer,String>("Text");
e.match(new IntStringMatcher());
```
- Matching classes - Closure
```java
public final class MatcherClosure {
  public MatcherClosure(Either<Integer,String> e) { e.match(new IntStringMatcher()); }

  private final class IntStringMatcher implements Either.Alternative<Integer,String> {
    @Override public void when(Integer value, L... _) {...}
    @Override public void when(String  value, R... _) {...}
  }
}
////////////// In use //////////////
Either<Integer,String> e = new Either<Integer,String>(0);
new MatcherClosure(e);
```