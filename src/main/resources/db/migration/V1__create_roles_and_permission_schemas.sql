CREATE TABLE roles
(

    id              UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    name            VARCHAR UNIQUE NOT NULL,
    description     VARCHAR,

    created_by      UUID,
    created_on      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_by UUID,
    last_updated_on TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_by      UUID,
    deleted_on      TIMESTAMP


);

CREATE TABLE permissions
(

    id              UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    name            VARCHAR UNIQUE NOT NULL,
    description     VARCHAR,

    created_by      UUID,
    created_on      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_by UUID,
    last_updated_on TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_by      UUID,
    deleted_on      TIMESTAMP

);

CREATE TABLE roles_permissions
(

    id            UUID DEFAULT uuid_generate_v4(),
    role_id       UUID NOT NULL REFERENCES roles (id),
    permission_id UUID NOT NULL REFERENCES permissions (id),

    PRIMARY KEY (role_id, permission_id)

);