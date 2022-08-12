package com.danssion.java.java8.stream;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class SteamUsage {

    public static void main(String[] args) {
        SteamUsage steamUsage = new SteamUsage();
        steamUsage.useApi();
//        steamUsage.useReduce();
//        steamUsage.useCollect();
//        steamUsage.sort();
    }

    private static List<Person> list = new ArrayList<>();

    static {
        list.add(new Person("Jam", 21));
        list.add(new Person("James",1000001L, 19, true));
        list.add(new Person("Lesha",1000002L,  21, true));
        list.add(new Person("Minu Very ", 21, true));
        list.add(new Person("Tom Sweet", 36));
        list.add(new Person("Jack Very",1000004L,  64, 1));
        list.add(new Person("Lily Dubble",1000003L,  11, 1));
        list.add(new Person("Candy Sweet",1000005L,  17, 2));

    }

    ;

    private void useApi() {
        System.out.println("use filer isStudent = true: ");
        List<Person> lp1 = list.stream().filter(Person::isStudent).collect(Collectors.toList());
        lp1.forEach(x -> System.out.println(x));

        System.out.println("use cu : ");
        List<Person> lp2 = list.stream().limit(3).collect(Collectors.toList());
        lp2.forEach(l -> System.out.println(l));

        System.out.println("skip 3 : ");
        List<Person> lp3 = list.stream().skip(3).collect(Collectors.toList());
        lp3.forEach(l -> System.out.println(l));

        System.out.println("use map : ");
        lp3.stream().map(Person::getName).collect(Collectors.toList()).forEach(l -> System.out.println(l));

        System.out.println("use map split , map  : ");
        list.stream().map(Person::getName).forEach(n -> System.out.println(n));
        List<Stream<String>> lp4 = list.stream().map(Person::getName).map(line -> line.split(" ")).
                map(Arrays::stream).collect(Collectors.toList());
        lp4.get(1).forEach(l -> System.out.println(l));

        System.out.println("use map split ,flat map  : ");
        List<String> lp5 = list.stream().map(Person::getName).map(line -> line.split(" ")).
                flatMap(Arrays::stream).collect(Collectors.toList());
        lp5.forEach(l -> System.out.println(l));

        System.out.println("use map split ,flat map ,distinct  : ");
        list.stream().map(Person::getName).map(line -> line.split(" ")).
                flatMap(Arrays::stream).distinct().forEach(l -> System.out.println(l));

        System.out.println("output map ");
        Map<Long,String> map = list.stream().collect(toMap(Person::getId,Person::getName,(v1,v2) -> v1));
        System.out.println(map);

        // Map<Integer,User> userMap4 = userList.stream().collect(Collectors.toMap(User::getId, Function.identity(),(key1,key2)->key2));
        Map<Long,Person> map1 = list.stream().collect(Collectors.toMap(item -> item.getId(),item -> item,(v1,v2) -> v1));
        System.out.println(map1);

    }

    public void sort() {
        //自然序列
        List<Person> personList1 = list.stream().sorted().collect(Collectors.toList());
        //逆序
        List<Person> personList2 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        //根据年龄自然顺序
        List<Person> personList3 = list.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        //根据年龄逆序
        List<Person> personList4 = list.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());

        personList4.forEach(p -> System.out.println("sex is " + p.getSex() + " ;name is " + p.getName() + ";age is " + p.getAge()));
    }

    public void useReduce() {
        int age1 = list.stream().map(Person::getAge).reduce(0, (p1, p2) -> p1 + p2);
        System.out.println("reduce age :" + age1);
        int age = list.stream().map(Person::getAge).reduce(0, Integer::sum);
        System.out.println("reduce Integer::sum age:" + age);
        list.stream().mapToInt(Person::getAge).sum();


        OptionalLong minage1 = list.stream().mapToLong(Person::getAge).min();
        OptionalInt maxage = list.stream().mapToInt(Person::getAge).max();

        System.out.println("max age:" + maxage.getAsInt() + "  min age:" + minage1.getAsLong());


        long count1 = list.stream().collect(counting());
        long count2 = list.stream().count();
        System.out.println("Collectors.counting() : " + count1 + " stream.count() :" + count2);

        OptionalDouble avg1 = list.stream().mapToInt(Person::getAge).average();

        System.out.println("mapToInt average:" + avg1.getAsDouble());

    }

    public void useCollect() {
        Integer sumAge = list.stream().
                collect(Collectors.reducing(0, Person::getAge, (i, j) -> i + j));
        System.out.println("collect sumAge : " + sumAge);

        Optional<Person> oldp = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
        System.out.println("max age person:" + oldp.get());

        double avg2 = list.stream().collect(Collectors.averagingInt(Person::getAge));

        System.out.println(" colloct avg:" + avg2);

        long count1 = list.stream().collect(counting());
        long count2 = list.stream().count();
        System.out.println("Collectors.counting() : " + count1 + " stream.count() :" + count2);

        IntSummaryStatistics all = list.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("all statics:" + all);

//        统计每一组的人数
        Map<String, Long> result = list.stream().collect(
                Collectors.groupingBy((person) -> {
                            if (person.getAge() > 60)
                                return "老年人";
                            else if (person.getAge() > 40)
                                return "中年人";
                            else
                                return "青年人";
                        },
                        counting()));
        System.out.println(result);

//        将所有人按性别划分，并计算每组最大的年龄。
        Object obj = list.stream().collect(
                Collectors.groupingBy(Person::getSex,
                        Collectors.collectingAndThen(
                                maxBy(Comparator.comparing(Person::getAge)),
                                Optional::get
                        )
                ));
        System.out.println(obj);

    }


}
