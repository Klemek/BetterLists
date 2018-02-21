# BetterLists (by Klemek)

An extension of the java.util.List interface which include some of the C# LINQ useful functions.

List classes are extended as well. (ArrayList -> BetterArrayList)

Current version v1.0

Before BetterLists :
```Java
ArrayList<Contact> contacts = someFunction();

ArrayList<String> contactsEmails = new ArrayList<>();
for(int i = 5; i < contacts.size(); i++){
	if(c.getEmail() != null){
		contactsEmails.add(c.getEmail());
	}
}
```
With BetterLists :
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<String> contactsEmails = contacts.skip(5)
					.where(c -> c.getEmail() != null)
					.<String>select(c -> c.getEmail());
```

NOTE : Please note that, unlike C# LINQ, these functions are not optimized at low levels and will have the same impact as standard loops in your program. 

## Download

* [betterlists-1.0.jar](../../raw/master/download/betterlists-1.0.jar)
* [betterlists-1.0-sources.jar](../../raw/master/download/betterlists-1.0-sources.jar)

## All code examples
### List

| Name | Description |
| :- | :- |
| [all](#all) | Determines whether all elements of the sequence satisfy a condition. |
| [any](#any) | Determines whether any element of the sequence satisfies a condition. |
| [count](#count) | Returns a number that represents how many elements in the specified sequence satisfy a condition. |
| [exclusion](#exclusion) | Produces the set exclusion of two sequences. |
| [first / firstOrDefault](#first-firstordefault) | Returns the first element in the sequence that satisfies a specified condition. (Returns an error if no elements match the condition unless you use the firstOrDefault function) |
| [last / lastOrDefault](#last-lastordefault) | Returns the last element in the sequence that satisfies a specified condition. (Returns an error if no elements match the condition unless you use the lastOrDefault function) |
| [max](#max) | Invokes a transform function on each element of the sequence and returns the maximum nullable Double value. |
| [mean](#mean) | Computes the mean of the sequence of Double values that are obtained by invoking a transform function on each element of the input sequence. |
| [min](#min) | Invokes a transform function on each element of the sequence and returns the minimum nullable Double value. |
| [orderBy / orderByDescending](#orderby-orderbydescending) | Sorts the elements of a sequence in ascending order by using a specified comparer. (You can user orderByDescending to change the order) |
| [reverse](#reverse) | Inverts the order of the elements in the sequence. |
| [select](#select) | Projects each element of a sequence into a new form. |
| [skip / skipWhile](#skip-skipwhile) | Bypasses elements in the sequence as long as a specified condition is true and then returns the remaining elements. |
| [sum](#sum) | Computes the sum of the sequence of Double values that are obtained by invoking a transform function on each element of the input sequence. |
| [take / takeWhile](#take-takewhile) | Returns a specified number of contiguous elements from the start of the sequence. |
| [union](#union) | Produces the set union of two sequences. |
| [where](#where) | Filters a sequence of values based on a predicate. |

### all
Determines whether all elements of the sequence satisfy a condition.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

boolean allAdults = contacts.all(c -> c.getAge() >= 21);
```

### any
Determines whether any element of the sequence satisfies a condition.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

boolean someUnderage = contacts.any(c -> c.getAge() < 21);
```

### count
Returns a number that represents how many elements in the specified sequence satisfy a condition.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

int adultsCount = contacts.count(c -> c.getAge() >= 21);
```

### exclusion
Produces the set exclusion of two sequences.
```Java
BetterArrayList<Contact> frenchContacts = BetterArrayList.fromList(someFunction());
ArrayList<Contact> validContacts = someOtherFunction();

BetterList<Contact> invalidFrenchContacts = frenchContacts.exclusion(validContacts);
```

### first / firstOrDefault
Returns the first element in the sequence that satisfies a specified condition. (Returns an error if no elements match the condition unless you use the `firstOrDefault` function)
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

Contact firstManager = contacts.first(c -> c.isManager());
```

### last / lastOrDefault
Returns the last element in the sequence that satisfies a specified condition. (Returns an error if no elements match the condition unless you use the `lastOrDefault` function)
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

Contact lastRegular = contacts.last(c -> !c.isManager());
```

### max
Invokes a transform function on each element of the sequence and returns the maximum nullable Double value.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

int maxAge = (int)contacts.max(c -> (double)c.getAge());
```

### mean
Computes the mean of the sequence of Double values that are obtained by invoking a transform function on each element of the input sequence.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

int meanAge = (int)contacts.mean(c -> (double)c.getAge());
```

### min
Invokes a transform function on each element of the sequence and returns the minimum nullable Double value.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

int minAge = (int)contacts.min(c -> (double)c.getAge());
```

### orderBy / orderByDescending
Sorts the elements of a sequence in ascending order by using a specified comparer. (You can user `orderByDescending` to change the order)
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<Contact> orderedContacts = contacts.orderBy(c -> c.getName);
```

### reverse
Inverts the order of the elements in the sequence.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<Contact> reversedContacts = contacts.reverse();
```

### select
Projects each element of a sequence into a new form.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<String> contactsMails = contacts.select(c -> c.getEmail());
```

### skip / skipWhile
Bypasses elements in the sequence as long as a specified condition is true and then returns the remaining elements.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<Contact> contacts2 = contacts.skipWhile(c -> c.getEmail().startsWith("society"));
```

### sum
Computes the sum of the sequence of Double values that are obtained by invoking a transform function on each element of the input sequence.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

double salary = contacts.skip(c -> c.getSalary());
```

### take / takeWhile
Returns a specified number of contiguous elements from the start of the sequence.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<Contact> contacts2 = contacts.takeWhile(c -> c.getEmail().startsWith("society"));
```

### union
Produces the set union of two sequences.
```Java
BetterArrayList<Contact> frenchContacts = BetterArrayList.fromList(someFunction());
ArrayList<Contact> validContacts = someOtherFunction();

BetterList<Contact> validFrenchContacts = frenchContacts.union(validContacts);
```

### where
Filters a sequence of values based on a predicate.
```Java
BetterArrayList<Contact> contacts = BetterArrayList.fromList(someFunction());

BetterList<Contact> validContacts = contacts.where(c -> c.getEmail() != null);
```
