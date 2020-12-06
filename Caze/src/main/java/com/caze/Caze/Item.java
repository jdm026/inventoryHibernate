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
 * Item
 */
@Entity
@Table(name = "item", catalog = "cazedb")
public class Item implements java.io.Serializable {

	private Integer inum;
	private Plano plano;
	private String part;
	private String alt;
	private Integer seq;
	private Integer qoh;
	private BigDecimal retail;
	private String idesc;
	private Integer adj;
	private String adjdesc;
	private Date adjdate;
	private Date lasts;
	

	public Item() {
	}

	public Item(String part) {
		this.part = part;
	}

	public Item(Plano plano, String part, String alt, Integer seq, Integer qoh, BigDecimal retail, String idesc,
			Integer adj, String adjdesc, Date adjdate, Date lasts) {
		this.plano = plano;
		this.part = part;
		this.alt = alt;
		this.seq = seq;
		this.qoh = qoh;
		this.retail = retail;
		this.idesc = idesc;
		this.adj = adj;
		this.adjdesc = adjdesc;
		this.adjdate = adjdate;
		this.lasts = lasts;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inum", unique = true, nullable = false)
	public Integer getInum() {
		return this.inum;
	}

	public void setInum(Integer inum) {
		this.inum = inum;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "plano")
	public Plano getPlano() {
		return this.plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Column(name = "part", nullable = false)
	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	@Column(name = "alt", nullable = true)
	public String getAlt() {
		return this.alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	@Column(name = "seq", nullable = true)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Column(name = "qoh", nullable = true)
	public Integer getQoh() {
		return this.qoh;
	}

	public void setQoh(Integer qoh) {
		this.qoh = qoh;
	}

	@Column(name = "retail", nullable = true)
	public BigDecimal getRetail() {
		return this.retail;
	}

	public void setRetail(BigDecimal retail) {
		this.retail = retail;
	}

	@Column(name = "idesc", nullable = true)
	public String getIdesc() {
		return this.idesc;
	}

	public void setIdesc(String idesc) {
		this.idesc = idesc;
	}

	@Column(name = "adj", nullable = true)
	public Integer getAdj() {
		return this.adj;
	}

	public void setAdj(Integer adj) {
		this.adj = adj;
	}

	@Column(name = "adjdesc", nullable = true)
	public String getAdjdesc() {
		return this.adjdesc;
	}

	public void setAdjdesc(String adjdesc) {
		this.adjdesc = adjdesc;
	}

	@Column(name = "adjdate", nullable = true)
	public Date getAdjdate() {
		return this.adjdate;
	}

	public void setAdjdate(Date adjdate) {
		this.adjdate = adjdate;
	}

	@Column(name = "lasts", nullable = true)
	@Temporal(TemporalType.DATE)
	public Date getLasts() {
		return this.lasts;
	}

	public void setLasts(Date lasts) {
		this.lasts = lasts;
	}
	
}

























