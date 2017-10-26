# Prompt

Write a function that returns all all subsets of a given set.

# Questions & Discussion

- What are these sets of? Doesn't matter.
- You'll want to take a `Set<T>` and return a `Set<Set<T>>`. The eventual
  signature is:

```java
  Set<Set<T>> subsets(Set<T> set)
```

- Is the empty set valid input? 
  - Yes.

- Is the empty set valid output?
  - Up to you, but generally I'd say yes, the output always includes
    the empty set.

# Examples

```java
  Subsets.of(Set.of(1, 2, 3))
    // ()
    // (1), (2), (3),
    // (1, 2), (2, 3), (1, 3)
    // (1, 2, 3)
  Subsets.of(Set.of())
    // ()
  Subsets.of(Set.of("hello"))
    // ()
    // ("hello")
```

As a bonus, return a value that tells the caller what bracket was
mismatched.

# Solutions

The recursive approach here is the best. It's safe to do---any approach to
finding all subsets is going to be O(2^n), so we can't possibly have a very
large set as input anyway.

Solution [here](src/barnard/Subsets.java)