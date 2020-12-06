package com.caze.Caze;
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


@Entity
@Table(name = "adjustment", catalog = "cazedb")
public class Adjustment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Manager manager;
	private Order order;
	private Sale sale;
	private Item item;
	private String part;
	private Integer adj;
	private String adesc;
	private Date date;

	public Adjustment() {
	}

	public Adjustment(Manager manager, Order order, Sale sale, Item item, String part, Integer adj, String adesc,
			Date date) {
		this.manager = manager;
		this.order = order;
		this.sale = sale;
		this.item = item;
		this.part = part;
		this.adj = adj;
		this.adesc = adesc;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid", nullable = true)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderid", nullable = true)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saleid", nullable = true)
	public Sale getSale() {
		return this.sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item", nullable = true)
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "part", nullable = true)
	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	@Column(name = "adj", nullable = true)
	public Integer getAdj() {
		return this.adj;
	}

	public void setAdj(Integer adj) {
		this.adj = adj;
	}

	@Column(name = "adesc", nullable = true)
	public String getAdesc() {
		return this.adesc;
	}

	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}

	@Column(name = "date", nullable = true)
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
