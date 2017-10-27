def subsets(s):
  if not s:
    return set([ frozenset() ])
  first = s.__iter__().next()
  without_first = set(s)
  without_first.remove(first)
  output = set()
  for subset in subsets(without_first):
    output.add(frozenset(subset))
    with_first = set(subset)
    with_first.add(first)
    output.add(frozenset(with_first))
  return output

print subsets(set([1,2,3]))
  
  