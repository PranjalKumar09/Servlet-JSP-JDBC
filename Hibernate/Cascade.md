# Cascade in Hibernate

## What is Cascade?

In Hibernate, the `cascade` attribute is used to define the behavior of related entities when certain operations (like persist, merge, remove) are performed on the parent entity. When you define a cascade on a relationship, it ensures that changes to the parent entity are automatically propagated to the associated child entities.

## Example

Here’s a typical example where cascade is applied in a `@ManyToMany` relationship between `Emp` and `Address` entities:

```java
@Entity
public class Emp {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "emp", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
```
## Cascade Effect in Hibernate

### Without Cascade Effect
In this scenario, only the `Emp` entity (e1) is persisted. The associated child entities (e.g., `Address` objects) need to be explicitly persisted. For example, you would need to call `session.persist(ad1)`, `session.persist(ad2)`, etc.

- **If child entities are not explicitly persisted:**  
  Only the parent entity (`Emp`) will be saved, and no information about the associated addresses will be persisted.

### With Cascade Effect
When you add `cascade = CascadeType.ALL` to the `@ManyToMany` or `@OneToMany` mapping, the behavior changes:

- **Automatic persistence of child entities:**  
  The child entities (e.g., `Address` objects) will also be persisted, updated, or deleted automatically when you perform those actions on the parent entity (`Emp`). You don’t need to call `persist()` on each of the child entities separately.

### Cascade Types in Hibernate
Hibernate provides several cascade types, each corresponding to a specific operation:

#### 1. `CascadeType.PERSIST`
- **Definition:** When the parent entity is persisted, all the associated child entities are also persisted.
- **Use Case:** When you want to ensure that new child entities are saved along with the parent entity.
- **Behavior:** Only saves the parent and its new associated child entities in the database.

#### 2. `CascadeType.MERGE`
- **Definition:** When the parent entity is merged (updated), all the associated child entities are also merged.
- **Use Case:** When you need to update both the parent and child entities in a single operation.
- **Behavior:** Updates the parent entity and its related entities in the database.

#### 3. `CascadeType.REMOVE`
- **Definition:** When the parent entity is removed, all the associated child entities are also removed.
- **Use Case:** When deleting the parent entity, and you want to automatically delete its associated entities.
- **Behavior:** Deletes the parent and child entities from the database.

#### 4. `CascadeType.REFRESH`
- **Definition:** When the parent entity is refreshed, all the associated child entities are also refreshed.
- **Use Case:** When you want to reload the parent and child entities from the database to ensure they are synchronized with the latest data.
- **Behavior:** Refreshes both the parent and child entities with the latest state from the database.

#### 5. `CascadeType.DETACH`
- **Definition:** When the parent entity is detached from the persistence context, all the associated child entities are also detached.
- **Use Case:** When you want to detach both parent and child entities from the session.
- **Behavior:** Detaches both the parent and child entities from the persistence context, so changes to them will not be tracked.

#### 6. `CascadeType.ALL`
- **Definition:** Combines all the above cascade types (PERSIST, MERGE, REMOVE, REFRESH, and DETACH).
- **Use Case:** When you want all operations on the parent entity to be propagated to the child entities.
- **Behavior:** Applies all the cascading behaviors for persistence, merging, removal, refreshing, and detaching of both parent and child entities.

### Default Cascade Behavior
By default, no cascading behavior is applied. If you don't specify a cascade attribute in the relationship annotation, Hibernate will not propagate operations from the parent entity to the child entities. You will need to manually manage the persistence, update, and deletion of child entities.

### Important Points to Remember
- **Choose the Right Cascade Type:** Be mindful of the cascade type you choose. For example, using `CascadeType.REMOVE` can delete associated entities unintentionally if not handled carefully.
- **Avoid Using CascadeType.ALL in Some Scenarios:** While `CascadeType.ALL` seems convenient, it’s often not the best choice for production environments where you want more control over what happens to the associated entities.
- **Cascade is Relationship-Specific:** Cascade only affects entities in a relationship, not standalone entities.
- **Session and Transaction Context:** Ensure that the session and transaction are properly handled to avoid common pitfalls like `LazyInitializationException` or unwanted cascading behavior outside of transactions.

### Summary of Cascade Types

| Cascade Type      | Description                                                       | Use Case                                                          |
|-------------------|-------------------------------------------------------------------|-------------------------------------------------------------------|
| PERSIST           | Automatically persists child entities when parent is persisted.   | Use when you want new child entities saved along with the parent. |
| MERGE             | Automatically merges child entities when parent is merged.       | Use when you want to update both parent and child entities in a single operation. |
| REMOVE            | Automatically removes child entities when parent is removed.     | Use when you want to delete both parent and child entities together. |
| REFRESH           | Automatically refreshes child entities when parent is refreshed. | Use when you need to reload the parent and child entities from the database. |
| DETACH            | Automatically detaches child entities when parent is detached.   | Use when you want to detach both parent and child entities from the session. |
| ALL               | Applies all of the above cascade types (PERSIST, MERGE, REMOVE, etc.). | Use when you want all operations on the parent entity to be cascaded to the child entities. |
