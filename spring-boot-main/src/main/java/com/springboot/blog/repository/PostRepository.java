package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// no need to add @Repository annotation because SimpleJpaRepository internaly annotate with @Repository in it
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategoryId(Long categoryId);
    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')") //JPQL
    List<Post> searchPost(String query);

    @Query(value = "SELECT * FROM posts p WHERE " +
            "p.title LIKE CONCAT('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')",nativeQuery = true) //Native SQL
    List<Post> searchPostSQL(String query);
}
