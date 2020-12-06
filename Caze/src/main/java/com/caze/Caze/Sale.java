package com.caze.Caze;
// Generated Oct 25, 2020, 1:35:20 PM by Hibernate Tools 5.4.18.Final
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sale 
 */
@Entity
@Table(name = "sale", catalog = "cazedb")
public class Sale implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer saleid;
	private Employee employee;
	private Item item;
	private String part;
	private int count;
	private BigDecimal price;
	private BigDecimal saletotal;
	private Date date;
	
	
	public Sale() {
	}

	public Sale(Employee employee, Item item, String part, int count, BigDecimal price, BigDecimal saletotal,
			Date date) {
		this.employee = employee;
		this.item = item;
		this.part = part;
		this.count = count;
		this.price = price;
		this.saletotal = saletotal;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleid", unique = true, nullable = false)
	public Integer getSaleid() {
		return this.saleid;
	}

	public void setSaleid(Integer saleid) {
		this.saleid = saleid;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "part", nullable = false)
	public String getPart() {
		return this.part;
	}

	
	public void setPart(String part) {
		this.part = part;
	}

	@Column(name = "count", nullable = false)
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "price", nullable = false)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "saletotal", nullable = false)
	public BigDecimal getSaletotal() {
		return this.saletotal;
	}

	public void setSaletotal(BigDecimal saletotal) {
		this.saletotal = saletotal;
	}

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}








































