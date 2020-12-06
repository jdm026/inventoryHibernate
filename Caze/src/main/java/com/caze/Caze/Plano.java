package com.caze.Caze;
// Generated Oct 25, 2020, 1:35:20 PM by Hibernate Tools 5.4.18.Final
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 	Plano
 */
@Entity
@Table(name = "plano", catalog = "cazedb")
public class Plano implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String planoid;
	private String planoname;

	public Plano() {
	}

	public Plano(String planoid) {
		this.planoid = planoid;
	}

	public Plano(String planoid, String planoname) {
		this.planoid = planoid;
		this.planoname = planoname;
	}

	@Id
	@Column(name = "planoid")
	public String getPlanoid() {
		return this.planoid;
	}

	public void setPlanoid(String planoid) {
		this.planoid = planoid;
	}

	@Column(name = "planoname", nullable = false)
	public String getPlanoname() {
		return this.planoname;
	}

	public void setPlanoname(String planoname) {
		this.planoname = planoname;
	}

}









































