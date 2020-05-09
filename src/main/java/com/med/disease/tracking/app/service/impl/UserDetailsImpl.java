package com.med.disease.tracking.app.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.med.disease.tracking.app.dto.UserDTO;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String uid;

	private String username;

	private String mobile;
	
	private String firstName;
	
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;


	public UserDetailsImpl(String username, String mobile, String password, String firstName, String lastName,

			Collection<? extends GrantedAuthority> authorities) {
//		this.uid = uid;
		this.username = firstName;
		this.mobile = mobile;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public static UserDetailsImpl build(UserDTO user) {
		Set<String> s = new HashSet<String>();
		s.add(user.getRole());
		List<GrantedAuthority> authorities = s.stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new UserDetailsImpl(

				user.getUserName(), user.getMobile(), user.getToken(), user.getFirstName(),user.getLastName(), authorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(uid, user.uid);
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
