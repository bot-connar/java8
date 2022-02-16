package test;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static test.HHH.Gender.FEMALE;
import static test.HHH.Gender.MALE;

public class HHH {

    public static void main(String[] args) {
        List<Person> list= Arrays.asList(
          new Person("John",MALE),
          new Person("Maria",FEMALE),
          new Person("Aisha",MALE),
          new Person("Alex",FEMALE)
        );

        list.stream().filter(isMale()).forEach(System.out::println);
        list.stream().filter(HHH::isMale).forEach(System.out::println);

        Function<Integer,Integer> function=a->a+2;
        System.out.println(function.compose((Integer b)->b-10).compose((Integer b)->b*3).andThen(a->a*2).apply(1));

        Consumer<Person> consumer1=System.out::println;
        Person zzz = new Person("zzz", MALE);
        consumer1.andThen(printLine()).accept(zzz);
        consumer1.andThen(HHH::printLine).accept(zzz);

        BiConsumer<Person,Predicate<Person>> booleanBiConsumer=(a,f)-> {
            if (f.test(a)){
                System.out.println("a");
            }else {
                System.out.println("b");
            }
        };

        booleanBiConsumer.accept(zzz,HHH::isMale);
        booleanBiConsumer.accept(zzz,isMale());
        booleanBiConsumer.accept(zzz,a->a.name.equals("zx"));


        list.stream().map(person -> person.name).mapToInt(String::length).forEach(System.out::println);

    }



    public static Predicate<Person> isMale(){
        return (person)->person.gender.equals(MALE);
    }

    public static Boolean isMale(Person person){
        return person.gender.equals(MALE);
    }

    public static Consumer<Person> printLine(){
        return a->System.out.println(a+"1111111111111");
    }

    private static void printLine(Person person) {
        System.out.println(person+"2222222222222222");
    }
    private static void printLine2(Person person) {
        System.out.println(person+"2222222222222222");
    }


    @Data @AllArgsConstructor
    static class Person{
        private final String name;
        private final Gender gender;
    }

    enum Gender{
        FEMALE,MALE
    }

}
