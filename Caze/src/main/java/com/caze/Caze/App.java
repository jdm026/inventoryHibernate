package com.caze.Caze;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main Application File
 *
 */
public class App 
{
    
	protected SessionFactory sessionFactory;
	
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main( String[] args ) throws ParseException
    {
        
		ApplicationContext context = new ClassPathXmlApplicationContext();
		
		App manager = new App();
		
	//	SearchView.searchView();
	//	SearchView.searchView(manager);
		
	//	manager.setup();
	//	manager.searchPart("11008");
	//	manager.searchItem(36);
	//	manager.createOrder();
	//	manager.create();
	
	//	ManagerView.managerView();
			

//	manager.searchPart("PM9246");
//   	manager.create();
    	manager.exit();
    	
    	
   	
    }	
	
	
	/* 
	 * Load Hibernate Session Factory
	 */
	protected void setup() 
	{
		
		
		
		Configuration con = new Configuration().configure();//.addAnnotatedClass(Item.class).addAnnotatedClass(Plano.class).addAnnotatedClass(Order.class);
    	final ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		sessionFactory = con.buildSessionFactory(reg);
			
	}
	
	/*
	 * 		Search by Item Number
	 */
	protected Item searchItem(int search)
	{
		Item item = new Item();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
    	
		item  = (Item)session.get(Item.class, search);
		
    	tx.commit();
    	
    	session.close();
    	
    	return item;
    	
    	
	}
	
	/*
	 * 		Search Manager by ID
	 */
	protected Manager searchManager(int search)
	{
		Manager manager = new Manager();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		manager = (Manager)session.get(Manager.class, search);
		
		tx.commit();
		session.close();
		
		return manager;
		
	}
	
	/*
	 * 		Create A Test Part & Test Plano
	 */
	protected void create() throws ParseException
	{
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Plano plano = new Plano();
		Item item = new Item();
		
		plano.setPlanoid("TSTPLA");
		plano.setPlanoname("Test Plan");
		item.setPlano(plano);
		
		item.setPart("TEST PART");
		item.setAlt("TEST ALT");
		item.setSeq(100000);
		item.setQoh(0);
		item.setRetail(new BigDecimal(9.99));
		
		item.setIdesc("TEST DESC");
		item.setAdj(0);
		item.setAdjdesc("TEST ADJDESC");
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormatter.parse("2020-8-02");
		
//		item.setAdjdate(date);
		
		session.save(item);
		session.save(plano);
		
		tx.commit();


		System.out.println(item.getInum() + ": created");
		
		session.close();
		
	}
	
	/*
	 * 		Create An Item With Specified Values
	 */
	protected void createItem(String part, String alt, double retail, String desc, String planoid, String planoname) throws ParseException
	{
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Plano plano = new Plano();
		Item item = new Item();
		
		plano.setPlanoid(planoid);
		plano.setPlanoname(planoname);
		item.setPlano(plano);
		
		item.setPart(part);
		item.setAlt(alt);
		item.setSeq(0);
		item.setQoh(1);
		item.setRetail(new BigDecimal(retail));
		
		item.setIdesc(desc);
		item.setAdj(0);
		
		session.save(item);
		session.save(plano);
		
		tx.commit();


		System.out.println(item.getInum() + ": created");
		
		session.close();
		
	}
	
	/*
	 * 		Close sessionFactory
	 */
	protected void exit()
	{
		sessionFactory.close();
	}
	
	protected void subtractQoh(Manager mID, Item item, int i)
	{
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		item.setQoh(item.getQoh() - i);
		item.setAdj(-i);
		item.setAdjdate(new Date());
		item.setAdjdesc((-i) + " sub from inv");
		
		Adjustment adjust = new Adjustment();
		adjust.setDate(new Date());
		adjust.setItem(item);
		adjust.setAdesc(-i + " sub inv ");
		adjust.setAdj(-i);
		adjust.setPart(item.getPart());
		adjust.setManager(mID);
		
		session.update(item);
		session.save(adjust);
		tx.commit();
		
		session.close();
		
	}
	
	protected void addQoh(Manager mID, Item item, int i)
	{
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		item.setQoh(item.getQoh() + i);
		item.setAdj(i);
		item.setAdjdate(new Date());
		item.setAdjdesc(i + " add into inv");
		
		Adjustment adjust = new Adjustment();
		adjust.setDate(new Date());
		adjust.setItem(item);
		adjust.setAdesc(i + " add into inv ");
		adjust.setAdj(i);
		adjust.setPart(item.getPart());
		adjust.setManager(mID);
		
		
		session.update(item);
		session.save(adjust);
		tx.commit();
		
		session.close();
		
	}
	
