
### Spring Data JPA Query Method Naming Conventions

Spring Data JPA derives SQL queries from method names, allowing the following intuitive and flexible naming patterns:

1. **Distinct**  
   - **Method**: `findDistinctByLastnameAndFirstname`
   - **SQL**: `SELECT DISTINCT ... WHERE lastname = ?1 AND firstname = ?2`

2. **AND Condition**  
   - **Method**: `findByLastnameAndFirstname`
   - **SQL**: `... WHERE lastname = ?1 AND firstname = ?2`

3. **OR Condition**  
   - **Method**: `findByLastnameOrFirstname`
   - **SQL**: `... WHERE lastname = ?1 OR firstname = ?2`

4. **Equality** (`Is`, `Equals`)  
   - **Methods**: `findByFirstname`, `findByFirstnameIs`, `findByFirstnameEquals`
   - **SQL**: `... WHERE firstname = ?1`

5. **Range - Between**  
   - **Method**: `findByStartDateBetween`
   - **SQL**: `... WHERE startDate BETWEEN ?1 AND ?2`

6. **Comparison Operators**  
   - **Less Than**: `findByAgeLessThan` → `... WHERE age < ?1`
   - **Less Than or Equal**: `findByAgeLessThanEqual` → `... WHERE age <= ?1`
   - **Greater Than**: `findByAgeGreaterThan` → `... WHERE age > ?1`
   - **Greater Than or Equal**: `findByAgeGreaterThanEqual` → `... WHERE age >= ?1`

7. **Temporal Queries**  
   - **After**: `findByStartDateAfter` → `... WHERE startDate > ?1`
   - **Before**: `findByStartDateBefore` → `... WHERE startDate < ?1`

8. **In/Not In**  
   - **In**: `findByAgeIn(Collection<Age> ages)` → `... WHERE age IN ?1`
   - **Not In**: `findByAgeNotIn(Collection<Age> ages)` → `... WHERE age NOT IN ?1`

9. **Boolean**  
   - **True**: `findByActiveTrue()`  
   - Requires a boolean field in the entity, e.g., `active`.


10. **Exists Query** `existsBy`
- **Method**: `existsByLastname(String lastname)`
- **SQL Equivalent**: `SELECT COUNT(1) > 0 WHERE lastname = ?1`
- **Purpose**: Checks if any record exists with the given `lastname`.
- **Use Case**: Useful for existence checks without retrieving the actual record data, for example, before performing operations that depend on whether a record exists.

11. **Ordering (Sorting)**

- #### `findByLastnameOrderByFirstnameAsc`
- **Method**: `findByLastnameOrderByFirstnameAsc(String lastname)`
- **SQL Equivalent**: `SELECT ... WHERE lastname = ?1 ORDER BY firstname ASC`
- **Purpose**: Sorts results by `firstname` in ascending order.
- **Use Case**: Use when sorting based on specific fields is required. Append `Desc` to sort in descending order.

- #### `findByLastnameOrderByAgeDesc`
- **Method**: `findByLastnameOrderByAgeDesc(String lastname)`
- **SQL Equivalent**: `SELECT ... WHERE lastname = ?1 ORDER BY age DESC`
- **Use Case**: Use when you need to sort results by `age` in descending order.


11. **Pattern Matching (Like, Containing, StartingWith, EndingWith)**

- #### `findByFirstnameLike`
- **Method**: `findByFirstnameLike(String pattern)`
- **SQL Equivalent**: `SELECT ... WHERE firstname LIKE ?1`
- **Purpose**: Allows partial matching with SQL wildcards (`%` and `_`).
- **Example**: `findByFirstnameLike("%John%")` finds names containing "John".
- **Use Case**: Use when you want flexible pattern-based matching on fields.

- #### `findByLastnameContaining`
- **Method**: `findByLastnameContaining(String substring)`
- **SQL Equivalent**: `SELECT ... WHERE lastname LIKE %?1%`
- **Purpose**: Searches for names containing the specified substring.
- **Use Case**: More intuitive alternative to `LIKE` without needing `%` wildcards.

- #### `findByFirstnameStartingWith`
- **Method**: `findByFirstnameStartingWith(String prefix)`
- **SQL Equivalent**: `SELECT ... WHERE firstname LIKE ?1%`
- **Purpose**: Finds records where `firstname` starts with the given prefix.
- **Use Case**: Use for prefix-based searching, such as finding names starting with "A".

- #### `findByLastnameEndingWith`
- **Method**: `findByLastnameEndingWith(String suffix)`
- **SQL Equivalent**: `SELECT ... WHERE lastname LIKE %?1`
- **Purpose**: Finds records where `lastname` ends with the given suffix.
- **Use Case**: Use for suffix-based searching, such as finding names ending in "son".



---

### Naming Conventions and Considerations

- **Entity Fields**: Method names reflect the entity fields exactly. For example, `findByAgeLessThan` relies on a field named `age`.
- **Parameter Naming**: Method parameter names (`X xx`) are case-insensitive and can start with an uppercase letter, though they do not impact query generation.
