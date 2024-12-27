### Auditing in Spring

#### 1. **Purpose of Auditing**
Auditing helps track who created or modified an entity, ensuring transparency in data changes. This can be done using annotations like `@CreatedBy`, `@LastModifiedBy`, `@CreatedDate`, and `@LastModifiedDate`.

#### 2. **Auditor Configuration**
To set up auditing, we implement `AuditorAware` and configure the current user who performs the operations.

```java
public class AuditorConfig implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(1); // Can be dynamically fetched from security context
    }
}
```

#### 3. **Entity Class with Auditing Annotations**
In the entity class, annotate fields with auditing annotations to track creation and modification details. Also, use `@EntityListeners` to enable auditing.

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @CreatedBy
    @Column(updatable = false)
    private Integer createdBy; // Tracks who created the entity

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate; // Tracks creation date

    @LastModifiedBy
    @Column(insertable = false)
    private Integer updatedBy; // Tracks who last updated the entity

    @LastModifiedDate
    @Column(insertable = false)
    private Date updatedDate; // Tracks last update date
}
```

#### 4. **Key Annotations**
- `@CreatedBy`: Stores the ID of the user who created the entity.
- `@CreatedDate`: Stores the timestamp of when the entity was created.
- `@LastModifiedBy`: Stores the ID of the user who last modified the entity.
- `@LastModifiedDate`: Stores the timestamp of the last modification.

#### 5. **Column Behavior**
- `@Column(updatable = false)`: Ensures the column is not updated after the initial insert (e.g., `createdBy` and `createdDate`).
- `@Column(insertable = false)`: Prevents the column from being inserted or updated on creation (e.g., `updatedBy` and `updatedDate`).

#### 6. **AuditingEntityListener**
The `AuditingEntityListener` class is required to enable auditing in the entity. It listens for changes and automatically populates the audit fields when an entity is persisted or updated.

#### 7. **Auditor Profile**
The `AuditorConfig` is set up with a static value (like `1` in this example), but in a real-world scenario, this value would be dynamically fetched from the security context (e.g., the logged-in user's ID) to track who made changes.

#### Summary
Auditing in Spring helps track the creation and modification details of entities. By using `@CreatedBy`, `@CreatedDate`, `@LastModifiedBy`, and `@LastModifiedDate`, along with the `AuditorAware` configuration, we can maintain a transparent log of changes in the system.