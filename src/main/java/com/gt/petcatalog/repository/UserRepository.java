package com.gt.petcatalog.repository;

import com.gt.petcatalog.tables.pojos.Users;
import com.gt.petcatalog.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.gt.petcatalog.Tables.USERS;

@Repository
public class UserRepository {

    private final DSLContext dbContext;

    public UserRepository(final DSLContext dbContext) {
        this.dbContext = dbContext;
    }

    public List<Users> findAll() {
        return dbContext.
                select().
                from(USERS).
                fetchInto(Users.class);
    }


    public Users findByUuid(String uuid) {
        return dbContext.
                select().
                from(USERS).
                where(USERS.EXTERNAL_ID.eq(UUID.fromString(uuid))).
                limit(1).
                fetchOneInto(Users.class);
    }

    public Users persist(Users user) {
        UsersRecord userRecord = new UsersRecord(user);

        if (user.getExternalId() == null) {
            userRecord.setExternalId(UUID.randomUUID());
            userRecord.setCreatedAt(LocalDateTime.now());
        }

        userRecord.setLastUpdate(LocalDateTime.now());

        int execution = dbContext.
                insertInto(USERS).
                set(userRecord).
                onDuplicateKeyUpdate().
                set(userRecord).execute();

        if (execution == 1) {
            return user;
        }

        return null;
    }

    public boolean delete(String uuid) {
        int execution = dbContext.
                deleteFrom(USERS).
                where(USERS.EXTERNAL_ID.eq(UUID.fromString(uuid))).
                execute();

        return execution == 1;
    }
}
