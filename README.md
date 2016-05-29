# loginmodule-jsp-servlet

This project shows implementation of login module using jsp,servlet and MySQL.

## Getting started 
1. Import databse in MySQL located under db folder.
2. Import project in eclipse.
3. Update database properties in DBproperties.java

You can login with following credentials.
```
username: admin@admin.com
password: admin
```
We've stored salt & hash(salt+password) in the table. Please check [SecurityUtility.java](https://github.com/kapateldhruv/loginmodule-jsp-servlet/blob/master/src/kapatel/dhruv/utils/SecurityUtility.java) for more information.

## Scripts Used
* [Bootstrap] (http://getbootstrap.com)  
* [Validation Engine] (https://jqueryvalidation.org)
