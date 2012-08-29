This is still in development and hence there are features to be added.

## A fluent Java API for spitting Amazon SimpleDB search queries

#### Creating a query object

    SimpleDBFluentQuery query = new SimpleDBFluentQuery();

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
