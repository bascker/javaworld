package com.bascker.springframework.service;

import com.bascker.springframework.model.User;

/**
 * UserService
 *
 * @author bascker
 */

public interface UserService {

    User get(final String id);

}
