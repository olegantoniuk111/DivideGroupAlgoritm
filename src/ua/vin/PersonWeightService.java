package ua.vin;

import java.awt.peer.ListPeer;
import java.util.*;

public class PersonWeightService {
    public static List<Person> sortPersons(List<Person> persons){
        Collections.sort(persons);

        //        persons.sort(new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                Integer weight1 = o1.getWeight();
//                Integer weight2 = o2.getWeight();
//                return weight1.compareTo(weight2);
//
//            }
//        });
        return persons;
    }

    public static Integer calculateGroupOfPersonsWeight(List<Person>persons){
        if(!persons.isEmpty()){
            int allWeight=0;
            for(int i =0 ;i < persons.size();i++)
                allWeight = allWeight + persons.get(i).getWeight();
            return allWeight;
        }return 0;

    }

    public static Person getPersonWithMaxWeight(List<Person> persons) {
        return sortPersons(persons).get(persons.size()-1);
    }

    public static Person getPersonWithMinWeight(List<Person> persons) {
        return  sortPersons(persons).get(0);
    }

    public static Integer getDiferenceWeightBetweenTwoGroups(List<Person> p1, List<Person> p2){
        Integer i = PersonWeightService.calculateGroupOfPersonsWeight(p1)- PersonWeightService.calculateGroupOfPersonsWeight(p2);
        return Math.abs(i);
    }

    public static List<Person> getMaxElementsBeforeHalf (List<Person> persons){

        List<Person>allPersons = new ArrayList<>(persons);
        List<Person> conditionalHalfList = new ArrayList<>();
        sortPersons(allPersons);
        int conditionalHalfListWeight;
        int allPersonsWeight;

        do{
            Person person = getPersonWithMaxWeight(allPersons);
            conditionalHalfList.add(person);
            allPersons.remove(person);
            conditionalHalfListWeight = calculateGroupOfPersonsWeight(conditionalHalfList);
            allPersonsWeight = calculateGroupOfPersonsWeight(allPersons);
        }while (conditionalHalfListWeight <= allPersonsWeight);
            Person p = PersonWeightService.getPersonWithMinWeight(conditionalHalfList);
            allPersons.add(p);
            conditionalHalfList.remove(p);
        return conditionalHalfList;

    }

    public static List<List<Person>> evaluateData(List<Person> moreWithLighter, List<Person> lessWithHigher, int searchBoundary){

        List<List<Person>> divided = new ArrayList<>();
        int difference;

        do{

            Person person = getPersonWithMinWeight(moreWithLighter);
            lessWithHigher.add(person);
            moreWithLighter.remove(person);
            difference = getDiferenceWeightBetweenTwoGroups(moreWithLighter, lessWithHigher);
        }while(difference > searchBoundary || difference ==0 );


        divided.add(0, moreWithLighter);
        divided.add(1,lessWithHigher);

        return divided;
    }

    public static List<Person> excludeGivenPersons(List<Person> all, List<Person> excude){
        for(Person p : excude){
            if (all.contains(p)){
                all.remove(p);
            }
            continue;
        }
        return all;
    }

    public static boolean hasIdenticElements(List<Person> persons) {

        for (int i=0; i<persons.size()-1 ;i++){
            List <Person> persons2 = new ArrayList<>(persons);
            Person person = persons.get(i);
            persons2.remove(i);
            if (persons2.contains(person)){
                return true;
            }

        }
        return false;
    }

    public static int getSearchBoundary (List<Person> persons){
        Integer groupWeight = calcOptimalGroupWeight(persons).intValue();
        int rez = (int) (groupWeight * 0.15);
        return rez;
    }

    public static Integer calcOptimalGroupWeight(List<Person> persons) {
        return calculateGroupOfPersonsWeight(persons) / 2;
    }

    public static Boolean hasSmallestElemThenDifference(List<Person>persons, int difeerence){
    if (getPersonWithMinWeight(persons).getWeight() < difeerence){
        return true;

    } return false;
    }




}
