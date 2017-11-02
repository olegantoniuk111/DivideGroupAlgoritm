package ua.vin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static PersonWeightService personeService;


    public static void main(String[] args) {

         List<Person> persons = new ArrayList<>();
        persons.add(new Person(21));
        persons.add(new Person(26));
        persons.add(new Person(22));
        persons.add(new Person(23));
        persons.add(new Person(24));
        persons.add(new Person(25));
        persons.add(new Person(26));
        persons.add(new Person(27));



        persons.stream().forEach(p-> System.out.print(p.getWeight() +"||| " ));
        System.out.println();

        System.out.println( "AllPersonsWeight "+ PersonWeightService.calculateGroupOfPersonsWeight(persons));
        System.out.println("SearchBoundary "+ PersonWeightService.getSearchBoundary(persons));

        System.out.println("Approximate optimal weight of one group should be : " + PersonWeightService.calcOptimalGroupWeight(persons) );

        System.out.println("sorting");
        System.out.println("----------------------------------------");
        List<Person> sortedPersons = personeService.sortPersons(persons);
        sortedPersons.stream().forEach(p-> System.out.print(p.getWeight() +"||| " ));
        System.out.println();
        System.out.println("-----------------------------------------");

        System.out.println("gettingMaxWeightBeforeHalfPersonsIntoOneGroup");
        System.out.println("----------------------------------------");
        List<Person> heavyWeightPersons = PersonWeightService.getMaxElementsBeforeHalf(sortedPersons);
        heavyWeightPersons.stream().forEach(p-> System.out.print(p.getWeight() +"||| " ));
        System.out.println();
        System.out.println("-----------------------------------------");

        System.out.println("gettingOtherPersonsIntoOtherGroup");
        System.out.println("----------------------------------------");

        List <Person> lighterweightPersons = PersonWeightService.excludeGivenPersons(sortedPersons, heavyWeightPersons);

        lighterweightPersons.stream().forEach(p-> System.out.print(p.getWeight() +"||| " ));
        System.out.println();
        System.out.println("-----------------------------------------");
        int difference = PersonWeightService.getDiferenceWeightBetweenTwoGroups(lighterweightPersons,heavyWeightPersons);
        System.out.println("difference before evaluation " + difference);

        int searchBoundary = PersonWeightService.getSearchBoundary(persons);
        List<List<Person>> divided = PersonWeightService.evaluateData(lighterweightPersons, heavyWeightPersons, searchBoundary);

                System.out.println("--------------------First Group--------------------");
                divided.get(0).stream().forEach(person -> System.out.println(person.getWeight()));
                System.out.println("First Group weight sum: "+PersonWeightService.calculateGroupOfPersonsWeight(divided.get(0)));

                System.out.println("--------------------Second Group--------------------");
                divided.get(1).stream().forEach(person -> System.out.println(person.getWeight()));
                System.out.println("Second Group weight sum: "+ PersonWeightService.calculateGroupOfPersonsWeight(divided.get(1)));

        int difference2 = PersonWeightService.getDiferenceWeightBetweenTwoGroups(lighterweightPersons,heavyWeightPersons);
        System.out.println("difference after evaluation " + difference2);
    }


    }

