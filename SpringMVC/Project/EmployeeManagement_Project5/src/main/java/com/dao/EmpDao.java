package com.dao;

import com.entity.Emp;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpDao {
    @Autowired
    private SessionFactory sessionFactory;

    public int insertEmp(Emp emp) {
        int userId = 0;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(emp);
            userId = emp.getId();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
    public List<Emp> getAllEmp(){
        List<Emp> empList = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Emp> criteriaQuery = criteriaBuilder.createQuery(Emp.class);

            // Define the root of the query
            Root<Emp> root = criteriaQuery.from(Emp.class);
            criteriaQuery.select(root);

            // Execute the query
            empList = session.createQuery(criteriaQuery).getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }
    public Emp getEmpById(int id) {
        Emp emp = null;
        try (Session session = sessionFactory.openSession()) {
            emp =  session.get(Emp.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
    public int updateEmp(Emp emp) {
        System.out.println("Function called -> updateEmp");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(emp);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    public int deleteEmp(Emp emp) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(emp);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}

