package com.caze.Caze;
// Generated Oct 25, 2020, 1:35:20 PM by Hibernate Tools 5.4.18.Final
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* Manager
*/

@Entity
@Table(name = "manager")

public class Manager implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer employeeid;
	private String first;
	private String last;
	private String email;
	private String password;
	private Date hiredate;
	

	public Manager() {
	}

	public Manager(String first, String last, String password, Date hiredate) {
		this.first = first;
		this.last = last;
		this.password = password;
		this.hiredate = hiredate;
	}

	public Manager(String first, String last, String email, String password, Date hiredate) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
		this.hiredate = hiredate;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeeid", unique = true, nullable = false)
	public Integer getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	@Column(name = "first", nullable = false)
	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	@Column(name = "last", nullable = false)
	public String getLast() {
		return this.last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Column(name = "email", nullable = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "hiredate", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

}
































































