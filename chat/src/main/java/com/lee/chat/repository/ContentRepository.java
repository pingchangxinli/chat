package com.lee.chat.repository;

import org.springframework.data.repository.CrudRepository;

import com.lee.chat.domain.Content;

public interface ContentRepository extends CrudRepository<Content, Long>{

}
