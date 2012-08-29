## A fluent api for spitting SimpleDb search queries

`SDbFluentQuery query = new SDbFluentQuery();
`query.select("attribute1", "attribute2").from("MyDatabase");
`query.build();

Would spit

`select attribute1, attribute2 from MyDatabase
