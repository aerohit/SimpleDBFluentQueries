This is still in development and hence there are features to be added.

## A fluent Java API for spitting Amazon SimpleDB search queries

#### Creating a query object

    SDbFluentQuery query = new SDbFluentQuery();

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
