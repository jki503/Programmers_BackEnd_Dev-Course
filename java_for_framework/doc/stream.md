# Stream

- 데이터의 연속
- Collctions.stream()을 제공
- filter. map forEach같은 고차 함수(함수를 인자로 받는 함수) 제공

## 스트림 생성

```java

// list.stream
        Stream<Integer> stream = Arrays.asList(1,2,3).stream();

        // primitive int -> IntStream
        IntStream stream2 = Arrays.stream(new int[]{1,2,3})
                .map(Integer::valueOf);

        // boxed 사용해서 primitive wrapper로 감싼 후 스트림
        Stream<Integer> stream3 = Arrays.stream(new int[]{1,2,3})
                .boxed()
                .map(Integer::valueOf);

        // boxed 후 collection으로
        List<Integer> list = Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList());

        // toArray 안해주면 Object[] 타입으로 반환
        Integer[] integers = Arrays.stream(new int[]{1, 2, 3}).boxed().toArray(Integer[]::new);

        // 계속 1을 발생시킴
        Stream.generate(() -> 1)
                .forEach(System.out::println);


        // 10개만 만들고 싶다.
        Stream.generate(() -> 1)
                .limit(10)
                .forEach(System.out::println);


        // seed 값을 가지고 다음 값을 어떻게 전달할지
        Stream.iterate(0,(i) -> i+2)
                .limit(10)
                .forEach(System.out::println);


```

|                   stream 제공 함수                    |   return type   |                                                 설명                                                  |
| :---------------------------------------------------: | :-------------: | :---------------------------------------------------------------------------------------------------: |
|             filter(Predicate<? super T>)              |   Stream\<T>    |                               내부 원소의 조건에 맞추서 스트림으로 반환                               |
|     map(Function<? super T, ? extends R> mapper)      | \<R> Stream\<R> |                                내부 원소의 타입을 변환하여 스트림 반환                                |
|       mapToInt(ToIntFunction<? super T> mapper)       |    IntStream    |                                 내부 원소 타입 맞춰서 IntStream 변환                                  |
| flatMapToInt(Function<? super T, ? extends IntStream> |    IntStream    |                                                                                                       |
|                      distinct()                       |   Stream\<T>    |                                            원소 중복 제거                                             |
|                       sorted()                        |   Stream\<T>    | stream 내부 원소 정렬 할 수 있고 Compartor나 Collection을 이용하거나 람다로도 정렬 기준 정할 수 있다! |
|                  limit(long maxSize)                  |   Stream<\T>    |                                        maxSize만큼 스트림 제한                                        |
|                       toArray()                       |    Object[]     |                               type을 지정해주지 않으면 Object[]로 반환                                |
