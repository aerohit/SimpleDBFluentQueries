This is still in development and hence there are features to be added.

## A fluent Java API for spitting Amazon SimpleDb search queries

### A simple search query

    SDbFluentQuery query = new SDbFluentQuery();

    query.selectAll().from("DomainName").build();

Would spit,
    
    "select * from DomainName"

For an explicit list of attributes,

    query.select("attribute1", "attribute2").from("DomainName").build();

Would spit,

    "select attribute1, attribute2 from DomainName"

For counting results,
    
    query.count().from("DomainName").build();

Would spit,
    
    "select count(*) from DomainName"
