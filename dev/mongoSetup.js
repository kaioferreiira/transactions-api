db.createUser(
        {
            user: "test",
            pwd: "123",
            roles: [
                {
                    role: "readWrite",
                    db: "transaction-api"
                }
            ]
        }
);

db.createCollection('test');