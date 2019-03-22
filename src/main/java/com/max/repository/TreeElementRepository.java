package com.max.repository;

import com.max.Entity.TreeElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeElementRepository extends JpaRepository<TreeElement, Integer> {

    TreeElement getById(int id);


    @Modifying
    @Query(value = "UPDATE tree_element SET finite = false, childrencount = childrencount + 1," +
            " updatedate = NOW() WHERE id =:ids", nativeQuery = true)
    int updateAddParentTreeElement(@Param("ids") int id);

    @Modifying
    @Query(value = "UPDATE tree_element SET finite = (CASE WHEN childrencount -1 = 0 then true ELSE false END)," +
            " childrencount = childrencount -1," +
            " updatedate = NOW() WHERE id = (SELECT parentid from tree_element where id =:ids)", nativeQuery = true)
    int updateDeleteParentTreeElementDel(@Param("ids") int id);

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

    @Modifying
    @Query(value = "delete from tree_element where id in (with recursive r as ( select" +
            " id, parentid, name, childrencount, createdate, " +
            " updatedate, finite from tree_element" +
            " where id =:id" +
            " union all " +
            " select tree_element.id, tree_element.parentid, tree_element.name, " +
            "tree_element.childrencount, tree_element.createdate, " +
            "tree_element.updatedate, tree_element.finite" +
            " from tree_element JOIN" +
            " r on tree_element.parentid = r.id) select id from r);", nativeQuery = true)
    void deleteTreeElementsById(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE tree_element SET name = :nam, parentid = :parentid," +
            " updatedate = NOW() WHERE id = :ids " +
            "AND EXISTS (SELECT id FROM tree_element WHERE id = :parentid OR :parentid = NULL)", nativeQuery = true)
    int updateTreeElementById(@Param("ids") int id, @Param("nam") String name, @Param("parentid") int i);



}
