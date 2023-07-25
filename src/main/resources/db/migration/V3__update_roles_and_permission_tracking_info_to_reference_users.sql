ALTER TABLE roles
    ADD CONSTRAINT roles_created_by_fkey FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE roles
    ADD CONSTRAINT roles_last_updated_by_fkey FOREIGN KEY (last_updated_by) REFERENCES users (id);

ALTER TABLE roles
    ADD CONSTRAINT roles_deleted_by_fkey FOREIGN KEY (deleted_by) REFERENCES users (id);


ALTER TABLE permissions
    ADD CONSTRAINT permissions_created_by_fkey FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE permissions
    ADD CONSTRAINT permissions_last_updated_by_fkey FOREIGN KEY (last_updated_by) REFERENCES users (id);

ALTER TABLE permissions
    ADD CONSTRAINT permissions_deleted_by_fkey FOREIGN KEY (deleted_by) REFERENCES users (id);