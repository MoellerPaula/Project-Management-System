CREATE TABLE app_user (
                          id UUID PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          email VARCHAR(254) NOT NULL UNIQUE,
                          password_hash VARCHAR(255) NOT NULL,
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL,
                          created_at TIMESTAMP NOT NULL
);

CREATE TABLE project (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         created_by UUID NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         completed_at TIMESTAMP,
                         start_date DATE,
                         due_date DATE,

                         CONSTRAINT chk_project_dates
                             CHECK (
                                 start_date IS NULL
                                 OR due_date IS NULL
                                 OR start_date <= due_date
                                 ),

                         status VARCHAR(20) NOT NULL,

                         CONSTRAINT fk_project_created_by
                             FOREIGN KEY (created_by)
                                 REFERENCES app_user(id)
);

CREATE TABLE project_member (
                                project_id UUID NOT NULL,
                                user_id UUID NOT NULL,
                                role VARCHAR(20) NOT NULL,
                                joined_at TIMESTAMP NOT NULL,

                                PRIMARY KEY (project_id, user_id),

                                CONSTRAINT fk_project_member_project
                                    FOREIGN KEY (project_id)
                                        REFERENCES project(id),

                                CONSTRAINT fk_project_member_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES app_user(id)
);

CREATE TABLE task (
                      id UUID PRIMARY KEY,
                      project_id UUID NOT NULL,
                      assigned_to UUID,
                      created_by UUID NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      created_at TIMESTAMP NOT NULL,
                      due_date DATE,
                      completed_at TIMESTAMP,
                      priority VARCHAR(20),
                      position INTEGER,

                      CONSTRAINT chk_task_position
                          CHECK(position IS NULL OR position >= 0),

                      status VARCHAR(20) NOT NULL,

                      CONSTRAINT fk_task_project
                          FOREIGN KEY (project_id)
                              REFERENCES project(id),

                      CONSTRAINT fk_task_assigned_to
                          FOREIGN KEY (assigned_to)
                              REFERENCES app_user(id),

                      CONSTRAINT fk_task_created_by
                          FOREIGN KEY (created_by)
                              REFERENCES app_user(id)
);