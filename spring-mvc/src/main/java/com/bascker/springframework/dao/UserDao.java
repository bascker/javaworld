package com.bascker.springframework.dao;

import com.bascker.springframework.model.User;

/**
 * User Dao
 *
 * @author bascker
 */
public interface UserDao {

    User query(final String id);

}