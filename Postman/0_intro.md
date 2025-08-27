
# Postman Notes

---

## 1. Repository & Forking

* Similar to GitHub, Postman allows **forking collections** to work independently.
* Forks can later be merged back (depending on permissions) using **pull requests or merge requests**.

---

## 2. Variables & Secrets

* Secrets and configuration can be stored in **collection variables**:

  ```yaml
  key1: value1
  ```
* Access variables using `{{key}}`, e.g. `{{baseUrl}}`.
* Example of setting a variable dynamically:

  ```javascript
  pm.collectionVariables.set('orderId', response.id);
  ```

---

## 3. Pre-request Scripts

* Pre-request scripts can set variables using Postman’s dynamic variables:

  ```javascript
  pm.collectionVariables.set('customerName', pm.variables.replaceIn('{{$randomFullName}}'));
  ```

---

## 4. Tests in Postman

* Tests are referred to as **post-request scripts**.
* **Only JavaScript** is supported.
* Each test case returns **one pass or fail**.

### Basic Test Structure

```javascript
pm.test("Example", function () {
    pm.expect(1).to.eql(1); // Always passes
});
```

### Checking Status Codes

```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
    pm.expect(pm.response.code).to.eql(200);
});
// "Status code is 200" shows in console if successful
```

* Errors lead to test failure.

### Accessing Response

```javascript
let response = pm.response.json();
```

### Validations

```javascript
pm.expect(response.isAvailable).to.eql(true);
pm.expect(response.isAvailable).to.be.false;
pm.expect(response.price).to.be.a('string');
pm.expect(response.price).to.be.a('number');
pm.expect(response.price).to.be.greaterThan(0);
```

* When comparing numeric strings, use:

  ```javascript
  parseInt(response.value);
  ```

---

## 5. Dynamic Variables

* Random variables like `{{$randomFullName}}` do **not work directly** in assertions:

  ```javascript
  pm.expect(response.customerName).to.eql('{{$randomFullName}}'); // ❌ doesn’t work
  ```
* Correct approach:

  ```javascript
  pm.expect(response.customerName).to.eql(pm.variables.replaceIn('{{$randomFullName}}'));
  ```

---

## 6. Regular Expressions

* Example using regex:

  ```javascript
  pm.expect(response.id).to.match(/^[A-Z0-9]{9}$/);
  ```

---

## 7. Schema Validation

* Example schema test:

  ```javascript
  pm.test('Schema is valid', function () {
      const schema = {
          type: 'object',
          properties: {
              id: { type: 'string', pattern: '^[A-Z0-9]{9}$' },
              clientId: { type: 'string' },
              customerName: { type: 'string' }
          },
          required: ['id', 'clientId', 'customerName'],
          additionalProperties: false
      };
      // Schema validation logic can be applied here
  });
  ```
* `required` ensures specific fields must exist.
* `additionalProperties: false` blocks unexpected fields.
* These rules also apply to **nested objects**.

---

## 8. Mock Servers

* Mock servers simulate real APIs.
  Steps:

  1. Save an example response.
  2. Create a mock collection.
  3. Assign a mock server name.
* Switch between **mock** and **real APIs** by changing environments.

---

## 9. Headers

* Access headers:

  ```javascript
  pm.response.headers.get('abc');
  ```

---

## 10. Automation & Runners

* Collections can be tested in sequence using the **Collection Runner**.
* By default, response bodies are hidden but can be enabled.
* **Scheduled runs** are available on Postman Cloud.

---

## 11. Postman CLI

* Check version:

  ```bash
  postman -v
  ```
* Requires login:

  ```bash
  postman login
  ```
* Supports automated runs (similar to Collection Runner).
* Integration with **CI/CD tools** is supported:

  * “Run on CLI” option generates commands for pipelines.
  * Configure `POSTMAN_API_KEY` for authentication.

---

## 12. Workspaces & Collaboration

* Workspaces allow **team collaboration**.
* Changes require a description explaining **why** they were made.
* Users can **fork collections** and later propose merges (via pull/merge requests).

---