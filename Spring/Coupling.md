# Coupling in Software Design

**Coupling** refers to the degree of dependency between components or systems.

- **Tight Coupling**: Software components are highly dependent on each other.
- **Loose Coupling**: Components are less dependent, allowing for greater flexibility.
- **Reduce Dependency**

## Importance in Software Design
- **Flexibility & Maintainability**
- **Scalability**
- **Testing**

## Achieving Loose Coupling
- **Interface & Abstraction**
- **Dependency Injection**
- **Event-Driven Architecture**

## Examples

### Tight Coupling
```java
// Tight Coupling Example

package com.tight.coupling;

public class UserDataBase {
    public String getUserDetails() {
        return "User Details from Database";
    }
}

package com.tight.coupling;

public class UserManager {
    private UserDataBase userDataBase;

    public UserManager(UserDataBase userDataBase) {
        this.userDataBase = userDataBase;
    }

    public String getUserInfo() {
        return userDataBase.getUserDetails();
    }
}
```
Modifications in the UserDataBase class require changes in UserManager, making it hard to adapt.

### Loose Coupling
``` java
// Loose Coupling Example

package com.loose.coupling;

public interface UserDataProvider {
    String getUserDetails();
}

package com.loose.coupling;

public class UserDataBaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "User Details from Database";
    }
}

package com.loose.coupling;

public class WebServiceDataProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "Fetching Data from Web Service";
    }
}

package com.loose.coupling;

public class UserManager {
    private UserDataProvider userDataProvider;

    public UserManager(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public String getUserInfo() {
        return userDataProvider.getUserDetails();
    }
}

package com.loose.coupling;

public class LooseCouplingExample {
    public static void main(String[] args) {
        UserDataBaseProvider userDataBaseProvider = new UserDataBaseProvider();
        UserManager userManager = new UserManager(userDataBaseProvider);
        System.out.println(userManager.getUserInfo());

        UserDataProvider webServiceProvider = new WebServiceDataProvider();
        UserManager webServiceManager = new UserManager(webServiceProvider);
        System.out.println(webServiceManager.getUserInfo());
    }
}

```
Changes to UserDataProvider do not require modifications in UserManager, promoting adaptability.
Here loose coupling happened with interface