package com.techschool.repository;

import com.techschool.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public Optional<Author> findByLoginName(String loginName);
}
