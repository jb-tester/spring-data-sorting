package com.mytests.spring.springdatasorting;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void populateDB(){
        List<Item> items = List.of(
                new Item("title one", "first description", 1, new Date(2000,1,20))
                ,new Item("title two", "second description", 2, new Date(2000,1,20))
                ,new Item("title three", "third description", 3, new Date(2000,1,20))
                ,new Item("title four", "fourth description", 0, new Date(2000,1,20))
                ,new Item("title five", "fifth description", 2, new Date(2000,1,20))
                ,new Item("title six", "sixth description", 3, new Date(2000,1,20))
                ,new Item("title seven", "seventh description", 0, new Date(2000,1,20))
                ,new Item("title eight", "eighth description", 3, new Date(2000,1,20))
                ,new Item("title nine", "ninth description", 3, new Date(2000,1,20))
                ,new Item("title ten", "tenth description", 1, new Date(2000,1,20))
                ,new Item("title eleven", "eleventh description", 1, new Date(2000,1,20))
                ,new Item("title twelve", "twelfth description", 2, new Date(2000,1,20))
        );
        itemRepository.saveAll(items);

    }

    public void offsetLimitTest(){
        System.out.println("================== offset + limit test:");
        System.out.println("-- no args:");
        itemRepository.limitOffsetNoParamsTest().iterator().forEachRemaining(System.out::println);
        System.out.println("-- with args:");
        itemRepository.limitOffsetNamedParamsTest(3,2).iterator().forEachRemaining(System.out::println);
    }

    public void offsetFetchTest(){
        System.out.println("================== offset + fetch test:");
        System.out.println("-- fetch with named param:");
        itemRepository.offsetFetchWithNamedParamTest(3).iterator().forEachRemaining(System.out::println);
        System.out.println("-- with index parameters for fetch and offset:");
        itemRepository.offsetFetchWithIndexParamsTest(3,2).iterator().forEachRemaining(System.out::println);
    }
    public void subqueriesTest(){
        System.out.println("================== subquery ordered by temp alias tests:");
        System.out.println("-- without functions:");
        itemRepository.subQuery0().iterator().forEachRemaining(System.out::println);
        System.out.println("-- with coalesce():");
        itemRepository.subQuery1().iterator().forEachRemaining(System.out::println);
        System.out.println("-- with substring():");
        itemRepository.subQuery2().iterator().forEachRemaining(System.out::println);
    }

}
