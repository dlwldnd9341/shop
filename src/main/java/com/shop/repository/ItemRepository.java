package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    //ItemNm에서 조건 값 찾기
    List<Item> findByItemNm(String itemNm);

    //ItemNm과 ItemDetail에서 조건1 or 조건2 값 찾기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //조건 이하의 값 찾기
    List<Item> findByPriceLessThan(Integer price);

    //조건 이하의 값 찾기 - 내림차순
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //JPQL쿼리 직접 입력 - :itemDetail 대입받을 변수
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //위 쿼리와 같은 내용
    @Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
    List<Item> findByItemDetail2(String itemDetail);

    //nativeQuery = true는 기존의 쿼리를 사용 - Hibernate는 컬럼명이 바뀌기때문이다
    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
