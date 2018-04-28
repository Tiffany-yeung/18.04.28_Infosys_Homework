package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

		//attributes
		@Id
		@GeneratedValue
		private Long id;
		
		@Column(name = "First name")
		private String firstName;
		
		@Column(name = "Last name")
		private String lastName;
		
		@Column(name = "Age")
		private Integer age;
		
		@Column(name = "Email")
		private String email;
		
		@Column(name = "Phone number")
		private Integer phoneNumber;
		
		//constructors
		public Account() {
		}
		
		public Account(String firstName, String lastName, Integer age, String email, Integer phoneNumber) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
			this.email = email;
			this.phoneNumber = phoneNumber;
		}

		//getters and setters
		public Long getId() {
			return id;
		}

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

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
}
