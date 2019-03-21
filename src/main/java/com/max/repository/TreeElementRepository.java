package com.max.repository;

import com.max.Entity.TreeElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface TreeElementRepository extends JpaRepository<TreeElement, Integer> {

    TreeElement getById(int id);

    @Modifying
    @Query(value = "UPDATE tree_element SET finite = false, childrencount = childrencount +1," +
            " updatedate =:upd WHERE id =:ids", nativeQuery = true)
    int updateParentTreeElement(@Param("ids") int id, @Param("upd") Calendar date);

    @Query (value = "with recursive r as ( select" +
            " id, parentid, name, childrencount, createdate, " +
            " updatedate, finite from tree_element" +
            " where id =:id" +
            " union all " +
            " select tree_element.id, tree_element.parentid, tree_element.name, " +
            "tree_element.childrencount, tree_element.createdate, " +
            "tree_element.updatedate, tree_element.finite" +
            " from tree_element JOIN" +
            " r on tree_element.parentid = r.id) select * from r;", nativeQuery = true)
    List<TreeElement> fac(@Param("id") int id);


}
