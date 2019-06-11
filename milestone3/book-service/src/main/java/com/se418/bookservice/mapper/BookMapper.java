package com.se418.bookservice.mapper;

import com.se418.bookservice.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface BookMapper {
    @Select("SELECT * FROM book")
    List<Book> selectAll();

    @Select("SELECT * FROM book WHERE id = #{id}")
    Optional<Book> selectById(long id);

    @Delete("DELETE FROM book WHERE id = #{id}")
    void deleteById(long id);

    @Insert("INSERT INTO book(author, title) VALUES(#{author}, #{title})")
    void insertBook(Book book);

    @Update("UPDATE book SET title=#{title}, author=#{author} WHERE id=#{id}")
    void updateBook(Book book);
}
