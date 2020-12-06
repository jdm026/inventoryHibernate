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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Order
 */
@Entity
@Table(name = "order", catalog = "cazedb")
public class Order implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderid;
	private Item item;
	private String part;
	private int itemcount;
	private BigDecimal amount;
	private int store;
	private Date date;
	
	
	public Order() {
	}

	public Order(Item item, String part, int itemcount, BigDecimal amount, int store, Date date) {
		this.item = item;
		this.part = part;
		this.itemcount = itemcount;
		this.amount = amount;
		this.store = store;
		this.date = date;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid", unique = true, nullable = false)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "itemid")
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

	@Column(name = "itemcount", nullable = false)
	public int getItemcount() {
		return this.itemcount;
	}

	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}

	@Column(name = "amount", nullable = false)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "store", nullable = false)
	public int getStore() {
		return this.store;
	}

	public void setStore(int store) {
		this.store = store;
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