	protected void transfer(List<Item> lstItems, List<Integer> lstAdj, Manager mgmtIn, int store) throws ParseException {
		
		Session session;
		Transaction tx;
		
		Order order = new Order();
		
		LocalDate ldate = LocalDate.now();
		
		Date date = dateFormatter.parse(ldate.getYear() + "-" + ldate.getMonthValue() + "-" + ldate.getDayOfMonth());
		System.out.println(ldate.getYear() + "-" + ldate.getMonthValue() + "-" + ldate.getDayOfMonth());
		
		
	//	 date = LocalDate.now();
		
		
		
		for (int i = 0; i < lstItems.size(); i++)
		{
			
			session = sessionFactory.openSession();
			
			tx = session.beginTransaction();
			order.setItem(lstItems.get(i));
			order.setDate(date);
			order.setAmount(lstItems.get(i).getRetail());
			order.setItemcount(lstAdj.get(i));
			order.setPart(lstItems.get(i).getPart());
			order.setStore(store);
			
			
			lstItems.get(i).setAdj(-lstAdj.get(i));
			lstItems.get(i).setAdjdate(date);
			lstItems.get(i).setQoh(lstItems.get(i).getQoh() - lstAdj.get(i));
			lstItems.get(i).setAdjdesc(-lstAdj.get(i) + " from inv by " + mgmtIn.getFirst() + mgmtIn.getLast());
			
			
			session.save(order);
			session.save(lstItems.get(i));
			
			tx.commit();
			session.close();
			
			
		}
		
	}

	protected void createOrder() throws ParseException
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		int randomMonth = ThreadLocalRandom.current().nextInt(0, 12);
		int randomDay;
		
		if (randomMonth == 1)
			randomDay = ThreadLocalRandom.current().nextInt(1, 29);
		else
			randomDay = ThreadLocalRandom.current().nextInt(1, 31);
		
		
		Date date = dateFormatter.parse("2020-" +randomMonth + "-" + randomDay);
		
		
		Order order = new Order();
		
		Manager manager = (Manager)session.get(Manager.class, 1);
		
		Item item = searchItem(1);
		
		order.setItem(item);
		order.setItemcount(2);
		order.setAmount(item.getRetail().multiply(new BigDecimal(order.getItemcount())));
		order.setPart(item.getPart());
		order.setStore(2025);
		order.setDate(date);
		
		int itmAdj = order.getItemcount();
		item.setQoh(item.getQoh() - itmAdj);
		item.setAdj(itmAdj);
		item.setAdjdesc(order.getItemcount() + " transferred to " + order.getStore());
	//	item.setAdjdate(date);
		
		Adjustment adjustment = new Adjustment();
		adjustment.setOrder(order);
		adjustment.setAdesc("Transferred "+ itmAdj + " to " + order.getStore());
		adjustment.setDate(order.getDate());
		
		session.save(order);
		session.save(adjustment);
		
		
		tx.commit();
		
		session.close();
	
	}
	
	protected Item search(String search)
	{
		Item item;
		
		if (search.isEmpty())
			return null;
    	
    	// Check to search for item number,
    	// part number, or alternate number
		if (isDigit(search))
		{
			item = searchItem(Integer.parseInt(search));
			if (item == null)
			{
				item = searchPart(search);
				
				if (item == null)
				{
					item = searchAlt(search);
				}	
			}
		}
		else
		{
			item = searchPart(search.toUpperCase());
			
			if (item == null)
				item = searchAlt(search.toUpperCase());
				
		}
		
		return item;
	}
	
	// Check to see if a String is an integer
    private boolean isDigit(String check)
	{
		for (int i = 0; i < check.length(); i++)
			if(!Character.isDigit(check.charAt(i)))
				return false;	
		
		return true;
	}

	protected Item searchPart(String part)
	{
		Session session = sessionFactory.openSession();
		
		String hql = "from Item i where i.part = :keyword";
		
		Query query = session.createQuery(hql).setParameter("keyword", part);

		Item item = (Item)query.uniqueResult();
		
		session.close();
		
		return item;
		
		
		
	}
	
	protected Manager searchManager(String search)
	{
		
		System.out.println("-" + search + "-");
		Session session = sessionFactory.openSession();
		
		String hql = "from Manager m where m.password = :keyword";
	//	String hql = "from Manager m where m.password = 11111";
		Query query = session.createQuery(hql).setParameter("keyword", search);
	//	Query query = session.createQuery(hql);//.setParameter("key", search);
		Manager manager = (Manager)query.uniqueResult();
		
		session.close();
		
		return manager;
		
	}
	
	protected Item searchAlt(String alt)
	{
		Session session = sessionFactory.openSession();
		
		String hql = "from Item i where i.alt = :keyword";
		
		Query query = session.createQuery(hql).setParameter("keyword", alt);

		Item item = (Item)query.uniqueResult();
		
		session.close();
		
		return item;
		
	}
	
	protected void getAdjustments()
	{
		Session session = sessionFactory.openSession();
		
	//	String
		
	}
	
}






















