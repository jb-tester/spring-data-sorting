package com.mytests.spring.springdatasorting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemRepository extends CrudRepository<Item,Integer> {


    @Query("""
            select item.title from Item item
            order by item.priority, item.created desc nulls last
            limit 3 offset 2
            """)
    List<String> limitOffsetNoParamsTest();

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
}
