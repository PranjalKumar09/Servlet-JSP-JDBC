# Hibernate Caching

Hibernate caching improves application performance by reducing the number of database queries, storing frequently accessed data in memory (cache). This is especially useful when the same data is fetched multiple times during the application's runtime.

## Types of Caching

1. **First Level Cache** (Session Cache)
   - The **Session** object holds the first-level cache.
   - Enabled by default in Hibernate.
   - The cache is local to the session, meaning the cached data is only available within the current session.
   - Each session object has its own cache, and an application can have multiple session objects.

``` java
 public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
       
        Student st = session.get(Student.class, 3);
        System.out.println(st);
        Student st2 = session.get(Student.class,4);
        System.out.println(st2);

        
        session.close();
    }
```
Output
```
Hibernate: select s1_0.id,s1_0.Salary,s1_0.address,s1_0.name from Student s1_0 where s1_0.id=?
Student [id=3, name=Charlie Brown, address=789 Oak Dr, Salary=29000]
Hibernate: select s1_0.id,s1_0.Salary,s1_0.address,s1_0.name from Student s1_0 where s1_0.id=?
Student [id=4, name=Dana White, address=135 Pine Ln, Salary=31000] 
```


2. **Second Level Cache** (SessionFactory Cache)
   - The **SessionFactory** object holds the second-level cache.
   - This cache is shared across all sessions in an application and must be explicitly enabled.
   - Second-level cache allows data to be reused across sessions, improving performance for the entire application.
   - Common providers for second-level cache:
     - EHCache (Easy Hibernate Cache)
     - Swarm Cache
     - OSCache
     - JBoss Cache

    
    To use it
``` xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
    <version>5.6.15.Final</version>
</dependency>
```

3. **Query Level Cache**
   - Hibernate provides query-level caching to store the results of specific queries.
   - Integrates closely with the second-level cache, allowing query results to be cached and reused without hitting the database.
   
## Caching Workflow 
Client <-> ORM <-> DB
### Without Caching:
Client <-> ORM <-> DB
----------------|-------------------
-------------Cache---------------













