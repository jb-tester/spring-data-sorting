package com.mytests.spring.springdatasorting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// https://youtrack.jetbrains.com/issue/IDEA-345421
public interface ItemRepository extends CrudRepository<Item,Integer> {


    @Query("""
            select item.title from Item item
            order by item.priority, item.created desc nulls last
            limit 3 offset 2
            """)
    List<String> limitOffsetNoParamsTest();

//    https://youtrack.jetbrains.com/issue/IDEA-346362

    @Query("""
            select item.title from Item item
            order by item.priority, item.created desc nulls last
            limit :arg1 offset :arg2
            """)
    List<String> limitOffsetNamedParamsTest(@Param("arg1") int arg1, @Param("arg2") int arg2);

    @Query("""
            select item.title from Item item
            where (item.priority > 0)
            order by item.priority
            offset 1 row fetch next :arg1 rows only
            """)
    List<String> offsetFetchWithNamedParamTest(@Param("arg1") int arg1);

    @Query("""
            select item.title from Item item
            where (item.priority > 0)
            order by item.created
            offset ?1 row fetch next ?2 rows only
            """)
    List<String> offsetFetchWithIndexParamsTest(int arg1, int arg2);

// https://youtrack.jetbrains.com/issue/IDEA-335026

    @Query("select concat( rez.rez_title, ': ', rez.rez_descr) from (select entity.created as rez_date, entity.title as rez_title, entity.description as rez_descr from Item entity) rez order by rez.rez_date, rez.rez_title")
    List<String> subQuery0();

    @Query("select rez.rez_title as t from (select coalesce(entity.title,'title') as rez_title from Item entity) rez order by rez.rez_title")
    List<String> subQuery1();

    @Query("select rez.rez_title as t from (select substring(entity.title, 6) as rez_title from Item entity) rez order by rez.rez_title")
    List<String> subQuery2();


}
