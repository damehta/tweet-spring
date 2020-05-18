db.createUser(
        {
            user: "user",
            pwd: "pwduser",
            roles: [
                {
                    role: "readWrite",
                    db: "test123"
                }
            ]
        }
);