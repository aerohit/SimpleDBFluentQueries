## A fluent Java API for spitting Amazon SimpleDB search queries

### Motivation

[Amazon SimpleDB Queries](http://docs.amazonwebservices.com/AmazonSimpleDB/latest/DeveloperGuide/UsingSelect.html)
has a very simple syntax for querying on data stored in their DB. I was searching 
for a query builder in Java but couldn't find one. Hence I decided to write my own.

*Note:* The library is inspired from [Hibernate's criteria api](http://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/querycriteria.html).
But the work is in progress and the following api is expected to change, including 
changes for more *type safety*. 

**Caveats:**

The library is under development, desired features may be missing but I am working 
on it. I am trying to keep the documentation up to date, but discrepancies might creep
in, unit tests tell the truth in such cases.



#### Creating a query object

    SimpleDBQuery query = new SimpleDBQuery();

#### Selecting all attributes

    query.selectAll().from("DomainName").build();

Would spit,
    
    "select * from DomainName"

#### An explicit list of attributes

    query.select("attribute1", "attribute2").from("DomainName").build();

Would spit,

    "select attribute1, attribute2 from DomainName"

#### Counting results
    
    query.count().from("DomainName").build();

Would spit,
    
    "select count(*) from DomainName"

#### Ordering results
    
    query.selectAll().from("DomainName").orderBy("attribute1").build();
    query.selectAll().from("DomainName").orderByAscending("attribute1").build();
    query.selectAll().from("DomainName").orderByDescending("attribute1").build();

Would spit,
    
    "select * from DomainName order by attribute1"
    "select * from DomainName order by attribute1 asc"
    "select * from DomainName order by attribute1 desc"

respectively.

#### Limiting results

    query.selectAll().from("DomainName").limit(5).build();

Would spit,
    
    "select * from DomainName limit 5"
