CREATE TABLE users
(
    id              UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    firstname       VARCHAR        NOT NULL,
    lastname        VARCHAR        NOT NULL,
    email           VARCHAR UNIQUE NOT NULL,
    username        VARCHAR UNIQUE NOT NULL,
    password        VARCHAR        NOT NULL,
    role_id         UUID REFERENCES roles (id),
    profile_id      UUID,
    userType        INTEGER        NOT NULL,

    created_by      UUID,
    created_on      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_by UUID,
    last_updated_on TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_by      UUID,
    deleted_on      TIMESTAMP
);

CREATE TABLE user_profiles
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date_of_birth DATE NOT NULL,
    city          VARCHAR,
    country       VARCHAR
);

ALTER TABLE users
    ADD CONSTRAINT users_profile_id_fkey FOREIGN KEY (profile_id) REFERENCES user_profiles (id);

ALTER TABLE users
    ADD CONSTRAINT users_created_by_fkey FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT users_last_updated_by_fkey FOREIGN KEY (last_updated_by) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT users_deleted_by_fkey FOREIGN KEY (deleted_by) REFERENCES users (id);
