package com.lee.chat.repository;

import org.springframework.data.repository.CrudRepository;

import com.lee.chat.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
