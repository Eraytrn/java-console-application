/**
 * Package containing classes related to user management and authentication.
 */
package com.turankanbur.calculator;

/**
 * Provides functionality for serialization, allowing objects to be converted into byte streams.
 */
import java.io.Serializable;

/**
 * Represents a user with a username and password. Provides functionality to
 * retrieve the username and password.
 */
public class User implements Serializable {

	/**
	 * Unique identifier for ensuring version compatibility of serialized objects.
	 */
	private static final long serialVersionUID = 1L;

	/** The username of the user. */
	private String username;

	/** The password of the user. */
	private String password;

	/**
	 * Constructs a User object with the specified username and password.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Retrieves the username of the user.
	 *
	 * @return the username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Retrieves the password of the user.
	 *
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}
}